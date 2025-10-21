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

package cn.toint.oktool.oss.model;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/7/14
 */
public class CalculatePostSignatureResponse {
    /**
     * 上传地址
     */
    private String uploadUrl;

    private Form form;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalculatePostSignatureResponse that = (CalculatePostSignatureResponse) o;
        return Objects.equals(uploadUrl, that.uploadUrl) && Objects.equals(form, that.form);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uploadUrl, form);
    }

    @Override
    public String toString() {
        return "CalculatePostSignatureResponse{" +
                "uploadUrl='" + uploadUrl + '\'' +
                ", form=" + form +
                '}';
    }

    public static class Form {
        /**
         * 签名
         */
        private String signature;

        private String ossAccessKeyId;

        /**
         * objectKey
         */
        private String key;

        /**
         * 策略
         */
        private String policy;

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getOssAccessKeyId() {
            return ossAccessKeyId;
        }

        public void setOssAccessKeyId(String ossAccessKeyId) {
            this.ossAccessKeyId = ossAccessKeyId;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPolicy() {
            return policy;
        }

        public void setPolicy(String policy) {
            this.policy = policy;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Form form = (Form) o;
            return Objects.equals(signature, form.signature) && Objects.equals(ossAccessKeyId, form.ossAccessKeyId) && Objects.equals(key, form.key) && Objects.equals(policy, form.policy);
        }

        @Override
        public int hashCode() {
            return Objects.hash(signature, ossAccessKeyId, key, policy);
        }

        @Override
        public String toString() {
            return "Form{" +
                    "signature='" + signature + '\'' +
                    ", ossAccessKeyId='" + ossAccessKeyId + '\'' +
                    ", key='" + key + '\'' +
                    ", policy='" + policy + '\'' +
                    '}';
        }
    }
}
