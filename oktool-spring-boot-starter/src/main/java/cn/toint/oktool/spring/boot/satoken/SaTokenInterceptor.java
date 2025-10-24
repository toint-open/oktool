package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import cn.toint.oktool.spring.boot.context.OkContext;
import cn.toint.oktool.spring.boot.trace.TraceInfo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

/**
 * SaToken拦截器包装类
 *
 * @author Toint
 * @since 2025/10/21
 */
public class SaTokenInterceptor extends SaInterceptor {

    @Resource
    private SaTokenInterceptorExtension saTokenInterceptorExtension;

    @PostConstruct
    private void init() {
        setAuth(handler -> {
            // 1. 认证开始之前执行
            saTokenInterceptorExtension.beforeAuth(handler);
            // 2. 认证逻辑
            saTokenInterceptorExtension.auth(handler);
            // 3. 认证成功之后执行
            saTokenInterceptorExtension.afterAuth(handler);

            // 写入用户信息到上下文
            Object loginId = StpUtil.getLoginId();
            String tokenValue = StpUtil.getTokenValue();
            OkContext.setToken(tokenValue);
            TraceInfo traceInfo = OkContext.getTraceInfo();
            if (traceInfo != null) {
                traceInfo.setUserId(loginId);
                traceInfo.setToken(tokenValue);
            }
        });
    }
}
