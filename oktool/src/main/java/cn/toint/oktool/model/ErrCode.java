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

package cn.toint.oktool.model;

/**
 * @author Toint
 * @since 2025/10/21
 */
public enum ErrCode {
    NOT_PERMISSION(403, "权限不足, 请联系管理员"),
    NOT_LOGIN(401, "登录已过期, 请重新登录"),
    FAIL(-1, "操作失败"),
    SUCCESS(0, "操作成功");

    private final int code;
    private final String msg;

    @Override
    public String toString() {
        return "ErrEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                "} " + super.toString();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    ErrCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
