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

package cn.toint.oktool.oss.model;

import cn.toint.oktool.oss.OssClientConfig;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.util.Objects;

/**
 * @author Toint
 * @date 2025/7/14
 */
public class CalculatePostSignatureRequest {
    @NotNull
    private Long minFileSize;

    @NotNull
    private Long maxFileSize;

    @NotBlank
    private String objectKey;

    @NotBlank
    private String bucketName;

    /**
     * 签名有效时间, 默认30分钟
     */
    @NotNull
    private Duration timeout = Duration.ofMinutes(30);

    /**
     * 是否内网上传链接, 为空则默认使用{@link OssClientConfig#getRegion()}
     */
    private Boolean internal;

    public CalculatePostSignatureRequest fileSize(Long minFileSize, Long maxFileSize) {
        this.minFileSize = minFileSize;
        this.maxFileSize = maxFileSize;
        return this;
    }

    public CalculatePostSignatureRequest fileSize(Long fileSize) {
        this.minFileSize = fileSize;
        this.maxFileSize = fileSize;
        return this;
    }

    public Long getMinFileSize() {
        return minFileSize;
    }

    public void setMinFileSize(Long minFileSize) {
        this.minFileSize = minFileSize;
    }

    public Long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalculatePostSignatureRequest that = (CalculatePostSignatureRequest) o;
        return Objects.equals(minFileSize, that.minFileSize) && Objects.equals(maxFileSize, that.maxFileSize) && Objects.equals(objectKey, that.objectKey) && Objects.equals(bucketName, that.bucketName) && Objects.equals(timeout, that.timeout) && Objects.equals(internal, that.internal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minFileSize, maxFileSize, objectKey, bucketName, timeout, internal);
    }

    @Override
    public String toString() {
        return "CalculatePostSignatureRequest{" +
                "minFileSize=" + minFileSize +
                ", maxFileSize=" + maxFileSize +
                ", objectKey='" + objectKey + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", timeout=" + timeout +
                ", internal=" + internal +
                '}';
    }
}
