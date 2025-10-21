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

package cn.toint.oktool.util;

import cn.hutool.v7.core.lang.Singleton;
import cn.hutool.v7.http.HttpGlobalConfig;
import cn.hutool.v7.http.client.ClientConfig;
import cn.hutool.v7.http.client.engine.ClientEngine;
import cn.hutool.v7.http.client.engine.ClientEngineFactory;
import cn.hutool.v7.http.client.engine.jdk.JdkClientEngine;
import cn.hutool.v7.http.client.engine.okhttp.OkHttpEngine;

import java.time.Duration;

/**
 * @author Toint
 * @since 2025/5/25
 */
public class HttpClientUtil {

    private static final Duration DEFAULT_GLOBAL_TIMEOUT = Duration.ofSeconds(30);
    private static final Class<? extends ClientEngine> DEFAULT_CLIENT_ENGINE = OkHttpEngine.class;

    /**
     * 创建 http 客户端
     *
     * @param clientEngineClass 客户端(可选), 默认: {@link HttpClientUtil#DEFAULT_CLIENT_ENGINE}
     * @param clientConfig      客户端配置(可选), 默认超时时间: {@link HttpClientUtil#DEFAULT_GLOBAL_TIMEOUT}
     * @return 单例 http 客户端
     */
    @SuppressWarnings("resource")
    public static ClientEngine clientEngine(Class<? extends ClientEngine> clientEngineClass, ClientConfig clientConfig) {
        // 全局超时时间
        if (HttpGlobalConfig.getTimeout() <= 0) {
            HttpGlobalConfig.setTimeout((int) DEFAULT_GLOBAL_TIMEOUT.toMillis());
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
            clientEngineClass = DEFAULT_CLIENT_ENGINE;
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
     * <li>客户端默认超时时间: {@link HttpClientUtil#DEFAULT_GLOBAL_TIMEOUT}</li>
     * <li>全局超时时间: {@link HttpClientUtil#DEFAULT_GLOBAL_TIMEOUT}</li>
     * <li>客户端类型: {@link HttpClientUtil#DEFAULT_CLIENT_ENGINE}</li>
     */
    public static void initGlobalConfig() {
        initGlobalConfig(null);
    }

    /**
     * 初始化全局配置
     *
     * <li>客户端默认超时时间: {@link HttpClientUtil#DEFAULT_GLOBAL_TIMEOUT}</li>
     * <li>全局超时时间: {@link HttpClientUtil#DEFAULT_GLOBAL_TIMEOUT}</li>
     * <li>客户端类型: {@link HttpClientUtil#DEFAULT_CLIENT_ENGINE}</li>
     *
     * @param clientEngineClass 客户端(可选), 默认 {@link OkHttpEngine}
     */
    public static void initGlobalConfig(Class<? extends ClientEngine> clientEngineClass) {
        initGlobalConfig(clientEngineClass, null, null);
    }

    /**
     * 初始化全局配置
     *
     * @param clientEngineClass 客户端(可选), 允许 null, 默认 {@link JdkClientEngine}
     * @param clientConfig      客户端配置(可选), 默认超时时间: 30s
     * @param globalTimeout     全局超时时间(可选), 默认: 30s
     */
    public static void initGlobalConfig(Class<? extends ClientEngine> clientEngineClass, ClientConfig clientConfig, Duration globalTimeout) {
        // 创建并覆盖全局单例客户端
        ClientEngine clientEngine = HttpClientUtil.clientEngine(clientEngineClass, clientConfig);
        Singleton.put(ClientEngine.class.getName(), clientEngine);
    }
}