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

package cn.toint.oktool.spring.boot.jakcson;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

/**
 * @author Toint
 * @date 2025/7/4
 */
@ConfigurationProperties(prefix = "oktool.jackson")
public class JacksonProperties {
    /**
     * jackson LocalDateTime日期模块
     */
    private LocalDateTimeModule localDateTimeModule = new LocalDateTimeModule();

    /**
     * jackson 安全Long模块
     */
    private SafeLongModule safeLongModule = new SafeLongModule();

    public LocalDateTimeModule getLocalDateTimeModule() {
        return localDateTimeModule;
    }

    public void setLocalDateTimeModule(LocalDateTimeModule localDateTimeModule) {
        this.localDateTimeModule = localDateTimeModule;
    }

    public SafeLongModule getSafeLongModule() {
        return safeLongModule;
    }

    public void setSafeLongModule(SafeLongModule safeLongModule) {
        this.safeLongModule = safeLongModule;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JacksonProperties that = (JacksonProperties) o;
        return Objects.equals(localDateTimeModule, that.localDateTimeModule) && Objects.equals(safeLongModule, that.safeLongModule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDateTimeModule, safeLongModule);
    }

    @Override
    public String toString() {
        return "JacksonProperties{" +
                "localDateTimeModule=" + localDateTimeModule +
                ", safeLongModule=" + safeLongModule +
                '}';
    }

    /**
     * jackson LocalDateTime日期模块
     */
    public static class LocalDateTimeModule {
        private boolean enabled = true;
        private String zoneId = "Asia/Shanghai";
        private String pattern = "yyyy-MM-dd HH:mm:ss";

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getZoneId() {
            return zoneId;
        }

        public void setZoneId(String zoneId) {
            this.zoneId = zoneId;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            LocalDateTimeModule that = (LocalDateTimeModule) o;
            return enabled == that.enabled && Objects.equals(zoneId, that.zoneId) && Objects.equals(pattern, that.pattern);
        }

        @Override
        public int hashCode() {
            return Objects.hash(enabled, zoneId, pattern);
        }

        @Override
        public String toString() {
            return "LocalDateTimeModule{" +
                    "enabled=" + enabled +
                    ", zoneId='" + zoneId + '\'' +
                    ", pattern='" + pattern + '\'' +
                    '}';
        }
    }

    /**
     * jackson 安全Long模块
     */
    public static class SafeLongModule {
        private boolean enabled = true;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            SafeLongModule that = (SafeLongModule) o;
            return enabled == that.enabled;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(enabled);
        }

        @Override
        public String toString() {
            return "SafeLongModule{" +
                    "enabled=" + enabled +
                    '}';
        }
    }
}
