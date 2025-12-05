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

/**
 * {@link MinioClient}封装
 *
 * @author Toint
 * @since 2025/12/1
 */
public class MinioTemplate implements AutoCloseable {

    private final MinioClient client;

    public MinioTemplate(MinioClient client) {
        Assert.notNullParam(client, "client");
        this.client = client;
    }

    public MinioClient client() {
        return client;
    }

    /**
     * 对象是否存在
     *
     * @param args args
     * @return 对象是否存在
     */
    public boolean existObject(StatObjectArgs args) {
        return MinioUtil.existObject(client, args);
    }

    @Override
    public void close() throws Exception {
        client.close();
    }
}
