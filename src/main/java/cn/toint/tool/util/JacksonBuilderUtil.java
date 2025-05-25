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

package cn.toint.tool.util;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.date.DateFormatPool;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Toint
 * @date 2025/5/25
 */
public class JacksonBuilderUtil {
    /**
     * 创建 {@link LocalDateTime} 序列化与反序列化配置模块
     *
     * @param pattern pattern, 不传默认: {@code yyyy-MM-dd HH:mm:ss}
     * @param zoneId  zoneId, 不传默认: {@code ZoneId.of("Asia/Shanghai")}
     * @return 将 {@link Module} 注册成 springboot bean, springboot 会将其加入 springboot 默认的 {@link ObjectMapper} 中
     */
    public static Module createTimeModule(String pattern, ZoneId zoneId) {
        if (StringUtils.isBlank(pattern)) {
            pattern = DateFormatPool.NORM_DATETIME_PATTERN;
        }

        if (zoneId == null) {
            zoneId = ZoneId.of("Asia/Shanghai");
        }

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern).withZone(zoneId);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        return javaTimeModule;
    }

    /**
     * 创建 {@link Long} 序列化与反序列化配置模块
     *
     * @return 将 {@link Module} 注册成 springboot bean, springboot 会将其加入 springboot 默认的 {@link ObjectMapper} 中
     */
    public static Module createLongModule() {
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
        return simpleModule;
    }
}
