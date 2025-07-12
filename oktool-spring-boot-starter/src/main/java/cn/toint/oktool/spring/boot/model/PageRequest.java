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

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Toint
 * @date 2025/7/12
 */
@Data
public class PageRequest {
    /**
     * 页码
     */
    @NotNull
    private Long pageNumber;

    /**
     * 每页数据数量量
     */
    @NotNull
    private Long pageSize;
}
