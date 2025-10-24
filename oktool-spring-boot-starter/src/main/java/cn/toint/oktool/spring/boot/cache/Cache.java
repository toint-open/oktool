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

package cn.toint.oktool.spring.boot.cache;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 缓存服务
 *
 * @author Toint
 * @since 2025/7/2
 */
public interface Cache {
    /**
     * 设置缓存
     *
     * @param key     缓存键, 不能为空
     * @param value   缓存值
     * @param timeout 缓存时间, 不能为空
     */
    default void put(String key, String value, Duration timeout) {
        put(key, value, timeout.toMillis());
    }

    /**
     * 设置缓存
     *
     * @param key     缓存键, 不能为空
     * @param value   缓存值
     * @param timeout 缓存时间(ms)
     */
    void put(String key, String value, long timeout);

    /**
     * 设置缓存如果不存在
     *
     * @param key     缓存键, 不能为空
     * @param value   缓存值
     * @param timeout 缓存时间, 不能为空
     * @return true=设置成功, false=不成功
     */
    default boolean putIfAbsent(String key, String value, Duration timeout) {
        return putIfAbsent(key, value, timeout.toMillis());
    }

    /**
     * 设置缓存如果不存在
     *
     * @param key     缓存键, 不能为空
     * @param value   缓存值
     * @param timeout 缓存时间(ms)
     * @return true=设置成功, false=不成功
     */
    boolean putIfAbsent(String key, String value, long timeout);

    /**
     * 获取缓存
     *
     * @param key 缓存键
     * @return 缓存值
     */
    String get(String key);

    /**
     * 获取缓存
     *
     * @param keys 缓存键
     * @return 缓存值集合, 如果对应位置的key不存在, 则用null占位
     */
    List<String> multiGet(Collection<String> keys);

    /**
     * 缓存是否存在
     *
     * @param key 缓存键, 不能为空
     * @return true=存在, false=不存在
     */
    boolean containsKey(String key);

    /**
     * 删除缓存
     *
     * @param key 缓存键, 不能为空
     */
    void delete(String key);

    /**
     * 缓存增加（整数）
     *
     * @param key 缓存键, 不能为空
     * @param delta 自增值
     * @return 自增后的值
     */
    long add(String key, long delta);

    /**
     * 缓存增加（浮点数）
     *
     * @param key 缓存键, 不能为空
     * @param delta 自增值
     * @return 自增后的值
     */
    double add(String key, double delta);

    /**
     * 缓存自增（整数）
     *
     * @param key 缓存键, 不能为空
     * @param delta 自增值
     * @return 自增后的值
     */
    default long increment(String key, long delta) {
        return add(key, delta);
    }

    /**
     * 缓存自增（浮点数）
     *
     * @param key 缓存键, 不能为空
     * @param delta 自增值
     * @return 自增后的值
     */
    default double increment(String key, double delta) {
        return add(key, delta);
    }

    /**
     * 缓存自减（整数）
     *
     * @param key 缓存键, 不能为空
     * @param delta 自减值
     * @return 自减后的值
     */
    default long decrement(String key, long delta) {
        return add(key, -delta);
    }

    /**
     * 缓存自减（浮点数）
     *
     * @param key 缓存键, 不能为空
     * @param delta 自增值
     * @return 自减后的值
     */
    default double decrement(String key, double delta) {
        return add(key, -delta);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key 缓存键, 不能为空
     * @param timeout 过期时间, 不能为空
     */
    default void expire(String key, Duration timeout) {
        expire(key, timeout.toMillis());
    }

    /**
     * 设置缓存过期时间
     *
     * @param key 缓存键, 不能为空
     * @param timeout 过期时间(ms)
     */
    void expire(String key, long timeout);

    /**
     * 设置缓存过期时间
     *
     * @param key 缓存键, 不能为空
     * @param timeout 过期时间, 不能为空
     */
    void expireAt(String key, LocalDateTime timeout);
}
