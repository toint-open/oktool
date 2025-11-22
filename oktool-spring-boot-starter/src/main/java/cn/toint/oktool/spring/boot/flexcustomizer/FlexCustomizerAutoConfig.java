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
 * MyBatisFlex自定义配置
 *
 * @author Toint
 * @since 2025/10/13
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
