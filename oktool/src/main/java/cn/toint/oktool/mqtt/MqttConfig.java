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

import com.hivemq.client.mqtt.lifecycle.MqttClientConnectedListener;
import com.hivemq.client.mqtt.lifecycle.MqttClientDisconnectedListener;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/11/23
 */
public class MqttConfig {
    /**
     * 客户端唯一标识符, 同一个identifier同时只能有一个连接, 新连接会踢掉旧连接
     */
    @NotBlank
    private String identifier;

    /**
     * 服务器地址
     */
    @NotBlank
    private String serverHost;

    /**
     * 服务器端口
     */
    private int serverPort = 1883;

    /**
     * 用户名
     */
    @NotNull
    private String username;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * 连接成功监听器
     */
    @NotNull
    private MqttClientConnectedListener connectedListener = _ -> {
    };

    /**
     * 连接断开监听器
     */
    @NotNull
    private MqttClientDisconnectedListener disconnectedListener = _ -> {
    };


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MqttConfig that = (MqttConfig) o;
        return serverPort == that.serverPort && Objects.equals(identifier, that.identifier) && Objects.equals(serverHost, that.serverHost) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(connectedListener, that.connectedListener) && Objects.equals(disconnectedListener, that.disconnectedListener);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, serverHost, serverPort, username, password, connectedListener, disconnectedListener);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MqttClientConnectedListener getConnectedListener() {
        return connectedListener;
    }

    public void setConnectedListener(MqttClientConnectedListener connectedListener) {
        this.connectedListener = connectedListener;
    }

    public MqttClientDisconnectedListener getDisconnectedListener() {
        return disconnectedListener;
    }

    public void setDisconnectedListener(MqttClientDisconnectedListener disconnectedListener) {
        this.disconnectedListener = disconnectedListener;
    }

}
