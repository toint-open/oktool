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

import cn.toint.oktool.util.JacksonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatisflex.core.handler.JacksonTypeHandler;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * @author Toint
 * @date 2025/7/4
 */
@Configuration
public class JacksonObjectMapperConfig {

    private static final Logger log = LoggerFactory.getLogger(JacksonObjectMapperConfig.class);

    @Resource
    private ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        JacksonUtil.setObjectMapper(objectMapper);
        log.info("JacksonUtil已使用Spring容器中的ObjectMapper实例: moduleIds={}", objectMapper.getRegisteredModuleIds());

        // 替换mybatis-flex, 如果存在
        if (ClassUtils.isPresent("com.mybatisflex.core.handler.JacksonTypeHandler", null)) {
            JacksonTypeHandler.setObjectMapper(objectMapper);
            log.info("MybatisFlex JacksonTypeHandler已使用Spring容器中的ObjectMapper实例: moduleIds={}", objectMapper.getRegisteredModuleIds());
        }
    }
}
