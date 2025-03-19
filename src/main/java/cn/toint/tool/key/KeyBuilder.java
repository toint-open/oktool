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

package cn.toint.tool.key;

import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.text.split.SplitUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 键构造工具
 *
 * @author Toint
 * @date 2024/4/6
 */
public class KeyBuilder {
    /**
     * 键前缀
     */
    private final List<String> prefixList = new ArrayList<>();


    private KeyBuilder() {
    }

    /**
     * @param prefix 键前缀
     */
    public KeyBuilder(final String prefix) {
        if (StringUtils.isNotBlank(prefix)) {
            prefixList.addAll(SplitUtil.splitTrim(prefix, ":"));
        }
    }

    public KeyBuilder(final List<String> prefixList) {
        if (CollUtil.isNotEmpty(prefixList)) {
            this.prefixList.addAll(prefixList);
        }
    }

    public KeyBuilder add(final String prefix) {
        if (StringUtils.isNotBlank(prefix)) {
            this.prefixList.add(prefix);
        }
        return this;
    }

    /**
     * @param prefix 键前缀
     */
    public static KeyBuilder of(final String prefix) {
        return new KeyBuilder(prefix);
    }

    /**
     * @param prefixList 键前缀
     */
    public static KeyBuilder of(final List<String> prefixList) {
        return new KeyBuilder(prefixList);
    }

    /**
     * 构造键
     *
     * @param value 主键id
     * @return 键字符串
     */
    public String build(final String value) {
        return this.build() + ":" + value;
    }

    public String build() {
        if (CollUtil.isEmpty(this.prefixList)) {
            throw new RuntimeException("prefixList must not be empty");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.prefixList.size(); i++) {
            sb.append(this.prefixList.get(i));
            if (i < this.prefixList.size() - 1) {
                sb.append(":");
            }
        }

        return sb.toString();
    }
}
