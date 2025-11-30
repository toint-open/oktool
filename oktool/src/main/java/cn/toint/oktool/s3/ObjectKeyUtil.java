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

package cn.toint.oktool.s3;

import cn.hutool.v7.core.regex.ReUtil;
import cn.hutool.v7.core.text.StrUtil;
import cn.hutool.v7.crypto.digest.DigestUtil;
import cn.toint.oktool.util.Assert;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author Toint
 * @since 2025/11/30
 */
public class ObjectKeyUtil {
    /**
     * 构建objectKey
     * <p>
     * objectKey规则: {基础路径}/{hash}/{文件ID}
     * hash: 为了避免热点问题, 使用fileId计算Md5结果的前4位
     *
     * @param basePath 基础路径, 空值表示根路径
     * @param fileId   文件ID(必填)
     */
    public static String buildObjectKey(String basePath, Long fileId) {
        Assert.notNullParam(fileId, "文件ID");

        if (StringUtils.isNotBlank(basePath)) {
            Assert.isTrue(ReUtil.isMatch("^[a-zA-Z0-9/_-]+$", basePath), "存储目录仅支持字母/数字/特殊符号(_-/)");
            Assert.isFalse(basePath.contains("//"), "存储目录不能包含连续的//");
            // 兼容/开头和结尾的存储目录
            basePath = StrUtil.removePrefix(basePath, "/");
            basePath = StrUtil.removeSuffix(basePath, "/");
        }

        // hash: 为了避免热点问题, 使用fileId计算Md5结果的前4位
        String hash = DigestUtil.md5Hex(fileId.toString()).substring(0, 4);

        // 存放根路径
        if (StringUtils.isBlank(basePath)) {
            return hash + "/" + fileId;
        } else {
            String objectKey = basePath + "/" + hash + "/" + fileId;
            Assert.isTrue(objectKey.getBytes(StandardCharsets.UTF_8).length <= 1024, "存储目录超过长度限制");
            return objectKey;
        }
    }
}
