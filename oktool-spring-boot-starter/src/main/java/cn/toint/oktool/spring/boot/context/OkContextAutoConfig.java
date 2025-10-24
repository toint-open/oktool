package cn.toint.oktool.spring.boot.context;

import cn.toint.oktool.spring.boot.constant.OrderConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 上下文自动配置
 *
 * @author Toint
 * @since 2025/10/21
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // 仅在Servlet环境启用
public class OkContextAutoConfig {

    private static final Logger log = LoggerFactory.getLogger(OkContextAutoConfig.class);

    /**
     * 注册上下文过滤器
     */
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<OkContextFilter> okContextFilter() {
        int order = OrderConstant.OK_CONTEXT_FILTER_ORDER;
        FilterRegistrationBean<OkContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new OkContextFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(order);
        log.info("OkContextFilter-上下文过滤器已开启. path: {}, order: {}", "/*", order);
        return registration;
    }
}
