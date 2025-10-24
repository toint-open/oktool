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

package cn.toint.oktool.spring.boot.cache.impl;

import cn.toint.oktool.spring.boot.cache.Cache;
import cn.toint.oktool.util.Assert;
import cn.hutool.v7.core.cache.impl.TimedCache;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本地缓存
 *
 * @author Toint
 * @since 2025/7/2
 */
public class LocalCacheImpl implements Cache {
    /**
     * 缓存容器
     */
    private final TimedCache<String, String> timedCache = initTimedCache();

    @Override
    public void put(String key, String value, Duration timeout) {
        Assert.notBlank(key, "key不能为空");
        Assert.notNull(timeout, "缓存时间不能为空");
        timedCache.put(key, value, timeout.toMillis());
    }

    private final ReentrantLock putIfAbsentLock = new ReentrantLock();

    @Override
    public boolean putIfAbsent(String key, String value, Duration timeout) {
        Assert.notBlank(key, "key不能为空");
        Assert.notNull(timeout, "缓存时间不能为空");

        putIfAbsentLock.lock();
        try {
            if (timedCache.containsKey(key)) {
                return false;
            } else {
                timedCache.put(key, value, timeout.toMillis());
                return true;
            }
        } finally {
            putIfAbsentLock.unlock();
        }
    }

    @Override
    public String get(String key) {
        Assert.notBlank(key, "key不能为空");
        return timedCache.get(key, false);
    }

    @Override
    public List<String> multiGet(Collection<String> keys) {
        Assert.notEmpty(keys, "keys不能为空");

        List<String> values = new ArrayList<>();
        for (String key : keys) {
            values.add(get(key));
        }

        return values;
    }

    @Override
    public boolean containsKey(String key) {
        Assert.notBlank(key, "key不能为空");
        return timedCache.containsKey(key);
    }

    @Override
    public void delete(String key) {
        Assert.notBlank(key, "key不能为空");
        timedCache.remove(key);
    }

    private TimedCache<String, String> initTimedCache() {
        // 不限制超时时间
        TimedCache<String, String> timedCache = new TimedCache<>(0);
        timedCache.schedulePrune(60000); // 定时任务60秒清除过期数据
        return timedCache;
    }
}
