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
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5PublishResult;
import com.hivemq.client.mqtt.mqtt5.message.subscribe.Mqtt5Subscribe;
import com.hivemq.client.mqtt.mqtt5.message.subscribe.suback.Mqtt5SubAck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * {@link MqttClient}封装
 *
 * @author Toint
 * @since 2025/12/5
 */
public class MqttTemplate implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(MqttTemplate.class);
    private final Mqtt5AsyncClient mqtt5AsyncClient;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    /**
     *
     * @param mqttConfig    客户端配置
     * @param connectConfig 连接配置(允许空值)
     */
    public MqttTemplate(MqttConfig mqttConfig, ConnectConfig connectConfig) {
        Assert.notNullParam(mqttConfig, "mqttConfig");
        mqtt5AsyncClient = MqttUtil.buildMqtt5AsyncClient(mqttConfig);
        MqttUtil.connect(mqtt5AsyncClient, connectConfig);
    }

    /**
     * 发布消息
     */
    CompletableFuture<Mqtt5PublishResult> publish(Mqtt5Publish publish) {
        Assert.notNullParam(publish, "publish");
        return mqtt5AsyncClient.publish(publish);
    }

    /**
     * 订阅主题
     *
     * @param subscribe             订阅信息
     * @param callback              消息到达时处理回调
     * @param manualAcknowledgement 手动确认
     */
    public CompletableFuture<Mqtt5SubAck> subscribe(Mqtt5Subscribe subscribe,
                                                    Consumer<Mqtt5Publish> callback,
                                                    boolean manualAcknowledgement) {
        Assert.notNullParam(subscribe, "subscribe");
        Assert.notNullParam(callback, "callback");

        // 回调报错会导致订阅中断后续无法再接收到新消息, 所以这里捕获回调中的异常
        Consumer<Mqtt5Publish> consumer = mqtt5Publish -> {
            try {
                callback.accept(mqtt5Publish);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        };

        return mqtt5AsyncClient.subscribe(subscribe, consumer, executorService, manualAcknowledgement);
    }

    public Mqtt5AsyncClient getMqtt5AsyncClient() {
        return mqtt5AsyncClient;
    }

    @Override
    public void close() throws Exception {
        if (mqtt5AsyncClient != null) {
            mqtt5AsyncClient.disconnect();
        }
    }
}
