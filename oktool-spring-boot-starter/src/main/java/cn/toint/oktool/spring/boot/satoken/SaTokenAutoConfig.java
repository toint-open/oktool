package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

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
public class SaTokenAutoConfig {

    /**
     * SaToken拦截器扩展, 优先使用其他的{@link SaTokenInterceptorExtension}
     */
    @Bean
    @ConditionalOnMissingBean(SaTokenInterceptorExtension.class)
    public SaTokenInterceptorExtension saTokenInterceptorExtension() {
        return new SaTokenInterceptorExtensionDefaultImpl();
    }

    /**
     * SaToken拦截器包装类, 优先使用其他的{@link SaInterceptor}拦截器
     */
    @Bean
    @ConditionalOnMissingBean(SaInterceptor.class)
    public SaTokenInterceptor saTokenInterceptor() {
        return new SaTokenInterceptor();
    }

    /**
     * SaToken拦截器注册, 如果存在{@link SaInterceptor}拦截器, 则注册{@link SaTokenWebMvcConfig}
     */
    @Bean
    @ConditionalOnBean(SaInterceptor.class)
    public SaTokenWebMvcConfig saTokenWebMvcConfig() {
        return new SaTokenWebMvcConfig();
    }
}
