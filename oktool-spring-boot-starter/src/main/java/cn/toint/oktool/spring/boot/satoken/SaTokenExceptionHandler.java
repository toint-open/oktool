package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.toint.oktool.model.ErrCode;
import cn.toint.oktool.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Toint
 * @since 2025/10/21
 */
@RestControllerAdvice
public class SaTokenExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(SaTokenExceptionHandler.class);

    @ExceptionHandler
    public Response<Void> notLoginException(NotLoginException e) {
        log.warn(e.getMessage());
        return Response.fail(ErrCode.NOT_LOGIN.getCode(), ErrCode.NOT_LOGIN.getMsg());
    }

    @ExceptionHandler
    public Response<Void> notPermissionException(NotPermissionException e) {
        log.warn(e.getMessage());
        return Response.fail(ErrCode.NOT_PERMISSION.getCode(), ErrCode.NOT_PERMISSION.getMsg());
    }
}
