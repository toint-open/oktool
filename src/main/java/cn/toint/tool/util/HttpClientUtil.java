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

package cn.toint.tool.util;

import org.dromara.hutool.core.lang.Singleton;
import org.dromara.hutool.http.HttpGlobalConfig;
import org.dromara.hutool.http.client.engine.ClientEngine;
import org.dromara.hutool.http.client.engine.ClientEngineFactory;
import org.dromara.hutool.http.client.engine.jdk.JdkClientEngine;

import java.time.Duration;

/**
 * @author Toint
 * @date 2025/5/25
 */
public class HttpClientUtil {
    /**
     * 覆盖 hutool http 客户端
     *
     * @param clientEngineClass 客户端, 不传默认 {@link JdkClientEngine}
     * @param timeout           超时时间, 不传默认: 10s
     * @return 单例 http 客户端
     */
    public static ClientEngine clientEngine(Class<? extends ClientEngine> clientEngineClass, Duration timeout) {
        if (timeout == null) {
            timeout = Duration.ofSeconds(10);
        }

        if (clientEngineClass == null) {
            clientEngineClass = JdkClientEngine.class;
        }

        HttpGlobalConfig.setTimeout(Math.toIntExact(timeout.toSeconds()));

        // 覆盖默认客户端
        final ClientEngine clientEngine = ClientEngineFactory.createEngine(clientEngineClass.getName());
        Singleton.put(ClientEngine.class.getName(), clientEngine);
        return clientEngine;
    }

    /**
     * 获取当前 http 客户端
     *
     * @return ClientEngine 单例 http 客户端
     */
    public static ClientEngine clientEngine() {
        return ClientEngineFactory.getEngine();
    }
}
