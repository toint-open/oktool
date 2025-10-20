package cn.toint.oktool.spring.boot.flexcustomizer;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

/**
 * @author Toint
 * @date 2025/10/14
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
