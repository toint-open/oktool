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
import org.dromara.hutool.http.client.ClientConfig;
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
     * 创建 http 客户端
     *
     * @param clientEngineClass 客户端, 允许 null, 默认: {@link JdkClientEngine}
     * @param clientConfig      客户端配置, 允许 null, 默认超时时间: 10s
     * @return 单例 http 客户端
     */
    @SuppressWarnings("resource")
    public static ClientEngine clientEngine(Class<? extends ClientEngine> clientEngineClass, ClientConfig clientConfig) {
        // 全局超时时间
        if (HttpGlobalConfig.getTimeout() <= 0) {
            HttpGlobalConfig.setTimeout(Math.toIntExact(Duration.ofSeconds(10).toMillis()));
        }

        if (clientConfig == null) {
            clientConfig = ClientConfig.of();
        }

        if (clientConfig.getConnectionTimeout() <= 0) {
            clientConfig.setConnectionTimeout(HttpGlobalConfig.getTimeout());
        }

        if (clientConfig.getReadTimeout() <= 0) {
            clientConfig.setReadTimeout(HttpGlobalConfig.getTimeout());
        }

        if (clientEngineClass == null) {
            clientEngineClass = JdkClientEngine.class;
        }

        return ClientEngineFactory.createEngine(clientEngineClass.getName()).init(clientConfig);
    }

    /**
     * 获取单例 http 客户端
     *
     * @return ClientEngine 单例 http 客户端
     */
    public static ClientEngine clientEngine() {
        return Singleton.get(ClientEngine.class.getName(), () -> HttpClientUtil.clientEngine(null, null));
    }

    /**
     * 初始化全局配置
     *
     * @param clientEngineClass 客户端, 允许 null, 默认 {@link JdkClientEngine}
     * @param clientConfig      客户端配置, 默认超时时间: 10s
     * @param globalTimeout     全局超时时间, 默认: 10s
     */
    public static void initGlobalConfig(final Class<? extends ClientEngine> clientEngineClass, final ClientConfig clientConfig, Duration globalTimeout) {
        // 全局超时时间
        if (globalTimeout == null || globalTimeout.toSeconds() <= 0) {
            globalTimeout = Duration.ofSeconds(10);
        }
        HttpGlobalConfig.setTimeout(Math.toIntExact(globalTimeout.toMillis()));

        // 创建并覆盖全局单例客户端
        final ClientEngine clientEngine = HttpClientUtil.clientEngine(clientEngineClass, clientConfig);
        Singleton.put(ClientEngine.class.getName(), clientEngine);
    }
}