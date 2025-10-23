package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.stp.StpUtil;
import cn.toint.oktool.spring.boot.constant.OrderConstant;
import cn.toint.oktool.spring.boot.context.OkContext;
import org.apache.commons.lang3.StringUtils;
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
        int order = OrderConstant.SA_TOKEN_INTERCEPTOR_ORDER;
        SaTokenInterceptor saTokenInterceptor = new SaTokenInterceptor();
        saTokenInterceptor.setAuth(_ -> {
            StpUtil.checkLogin();
            String tokenValue = StpUtil.getTokenValue();
            if (StringUtils.isNotBlank(tokenValue)) {
                OkContext.setToken(tokenValue);
            }
        });
        registry.addInterceptor(saTokenInterceptor)
                .addPathPatterns("/**")
                .order(order);
        log.info("SaTokenInterceptor-认证拦截器已开启. path: {}, order: {}", "/**", order);
    }

    @Bean
    public SaTokenExceptionHandler saTokenExceptionHandler() {
        log.info("SaTokenExceptionHandler-SaToken异常处理器已开启");
        return new SaTokenExceptionHandler();
    }
}
