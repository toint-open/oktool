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

package cn.toint.oktool.spring.boot.satoken;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.toint.oktool.spring.boot.constant.OrderConstant;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Toint
 * @since 2025/10/25
 */
public class SaTokenWebMvcConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SaTokenWebMvcConfig.class);

    @Resource
    private SaInterceptor saInterceptor;

    /**
     * sa-token拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        int order = OrderConstant.SA_TOKEN_INTERCEPTOR_ORDER;
        registry.addInterceptor(saInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/favicon.ico")
                .order(order);
        log.info("SaInterceptor-认证拦截器已开启. path: {}, order: {}", "/**", order);
    }
}
