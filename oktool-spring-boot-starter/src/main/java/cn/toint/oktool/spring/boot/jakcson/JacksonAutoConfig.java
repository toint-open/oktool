package cn.toint.oktool.spring.boot.jakcson;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * jackson自动配置
 *
 * @author Toint
 * @dete 2025/10/12
 */
@AutoConfiguration
@EnableConfigurationProperties(JacksonProperties.class)
@Import({
        JacksonModelConfig.class,
        JacksonObjectMapperConfig.class
})
public class JacksonAutoConfig {
}
