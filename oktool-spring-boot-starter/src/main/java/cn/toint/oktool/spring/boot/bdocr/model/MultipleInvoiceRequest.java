package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 优先级：image > url > pdf_file > ofd_file ，当image字段存在时，url、pdf_file、ofd_file 字段失效
 *
 * @author Toint
 * @dete 2025/9/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MultipleInvoiceRequest extends BaseOcrRequest {
    /**
     * 是否开启验真，默认为 false，即不开启，当为 true 时，返回匹配发票验真接口所需的6要素信息，具体返回信息详见末尾说明
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
}
