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

package cn.toint.oktool.spring.boot.trace;

import cn.hutool.v7.core.date.TimeUtil;
import cn.toint.oktool.model.WriteValue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author Toint
 * @since 2025/10/25
 */
public class TraceInfo implements WriteValue {
    /**
     * 请求URI
     */
    private String uri;

    /**
     * 请求方法（GET/POST/PUT/DELETE等）
     */
    private String method;

    /**
     * 查询参数
     */
    private String query;

    /**
     * 请求ID（用于链路追踪）
     */
    private String traceId;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * User-Agent
     */
    private String userAgent;

    /**
     * 响应状态码
     */
    private Integer status;

    /**
     * 请求开始时间
     */
    private LocalDateTime startTime;

    /**
     * 请求结束时间
     */
    private LocalDateTime endTime;

    /**
     * 请求耗时（毫秒）
     */
    private Long duration;

    /**
     * 是否慢请求（耗时 > 3000ms）
     */
    private Boolean slow;

    /**
     * 用户ID（如果已登录）
     */
    private Object userId;

    /**
     * Token（脱敏后的，如：Bearer xxx...yyy）
     */
    private String token;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        TraceInfo traceInfo = (TraceInfo) object;
        return Objects.equals(traceId, traceInfo.traceId) && Objects.equals(method, traceInfo.method) && Objects.equals(uri, traceInfo.uri) && Objects.equals(query, traceInfo.query) && Objects.equals(clientIp, traceInfo.clientIp) && Objects.equals(userAgent, traceInfo.userAgent) && Objects.equals(status, traceInfo.status) && Objects.equals(startTime, traceInfo.startTime) && Objects.equals(endTime, traceInfo.endTime) && Objects.equals(duration, traceInfo.duration) && Objects.equals(slow, traceInfo.slow) && Objects.equals(userId, traceInfo.userId) && Objects.equals(token, traceInfo.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traceId, method, uri, query, clientIp, userAgent, status, startTime, endTime, duration, slow, userId, token);
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getSlow() {
        return slow;
    }

    public void setSlow(Boolean slow) {
        this.slow = slow;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 计算请求耗时+是否慢请求
     */
    public Long calculateDuration() {
        LocalDateTime startTime = getStartTime();
        LocalDateTime endTime = getEndTime();
        if (startTime != null && endTime != null) {
            long duration = TimeUtil.between(startTime, endTime, ChronoUnit.MILLIS);
            setDuration(duration);
            setSlow(duration >= 3000);
            return duration;
        }
        return null;
    }
}
