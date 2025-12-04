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

package cn.toint.oktool.mqtt;

import cn.toint.oktool.util.Assert;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import com.hivemq.client.mqtt.mqtt5.Mqtt5ClientBuilder;
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

/**
 * @author Toint
 * @since 2025/11/23
 */
public class MqttUtil {
    /**
     * 构建MQTT客户端, 返回的客户端是未连接状态, 需调用者调用{@link Mqtt5AsyncClient#connect()}进行连接
     */
    public static Mqtt5AsyncClient buildMqtt5AsyncClient(MqttConfig mqttConfig) {
        // 1. 校验参数
        Assert.validate(mqttConfig);

        // 2. 构建客户端
        Mqtt5AsyncClient mqtt5AsyncClient;

        // 基础
        Mqtt5ClientBuilder mqtt5ClientBuilder = Mqtt5Client.builder()
                .identifier(mqttConfig.getIdentifier())
                .serverHost(mqttConfig.getServerHost())
                .serverPort(mqttConfig.getServerPort());

        // 身份
        mqtt5ClientBuilder.simpleAuth()
                .username(mqttConfig.getUsername())
                .password(mqttConfig.getPassword().getBytes(StandardCharsets.UTF_8))
                .applySimpleAuth();

        // 其他
        mqtt5AsyncClient = mqtt5ClientBuilder.addConnectedListener(mqttConfig.getConnectedListener())
                .addDisconnectedListener(mqttConfig.getDisconnectedListener())
                .automaticReconnectWithDefaultConfig()
                .buildAsync();

        return mqtt5AsyncClient;
    }

    /**
     * 客户端连接
     *
     * @param mqtt5AsyncClient 客户端实例
     * @param connectConfig          连接对象(传空使用默认对象)
     * @return CompletableFuture
     */
    public static CompletableFuture<Mqtt5ConnAck> connect(Mqtt5AsyncClient mqtt5AsyncClient, ConnectConfig connectConfig) {
        Assert.notNullParam(mqtt5AsyncClient, "mqtt5AsyncClient");

        if (connectConfig == null) {
            connectConfig = new ConnectConfig();
        }

        return mqtt5AsyncClient.connectWith()
                .cleanStart(connectConfig.isCleanStart())
                .keepAlive(connectConfig.getKeepAlive())
                .sessionExpiryInterval(connectConfig.getSessionExpiryInterval())
                .send();
    }
}
