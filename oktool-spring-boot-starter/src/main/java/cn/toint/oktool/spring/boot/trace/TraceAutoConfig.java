package cn.toint.oktool.spring.boot.trace;

import cn.toint.oktool.spring.boot.constant.OrderConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        int order = OrderConstant.TRACE_INTERCEPTOR_ORDER;
        registry.addInterceptor(new TraceInterceptor())
                .addPathPatterns("/**")
                .order(order);
        log.info("TraceInterceptor-任务追踪拦截器已开启. path: {}, order: {}", "/**", order);
    }
}
