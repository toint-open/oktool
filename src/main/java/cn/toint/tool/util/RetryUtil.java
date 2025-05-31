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

import cn.toint.tool.exception.RetryException;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.dromara.hutool.core.array.ArrayUtil;
import org.dromara.hutool.core.thread.ThreadUtil;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重试工具
 *
 * @author Toint
 * @date 2025/5/31
 */
public class RetryUtil {
    /**
     * 重试机制
     *
     * @param callable       执行方法
     * @param retrySize      重试次数 (不包含首次执行, 小于1表示不重试, 但无论如何方法会执行1次)
     * @param intervalTime   间隔时间 (null 或 小于等于0, 表示立刻重试不会等待)
     * @param exceptionClass 需要重试的异常类型 (默认 {@link Exception})
     * @param <R>            返回类型
     * @return 方法执行结果
     * @throws RetryException 重试失败
     */
    @SafeVarargs
    public static <R> R execute(@Nonnull final Callable<R> callable,
                                final int retrySize,
                                @Nullable final Duration intervalTime,
                                @Nullable final Class<? extends Throwable>... exceptionClass) {
        Assert.notNull(callable, "callable must not be null");

        // 异常类型, 如果为空默认捕获 Exception
        final Set<Class<? extends Throwable>> classes = new HashSet<>();
        if (ArrayUtil.isNotEmpty(exceptionClass)) {
            for (final Class<? extends Throwable> item : exceptionClass) {
                if (item != null) {
                    classes.add(item);
                }
            }
        }
        if (classes.isEmpty()) {
            classes.add(Exception.class);
        }

        // 剩余次数
        final AtomicInteger size = new AtomicInteger(retrySize);

        while (true) {
            try {
                return callable.call();
            } catch (Throwable e) {
                // 检查异常类型, 没有匹配上抛出异常不重试
                if (classes.stream().noneMatch(ex -> ex.isAssignableFrom(e.getClass()))) {
                    throw new RetryException(e.getMessage(), e);
                }

                // 次数耗尽, 抛出异常
                if (size.decrementAndGet() < 0) {
                    throw new RetryException(e.getMessage(), e);
                }

                // 执行休眠重试
                if (intervalTime != null && intervalTime.isPositive()) {
                    ThreadUtil.sleep(intervalTime);
                }
            }
        }
    }
}
