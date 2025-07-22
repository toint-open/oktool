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

package cn.toint.oktool.spring.boot.model;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import lombok.Data;
import cn.hutool.v7.core.data.id.IdUtil;

import java.time.LocalDateTime;

/**
 * @author Toint
 * @date 2025/7/12
 */
@Data
public class BaseDo {
    @Id
    private Long id;

    @Column
    private LocalDateTime createTime;


    @Column
    private LocalDateTime updateTime;

    /**
     * 初始化
     */
    public BaseDo init() {
        initId().initCreateTime().initUpdateTime();
        return this;
    }

    /**
     * 初始化ID
     * 如果字段有值将忽略执行
     */
    public BaseDo initId() {
        if (this.id == null) {
            setId(IdUtil.getSnowflakeNextId());
        }
        return this;
    }

    /**
     * 初始化时间
     * 如果字段有值将忽略执行
     */
    public BaseDo initCreateAndUpdateTime() {
        initCreateTime();
        initUpdateTime();
        return this;
    }

    /**
     * 初始化创建时间
     * 如果字段有值将忽略执行
     */
    public BaseDo initCreateTime() {
        if (this.createTime == null) {
            setCreateTime(LocalDateTime.now());
        }
        return this;
    }

    /**
     * 初始化更新时间
     */
    public BaseDo initUpdateTime() {
        freshUpdateTime();
        return this;
    }

    /**
     * 刷新更新时间
     */
    public BaseDo freshUpdateTime() {
        setUpdateTime(LocalDateTime.now());
        return this;
    }
}