package cn.toint.oktool.spring.boot.bdocr;

import cn.hutool.v7.core.map.MapUtil;
import cn.hutool.v7.core.net.url.UrlBuilder;
import cn.hutool.v7.http.HttpUtil;
import cn.hutool.v7.http.client.Request;
import cn.hutool.v7.http.client.Response;
import cn.hutool.v7.http.client.body.HttpBody;
import cn.hutool.v7.http.client.body.UrlEncodedFormBody;
import cn.hutool.v7.http.meta.Method;
import cn.toint.oktool.spring.boot.bdocr.model.*;
import cn.toint.oktool.spring.boot.cache.Cache;
import cn.toint.oktool.util.Assert;
import cn.toint.oktool.util.JacksonUtil;
import cn.toint.oktool.util.KeyBuilderUtil;
import cn.toint.oktool.util.RetryUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

/**
 * 百度ocr
 *
 * @author Toint
 * @dete 2025/9/7
 */
public class BdOcrClient {

    private BdOcrClientConfig bdOcrClientConfig;
    private Cache cache;
    private final KeyBuilderUtil cacheKeyBuilder = KeyBuilderUtil.of("bdOcrToken");

    public BdOcrClient(BdOcrClientConfig bdOcrClientConfig, Cache cache) {
        Assert.validate(bdOcrClientConfig);
        Assert.notNull(cache, "cache must not be null");

        this.bdOcrClientConfig = bdOcrClientConfig;
        this.cache = cache;
    }

    /**
     * 获取token, 如果token失效, 会自动刷新token
     *
     * @return token
     */
    public String getToken() {
        // 先查询缓存是否存在
        String cacheToken = cache.get(cacheKeyBuilder.build(bdOcrClientConfig.getApiKey()));
        if (StringUtils.isNotBlank(cacheToken)) return cacheToken;

        // 未命中缓存, 执行请求获取token
        // 构建请求URL
        String url = UrlBuilder.ofHttp("https://aip.baidubce.com/oauth/2.0/token")
                .addQuery("grant_type", "client_credentials")
                .addQuery("client_id", bdOcrClientConfig.getApiKey())
                .addQuery("client_secret", bdOcrClientConfig.getSecretKey())
                .build();

        // 获取token
        TokenResponse tokenResponse = RetryUtil.execute(() -> {
            try (Response response = HttpUtil.createGet(url).send()) {
                String bodyStr = response.bodyStr();
                Assert.notBlank(bodyStr, "baidu error, body is null");
                TokenResponse tokenResponseOrigin = JacksonUtil.readValue(bodyStr, TokenResponse.class);
                Assert.notBlank(tokenResponseOrigin.getAccessToken(), "baidu error, access token is null");
                Assert.notNull(tokenResponseOrigin.getExpires_in(), "baidu error, expires_in is null");
                return tokenResponseOrigin;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });

        // 缓存token
        cache.put(cacheKeyBuilder.build(bdOcrClientConfig.getApiKey()),
                tokenResponse.getAccessToken(),
                Duration.ofSeconds(tokenResponse.getExpires_in()));

        return tokenResponse.getAccessToken();
    }

    /**
     * 执行请求, 内置自动获取鉴权与重试机制
     *
     * @param method 请求方法
     * @param url 服务地址
     * @param body 请求体
     * @return 响应体
     */
    private String request(Method method, String url, HttpBody body) {
        Assert.notNull(method, "method must not be null");
        Assert.notBlank(url, "url must not be blank");
        Assert.notNull(body, "body must not be null");

        Request request = Request.of(url)
                .method(method)
                .body(body);

        return request(request);
    }

    /**
     * 执行请求, 内置自动获取鉴权与重试机制
     *
     * @param request 完整的请求信息
     * @return 响应体
     */
    private String request(Request request) {
        Assert.notNull(request, "request must not be null");

        String token = getToken();
        request.url().addQuery("access_token", token);

        return RetryUtil.execute(() -> {
            try (Response response = request.send()) {
                String bodyStr = response.bodyStr();
                Assert.notBlank(bodyStr, "baidu error, body is null");
                return bodyStr;
            }
        });
    }

    /**
     * 智能财务发票
     * <br>
     * 支持财务场景中13种常见票据的分类及结构化识别，包括增值税发票、卷票、机打发票、定额发票、火车票（含电子发票铁路电子客票）、出租车票、网约车行程单、飞机行程单（含电子发票航空电子客票行程单）、汽车票、过路过桥费、船票、机动车/二手车销售发票（含电子发票机动车/二手车销售统一发票）。
     * 支持多张不同种类票据在同一张图片上的混贴场景，可返回每张票据的位置、种类及票面信息的结构化识别结果。
     * <br>
     * 文档地址: {@code https://cloud.baidu.com/doc/OCR/s/7ktb8md0j}
     *
     * @param request req
     * @return 识别结果
     */
    public MultipleInvoiceResponse multipleInvoice(MultipleInvoiceRequest request) {
        // image/url/pdf_file/ofd_file, 4选1
        if (StringUtils.isAllBlank(request.getUrl(), request.getImage(), request.getPdfFile(), request.getOfdFile())) {
            throw new RuntimeException("image/url/pdf_file/ofd_file, 4选1");
        }

        // 识别发票
        Map<String, Object> bodyMap = JacksonUtil.convertValue(request, new TypeReference<>() {
        });
        MapUtil.removeNullValue(bodyMap);
        UrlEncodedFormBody urlEncodedFormBody = UrlEncodedFormBody.of(bodyMap, StandardCharsets.UTF_8);
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/multiple_invoice";
        String responseStr = request(Method.POST, url, urlEncodedFormBody);
        return JacksonUtil.readValue(responseStr, MultipleInvoiceResponse.class);
    }

    public VatInvoiceResponse vatInvoice(VatInvoiceRequest request) {
        // image/url/pdf_file/ofd_file, 4选1
        if (StringUtils.isAllBlank(request.getUrl(), request.getImage(), request.getPdfFile(), request.getOfdFile())) {
            throw new RuntimeException("image/url/pdf_file/ofd_file, 4选1");
        }

        // 识别发票
        Map<String, Object> bodyMap = JacksonUtil.convertValue(request, new TypeReference<>() {
        });
        MapUtil.removeNullValue(bodyMap);
        UrlEncodedFormBody urlEncodedFormBody = UrlEncodedFormBody.of(bodyMap, StandardCharsets.UTF_8);
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice";
        String responseStr = request(Method.POST, url, urlEncodedFormBody);
        return JacksonUtil.readValue(responseStr, VatInvoiceResponse.class);
    }
}
