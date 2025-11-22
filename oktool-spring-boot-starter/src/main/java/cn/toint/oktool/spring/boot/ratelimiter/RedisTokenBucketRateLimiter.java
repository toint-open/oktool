/*
 * Copyright 2025 Toint (599818663@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.toint.oktool.spring.boot.ratelimiter;

import cn.hutool.v7.core.thread.ratelimiter.RateLimiter;
import cn.hutool.v7.core.thread.ratelimiter.RateLimiterConfig;
import cn.toint.oktool.util.Assert;
import cn.toint.oktool.util.KeyBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.locks.LockSupport;

/**
 * 基于Redis的分布式令牌桶限流器
 *
 * @author Toint
 * @since 2025/10/25
 */
public class RedisTokenBucketRateLimiter implements RateLimiter {

    private final StringRedisTemplate redisTemplate;
    private final RateLimiterConfig config;
    private final String redisKey;
    private final DefaultRedisScript<Long> script;

    /**
     * Lua脚本：令牌桶算法
     * <p>
     * 参数说明：
     * KEYS[1] - Redis Key
     * ARGV[1] - 桶容量
     * ARGV[2] - 每秒生成速率
     * ARGV[3] - 请求的令牌数
     * ARGV[4] - 当前时间戳（毫秒）
     * ARGV[5] - 过期时间（秒）
     * <p>
     * 返回值：
     * 1 - 获取成功
     * 0 - 令牌不足
     */
    private static final String LUA_SCRIPT = """
            -- 获取参数
            local key = KEYS[1]
            local capacity = tonumber(ARGV[1])       -- 桶容量
            local rate = tonumber(ARGV[2])           -- 每秒生成多少令牌
            local requested = tonumber(ARGV[3])      -- 本次请求几个令牌
            local now = tonumber(ARGV[4])            -- 当前时间戳（毫秒）
            local ttl = tonumber(ARGV[5])            -- 动态过期时间
            
            -- 从Redis获取当前令牌数和上次更新时间
            local bucket = redis.call('hmget', key, 'tokens', 'last_time')
            local tokens = tonumber(bucket[1])
            local last_time = tonumber(bucket[2])
            
            -- 第一次访问，初始化为满桶
            if tokens == nil then
                tokens = capacity
                last_time = now
            end
            
            -- 计算从上次到现在应该补充多少令牌
            local delta_time = math.max(0, now - last_time)  -- 时间差（毫秒）
            local add_tokens = (delta_time * rate) / 1000    -- 应补充的令牌数
            tokens = math.min(capacity, tokens + add_tokens) -- 不超过容量上限
            
            -- 尝试获取令牌
            local ok = 0
            if tokens >= requested then
                tokens = tokens - requested  -- 扣减令牌
                ok = 1                       -- 获取成功
            end
            
            -- 保存到Redis（使用Hash结构）
            redis.call('hmset', key, 'tokens', tokens, 'last_time', now)
            redis.call('expire', key, ttl)  -- 设置过期时间，避免长期占用内存
            
            return ok
            """;


    /**
     * 构造函数
     *
     * @param redisTemplate Redis操作模板
     * @param config        Hutool配置对象
     * @param name          限流器名称（用于区分不同限流器）
     */
    public RedisTokenBucketRateLimiter(StringRedisTemplate redisTemplate,
                                       RateLimiterConfig config,
                                       String name) {
        Assert.notNull(redisTemplate, "redisTemplate");
        Assert.notNull(config, "config");
        Assert.notBlankParam(name, "name");
        Assert.isTrue(config.getCapacity() > 0, "桶容量必须大于0");
        Assert.isTrue(config.getMaxReleaseCount() > 0, "令牌生成速率必须大于0");
        Assert.isTrue(config.getRefreshPeriod().isPositive(), "刷新周期必须大于0");

        this.redisTemplate = redisTemplate;
        this.config = config;
        this.redisKey = KeyBuilder.of("rate-limiter").add(name).build();
        this.script = new DefaultRedisScript<>(LUA_SCRIPT, Long.class);
    }

    @Override
    public boolean tryAcquire(int permits) {
        // 参数校验
        checkPermits(permits);

        // 计算每秒生成多少令牌
        long periodMs = config.getRefreshPeriod().toMillis();
        double tokensPerSecond = (1000.0 / periodMs) * config.getMaxReleaseCount();

        // 计算过期时间（周期的3倍，最少60秒）
        long ttl = Math.max(60, (periodMs / 1000) * 3);

        // 执行Lua脚本
        Long result = redisTemplate.execute(
                script,
                Collections.singletonList(redisKey),
                // 桶容量
                String.valueOf(config.getCapacity()),
                // 每秒生成速率
                String.valueOf(tokensPerSecond),
                // 请求令牌数
                String.valueOf(permits),
                // 当前时间
                String.valueOf(System.currentTimeMillis()),
                // 动态过期时间
                String.valueOf(ttl)
        );

        return result == 1L;
    }

    @Override
    public void acquire(int permits) {
        // 参数校验
        checkPermits(permits);

        // 计算等待间隔：10-100ms
        long periodMs = config.getRefreshPeriod().toMillis();
        long waitMs = Math.max(10, Math.min(100,
                periodMs * permits / config.getMaxReleaseCount()));
        long waitNanos = waitMs * 1_000_000L;

        // 阻塞等待
        while (!tryAcquire(permits)) {
            LockSupport.parkNanos(waitNanos);
            if (Thread.interrupted()) {
                throw new RuntimeException("线程中断");
            }
        }
    }

    /**
     * 校验请求令牌数
     *
     * @param permits 请求令牌数
     */
    private void checkPermits(int permits) {
        Assert.isTrue(permits > 0, "permits 必须大于 0，当前值：" + permits);
        Assert.isTrue(permits <= config.getCapacity(), "请求令牌数[{}]不能超过桶容量[{}]", permits, config.getCapacity());
    }
}
