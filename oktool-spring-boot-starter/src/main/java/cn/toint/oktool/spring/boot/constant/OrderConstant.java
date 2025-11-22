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

package cn.toint.oktool.spring.boot.constant;

import org.springframework.core.Ordered;

/**
 * 顺序常量
 *
 * @author Toint
 * @since 2025/10/21
 */
public class OrderConstant {

    public static final int DEFAULT_ORDER = -1000;

    /**
     * OkContextFilter-上下文过滤器
     */
    public static final int OK_CONTEXT_FILTER_ORDER = Ordered.HIGHEST_PRECEDENCE;

    /**
     * TraceInterceptor-任务追踪拦截器
     */
    public static final int TRACE_INTERCEPTOR_ORDER = DEFAULT_ORDER;

    /**
     * SaTokenInterceptor-认证拦截器
     */
    public static final int SA_TOKEN_INTERCEPTOR_ORDER = TRACE_INTERCEPTOR_ORDER + 10;

    /**
     * FlexTenantInterceptor-租户拦截器
     */
    public static final int FLEX_TENANT_INTERCEPTOR_ORDER = SA_TOKEN_INTERCEPTOR_ORDER + 10;

}
