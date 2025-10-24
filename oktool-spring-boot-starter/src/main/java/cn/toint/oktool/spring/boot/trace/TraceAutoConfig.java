package cn.toint.oktool.spring.boot.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求任务编号自动配置
 *
 * @author Toint
 * @since 2025/10/12
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // 仅在Servlet环境启用
public class TraceAutoConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(TraceAutoConfig.class);

    @Bean
    @ConditionalOnMissingBean
    public TraceInterceptor traceInterceptor() {
        return new TraceInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public TraceWebMvcConfig traceWebMvcConfig() {
        return new TraceWebMvcConfig();
    }
}
