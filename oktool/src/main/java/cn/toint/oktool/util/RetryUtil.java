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
import cn.hutool.v7.core.collection.CollUtil;
import cn.hutool.v7.core.thread.ThreadUtil;
import cn.toint.oktool.exception.RetryException;
import cn.toint.oktool.model.RetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重试工具
 *
 * @author Toint
 * @date 2025/5/31
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
    public static <R> R execute(final Callable<R> callable) {
        return RetryUtil.execute(callable, 3, Duration.ofSeconds(1), true, RuntimeException.class);
    }

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
    public static <R> R execute(final Callable<R> callable,
                                final int retrySize,
                                final Duration intervalTime,
                                final Class<? extends Throwable>... exceptionClass) {
        return RetryUtil.execute(callable, retrySize, intervalTime, false, exceptionClass);
    }

    /**
     * 重试机制
     *
     * @param callable        执行方法
     * @param retrySize       重试次数 (不包含首次执行, 小于1表示不重试, 但无论如何方法会执行1次)
     * @param intervalTime    间隔时间 (null 或 小于等于0, 表示立刻重试不会等待)
     * @param exceptionClass  需要重试的异常类型 (默认 {@link Exception})
     * @param printStackTrace 重试时是否打印异常信息 (false 不打印)
     * @param <R>             返回类型
     * @return 方法执行结果
     * @throws RetryException 重试失败
     */
    @SafeVarargs
    public static <R> R execute(final Callable<R> callable,
                                final int retrySize,
                                final Duration intervalTime,
                                final boolean printStackTrace,
                                final Class<? extends Throwable>... exceptionClass) {
        final List<RetryPolicy> retryPolicies = new ArrayList<>();
        if (ArrayUtil.isNotEmpty(exceptionClass)) {
            for (final Class<? extends Throwable> item : exceptionClass) {
                if (item != null) {
                    final RetryPolicy retryPolicy = new RetryPolicy(retrySize, intervalTime, item, printStackTrace);
                    retryPolicies.add(retryPolicy);
                }
            }
        }

        // 默认捕获: Exception.class
        if (CollUtil.isEmpty(retryPolicies)) {
            retryPolicies.add(new RetryPolicy(retrySize, intervalTime, Exception.class, printStackTrace));
        }

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
    public static <R> R execute(final Callable<R> callable,
                                Collection<RetryPolicy> retryPolicies) {
        Assert.notNull(callable, "callable must not be null");

        // 为每个策略创建独立的计数器
        Map<RetryPolicy, AtomicInteger> retryCounters = new HashMap<>();
        if (CollUtil.isNotEmpty(retryPolicies)) {
            for (RetryPolicy policy : retryPolicies) {
                retryCounters.put(policy, new AtomicInteger(policy.getRetrySize()));
            }
        }

        while (true) {
            try {
                return callable.call();
            } catch (Exception e) {
                // 检查异常类型, 没有匹配上抛出异常不重试
                RetryPolicy retryPolicy = null;
                if (CollUtil.isNotEmpty(retryPolicies)) {
                    for (final RetryPolicy item : retryPolicies) {
                        if (item.getExceptionClass() != null && item.getExceptionClass().isAssignableFrom(e.getClass())) {
                            retryPolicy = item;
                            break;
                        }
                    }
                }
                if (retryPolicy == null) {
                    throw new RetryException(e.getMessage(), e);
                }

                // 剩余重试次数耗尽, 抛出异常
                final AtomicInteger remainSize = retryCounters.get(retryPolicy);
                if (remainSize == null || remainSize.decrementAndGet() < 0) {
                    throw new RetryException(e.getMessage(), e);
                }

                // 打印日志
                if (retryPolicy.isPrintStackTrace()) {
                    log.warn("({}/{}) {}", remainSize.get() + 1, retryPolicy.getRetrySize(), e.getMessage(), e);
                }

                // 执行休眠重试
                if (retryPolicy.getIntervalTime() != null && retryPolicy.getIntervalTime().isPositive()) {
                    ThreadUtil.sleep(retryPolicy.getIntervalTime());
                }
            }
        }
    }
}
