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

import cn.hutool.v7.core.cache.impl.TimedCache;
import cn.hutool.v7.core.date.TimeUtil;
import cn.toint.oktool.spring.boot.cache.Cache;
import cn.toint.oktool.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本地缓存
 *
 * <p>使用hutool的{@link TimedCache}实现</p>
 *
 * @author Toint
 * @since 2025/7/2
 */
public class LocalCacheImpl implements Cache {
    /**
     * 缓存容器
     */
    private final TimedCache<String, String> timedCache = initTimedCache();

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void put(String key, String value, long timeout) {
        Assert.notBlank(key, "key不能为空");
        timedCache.put(key, value, timeout);
    }

    @Override
    public boolean putIfAbsent(String key, String value, long timeout) {
        Assert.notBlank(key, "key不能为空");

        lock.lock();
        try {
            if (containsKey(key)) {
                return false;
            } else {
                put(key, value, timeout);
                return true;
            }
        } finally {
            lock.unlock();
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

    @Override
    public long add(String key, long delta) {
        Assert.notBlank(key, "key不能为空");

        lock.lock();
        try {
            String valueStr = get(key);
            long valueNum = valueStr == null ? 0 : Long.parseLong(valueStr);
            valueNum += delta;
            put(key, String.valueOf(valueNum), 0);
            return valueNum;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double add(String key, double delta) {
        Assert.notBlank(key, "key不能为空");
        lock.lock();
        try {
            String valueStr = get(key);
            double valueNum = valueStr == null ? 0 : Double.parseDouble(valueStr);
            valueNum += delta;
            put(key, String.valueOf(valueNum), 0);
            return valueNum;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void expire(String key, long timeout) {
        Assert.notBlank(key, "key不能为空");
        lock.lock();
        try {
            // 时间小于就删除key
            if (timeout <= 0) {
                delete(key);
                return;
            }

            String value = get(key);
            if (value != null) {
                put(key, value, timeout);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void expireAt(String key, LocalDateTime timeout) {
        Assert.notBlank(key, "key不能为空");
        Assert.notNull(timeout, "过期时间不能为空");

        // 计算时间差(ms), 已经过期的就删除key
        long between = TimeUtil.between(TimeUtil.now(), timeout, ChronoUnit.MILLIS);
        expire(key, between);
    }

    private TimedCache<String, String> initTimedCache() {
        // 不限制超时时间
        TimedCache<String, String> timedCache = new TimedCache<>(0);
        timedCache.schedulePrune(60000); // 定时任务60秒清除过期数据
        return timedCache;
    }
}
