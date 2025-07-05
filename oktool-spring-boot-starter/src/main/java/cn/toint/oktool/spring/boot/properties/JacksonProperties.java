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

package cn.toint.oktool.spring.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Toint
 * @date 2025/7/4
 */
@ConfigurationProperties(prefix = "oktool.jackson")
@Data
public class JacksonProperties {
    /**
     * jackson LocalDateTime日期模块
     */
    private LocalDateTimeModule localDateTimeModule = new LocalDateTimeModule();

    /**
     * jackson 安全Long模块
     */
    private SafeLongModule safeLongModule = new SafeLongModule();

    /**
     * jackson LocalDateTime日期模块
     */
    @Data
    public static class LocalDateTimeModule {
        private boolean enabled = true;
        private String zoneId = "Asia/Shanghai";
        private String pattern =  "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * jackson 安全Long模块
     */
    @Data
    public static class SafeLongModule {
        private boolean enabled = true;
    }
}
