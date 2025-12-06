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

package cn.toint.oktool.emqx;

import cn.hutool.v7.core.collection.CollUtil;
import cn.hutool.v7.core.net.url.UrlBuilder;
import cn.hutool.v7.core.net.url.UrlQuery;
import cn.hutool.v7.http.client.Request;
import cn.hutool.v7.http.client.Response;
import cn.hutool.v7.http.meta.Method;
import cn.toint.oktool.emqx.model.EmqxClientConfig;
import cn.toint.oktool.emqx.model.ClientInfo;
import cn.toint.oktool.emqx.model.ListClientInfoReuqest;
import cn.toint.oktool.util.Assert;
import cn.toint.oktool.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Emqx方法封装
 *
 * @author Toint
 * @since 2025/11/27
 */
public class EmqxClient {

    private EmqxClientConfig config;

    public EmqxClient(EmqxClientConfig config) {
        Assert.validate(config);
        this.config = config;
    }

    /**
     * 获取指定客户端的信息
     */
    public List<ClientInfo> listClientInfo(ListClientInfoReuqest listClientInfoReuqest) {
        List<ClientInfo> clientInfo = new ArrayList<>();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            UrlQuery urlQuery = UrlQuery.of();

            List<String> clientIds = listClientInfoReuqest.getClientIds();
            if (CollUtil.isNotEmpty(clientIds)) {
                clientIds.forEach(clientId -> {
                    urlQuery.add("clientid", clientId);
                });
            }

            urlQuery.add("page", i + 1);
            urlQuery.add("limit", 10000);

            JsonNode response = request(Method.GET, "/api/v5/clients", urlQuery, null);
            Optional.ofNullable(response.get("data"))
                    .filter(JacksonUtil::isNotNull)
                    .map(jsonNode -> JacksonUtil.treeToValue(jsonNode, new TypeReference<List<ClientInfo>>() {
                    }))
                    .ifPresent(clientInfo::addAll);

            boolean hasNext = response.path("meta").path("hasnext").asBoolean();
            if (!hasNext) {
                break;
            }
        }

        return clientInfo;
    }

    private JsonNode request(Method method, String urlPath, UrlQuery urlQuery, String body) {
        Assert.notNullParam(method, "method");
        Assert.notBlankParam(urlPath, "urlPath");

        UrlBuilder urlBuilder = UrlBuilder.ofHttp(config.getHost())
                .addPath(urlPath);

        if (urlQuery != null) {
            urlBuilder.setQuery(urlQuery);
        }

        Request request = Request.of(urlBuilder)
                .method(method)
                .basicAuth(config.getKey(), config.getSecret());

        if (body != null) {
            request.body(body);
        }

        try (Response response = request.send()) {
            Assert.isTrue(response.isOk(), "http response status: " + response.getStatus());
            String bodyStr = response.bodyStr();
            Assert.notBlank(bodyStr, "http response body is blank");
            return JacksonUtil.readTree(bodyStr);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
