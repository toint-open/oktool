package cn.toint.oktool.spring.boot.flextenant;

import cn.dev33.satoken.stp.StpUtil;
import cn.toint.oktool.spring.boot.constant.OrderConstant;
import com.mybatisflex.core.tenant.TenantFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mybatis-flex租户自动配置
 *
 * @author Toint
 * @since 2025/10/12
 */
@AutoConfiguration
@ConditionalOnClass({
        StpUtil.class, // satoken环境
        FlexTenantFactory.class // mybatis-flex环境
})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // 仅在Servlet环境启用
public class FlexTenantAutoConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(FlexTenantAutoConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        int order = OrderConstant.FLEX_TENANT_INTERCEPTOR_ORDER;
        registry.addInterceptor(new FlexTenantInterceptor())
                .addPathPatterns("/**")
                // 在SaTokenInterceptor之后
                .order(order);
        log.info("FlexTenantInterceptor-租户拦截器已开启. path: /**, order: {}", order);
    }

    @Bean
    public TenantFactory tenantFactory() {
        log.info("TenantFactory-多租户功能已开启");
        return new FlexTenantFactory();
    }
}
