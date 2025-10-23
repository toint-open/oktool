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

import cn.hutool.v7.core.collection.CollUtil;
import cn.hutool.v7.core.text.StrUtil;
import cn.hutool.v7.core.text.split.SplitUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * key 构建工具, 用于构造为: {@code a:b:c} 格式的 key
 *
 * @author Toint
 * @since 2024/4/6
 */
public class KeyBuilder {

    private final List<String> values = new ArrayList<>();

    /**
     * 添加键值
     *
     * @param value 键值
     * @return this
     */
    public KeyBuilder add(String value) {
        if (StringUtils.isNotBlank(value)) {
            SplitUtil.split(value, ":")
                    .stream()
                    .map(StrUtil::trim)
                    .filter(StringUtils::isNotBlank)
                    .forEach(values::add);
        }
        return this;
    }

    /**
     * 添加键值
     *
     * @param values 键值
     * @return this
     */
    public KeyBuilder addAll(List<String> values) {
        if (CollUtil.isNotEmpty(values)) {
            values.forEach(this::add);
        }
        return this;
    }

    public static KeyBuilder of(String value) {
        return new KeyBuilder().add(value);
    }

    public static KeyBuilder of(List<String> values) {
        return new KeyBuilder().addAll(values);
    }

    public String build() {
        Assert.notEmpty(values, "values must not be empty");
        return String.join(":", values);
    }
}
