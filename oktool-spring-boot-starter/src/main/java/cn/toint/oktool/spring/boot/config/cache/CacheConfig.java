package cn.toint.oktool.spring.boot.config.cache;

import cn.toint.oktool.spring.boot.cache.Cache;
import cn.toint.oktool.spring.boot.cache.impl.LocalCacheImpl;
import cn.toint.oktool.spring.boot.cache.impl.RedisCacheImpl;
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
@EnableConfigurationProperties(CacheProperties.class)
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
