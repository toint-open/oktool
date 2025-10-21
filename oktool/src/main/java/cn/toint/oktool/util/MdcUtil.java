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

import com.alibaba.ttl.TransmittableThreadLocal;
import cn.hutool.v7.core.data.id.IdUtil;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * MDC工具
 * 可以多线程传递MDC数据
 *
 * @author Toint
 * @since 2025/6/30
 */
public class MdcUtil {

    static {
        init();
    }

    private static final String TRACE_ID = "traceId";

    private static TransmittableThreadLocal<Map<String, String>> ttlMdc;

    /**
     * 初始化任务编号, 用完记得clear, 否则可能有内存泄漏风险, 不知道怎么用谨慎调用本方法
     */
    public static void initTraceId() {
        put(TRACE_ID, IdUtil.fastSimpleUUID());
    }

    public static String getTraceId() {
        return get(TRACE_ID);
    }

    public static void put(String key, String value) {
        Assert.notBlank(key, "key不能为空");
        ttlMdc.get().put(key, value);
        MDC.put(key, value);
    }

    public static String get(String key) {
        Assert.notBlank(key, "key不能为空");

        String value = MDC.get(key);
        if (value != null) return value;
        return ttlMdc.get().get(key);
    }

    public static void remove(String key) {
        ttlMdc.get().remove(key);
        MDC.remove(key);
    }

    public static void clear() {
        ttlMdc.get().clear();
        ttlMdc.remove();
        MDC.clear();
    }

    private static void init() {
        ttlMdc = new TransmittableThreadLocal<>() {
            /**
             * 在异步线程之前任务前, 初始化数据
             */
            @Override
            protected Map<String, String> initialValue() {
                return new HashMap<>();
            }

            /**
             * 在异步线程执行任务前, 将数据复制一份给异步线程的MDC
             */
            @Override
            protected void beforeExecute() {
                final Map<String, String> map = this.get();
                map.forEach(MDC::put);
            }

            /**
             * 在异步线程执行任务后, 清空当前异步线程MDC数据
             */
            @Override
            protected void afterExecute() {
                MDC.clear();
            }
        };
    }
}