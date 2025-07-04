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

import cn.toint.oktool.spring.boot.config.cache.CacheConfig;
import cn.toint.oktool.spring.boot.config.jackson.JacksonModelConfig;
import cn.toint.oktool.spring.boot.config.jackson.JacksonObjectMapperConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Toint
 * @date 2025/7/2
 */
@AutoConfiguration
@EnableConfigurationProperties(OkToolProperties.class)
@Slf4j
public class OkToolAutoConfig {

    @Resource
    private OkToolProperties okToolProperties;

    @Bean
    @ConditionalOnMissingBean
    public CacheConfig cacheConfig() {
        return new CacheConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public JacksonModelConfig jacksonConfig() {
        return new JacksonModelConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public JacksonObjectMapperConfig jacksonObjectMapperConfig() {
        return new JacksonObjectMapperConfig();
    }
}
