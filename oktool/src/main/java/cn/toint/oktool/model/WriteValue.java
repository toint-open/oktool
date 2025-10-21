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
package cn.toint.oktool.model;

import cn.toint.oktool.util.JacksonUtil;

import java.io.Serializable;

/**
 * 实现本接口, 默认拥有 {@link #toJsonString} 和 {@link #toJsonBytes()} 方法
 *
 * @author Toint
 * @since 2025/1/15
 */
public interface WriteValue extends Serializable {

    /**
     * toJsonString
     */
    default String toJsonString() {
        return JacksonUtil.writeValueAsString(this);
    }

    /**
     * toBytes
     */
    default byte[] toJsonBytes() {
        return JacksonUtil.writeValueAsBytes(this);
    }
}
