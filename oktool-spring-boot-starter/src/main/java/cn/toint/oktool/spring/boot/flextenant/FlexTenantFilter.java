//package cn.toint.oktool.spring.boot.flextenant;
//
//import cn.dev33.satoken.stp.StpUtil;
//import cn.toint.oktool.spring.boot.satoken.SaTokenFilter;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * MyBatis-Flex 租户过滤器（基于 ScopedValue）
// * <p>
// * 在请求处理前从 SaToken 获取登录用户 ID，并绑定到 ScopedValue 作用域中。
// * 整个请求处理过程中（包括异步任务，如果使用 ScopeValueUtil 传播），都可以访问租户上下文。
// * 请求结束后，ScopedValue 自动解绑，无需手动清理。
// * </p>
// *
// * @author Toint
// * @since 2025/10/21
// */
//public class FlexTenantFilter implements Filter {
//
//    private static final Logger log = LoggerFactory.getLogger(FlexTenantFilter.class);
//
//    /**
//     * 默认的过滤器顺序，在 SaTokenFilter 之后注册
//     */
//    private static final int DEFAULT_ORDER = SaTokenFilter.getDefaultOrder() + 100;
//
//    public static int getDefaultOrder() {
//        return DEFAULT_ORDER;
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        log.info("FlexTenantFilter初始化完成, 租户隔离功能已启用 (基于ScopedValue)");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        log.info("FlexTenantFilter开始处理");
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//        // 获取当前登录用户 ID
//        Object loginId = StpUtil.getLoginIdDefaultNull();
//
//        if (loginId == null) {
//            // 未登录用户，不绑定租户上下文，直接放行
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // 绑定租户上下文到 ScopedValue 作用域
//        List<Object> tenantIds = List.of(loginId);
//        ScopedValue.where(FlexTenantContextHolder.getTenantIdsScopedValue(), tenantIds)
//                .run(() -> {
//                    try {
//                        // 在 ScopedValue 作用域内执行整个请求处理链
//                        chain.doFilter(request, response);
//                    } catch (Exception e) {
//                        log.error("租户过滤器异常, URI: {}, loginId: {}", httpRequest.getRequestURI(), loginId, e);
//                        throw new RuntimeException("租户上下文处理失败", e);
//                    }
//                });
//
//        // 注意：ScopedValue 会在作用域结束时自动清理，无需手动 clear()
//    }
//}