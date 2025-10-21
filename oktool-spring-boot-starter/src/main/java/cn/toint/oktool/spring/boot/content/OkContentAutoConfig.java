package cn.toint.oktool.spring.boot.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * 上下文自动配置
 *
 * @author Toint
 * @since 2025/10/21
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // 仅在Servlet环境启用
public class OkContentAutoConfig {

    private static final Logger log = LoggerFactory.getLogger(OkContentAutoConfig.class);

    /**
     * 注册上下文过滤器
     */
    @Bean
    public FilterRegistrationBean<OkContentFilter> flexTenantFilter() {
        FilterRegistrationBean<OkContentFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new OkContentFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        log.info("OkContentFilter-上下文过滤器已注册. path: {}, order: {}", "/*", Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
