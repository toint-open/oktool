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

package cn.toint.oktool.spring.boot.cache;

import cn.toint.oktool.spring.boot.cache.impl.LocalCacheImpl;
import cn.toint.oktool.spring.boot.cache.impl.RedisCacheImpl;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;

/**
 * 缓存自动配置
 *
 * @author Toint
 * @since 2025/7/4
 */
@AutoConfiguration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAutoConfig {

    private static final Logger log = LoggerFactory.getLogger(CacheAutoConfig.class);

    @Resource
    private CacheProperties cacheProperties;

    @Bean
    @ConditionalOnMissingBean
    public Cache cacheService() {
        CacheProperties.Type cacheType = cacheProperties.getType();
        if (cacheType == null) cacheType = CacheProperties.Type.AUTO;

        Cache cache;
        if (cacheType == CacheProperties.Type.AUTO &&
                ClassUtils.isPresent("org.springframework.data.redis.core.StringRedisTemplate", null)) {
            // auto: 如果有redis依赖, 优先使用redis, 否则使用本地
            cache = new RedisCacheImpl();
        } else if (cacheType == CacheProperties.Type.REDIS) {
            // redis: 这里不检查依赖, 如果没有redis依赖, 让框架自己报错
            cache = new RedisCacheImpl();
        } else {
            // 其他情况, 使用本地缓存
            cache = new LocalCacheImpl();
        }

        log.info("Cache缓存服务初始化成功, 实现类: {}", cache.getClass().getName());
        return cache;
    }
}
