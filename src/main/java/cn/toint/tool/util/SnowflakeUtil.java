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

import org.dromara.hutool.core.data.id.Snowflake;

/**
 * 雪花 ID
 *
 * @author Toint
 * @date 2025/5/25
 */
public class SnowflakeUtil {

    private static Snowflake snowflake = new Snowflake();

    /**
     * @param workerId 终端 ID
     */
    public static void snowflake(final long workerId) {
        snowflake = new Snowflake(workerId);
    }

    /**
     * @param workerId     终端 ID
     * @param dataCenterId 数据中心 ID
     */
    public static void snowflake(final long workerId, final long dataCenterId) {
        snowflake = new Snowflake(workerId, dataCenterId);
    }

    public static long nextId() {
        return snowflake.next();
    }

    public static String nextStr() {
        return snowflake.nextStr();
    }

}
