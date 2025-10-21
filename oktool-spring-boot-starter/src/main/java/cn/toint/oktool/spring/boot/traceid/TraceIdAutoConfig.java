package cn.toint.oktool.spring.boot.traceid;

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
public class TraceIdAutoConfig implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(TraceIdAutoConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdInterceptor())
                .addPathPatterns("/**")
                .order(OrderConstant.TRACE_ID_INTERCEPTOR_ORDER);
        log.info("TraceIdInterceptor-任务编号拦截器已开启. path: {}, order: {}", "/**", OrderConstant.TRACE_ID_INTERCEPTOR_ORDER);
    }
}
