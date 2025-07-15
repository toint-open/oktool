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
import lombok.Data;

import java.time.Duration;

/**
 * @author Toint
 * @date 2025/7/14
 */
@Data
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
}
