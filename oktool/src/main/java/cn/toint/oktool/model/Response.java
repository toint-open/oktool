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

import cn.hutool.v7.core.date.SystemClock;
import cn.toint.oktool.constant.ResponseConstant;
import cn.toint.oktool.util.MdcUtil;

import java.util.Objects;

/**
 * @author Toint
 * @date 2025/7/12
 */
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return Objects.equals(code, response.code) && Objects.equals(msg, response.msg) && Objects.equals(data, response.data) && Objects.equals(timestamp, response.timestamp) && Objects.equals(traceId, response.traceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, msg, data, timestamp, traceId);
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                ", traceId='" + traceId + '\'' +
                '}';
    }
}
