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

import java.util.List;
import java.util.Objects;

/**
 * @author Toint
 * @since 2025/11/27
 */
public class ListClientInfoDto {
    /**
     * 要查询的客户端ID列表
     */
    private List<String> clientIds;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ListClientInfoDto that = (ListClientInfoDto) o;
        return Objects.equals(clientIds, that.clientIds);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clientIds);
    }

    public List<String> getClientIds() {
        return clientIds;
    }

    public void setClientIds(List<String> clientIds) {
        this.clientIds = clientIds;
    }
}
