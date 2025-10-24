package cn.toint.oktool.spring.boot.satoken;

import cn.toint.oktool.spring.boot.constant.OrderConstant;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Toint
 * @since 2025/10/25
 */
public class SaTokenWebMvcConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SaTokenWebMvcConfig.class);

    @Resource
    private SaTokenInterceptor saTokenInterceptor;

    /**
     * sa-token拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        int order = OrderConstant.SA_TOKEN_INTERCEPTOR_ORDER;
        registry.addInterceptor(saTokenInterceptor)
                .addPathPatterns("/**")
                .order(order);
        log.info("SaTokenInterceptor-认证拦截器已开启. path: {}, order: {}", "/**", order);
    }
}
