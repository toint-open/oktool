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

package cn.toint.oktool.service.impl;

import cn.toint.oktool.service.CacheService;
import cn.toint.oktool.util.Assert;
import org.dromara.hutool.core.cache.impl.TimedCache;

import java.time.Duration;

/**
 * 本地缓存
 *
 * @author Toint
 * @date 2025/7/2
 */
public class LocalCacheServiceImpl implements CacheService {
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

    @Override
    public String get(String key) {
        Assert.notBlank(key, "key不能为空");
        return timedCache.get(key);
    }

    private TimedCache<String, String> initTimedCache() {
        // 不限制超时时间
        TimedCache<String, String> timedCache = new TimedCache<>(0);
        timedCache.schedulePrune(60000); // 定时任务60秒清除过期数据
        return timedCache;
    }
}
