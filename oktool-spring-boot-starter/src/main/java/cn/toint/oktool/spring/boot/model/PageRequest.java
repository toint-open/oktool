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

import cn.toint.oktool.util.Assert;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.paginate.Page;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/7/12
 */
public class PageRequest {
    /**
     * 页码
     */
    @NotNull
    private Long pageNumber;

    /**
     * 每页数据数量
     */
    @NotNull
    private Long pageSize;

    public <T> Page<T> toFlexPage() {
        Assert.notNullParam(pageNumber, "pageNumber");
        Assert.notNullParam(pageSize, "pageSize");

        Assert.isTrue(pageNumber > 0, "页码错误");

        // mybatis-flex执行分页查询时, 当pageSize超过最大数量时不会报错, 而是会修改为默认的最大数量
        // 所以这里进行校验报错
        if (pageSize > FlexGlobalConfig.getDefaultConfig().getDefaultMaxPageSize()) {
            throw new IllegalArgumentException("每页显示的数据数量超过最大限制");
        }

        return new Page<>(pageNumber, pageSize);
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PageRequest that = (PageRequest) o;
        return Objects.equals(pageNumber, that.pageNumber) && Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize);
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
