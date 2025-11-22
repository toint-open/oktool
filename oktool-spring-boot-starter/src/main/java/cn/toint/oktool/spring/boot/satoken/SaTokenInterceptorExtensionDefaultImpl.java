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

import cn.dev33.satoken.stp.StpUtil;

/**
 * @author Toint
 * @since 2025/10/25
 */
public class SaTokenInterceptorExtensionDefaultImpl implements SaTokenInterceptorExtension {
    @Override
    public void beforeAuth(Object handler) {

    }

    @Override
    public void auth(Object handler) {
        StpUtil.checkLogin();
    }

    @Override
    public void afterAuth(Object handler) {
    }
}
