package cn.toint.oktool.spring.boot.bdocr.model;

import cn.hutool.v7.core.util.EnumUtil;
import cn.toint.oktool.spring.boot.bdocr.util.MultipleInvoiceUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 识别结果
 *
 * @author Toint
 * @dete 2025/9/8
 */
@Data
public class MultipleInvoiceResponse {
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
    private List<WordsResult> wordsResult;


    @Data
    public static class WordsResult {
        /**
         * 每一张票据的种类
         */
        private String type;

        /**
         * 单张票据的识别结果
         */
        private MultipleInvoiceResult result;

        public TypeEnum typeEnum() {
            if (StringUtils.isBlank(type)) return null;
            return EnumUtil.getBy(TypeEnum::getCode, type);
        }
    }

    public MultipleInvoiceVo toVo() {
        return MultipleInvoiceUtil.convert(this);
    }
}
