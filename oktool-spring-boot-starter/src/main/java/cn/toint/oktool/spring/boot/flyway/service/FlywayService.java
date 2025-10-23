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

package cn.toint.oktool.spring.boot.flyway.service;

import com.mybatisflex.core.datasource.FlexDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 数据库版本控制
 * <p>
 * 核心配置说明：
 * <ul>
 *   <li>关闭配置文件:（{@code flyway.enabled=false}）</li>
 *   <li>启用脚本的数据源：数据源名称</li>
 *   <li>迁移脚本路径：{@code classpath:db/migration/数据源名称/*.sql}</li>
 *   <li>历史表：默认创建 {@code flyway_schema_history} 以追踪执行记录</li>
 * </ul>
 * </p>
 *
 * <p>
 * Maven 依赖:
 * <pre>{@code
 * <dependency>
 *     <groupId>org.flywaydb</groupId>
 *     <artifactId>flyway-core</artifactId>
 * </dependency>
 * <dependency>
 *     <groupId>org.flywaydb</groupId>
 *     <artifactId>flyway-mysql</artifactId>
 * </dependency>
 * }</pre>
 * </p>
 *
 * @author Toint
 * @since 2025/10/11
 */
public class FlywayService {

    private static final Logger log = LoggerFactory.getLogger(FlywayService.class);

    /**
     * 数据源
     * <p>
     * 注意: 这里直接使用FlexDataSource类型是不管用的
     */
    @Resource
    private DataSource dataSource;

    @PostConstruct
    private void init() {
        Map<String, DataSource> dataSourceMap = ((FlexDataSource) dataSource).getDataSourceMap();
        dataSourceMap.forEach((dataSourceKey, dataSourceInstance) -> {
            if (dataSourceInstance == null) return;
            Flyway.configure()
                    .dataSource(dataSourceInstance)
                    // 全新空数据库填写: false
                    // 非全新空数据库填写: true
                    .baselineOnMigrate(true)
                    // 脚本目录, 脚本文件保持和数据源同名
                    .locations("db/migration/" + dataSourceKey)
                    .load()
                    // 开始进行数据库迁移
                    .migrate();
            log.info("数据库迁移任务初始化成功, dataSourceKey: {}", dataSourceKey);
        });
    }
}
