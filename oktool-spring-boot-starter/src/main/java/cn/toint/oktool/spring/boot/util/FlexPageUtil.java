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

package cn.toint.oktool.spring.boot.util;

import cn.toint.oktool.util.Assert;
import com.mybatisflex.core.paginate.Page;

import java.util.List;
import java.util.function.Function;

/**
 * @author Toint
 * @date 2025/7/19
 */
public class FlexPageUtil {

    /**
     * 批量转换数据
     *
     * @param page     分页对象
     * @param function 操作
     * @param <T>      初始类型
     * @param <R>      转换后的类型
     * @return 转换后的分页对线
     */
    public static <T, R> Page<R> map(Page<T> page, Function<List<T>, List<R>> function) {
        Assert.notNull(page, "page must not be null");
        Assert.notNull(function, "function must not be null");

        Page<R> newPage = new Page<>();
        newPage.setPageNumber(page.getPageNumber());
        newPage.setPageSize(page.getPageSize());
        newPage.setTotalPage(page.getTotalPage());
        newPage.setTotalRow(page.getTotalRow());

        List<R> newRecords = function.apply(page.getRecords());
        newPage.setRecords(newRecords);
        return newPage;
    }

}
