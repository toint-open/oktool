package cn.toint.oktool.spring.boot.config.jackson;

import cn.toint.oktool.util.JacksonUtil;
import com.fasterxml.jackson.databind.Module;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;

/**
 * @author Toint
 * @date 2025/7/4
 */
@Configuration
@EnableConfigurationProperties(JacksonProperties.class)
@Slf4j
public class JacksonModelConfig {

    @Resource
    private JacksonProperties  jacksonProperties;

    /**
     * jackson LocalDateTime日期模块
     */
    @Bean
    @ConditionalOnProperty(name = "oktool.jackson.local-date-time-module.enabled", havingValue = "true", matchIfMissing = true)
    public com.fasterxml.jackson.databind.Module jacksonLocalDateTimeModule() {
        JacksonProperties.LocalDateTimeModule localDateTimeModule = jacksonProperties.getLocalDateTimeModule();
        ZoneId zoneId = ZoneId.of(localDateTimeModule.getZoneId());
        String pattern = localDateTimeModule.getPattern();
        com.fasterxml.jackson.databind.Module timeModule = JacksonUtil.createLocalDateTimeModule(pattern, zoneId);
        log.info("Jackson LocalDateTimeModule初始化成功: zoneId={}, pattern={}", zoneId, pattern);
        return timeModule;
    }

    /**
     * jackson 安全Long模块
     */
    @Bean
    @ConditionalOnProperty(name = "oktool.jackson.safe-long-module.enabled", havingValue = "true", matchIfMissing = true)
    public com.fasterxml.jackson.databind.Module jacksonSafeLongModule() {
        final Module longModule = JacksonUtil.createSafeLongModule();
        log.info("Jackson SafeLongModule初始化成功");
        return longModule;
    }
}
