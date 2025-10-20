package cn.toint.oktool.spring.boot.flexcustomizer;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.ConsoleMessageCollector;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Toint
 * @date 2025/10/13
 */
@AutoConfiguration
@ConditionalOnClass(MyBatisFlexCustomizer.class)
@ConditionalOnMissingBean(MyBatisFlexCustomizer.class)
@EnableConfigurationProperties(FlexCustomizerProperties.class)
public class FlexCustomizerAutoConfig implements MyBatisFlexCustomizer {

    @Resource
    private FlexCustomizerProperties flexCustomizerProperties;

    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        // 不打印启动信息
        globalConfig.setPrintBanner(false);

        // 每页显示的数据数量最大限制
        globalConfig.setDefaultMaxPageSize(flexCustomizerProperties.getMaxPageSize());

        // 打印日志
        if (flexCustomizerProperties.isPrintSql()) {
            AuditManager.setAuditEnable(true);
            AuditManager.setMessageCollector(new ConsoleMessageCollector());
        }
    }
}
