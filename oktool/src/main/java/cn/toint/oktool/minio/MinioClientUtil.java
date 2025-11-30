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

package cn.toint.oktool.minio;

import cn.hutool.v7.core.net.url.UrlBuilder;
import cn.toint.oktool.util.Assert;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.http.Method;
import io.minio.messages.ErrorResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Toint
 * @since 2025/12/1
 */
public class MinioClientUtil {

    /**
     * 修改为CDN加速地址
     *
     * @param method    请求方法, 只有GET方法才会使用CDN
     * @param cdn       CDN加速地址
     * @param objectUrl 对象原始地址
     * @return 如果采用的是GET方法且已提供了CDN, 则返回带有CDN加速地址的URL; 否则返回原始对象的URL。
     */
    public static String toCdnUrl(Method method, String cdn, String objectUrl) {
        // 只有下载才使用CDN, 上传使用源站
        if (method != Method.GET || StringUtils.isBlank(cdn) || StringUtils.isBlank(objectUrl)) {
            return objectUrl;
        }

        // 如果是下载签名链接, 检查替换CDN加速域名
        UrlBuilder presignedObjectUrlUrlBuilder = UrlBuilder.ofHttpWithoutEncode(objectUrl);
        return UrlBuilder.ofHttpWithoutEncode(cdn)
                .addPath(presignedObjectUrlUrlBuilder.getPathStr())
                .setQuery(presignedObjectUrlUrlBuilder.getQuery())
                .build();
    }

    /**
     * 对象是否存在
     *
     * @return 桶/对象不存在
     */
    public static boolean existObject(MinioClient minioClient, StatObjectArgs args) {
        Assert.notNullParam(minioClient, "minioClient");
        Assert.notNullParam(args, "args");

        try {
            minioClient.statObject(args);
            // 如果没有抛出异常，说明对象存在
            return true;
        } catch (ErrorResponseException e) {
            // 文件不存在会抛出ErrorResponseException, 错误码为: NoSuchKey/NoSuchBucket
            String code = Optional.ofNullable(e.errorResponse())
                    .map(ErrorResponse::code)
                    .orElse(null);
            if (Objects.equals("NoSuchKey", code) || Objects.equals("NoSuchBucket", code)) {
                return false;
            }
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
