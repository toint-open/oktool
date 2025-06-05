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

import cn.toint.tool.model.RetryPolicy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Toint
 * @date 2025/5/31
 */
@Slf4j
public class RetryUtilTest {
    @Test
    void execute() {
        // 重试次数
        final int retrySize = 3;
        // 执行次数
        final AtomicInteger runCount = new AtomicInteger(0);
        try {
            RetryUtil.execute(() -> {
                        log.info("执行{}次", runCount.incrementAndGet());
                        // 模拟异常
                        throw new RuntimeException("模拟异常");
                    },
                    retrySize,
                    Duration.ofMillis(500),
                    true,
                    RuntimeException.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        Assert.isTrue(retrySize + 1 == runCount.get(), "RetryUtilTest test fail");
    }

    @Test
    void execute2() {
        // 重试次数
        final int retrySize = 3;
        // 执行次数
        final AtomicInteger runCount = new AtomicInteger(0);

        final List<RetryPolicy> retryPolicies = new ArrayList<>();
        retryPolicies.add(new RetryPolicy(retrySize, Duration.ofMillis(500), IOException.class));
        retryPolicies.add(new RetryPolicy(retrySize, Duration.ofMillis(500), RuntimeException.class));
        try {
            RetryUtil.execute(() -> {
                        log.info("执行{}次", runCount.incrementAndGet());
                        // 模拟异常
                        throw new IllegalArgumentException("模拟异常");
                    },
                    retryPolicies);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        Assert.isTrue(retrySize + 1 == runCount.get(), "RetryUtilTest test fail");
    }


}