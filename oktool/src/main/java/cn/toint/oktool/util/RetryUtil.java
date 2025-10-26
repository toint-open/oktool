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

import cn.hutool.v7.core.array.ArrayUtil;
import cn.toint.oktool.exception.RetryException;
import cn.toint.oktool.model.RetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 重试工具
 *
 * @author Toint
 * @since 2025/5/31
 */
public class RetryUtil {

    private static final Logger log = LoggerFactory.getLogger(RetryUtil.class);

    /**
     * 重试机制
     *
     * @param callable 执行方法
     * @param <R>      返回类型
     * @return 方法执行结果
     * @throws RetryException 重试失败
     */
    public static <R> R execute(Callable<R> callable) {
        return RetryUtil.execute(callable, 3, Duration.ofSeconds(1), true, RuntimeException.class);
    }

    /**
     * 重试机制
     *
     * @param callable       执行方法
     * @param <R>            返回类型
     * @param exceptionClass 需要重试的异常类型 (默认 {@link RuntimeException})
     * @return 方法执行结果
     * @throws RetryException 重试失败
     */
    @SafeVarargs
    public static <R> R execute(Callable<R> callable, Class<? extends Throwable>... exceptionClass) {
        return RetryUtil.execute(callable, 3, Duration.ofSeconds(1), true, exceptionClass);
    }

    /**
     * 重试机制
     *
     * @param callable       执行方法
     * @param retrySize      重试次数 (不包含首次执行, 小于1表示不重试, 但无论如何方法会执行1次)
     * @param intervalTime   间隔时间 (null 或 小于等于0, 表示立刻重试不会等待)
     * @param exceptionClass 需要重试的异常类型 (默认 {@link RuntimeException})
     * @param <R>            返回类型
     * @return 方法执行结果
     * @throws RetryException 重试失败
     */
    @SafeVarargs
    public static <R> R execute(Callable<R> callable,
                                int retrySize,
                                Duration intervalTime,
                                Class<? extends Throwable>... exceptionClass) {
        return RetryUtil.execute(callable, retrySize, intervalTime, true, exceptionClass);
    }

    /**
     * 重试机制
     *
     * @param callable        执行方法
     * @param retrySize       重试次数 (不包含首次执行, 小于1表示不重试, 但无论如何方法会执行1次)
     * @param intervalTime    间隔时间 (null 或 小于等于0, 表示立刻重试不会等待)
     * @param exceptionClass  需要重试的异常类型 (默认 {@link RuntimeException})
     * @param printStackTrace 重试时是否打印异常信息 (false 不打印)
     * @param <R>             返回类型
     * @return 方法执行结果
     * @throws RetryException 重试失败
     */
    @SafeVarargs
    public static <R> R execute(Callable<R> callable,
                                int retrySize,
                                Duration intervalTime,
                                boolean printStackTrace,
                                Class<? extends Throwable>... exceptionClass) {

        List<Class<? extends Throwable>> exceptionClassList;
        if (ArrayUtil.isEmpty(exceptionClass)) {
            exceptionClassList = List.of(RuntimeException.class);
        } else {
            exceptionClassList = Arrays.stream(exceptionClass).toList();
        }

        List<RetryPolicy> retryPolicies = exceptionClassList.stream()
                .filter(Objects::nonNull)
                .map(item -> new RetryPolicy(retrySize, intervalTime, item, printStackTrace))
                .toList();

        return RetryUtil.execute(callable, retryPolicies);
    }

    /**
     * 重试机制
     *
     * @param callable      执行方法
     * @param retryPolicies 重试策略
     * @param <R>           返回类型
     * @return 方法执行结果
     * @throws RetryException 重试失败
     */
    public static <R> R execute(Callable<R> callable,
                                Collection<RetryPolicy> retryPolicies) {
        Assert.notNull(callable, "callable must not be null");

        // 重试计数器
        Map<RetryPolicy, AtomicInteger> retryCounters = new HashMap<>();

        if (retryPolicies == null) {
            retryPolicies = List.of();
        } else {
            retryPolicies = retryPolicies.stream()
                    .filter(Objects::nonNull)
                    // 重试次数小于1的策略不重试
                    .filter(item -> item.getRetrySize() > 0)
                    // 无异常类型不会重试
                    .filter(item -> item.getExceptionClass() != null)
                    // 初始化计数器
                    .peek(item -> retryCounters.put(item, new AtomicInteger(item.getRetrySize())))
                    .toList();
        }

        while (true) {
            try {
                return callable.call();
            } catch (Exception e) {
                // 查找匹配的重试策略, 没有匹配上抛出异常不重试
                RetryPolicy retryPolicy = retryPolicies.stream()
                        .filter(item -> item.getExceptionClass().isInstance(e))
                        .findFirst()
                        .orElseThrow(() -> new RetryException(e.getMessage(), e));

                // 检查剩余重试次数
                AtomicInteger remainSize = retryCounters.get(retryPolicy);
                if (remainSize.decrementAndGet() < 0) {
                    throw new RetryException(e.getMessage(), e);
                }

                // 打印日志
                if (retryPolicy.isPrintStackTrace()) {
                    log.warn("[{}/{}] {}", remainSize.get() + 1, retryPolicy.getRetrySize(), e.getMessage(), e);
                }

                // 睡眠等待
                Optional.ofNullable(retryPolicy.getIntervalTime())
                        .filter(Duration::isPositive)
                        .map(Duration::toNanos)
                        .ifPresent(LockSupport::parkNanos);

                if (Thread.interrupted()) {
                    throw new RuntimeException("线程中断", e);
                }
            }
        }
    }
}
