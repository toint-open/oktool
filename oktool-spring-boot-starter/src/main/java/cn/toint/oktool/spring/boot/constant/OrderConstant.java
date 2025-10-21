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
