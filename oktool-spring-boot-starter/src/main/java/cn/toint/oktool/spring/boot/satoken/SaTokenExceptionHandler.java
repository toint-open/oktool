/*
 * Copyright 2025 Toint (599818663@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
