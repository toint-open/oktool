package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.stp.StpUtil;

/**
 * @author Toint
 * @since 2025/10/25
 */
public class SaTokenInterceptorExtensionDefaultImpl implements SaTokenInterceptorExtension {
    @Override
    public void beforeAuth(Object handler) {

    }

    @Override
    public void auth(Object handler) {
        StpUtil.checkLogin();
    }

    @Override
    public void afterAuth(Object handler) {
    }
}
