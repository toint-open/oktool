package cn.toint.oktool.spring.boot.trace;

import cn.toint.oktool.spring.boot.constant.OrderConstant;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Toint
 * @since 2025/10/25
 */
@AutoConfiguration
public class TraceWebMvcConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(TraceWebMvcConfig.class);

    @Resource
    private TraceInterceptor traceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        int order = OrderConstant.TRACE_INTERCEPTOR_ORDER;
        registry.addInterceptor(new TraceInterceptor())
                .addPathPatterns("/**")
                .order(order);
        log.info("TraceInterceptor-任务追踪拦截器已开启. path: {}, order: {}", "/**", order);
    }
}
