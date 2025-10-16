package cn.toint.oktool.spring.boot.flextenant;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * mybatis-flex租户拦截器
 *
 * @author Toint
 * @dete 2025/10/12
 */
public class FlexTenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将当前访问用户写入租户上下文
        // 注意: 即使用户使用了satoken的@SaIgnore注解, 任然不会影响这里获取用户信息
        Object loginId = StpUtil.getLoginIdDefaultNull();
        if (loginId != null) {
            FlexTenantContextHolder.setTenantIds(List.of(loginId));
        } else {
            FlexTenantContextHolder.clear();
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除租户上下文
        FlexTenantContextHolder.clear();
    }
}
