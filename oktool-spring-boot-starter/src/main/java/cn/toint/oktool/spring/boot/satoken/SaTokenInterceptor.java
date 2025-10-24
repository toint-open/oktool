package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.exception.BackResultException;
import cn.dev33.satoken.exception.StopMatchException;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaAnnotationStrategy;
import cn.toint.oktool.spring.boot.context.OkContext;
import cn.toint.oktool.spring.boot.trace.TraceInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

/**
 * SaToken拦截器
 *
 * @author Toint
 * @since 2025/10/21
 */
public class SaTokenInterceptor implements HandlerInterceptor {
    /**
     * 每次请求之前触发的方法
     */
    @Override
    @SuppressWarnings("all")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        try {
            // 这里必须确保 handler 是 HandlerMethod 类型时，才能进行注解鉴权
            if (handler instanceof HandlerMethod) {
                Method method = ((HandlerMethod) handler).getMethod();
                SaAnnotationStrategy.instance.checkMethodAnnotation.accept(method);
            }

            // Auth 校验
            StpUtil.checkLogin();
            Object loginId = StpUtil.getLoginId();
            String tokenValue = StpUtil.getTokenValue();
            OkContext.setToken(tokenValue);
            TraceInfo traceInfo = OkContext.getTraceInfo();
            if (traceInfo != null) {
                traceInfo.setUserId(loginId);
                traceInfo.setToken(tokenValue);
            }

        } catch (StopMatchException e) {
            // StopMatchException 异常代表：停止匹配，进入Controller

        } catch (BackResultException e) {
            // BackResultException 异常代表：停止匹配，向前端输出结果
            // 		请注意此处默认 Content-Type 为 text/plain，如果需要返回 JSON 信息，需要在 back 前自行设置 Content-Type 为 application/json
            // 		例如：SaHolder.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
            if (response.getContentType() == null) {
                response.setContentType("text/plain; charset=utf-8");
            }
            response.getWriter().print(e.getMessage());
            return false;
        }

        // 通过验证
        return true;
    }
}
