package cn.toint.oktool.spring.boot.flextenant;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.tenant.TenantFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

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
public class FlexTenantAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // 仅在Servlet环境启用
    public FlexTenantWebMvcConfig flexTenantWebMvcConfig() {
        return new FlexTenantWebMvcConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public TenantFactory tenantFactory() {
        return new FlexTenantFactory();
    }
}
