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

package cn.toint.oktool.oss;

import cn.toint.oktool.oss.model.RegionAndEndpointEnum;
import cn.toint.oktool.util.Assert;
import com.aliyun.oss.ClientConfiguration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/7/15
 */
public class OssClientConfig {
    @NotBlank
    private String accessKeyId;

    @NotBlank
    private String secretAccessKey;

    @NotBlank
    private String endpoint;

    @NotBlank
    private String region;

    /**
     * 请求超时
     */
    @NotNull
    private Integer requestTimeout = ClientConfiguration.DEFAULT_REQUEST_TIMEOUT;

    /**
     * 连接超时
     */
    @NotNull
    private Integer connectionTimeout = ClientConfiguration.DEFAULT_CONNECTION_TIMEOUT;

    /**
     * 获取可用连接超时
     */
    @NotNull
    private Integer connectionRequestTimeout = 5000;

    public OssClientConfig regionAndEndpoint(RegionAndEndpointEnum regionAndEndpointEnum) {
        Assert.notNull(regionAndEndpointEnum, "regionAndEndpointEnum must not be null");
        this.region = regionAndEndpointEnum.getRegion();
        this.endpoint = regionAndEndpointEnum.getEndpoint();
        return this;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OssClientConfig that = (OssClientConfig) o;
        return Objects.equals(accessKeyId, that.accessKeyId) && Objects.equals(secretAccessKey, that.secretAccessKey) && Objects.equals(endpoint, that.endpoint) && Objects.equals(region, that.region) && Objects.equals(requestTimeout, that.requestTimeout) && Objects.equals(connectionTimeout, that.connectionTimeout) && Objects.equals(connectionRequestTimeout, that.connectionRequestTimeout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessKeyId, secretAccessKey, endpoint, region, requestTimeout, connectionTimeout, connectionRequestTimeout);
    }

    @Override
    public String toString() {
        return "OssClientConfig{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", secretAccessKey='" + secretAccessKey + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", region='" + region + '\'' +
                ", requestTimeout=" + requestTimeout +
                ", connectionTimeout=" + connectionTimeout +
                ", connectionRequestTimeout=" + connectionRequestTimeout +
                '}';
    }
}
