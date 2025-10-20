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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.util.Objects;

/**
 * @author Toint
 * @date 2025/7/15
 */
public class GeneratePresignedUrlRequest {
    @NotBlank
    private String bucketName;

    @NotBlank
    private String objectKey;

    @NotNull
    private Duration timeout;

    /**
     * cdn加速链接, 示例: {@code https://cdn.toint.cn}
     */
    private String cdnUrl;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    public String getCdnUrl() {
        return cdnUrl;
    }

    public void setCdnUrl(String cdnUrl) {
        this.cdnUrl = cdnUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GeneratePresignedUrlRequest that = (GeneratePresignedUrlRequest) o;
        return Objects.equals(bucketName, that.bucketName) && Objects.equals(objectKey, that.objectKey) && Objects.equals(timeout, that.timeout) && Objects.equals(cdnUrl, that.cdnUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketName, objectKey, timeout, cdnUrl);
    }

    @Override
    public String toString() {
        return "GeneratePresignedUrlRequest{" +
                "bucketName='" + bucketName + '\'' +
                ", objectKey='" + objectKey + '\'' +
                ", timeout=" + timeout +
                ", cdnUrl='" + cdnUrl + '\'' +
                '}';
    }
}
