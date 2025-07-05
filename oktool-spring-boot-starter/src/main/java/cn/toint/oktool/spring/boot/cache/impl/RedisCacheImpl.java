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
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Objects;

/**
 * @author Toint
 * @date 2025/7/2
 */
public class RedisCacheImpl implements Cache {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void put(String key, String value, Duration timeout) {
        Assert.notBlank(key, "key不能为空");
        Assert.notNull(timeout, "缓存时间不能为空");
        stringRedisTemplate.opsForValue().set(key, value, timeout);
    }

    @Override
    public boolean putIfAbsent(String key, String value, Duration timeout) {
        Assert.notBlank(key, "key不能为空");
        Assert.notNull(timeout, "缓存时间不能为空");
        Boolean status = stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout);
        return Objects.equals(Boolean.TRUE, status);
    }

    @Override
    public String get(String key) {
        Assert.notBlank(key, "key不能为空");
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean containsKey(String key) {
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        return Objects.equals(Boolean.TRUE, hasKey);
    }
}
