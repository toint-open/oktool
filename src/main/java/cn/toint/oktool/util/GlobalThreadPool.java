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

import com.alibaba.ttl.threadpool.TtlExecutors;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Toint
 * @date 2025/6/30
 */
@Slf4j
public class GlobalThreadPool {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            getTtlVirtualExecutorService().shutdown();
        }));
    }

    /**
     * 全局ttl虚拟线程池
     */
    @Getter
    @Nonnull
    private static final ExecutorService ttlVirtualExecutorService = TtlExecutors.getTtlExecutorService(Executors.newVirtualThreadPerTaskExecutor());

}
