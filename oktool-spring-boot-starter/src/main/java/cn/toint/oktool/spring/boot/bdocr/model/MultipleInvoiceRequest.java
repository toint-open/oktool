package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 优先级：image > url > pdf_file > ofd_file ，当image字段存在时，url、pdf_file、ofd_file 字段失效
 *
 * @author Toint
 * @date 2025/9/7
 */
public class MultipleInvoiceRequest extends BaseOcrRequest {
    /**
     * 是否开启验真，默认为 false，即不开启，当为 true 时，只会返回匹配发票验真接口所需的6要素信息, 不会返回原有识别结果.
     */
    @JsonProperty("verify_parameter")
    private boolean verifyParameter;

    /**
     * 是否返回字段置信度，默认为 false ，即不返回
     */
    private boolean probability;

    /**
     * 是否返回字段位置坐标，默认为 false，即不返回
     */
    private boolean location;

    public MultipleInvoiceRequest(byte[] bytes) {
        super(bytes);
    }

    public MultipleInvoiceRequest(String url) {
        super(url);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MultipleInvoiceRequest that = (MultipleInvoiceRequest) o;
        return verifyParameter == that.verifyParameter && probability == that.probability && location == that.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), verifyParameter, probability, location);
    }

    @Override
    public String toString() {
        return "MultipleInvoiceRequest{" +
                "verifyParameter=" + verifyParameter +
                ", probability=" + probability +
                ", location=" + location +
                "} " + super.toString();
    }

    public boolean isVerifyParameter() {
        return verifyParameter;
    }

    public void setVerifyParameter(boolean verifyParameter) {
        this.verifyParameter = verifyParameter;
    }

    public boolean isProbability() {
        return probability;
    }

    public void setProbability(boolean probability) {
        this.probability = probability;
    }

    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean location) {
        this.location = location;
    }
}
