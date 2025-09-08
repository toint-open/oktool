package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Toint
 * @dete 2025/9/8
 */
@Data
public class SingleInvoiceResponse {
    /**
     * 用于定位问题
     */
    @JsonProperty("log_id")
    private Long logId;

    /**
     * 识别结果数，表示words_result的元素个数
     */
    @JsonProperty("words_result_num")
    private Integer wordsResultNum;

    /**
     * 识别结果
     */
    @JsonProperty("words_result")
    private MultipleInvoiceResponse.WordsResult wordsResult;
}
