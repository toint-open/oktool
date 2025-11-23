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

import cn.hutool.v7.core.array.ArrayUtil;
import cn.hutool.v7.core.text.StrUtil;
import cn.hutool.v7.extra.validation.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;

import java.util.*;


/**
 * 断言工具
 *
 * @author Toint
 * @since 2025/5/28
 */
public class Assert {

    @Contract("null -> fail")
    public static <T> T notNull(T object) {
        if (object == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        return object;
    }

    @Contract("null, _, _ -> fail")
    public static <T> T notNull(T object, CharSequence template, Object... params) {
        if (object == null) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
        return object;
    }

    @Contract("null, _ -> fail")
    public static <T> T notNullParam(T object, CharSequence paramName) {
        if (object == null) {
            throw new IllegalArgumentException(paramName + "不能为空");
        }
        return object;
    }

    @Contract("!null -> fail")
    public static <T> T isNull(T object) {
        if (object != null) {
            throw new IllegalArgumentException("参数必须为空");
        }
        return null;
    }

    @Contract("!null, _, _ -> fail")
    public static <T> T isNull(T object, CharSequence template, Object... params) {
        if (object != null) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
        return null;
    }

    @Contract("!null, _ -> fail")
    public static <T> T isNullParam(T object, CharSequence paramName) {
        if (object != null) {
            throw new IllegalArgumentException(paramName + "必须为空");
        }
        return null;
    }

    @Contract("null -> fail")
    public static <T extends CharSequence> T notBlank(T text) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        return text;
    }

    @Contract("null, _, _ -> fail")
    public static <T extends CharSequence> T notBlank(T text, CharSequence template, Object... params) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
        return text;
    }

    @Contract("null, _ -> fail")
    public static <T extends CharSequence> T notBlankParam(T text, CharSequence paramName) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(paramName + "不能为空");
        }
        return text;
    }

    @Contract("null -> fail")
    public static <T> T[] notEmpty(T[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("参数不能为空");
        }
        return arr;
    }

    @Contract("null, _, _ -> fail")
    public static <T> T[] notEmpty(T[] arr, CharSequence template, Object... params) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
        return arr;
    }

    @Contract("null, _ -> fail")
    public static <T> T[] notEmptyParam(T[] arr, CharSequence paramName) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException(paramName + "参数不能为空");
        }
        return arr;
    }

    @Contract("null -> fail")
    public static <C extends Iterable<T>, T> C notEmpty(C collection) {
        if (collection == null || !collection.iterator().hasNext()) {
            throw new IllegalArgumentException("参数不能为空");
        }
        return collection;
    }

    @Contract("null, _, _ -> fail")
    public static <C extends Iterable<T>, T> C notEmpty(C collection, CharSequence template, Object... params) {
        if (collection == null || !collection.iterator().hasNext()) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
        return collection;
    }

    @Contract("null, _ -> fail")
    public static <C extends Iterable<T>, T> C notEmptyParam(C collection, CharSequence paramName) {
        if (collection == null || !collection.iterator().hasNext()) {
            throw new IllegalArgumentException(paramName + "不能为空");
        }
        return collection;
    }

    @Contract("null, _, _ -> fail")
    public static <M extends Map<K, V>, K, V> M notEmpty(M map, CharSequence template, Object... params) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
        return map;
    }

    @Contract("null, _ -> fail")
    public static <M extends Map<K, V>, K, V> M notEmptyParam(M map, CharSequence paramName) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(paramName + "不能为空");
        }
        return map;
    }

    public static void equals(Object a, Object b, CharSequence template, Object... params) {
        if (!Objects.equals(a, b)) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    public static void notEquals(Object a, Object b, CharSequence template, Object... params) {
        if (Objects.equals(a, b)) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("false, _, _ -> fail")
    public static void isTrue(boolean b, CharSequence template, Object... params) {
        if (!b) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("true, _, _ -> fail")
    public static void isFalse(boolean b, CharSequence template, Object... params) {
        if (b) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }
    }

    @Contract("null, _ -> fail")
    public static <T> T validate(T object, Class<?>... groups) {
        Assert.notNull(object);
        ValidationUtil.validateAndThrowFirst(object, groups);
        return object;
    }

    /**
     * 验证对象, 失败则抛异常
     *
     * <p>如果校验失败, 异常信息会添加到 {@code params} 数组末尾, 调用者可在 {@code template} 预留位置, 否则忽略</p>
     */
    @Contract("null, _, _ -> fail")
    public static <T> T validate(T object, CharSequence template, Object... params) {
        if (object == null) {
            throw new IllegalArgumentException(StrUtil.format(template, params));
        }

        try {
            ValidationUtil.validateAndThrowFirst(object);
            return object;
        } catch (Exception e) {
            if (StringUtils.isBlank(template)) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }

            List<Object> newParams = new ArrayList<>();
            if (ArrayUtil.isNotEmpty(params)) {
                newParams.addAll(Arrays.asList(params));
            }
            newParams.add(e.getMessage());
            throw new IllegalArgumentException(StrUtil.format(template, newParams.toArray()), e);
        }
    }
}