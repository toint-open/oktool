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
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Long 安全序列化
 *
 * @author Toint
 * @since 2025/5/26
 */
public class SafeLongSerializer extends JsonSerializer<Long> {
    /**
     * 静态实例
     */
    public final static SafeLongSerializer instance = new SafeLongSerializer();

    /**
     * JavaScript 最大支持整数
     */
    private static final long JS_MAX_SAFE_NUMBER = 9007199254740991L;

    /**
     * JavaScript 最小支持整数
     */
    private static final long JS_MIN_SAFE_NUMBER = -9007199254740991L;

    /**
     * @see JacksonUtil#createSafeLongModule()
     */
    @Override
    public void serialize(final Long value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        // 判断是否超出 JavaScript 安全范围
        if (value > JS_MAX_SAFE_NUMBER || value < JS_MIN_SAFE_NUMBER) {
            // 超出范围时转为 String
            gen.writeString(String.valueOf(value));
        } else {
            // 安全范围内保持 Number
            gen.writeNumber(value);
        }
    }
}
