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

package cn.toint.oktool.spring.boot.bdocr;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

/**
 * 百度ocr配置
 *
 * @author Toint
 * @since 2025/9/7
 */
public class BdOcrClientConfig {
    @NotBlank
    private String apiKey;

    @NotBlank
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BdOcrClientConfig that = (BdOcrClientConfig) o;
        return Objects.equals(apiKey, that.apiKey) && Objects.equals(secretKey, that.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, secretKey);
    }

    @Override
    public String toString() {
        return "BdOcrClientConfig{" +
                "apiKey='" + apiKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
