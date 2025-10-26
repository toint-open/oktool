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

import java.time.Duration;
import java.util.Objects;

/**
 * 重试策略
 *
 * @author Toint
 * @since 2025/5/31
 */
public class RetryPolicy {
    /**
     * 重试次数 (不包含首次执行, 小于1表示不重试, 但无论如何方法会执行1次)
     */
    private final int retrySize;

    /**
     * 间隔时间 (null 或 小于等于0, 表示立刻重试不会等待)
     */
    private final Duration intervalTime;

    /**
     * 匹配异常类型 (null 不会重试)
     */
    private final Class<? extends Throwable> exceptionClass;

    /**
     * 重试时是否打印异常信息 (默认 false 不打印)
     */
    private final boolean printStackTrace;

    /**
     * @param retrySize      重试次数 (不包含首次执行, 小于1表示不重试, 但无论如何方法会执行1次)
     * @param intervalTime   间隔时间 (null 或 小于等于0, 表示立刻重试不会等待)
     * @param exceptionClass 匹配异常类型 (null 不会重试)
     */
    public RetryPolicy(int retrySize,
                       Duration intervalTime,
                       Class<? extends Throwable> exceptionClass) {
        this.retrySize = retrySize;
        this.intervalTime = intervalTime;
        this.exceptionClass = exceptionClass;
        this.printStackTrace = false;
    }

    /**
     * @param retrySize       重试次数 (不包含首次执行, 小于1表示不重试, 但无论如何方法会执行1次)
     * @param intervalTime    间隔时间 (null 或 小于等于0, 表示立刻重试不会等待)
     * @param exceptionClass  匹配异常类型 (null 不会重试)
     * @param printStackTrace 重试时是否打印异常信息 (false 不打印)
     */
    public RetryPolicy(int retrySize,
                       Duration intervalTime,
                       Class<? extends Throwable> exceptionClass,
                       boolean printStackTrace) {
        this.retrySize = retrySize;
        this.intervalTime = intervalTime;
        this.exceptionClass = exceptionClass;
        this.printStackTrace = printStackTrace;
    }

    public int getRetrySize() {
        return retrySize;
    }


    public Duration getIntervalTime() {
        return intervalTime;
    }


    public Class<? extends Throwable> getExceptionClass() {
        return exceptionClass;
    }

    public boolean isPrintStackTrace() {
        return printStackTrace;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RetryPolicy that = (RetryPolicy) o;
        return retrySize == that.retrySize && printStackTrace == that.printStackTrace && Objects.equals(intervalTime, that.intervalTime) && Objects.equals(exceptionClass, that.exceptionClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retrySize, intervalTime, exceptionClass, printStackTrace);
    }

    @Override
    public String toString() {
        return "RetryPolicy{" +
                "retrySize=" + retrySize +
                ", intervalTime=" + intervalTime +
                ", exceptionClass=" + exceptionClass +
                ", printStackTrace=" + printStackTrace +
                '}';
    }
}
