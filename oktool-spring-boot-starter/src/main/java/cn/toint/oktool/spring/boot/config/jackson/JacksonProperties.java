package cn.toint.oktool.spring.boot.config.jackson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Toint
 * @date 2025/7/4
 */
@ConfigurationProperties(prefix = "oktool.jackson")
@Data
public class JacksonProperties {
    /**
     * jackson LocalDateTime日期模块
     */
    private LocalDateTimeModule localDateTimeModule = new LocalDateTimeModule();

    /**
     * jackson 安全Long模块
     */
    private SafeLongModule safeLongModule = new SafeLongModule();

    /**
     * jackson LocalDateTime日期模块
     */
    @Data
    public static class LocalDateTimeModule {
        private boolean enabled = true;
        private String zoneId = "Asia/Shanghai";
        private String pattern =  "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * jackson 安全Long模块
     */
    @Data
    public static class SafeLongModule {
        private boolean enabled = true;
    }
}
