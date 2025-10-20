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

import cn.hutool.v7.core.collection.CollUtil;
import cn.hutool.v7.core.io.file.FileUtil;
import cn.hutool.v7.core.io.resource.Resource;
import cn.hutool.v7.core.io.resource.ResourceUtil;
import cn.hutool.v7.core.text.StrUtil;
import cn.hutool.v7.core.text.split.SplitUtil;
import cn.toint.oktool.util.Assert;
import com.mybatisflex.core.datasource.DataSourceKey;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.util.SqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Sql脚本执行工具
 *
 * @author Toint
 * @date 2025/10/11
 */
public class SqlScriptUtil {

    private static final Logger log = LoggerFactory.getLogger(SqlScriptUtil.class);

    /**
     * 执行SQL脚本
     *
     * @param dataSourceKey 数据源key
     * @param sqlDir        脚本目录, 可以是绝对路径, 也可以是相对路径（相对ClassPath）
     * @param comparator    排序器(传空则不排序)
     * @param fileFilter    文件过滤器(传空则不过滤)
     * @return 脚本执行成功数量
     */
    public static int execute(String dataSourceKey,
                              String sqlDir,
                              Comparator<? super File> comparator,
                              FileFilter fileFilter) {
        Assert.notBlank(dataSourceKey, "dataSourceKey must not be null");
        Assert.notBlank(sqlDir, "dir must not be null");
        Assert.isTrue(FileUtil.isDirectory(sqlDir), "dir must be a directory");

        // 读取脚本文件
        Resource resource = ResourceUtil.getResource(sqlDir);
        List<File> files = Optional.ofNullable(resource.getUrl())
                .map(URL::getPath)
                .map(path -> FileUtil.loopFiles(path, fileFilter))
                .orElseThrow(() -> new RuntimeException("无法读取文件资源"));

        return execute(dataSourceKey, files, comparator);
    }

    /**
     * 执行SQL脚本
     *
     * @param dataSourceKey 数据源key
     * @param files         脚本文件
     * @param comparator    排序器(传空则不排序)
     * @return 脚本执行成功数量
     */
    public static int execute(String dataSourceKey, List<File> files, Comparator<? super File> comparator) {
        Assert.notBlank(dataSourceKey, "dataSourceKey must not be null");
        if (CollUtil.isEmpty(files)) return 0;

        // 根据文件名称进行排序, 集合索引越前越优先
        if (comparator != null) {
            files.sort(comparator);
        }

        // 解析所有sql
        List<String> sql = new ArrayList<>();
        files.forEach(item -> {
            if (!FileUtil.exists(item)) return;
            String fileStr = FileUtil.readUtf8String(item);
            if (StringUtils.isBlank(fileStr)) return;
            sql.addAll(SplitUtil.split(fileStr, ";"));
        });

        return execute(dataSourceKey, sql);
    }

    /**
     * 执行SQL
     *
     * @param dataSourceKey 数据源key
     * @param sql           要执行的SQL集合
     * @return SQL执行成功数量
     */
    public static int execute(String dataSourceKey, List<String> sql) {
        Assert.notBlank(dataSourceKey, "dataSourceKey must not be null");
        if (CollUtil.isEmpty(sql)) return 0;

        AtomicInteger successSize = new AtomicInteger();
        sql.stream()
                // 去除换行符
                .map(StrUtil::removeAllLineBreaks)
                .forEach(item -> {
                    if (StringUtils.isBlank(item)) return;
                    try {
                        DataSourceKey.use(dataSourceKey, () -> {
                            if (SqlUtil.toBool(Db.updateBySql(item))) {
                                successSize.incrementAndGet();
                            }
                        });
                    } catch (Exception e) {
                        log.error("SQL执行失败: {}", e.getMessage());
                    }
                });
        return successSize.get();
    }
}
