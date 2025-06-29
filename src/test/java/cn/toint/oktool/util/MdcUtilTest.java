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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.concurrent.ExecutionException;

/**
 * @author Toint
 * @date 2025/6/30
 */
@Slf4j
public class MdcUtilTest {
    @Test
    void traceIdTest() throws InterruptedException, ExecutionException {
        // 主线程任务编号
        MdcUtil.initTraceId();
        String traceId = MdcUtil.getTraceId();
        log.info("traceId={}", traceId);

        // 开启多个子线程
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            GlobalThreadPool.getTtlVirtualExecutorService().submit(() -> {
                Assert.equals(MdcUtil.getTraceId(), traceId, "MdcUtil父子线程任务编号不一致, count={}", finalI);
                Assert.equals(MDC.get("traceId"), traceId, "MDC父子线程任务编号不一致, count={}", finalI);
            }).get();
        }
    }
}
