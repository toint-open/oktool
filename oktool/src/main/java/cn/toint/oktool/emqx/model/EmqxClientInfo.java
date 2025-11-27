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

package cn.toint.oktool.emqx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/11/27
 */
public class EmqxClientInfo {
    @JsonProperty("clientid")
    private String clientId;

    @JsonProperty("connected")
    private Boolean connected;

    @JsonProperty("connected_at")
    private String connectedAt;

    @JsonProperty("disconnected_at")
    private String disconnectedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmqxClientInfo that = (EmqxClientInfo) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(connected, that.connected) && Objects.equals(connectedAt, that.connectedAt) && Objects.equals(disconnectedAt, that.disconnectedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, connected, connectedAt, disconnectedAt);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public String getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(String connectedAt) {
        this.connectedAt = connectedAt;
    }

    public String getDisconnectedAt() {
        return disconnectedAt;
    }

    public void setDisconnectedAt(String disconnectedAt) {
        this.disconnectedAt = disconnectedAt;
    }
}
