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

package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/9/8
 */
public class VatInvoiceRequest extends BaseOcrRequest {
    /**
     * 进行识别的增值税发票类型，默认为 normal，可缺省
     * - normal：可识别增值税普票、专票、电子发票
     * - roll：可识别增值税卷票
     */
    private String type = "normal";

    /**
     * 是否开启印章判断功能，并返回印章内容的识别结果
     * - true：开启
     * - false：不开启
     */
    @JsonProperty("seal_tag")
    private boolean sealTag;

    public VatInvoiceRequest(byte[] bytes) {
        super(bytes);
    }

    public VatInvoiceRequest(String url) {
        super(url);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSealTag() {
        return sealTag;
    }

    public void setSealTag(boolean sealTag) {
        this.sealTag = sealTag;
    }

    @Override
    public String toString() {
        return "VatInvoiceRequest{" +
                "type='" + type + '\'' +
                ", sealTag=" + sealTag +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VatInvoiceRequest that = (VatInvoiceRequest) o;
        return sealTag == that.sealTag && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, sealTag);
    }
}
