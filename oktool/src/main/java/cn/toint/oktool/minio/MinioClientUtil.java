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

import cn.toint.oktool.util.Assert;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.messages.ErrorResponse;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Toint
 * @since 2025/12/1
 */
public class MinioClientUtil {

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
