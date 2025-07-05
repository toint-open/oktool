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

package cn.toint.oktool.spring.boot.config;

import cn.toint.oktool.spring.boot.cache.Cache;
import cn.toint.oktool.spring.boot.cache.impl.LocalCacheImpl;
import cn.toint.oktool.spring.boot.cache.impl.RedisCacheImpl;
import cn.toint.oktool.spring.boot.properties.CacheProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * @author Toint
 * @date 2025/7/4
 */
@Configuration
@Slf4j
public class CacheConfig {

    @Resource
    private CacheProperties cacheProperties;

    @Bean
    @ConditionalOnMissingBean
    public Cache cacheService() {
        CacheProperties.Type cacheType = cacheProperties.getType();
        Cache cache;
        if (cacheType == null || cacheType == CacheProperties.Type.AUTO) {
            // 优先使用redis
            boolean hasRedis = ClassUtils.isPresent("org.springframework.data.redis.core.StringRedisTemplate", null);
            if (hasRedis) {
                cache = new RedisCacheImpl();
            } else {
                cache = new LocalCacheImpl();
            }
        } else if (cacheType == CacheProperties.Type.REDIS) {
            cache = new RedisCacheImpl();
        } else {
            cache = new LocalCacheImpl();
        }

        log.info("缓存服务初始化成功: 实现类={}", cache.getClass().getName());
        return cache;
    }
}
