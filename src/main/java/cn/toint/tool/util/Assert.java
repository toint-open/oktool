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

import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.text.StrUtil;

import java.util.Map;
import java.util.Objects;

/**
 * 断言工具
 *
 * @author Toint
 * @date 2025/5/28
 */
public class Assert {

    public static void notNull(final Object object, final CharSequence template, final Object... params) {
        if (null == object) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    public static void notBlank(final CharSequence text, final CharSequence template, final Object... params) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    public static void notEmpty(final Object[] arr, final CharSequence template, final Object... params) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    public static void notEmpty(final Iterable<?> collection, final CharSequence template, final Object... params) {
        if (collection == null || !collection.iterator().hasNext()) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    public static void notEmpty(final Map<?, ?> map, final CharSequence template, final Object... params) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    public static void equals(final Object a, final Object b, final CharSequence template, final Object... params) {
        if (!Objects.equals(a, b)) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    public static void equals(final boolean b, final CharSequence template, final Object... params) {
        if (!b) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }
}