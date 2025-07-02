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

package cn.toint.oktool.config;

import cn.toint.oktool.model.CacheType;
import cn.toint.oktool.properties.OkToolProperties;
import cn.toint.oktool.service.CacheService;
import cn.toint.oktool.service.impl.LocalCacheServiceImpl;
import cn.toint.oktool.service.impl.RedisCacheServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;

/**
 * @author Toint
 * @date 2025/7/2
 */
@AutoConfiguration
@ConfigurationPropertiesScan({"cn.toint.oktool.properties"})
//@ComponentScan({"cn.toint.oktool.service"})
@Slf4j
public class OkToolAutoConfig {

    @Resource
    private OkToolProperties okToolProperties;

    @Bean
    @ConditionalOnMissingBean
    public CacheService cacheService() {
        CacheType cacheType = okToolProperties.getCacheType();
        CacheService cacheService;
        if (cacheType == null || cacheType == CacheType.AUTO) {
            // 优先使用redis
            boolean hasRedis = ClassUtils.isPresent("org.springframework.data.redis.core.StringRedisTemplate", null);
            if (hasRedis) {
                cacheService = new RedisCacheServiceImpl();
            } else {
                cacheService = new LocalCacheServiceImpl();
            }
        } else if (cacheType == CacheType.REDIS) {
            // 不判断, 如果redis不存在springboot会自动报错
            cacheService = new RedisCacheServiceImpl();
        } else {
            cacheService = new LocalCacheServiceImpl();
        }

        log.info("缓存服务初始化成功, 实现类: {}", cacheService.getClass().getName());
        return cacheService;
    }
}
