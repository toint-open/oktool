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
import lombok.Data;

/**
 * @author Toint
 * @date 2025/7/15
 */
@Data
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
}
