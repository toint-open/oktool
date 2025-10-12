package cn.toint.oktool.spring.boot.flexcustomizer;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * @author Toint
 * @dete 2025/10/13
 */
@AutoConfiguration
@ConditionalOnClass(MyBatisFlexCustomizer.class)
@ConditionalOnMissingBean(MyBatisFlexCustomizer.class)
@Slf4j
public class FlexCustomizerAutoConfig implements MyBatisFlexCustomizer {
    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        // 不打印启动信息
        globalConfig.setPrintBanner(false);
        log.info("mybatis-flex全局配置: globalConfig.isPrintBanner()={}", globalConfig.isPrintBanner());
        // 默认最大分页数量
        globalConfig.setDefaultMaxPageSize(200);
        log.info("mybatis-flex全局配置: globalConfig.getDefaultMaxPageSize()={}", globalConfig.getDefaultMaxPageSize());
    }
}
