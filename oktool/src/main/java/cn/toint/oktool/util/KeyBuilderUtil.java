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

import org.apache.commons.lang3.StringUtils;
import cn.hutool.v7.core.collection.CollUtil;
import cn.hutool.v7.core.text.split.SplitUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * key 构建工具, 用于构造为: {@code a:b:c} 格式的 key
 *
 * @author Toint
 * @date 2024/4/6
 */
public class KeyBuilderUtil {
    /**
     * 键前缀
     */
    private final List<String> values = new ArrayList<>();

    private KeyBuilderUtil() {
    }

    public KeyBuilderUtil(final String value) {
        if (StringUtils.isNotBlank(value)) {
            // 忽略空格
            values.addAll(SplitUtil.splitTrim(value, ":"));
        }
    }

    public KeyBuilderUtil(final List<String> values) {
        if (CollUtil.isNotEmpty(values)) {
            this.values.addAll(values);
        }
    }

    public KeyBuilderUtil add(final String prefix) {
        if (StringUtils.isNotBlank(prefix)) {
            this.values.add(prefix);
        }
        return this;
    }

    public static KeyBuilderUtil of(final String prefix) {
        return new KeyBuilderUtil(prefix);
    }

    public static KeyBuilderUtil of(final List<String> prefixList) {
        return new KeyBuilderUtil(prefixList);
    }

    public String build(final String value) {
        if (StringUtils.isBlank(value)) {
            return this.build();
        } else {
            return this.build() + ":" + value;
        }
    }

    public String build() {
        if (CollUtil.isEmpty(this.values)) {
            throw new RuntimeException("values must not be empty");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.values.size(); i++) {
            sb.append(this.values.get(i));
            if (i < this.values.size() - 1) {
                sb.append(":");
            }
        }

        return sb.toString();
    }
}
