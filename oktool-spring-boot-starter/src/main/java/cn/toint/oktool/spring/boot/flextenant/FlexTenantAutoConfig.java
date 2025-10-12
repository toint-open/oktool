package cn.toint.oktool.spring.boot.flextenant;

import cn.dev33.satoken.stp.StpUtil;
import cn.toint.oktool.spring.boot.traceid.TraceIdInterceptor;
import com.mybatisflex.core.tenant.TenantFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mybatis-flex租户自动配置
 *
 * @author Toint
 * @dete 2025/10/12
 */
@AutoConfiguration
@ConditionalOnClass({
        StpUtil.class,
        TenantFactory.class,
        DispatcherServlet.class,
        HandlerInterceptor.class
})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // 仅在Servlet环境启用
@Slf4j
public class FlexTenantAutoConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdInterceptor())
                .addPathPatterns("/**");           // 明确拦截路径
        log.info("任务ID拦截器初始化成功");
    }

    @Bean
    public TenantFactory tenantFactory() {
        return new FlexTenantFactory();
    }
}
