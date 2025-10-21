package cn.toint.oktool.spring.boot.context;

import cn.hutool.v7.http.meta.ContentType;
import cn.hutool.v7.http.server.servlet.ServletUtil;
import cn.toint.oktool.model.ErrCode;
import cn.toint.oktool.model.Response;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 上下文过滤器
 *
 * @author Toint
 * @since 2025/10/21
 */
public class OkContextFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(OkContextFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        OkContext.runWithNewContext(() -> {
            try {
                chain.doFilter(request, response);
            } catch (Exception e) {
                // 当异常未被springboot拦截时, 统一处理
                log.error(e.getMessage(), e);
                writeErrMsg(httpServletResponse, ErrCode.FAIL);
            }
        });
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("OkContextFilter-上下文过滤器初始化成功 (基于ScopedValue)");
    }

    /**
     * 响应错误信息
     */
    private static void writeErrMsg(HttpServletResponse response, ErrCode errCode) {
        String errMsg = Response.fail(errCode.getCode(), errCode.getMsg()).toJsonString();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentLength(errMsg.getBytes().length);
        ServletUtil.write(response, errMsg, ContentType.JSON.getValue());
    }
}
