package cn.toint.oktool.spring.boot.satoken;

/**
 * sa-token认证拦截器扩展点
 *
 * @author Toint
 * @since 2025/10/25
 */
public interface SaTokenInterceptorExtension {
    /**
     * 1. 认证开始之前执行
     *
     * @param handler 即将执行的 Controller 方法的包装对象
     */
    void beforeAuth(Object handler);

    /**
     * 2. 认证逻辑
     *
     * @param handler 即将执行的 Controller 方法的包装对象
     */
    void auth(Object handler);

    /**
     * 3. 认证成功之后执行
     *
     * @param handler 即将执行的 Controller 方法的包装对象
     */
    void afterAuth(Object handler);
}
