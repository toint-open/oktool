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

package cn.toint.oktool.oss;

import cn.toint.oktool.oss.model.CalculatePostSignatureRequest;
import cn.toint.oktool.oss.model.CalculatePostSignatureResponse;
import cn.toint.oktool.util.Assert;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.dromara.hutool.core.codec.binary.Base64;
import org.dromara.hutool.core.date.DateTime;
import org.dromara.hutool.core.date.DateUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.extra.validation.ValidationUtil;

/**
 * @author Toint
 * @date 2025/7/14
 */
public class OssClient {

    private OssClientConfig config;

    private OSSClient oss;

    public OssClient() {
    }

    public OssClient(OSSClient oss) {
        this.oss = oss;
    }

    public static OssClient of(OssClientConfig config) {
        Assert.notNull(config, "阿里云OSS配置信息不能为空");
        Assert.validate(config);

        String accessKeyId = config.getAccessKeyId();
        String secretAccessKey = config.getSecretAccessKey();
        String endpoint = config.getEndpoint();
        String region = config.getRegion();

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        clientBuilderConfiguration.setRequestTimeout(config.getRequestTimeout());
        clientBuilderConfiguration.setConnectionTimeout(config.getConnectionTimeout());
        clientBuilderConfiguration.setConnectionRequestTimeout(config.getConnectionRequestTimeout());

        final DefaultCredentialProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, secretAccessKey);

        OSSClient oss = new OSSClient(endpoint, credentialsProvider, clientBuilderConfiguration);
        oss.setRegion(region);

        return new OssClient(oss);
    }

    /**
     * 计算上传签名, 用于前端上传文件
     */
    public CalculatePostSignatureResponse calculatePostSignature(CalculatePostSignatureRequest request) {
        Assert.notNull(request, "请求参数不能为空");
        Assert.validate(request);

        final String bucketName = request.getBucketName();
        final String accessKeyId = config.getAccessKeyId();
        String objectKey = request.getObjectKey();
        String endpoint = request.getEndpoint();

        Long minFileSize = request.getMinFileSize();
        Long maxFileSize = request.getMaxFileSize();
        Assert.isTrue(minFileSize != null && minFileSize > 0L, "文件最小值不合法");
        Assert.isTrue(maxFileSize != null && maxFileSize > 0L, "文件最大值不合法");

        // 过期时间
        long seconds = request.getTimeout().toSeconds();
        final DateTime expiration = DateUtil.offsetSecond(DateUtil.now(), Math.toIntExact(seconds));

        // 上传策略
        PolicyConditions policyConditions = new PolicyConditions();
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, minFileSize, maxFileSize);
        policyConditions.addConditionItem(MatchMode.Exact, PolicyConditions.COND_KEY, objectKey);
        policyConditions.addConditionItem(MatchMode.Exact, "bucket", bucketName);
        final String postPolicy = oss.generatePostPolicy(expiration, policyConditions);
        final String postPolicyBase64 = Base64.encode(postPolicy);

        // 上传签名
        final String postSignature = oss.calculatePostSignature(postPolicy);

        // 构造上传地址
        // 应使用阿里云原生的域名上传, 避免cdn域名存在一些上传限制, 比如阿里云esa或者CDN会有最大上传限制
        if (!request.isInternal()) {
            endpoint = StrUtil.removeAll(endpoint, "-internal");
        }
        String uploadUrl = StrUtil.format("https://{}.{}", bucketName, endpoint);

        // 上传签名返回对象, 客户端拿这个信息去上传文件
        final CalculatePostSignatureResponse calculatePostSignatureResponse = new CalculatePostSignatureResponse();
        calculatePostSignatureResponse.setSignature(postSignature);
        calculatePostSignatureResponse.setOssAccessKeyId(accessKeyId);
        calculatePostSignatureResponse.setUploadUrl(uploadUrl);
        calculatePostSignatureResponse.setKey(objectKey);
        calculatePostSignatureResponse.setPolicy(postPolicyBase64);
        ValidationUtil.validateAndThrowFirst(calculatePostSignatureResponse);
        return calculatePostSignatureResponse;
    }
}
