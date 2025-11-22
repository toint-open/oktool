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

package cn.toint.oktool.spring.boot.flexcustomizer;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/10/14
 */
@ConfigurationProperties("oktool.mybatis-flex")
public class FlexCustomizerProperties {
    /**
     * 每页显示的数据数量最大限制
     */
    private int maxPageSize = 200;

    /**
     * 控制台打印SQL日志(生产环境不建议开启)
     */
    private boolean printSql = false;

    public int getMaxPageSize() {
        return maxPageSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize;
    }

    public boolean isPrintSql() {
        return printSql;
    }

    public void setPrintSql(boolean printSql) {
        this.printSql = printSql;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlexCustomizerProperties that = (FlexCustomizerProperties) o;
        return maxPageSize == that.maxPageSize && printSql == that.printSql;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxPageSize, printSql);
    }

    @Override
    public String toString() {
        return "FlexCustomizerProperties{" +
                "maxPageSize=" + maxPageSize +
                ", printSql=" + printSql +
                '}';
    }
}
