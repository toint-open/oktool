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

package cn.toint.oktool.util;

import cn.hutool.v7.core.text.StrUtil;

/**
 * @author Toint
 * @since 2025/5/30
 */
public class ExceptionUtil {
    public static RuntimeException wrapRuntimeException(final String msgTemplate, final Object... params) {
        return new RuntimeException(StrUtil.format(msgTemplate, params));
    }

    public static RuntimeException wrapRuntimeException(final Throwable throwable, final String msgTemplate, final Object... params) {
        return new RuntimeException(StrUtil.format(msgTemplate, params), throwable);
    }
}
