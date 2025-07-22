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

import cn.toint.oktool.constant.ResponseConstant;
import cn.toint.oktool.util.MdcUtil;
import lombok.Data;
import cn.hutool.v7.core.date.SystemClock;

/**
 * @author Toint
 * @date 2025/7/12
 */
@Data
public class Response<T> implements WriteValue {
    private Integer code;

    private String msg;

    private T data;

    private Long timestamp = SystemClock.now();

    private String traceId = MdcUtil.getTraceId();

    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        response.setCode(ResponseConstant.successCode);
        response.setMsg(ResponseConstant.successMsg);
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(ResponseConstant.successCode);
        response.setMsg(ResponseConstant.successMsg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setCode(ResponseConstant.failCode);
        response.setMsg(ResponseConstant.failMsg);
        return response;
    }

    public static <T> Response<T> fail(T data) {
        Response<T> response = new Response<>();
        response.setCode(ResponseConstant.failCode);
        response.setMsg(ResponseConstant.failMsg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(String message) {
        Response<T> response = new Response<>();
        response.setCode(ResponseConstant.failCode);
        response.setMsg(message);
        return response;
    }

    public static <T> Response<T> fail(int code, String message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }

    public static <T> Response<T> fail(int code, String message, T data) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMsg(message);
        response.setData(data);
        return response;
    }
}
