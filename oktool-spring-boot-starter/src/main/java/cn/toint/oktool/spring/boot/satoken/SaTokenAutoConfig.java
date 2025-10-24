package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.stp.StpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

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

    private static final Logger log = LoggerFactory.getLogger(SaTokenAutoConfig.class);

    @Bean
    @ConditionalOnMissingBean
    public SaTokenInterceptor saTokenInterceptor() {
        return new SaTokenInterceptor();
    }

    /**
     * SaToken异常处理器
     * <p>想覆盖sa-token异常处理的实现, 声明一个SaTokenExceptionHandler Bean即可</p>
     * @see SaTokenExceptionHandler
     */
    @Bean
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SaTokenExceptionHandler saTokenExceptionHandler() {
        log.info("SaTokenExceptionHandler-SaToken异常处理器已开启");
        return new SaTokenExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public SaTokenWebMvcConfig saTokenWebMvcConfig() {
        return new SaTokenWebMvcConfig();
    }
}
