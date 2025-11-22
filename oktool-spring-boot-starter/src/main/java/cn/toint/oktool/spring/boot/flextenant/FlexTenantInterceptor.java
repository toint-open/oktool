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
import cn.toint.oktool.spring.boot.context.OkContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * mybatis-flex租户拦截器
 *
 * @author Toint
 * @since 2025/10/12
 */
public class FlexTenantInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(FlexTenantInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将当前访问用户写入租户上下文
        // 注意: 即使用户使用了satoken的@SaIgnore注解, 任然不会影响这里获取用户信息
        Object loginId = StpUtil.getLoginIdDefaultNull();
        if (loginId != null) {
            OkContext.setTenantIds(List.of(loginId));
        }
        return true;
    }
}
