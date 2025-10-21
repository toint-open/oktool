package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.stp.StpUtil;
import cn.toint.oktool.spring.boot.constant.OrderConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SaToken自动配置
 *
 * @author Toint
 * @since 2025/10/21
 */
@AutoConfiguration
@ConditionalOnClass({
        StpUtil.class // satoken环境
})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // 仅在Servlet环境启用
public class SaTokenAutoConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SaTokenAutoConfig.class);

    /**
     * sa-token拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SaTokenInterceptor saTokenInterceptor = new SaTokenInterceptor();
        saTokenInterceptor.setAuth(_ -> StpUtil.checkLogin());
        registry.addInterceptor(saTokenInterceptor)
                .addPathPatterns("/**")
                .order(OrderConstant.SA_TOKEN_INTERCEPTOR_ORDER);
        log.info("SaTokenInterceptor-认证拦截器已开启. path: {}, order: {}", "/**", OrderConstant.DEFAULT_ORDER);
    }

    @Bean
    public SaTokenExceptionHandler saTokenExceptionHandler() {
        log.info("SaTokenExceptionHandler-SaToken异常处理器已开启");
        return new SaTokenExceptionHandler();
    }
}
