package cn.toint.oktool.spring.boot.config.jackson;

import cn.toint.oktool.util.JacksonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatisflex.core.handler.JacksonTypeHandler;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * @author Toint
 * @date 2025/7/4
 */
@Configuration
@Slf4j
public class JacksonObjectMapperConfig {

    @Resource
    private ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        JacksonUtil.setObjectMapper(objectMapper);
        log.info("JacksonUtil已使用Spring容器中的ObjectMapper实例: moduleIds={}", objectMapper.getRegisteredModuleIds());

        // 替换mybatis-flex, 如果存在
        if (ClassUtils.isPresent("com.mybatisflex.core.handler.JacksonTypeHandler", null)) {
            JacksonTypeHandler.setObjectMapper(objectMapper);
            log.info("MybatisFlex JacksonTypeHandler已使用Spring容器中的ObjectMapper实例: moduleIds={}", objectMapper.getRegisteredModuleIds());
        }
    }
}
