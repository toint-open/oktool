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

import org.apache.commons.lang3.StringUtils;
import cn.hutool.v7.core.codec.binary.Base64;
import cn.hutool.v7.core.date.DateUtil;
import cn.hutool.v7.crypto.SecureUtil;
import cn.hutool.v7.crypto.digest.mac.HMac;
import cn.hutool.v7.crypto.digest.mac.HmacAlgorithm;
import cn.hutool.v7.http.HttpUtil;
import cn.hutool.v7.http.client.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 飞书工具
 *
 * @author Toint
 * @date 2025/2/19
 */
public class FeiShuUtil {

    /**
     * 生成 webhook 签名
     *
     * @param secret    密钥
     * @param timestamp 当前时间(秒)
     * @return 签名
     */
    public static String createWebhookSign(final String secret, final Long timestamp) {
        Assert.notBlank(secret, "secret must not be blank");
        Assert.notNull(timestamp, "timestamp must not be null");

        final String stringToSign = timestamp + "\n" + secret;
        final HMac hmac = SecureUtil.hmac(HmacAlgorithm.HmacSHA256, stringToSign.getBytes(StandardCharsets.UTF_8));
        final byte[] signData = hmac.digest(new byte[]{});
        return Base64.encode(signData);
    }

    /**
     * 发送文本消息 - webhook
     *
     * @param url    url
     * @param secret secret
     * @param text   text
     * @throws RuntimeException 发送失败
     */
    public static void sendWebhookTextMsgAsync(final String url, final String secret, final String text) {
        Thread.startVirtualThread(() -> FeiShuUtil.sendWebhookTextMsg(url, secret, text));
    }

    /**
     * 发送文本消息 - webhook
     *
     * @param url    url
     * @param secret secret
     * @param text   text
     * @throws RuntimeException 发送失败
     */
    public static void sendWebhookTextMsg(final String url, final String secret, final String text) {
        Assert.notBlank(url, "url must not be blank");
        Assert.notBlank(secret, "secret must not be blank");
        Assert.notBlank(text, "text must not be blank");

        final long timestamp = DateUtil.currentSeconds();
        final String sign = FeiShuUtil.createWebhookSign(secret, timestamp);

        final Map<String, Object> body = new HashMap<>();
        body.put("timestamp", timestamp);
        body.put("sign", sign);
        body.put("msg_type", "text");
        body.put("content", Map.of("text", text));

        try (Response response = HttpUtil.createPost(url)
                .body(JacksonUtil.writeValueAsString(body))
                .send()) {
            final String bodyStr = response.bodyStr();

            Assert.isTrue(response.isOk() && StringUtils.isBlank(bodyStr),
                    "请求飞书失败, status: {}, body: {}", response.getStatus(), bodyStr);

            final int code = JacksonUtil.readTree(bodyStr)
                    .path("code")
                    .asInt(-1);
            Assert.isTrue(code == 0, bodyStr);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
