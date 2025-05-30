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

import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.array.ArrayUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.extra.validation.ValidationUtil;
import org.jetbrains.annotations.Contract;

import java.util.*;


/**
 * 断言工具
 *
 * @author Toint
 * @date 2025/5/28
 */
public class Assert {

    @Contract("null, _, _ -> fail")
    public static void notNull(@Nullable final Object object, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (object == null) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("!null, _, _ -> fail")
    public static void isNull(@Nullable final Object object, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (object != null) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("null, _, _ -> fail")
    public static void notBlank(@Nullable final CharSequence text, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("null, _, _ -> fail")
    public static void notEmpty(@Nullable final Object[] arr, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("null, _, _ -> fail")
    public static void notEmpty(@Nullable final Iterable<?> collection, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (collection == null || !collection.iterator().hasNext()) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("null, _, _ -> fail")
    public static void notEmpty(@Nullable final Map<?, ?> map, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    public static void equals(@Nullable final Object a, @Nullable final Object b, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (!Objects.equals(a, b)) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("false, _, _ -> fail")
    public static void isTrue(final boolean b, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (!b) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("true, _, _ -> fail")
    public static void isFalse(final boolean b, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (b) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("null, _ -> fail")
    public static void validate(@Nullable final Object object, final Class<?>... groups) {
        ValidationUtil.validateAndThrowFirst(object, groups);
    }

    /**
     * 验证对象, 失败则抛异常
     *
     * <p>如果校验失败, 异常信息会添加到 {@code params} 数组末尾, 调用者可在 {@code template} 预留位置, 否则忽略</p>
     */
    @Contract("null, _, _ -> fail")
    public static void validate(@Nullable final Object object, @Nullable final CharSequence template, @Nullable final Object... params) {
        if (object == null) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }

        try {
            ValidationUtil.validateAndThrowFirst(object);
        } catch (Exception e) {
            if (StringUtils.isBlank(template)) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }

            final List<Object> newParams = new ArrayList<>();
            if (ArrayUtil.isNotEmpty(params)) {
                newParams.addAll(Arrays.asList(params));
            }
            newParams.add(e.getMessage());
            throw new IllegalArgumentException(StrUtil.format(template, newParams.toArray()), e);
        }
    }
}