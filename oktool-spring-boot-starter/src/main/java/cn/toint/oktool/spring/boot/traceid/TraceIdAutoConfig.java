package cn.toint.oktool.spring.boot.traceid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求任务编号自动配置
 *
 * @author Toint
 * @dete 2025/10/12
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // 仅在Servlet环境启用
@ConditionalOnClass({DispatcherServlet.class, HandlerInterceptor.class})
@Slf4j
public class TraceIdAutoConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdInterceptor())
                .addPathPatterns("/**")           // 明确拦截路径
                .order(Ordered.HIGHEST_PRECEDENCE);  // 设置最高优先级，确保最先执行
        log.info("请求任务ID拦截器初始化成功");
    }
}
