package cn.toint.oktool.spring.boot.flextenant;

import cn.toint.oktool.spring.boot.constant.OrderConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Toint
 * @since 2025/11/10
 */
public class FlexTenantWebMvcConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(FlexTenantWebMvcConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        int order = OrderConstant.FLEX_TENANT_INTERCEPTOR_ORDER;
        registry.addInterceptor(new FlexTenantInterceptor())
                .addPathPatterns("/**")
                // 在SaTokenInterceptor之后
                .order(order);
        log.info("FlexTenantInterceptor-租户拦截器已开启. path: /**, order: {}", order);
    }
}
