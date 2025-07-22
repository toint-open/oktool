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

package cn.toint.oktool.spring.boot.config;

import cn.toint.oktool.spring.boot.properties.JacksonProperties;
import cn.toint.oktool.util.JacksonUtil;
import com.fasterxml.jackson.databind.Module;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;

/**
 * @author Toint
 * @date 2025/7/4
 */
@Configuration
@Slf4j
public class JacksonModelConfig {

    @Resource
    private JacksonProperties jacksonProperties;

    /**
     * jackson LocalDateTime日期模块
     */
    @Bean
    @ConditionalOnProperty(name = "oktool.jackson.local-date-time-module.enabled", havingValue = "true", matchIfMissing = true)
    public Module jacksonLocalDateTimeModule() {
        JacksonProperties.LocalDateTimeModule localDateTimeModule = jacksonProperties.getLocalDateTimeModule();
        ZoneId zoneId = ZoneId.of(localDateTimeModule.getZoneId());
        String pattern = localDateTimeModule.getPattern();
        Module timeModule = JacksonUtil.createLocalDateTimeModule(pattern, zoneId);
        log.info("Jackson LocalDateTimeModule初始化成功. 反序列化配置: 动态识别格式. 序列化配置: zoneId={}, pattern={}", zoneId, pattern);
        return timeModule;
    }

    /**
     * jackson 安全Long模块
     */
    @Bean
    @ConditionalOnProperty(name = "oktool.jackson.safe-long-module.enabled", havingValue = "true", matchIfMissing = true)
    public com.fasterxml.jackson.databind.Module jacksonSafeLongModule() {
        final Module longModule = JacksonUtil.createSafeLongModule();
        log.info("Jackson SafeLongModule初始化成功");
        return longModule;
    }
}
