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

package cn.toint.oktool.util;

import jakarta.annotation.Nonnull;
import org.dromara.hutool.extra.spring.SpringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Redis 分布式锁工具
 *
 * @author Toint
 * @date 2025/6/13
 */
public class RedisLockUtil {
    /**
     * 上锁
     *
     * @param key     key
     * @param timeout 上锁时长(ms)
     * @return true: 获取到锁(获取到执行权); false: 未能获取到锁(未获取到执行权)
     */
    public static boolean lock(@Nonnull String key, long timeout) {
        return RedisLockUtil.lock(key, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 上锁
     *
     * @param key     key
     * @param timeout 上锁时长
     * @param unit    上锁时长单位
     * @return true: 获取到锁(获取到执行权); false: 未能获取到锁(未获取到执行权)
     */
    public static boolean lock(@Nonnull String key, long timeout, @Nonnull TimeUnit unit) {
        Assert.notBlank(key, "key must not be blank");
        Assert.notNull(unit, "unit must not be null");
        Assert.isTrue(timeout > 0, "timeout 必须大于 0");

        Boolean setIfAbsent = SpringUtil.getBean(StringRedisTemplate.class)
                .opsForValue()
                .setIfAbsent(key, "", timeout, unit);

        return Boolean.TRUE.equals(setIfAbsent);
    }

    /**
     * 释放锁
     *
     * @param key 锁键
     */
    public static void unlock(@Nonnull String key) {
        Assert.notBlank(key, "key must not be blank");
        SpringUtil.getBean(StringRedisTemplate.class).delete(key);
    }
}
