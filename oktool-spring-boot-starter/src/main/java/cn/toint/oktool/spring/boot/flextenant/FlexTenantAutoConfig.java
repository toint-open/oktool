/*
 * Copyright 2025 Toint (599818663@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
