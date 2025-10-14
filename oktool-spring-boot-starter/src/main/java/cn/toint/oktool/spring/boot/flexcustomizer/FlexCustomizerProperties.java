package cn.toint.oktool.spring.boot.flexcustomizer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Toint
 * @dete 2025/10/14
 */
@Data
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
}
