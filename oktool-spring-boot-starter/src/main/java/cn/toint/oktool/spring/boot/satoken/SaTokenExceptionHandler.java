package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.toint.oktool.model.ErrCode;
import cn.toint.oktool.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * sa-token异常处理
 *
 * @author Toint
 * @since 2025/10/21
 */
public class SaTokenExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SaTokenExceptionHandler.class);

    /**
     * 登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Response<Void> notLoginException(NotLoginException e) {
        log.warn(e.getMessage());
        return Response.fail(ErrCode.NOT_LOGIN.getCode(), ErrCode.NOT_LOGIN.getMsg());
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public Response<Void> notPermissionException(NotPermissionException e) {
        log.warn(e.getMessage());
        return Response.fail(ErrCode.NOT_PERMISSION.getCode(), ErrCode.NOT_PERMISSION.getMsg());
    }

    /**
     * 角色异常
     */
    @ExceptionHandler(NotRoleException.class)
    public Response<Void> exception(NotRoleException e) {
        log.error(e.getMessage());
        return Response.fail(ErrCode.NOT_PERMISSION.getCode(), ErrCode.NOT_PERMISSION.getMsg());
    }
}
