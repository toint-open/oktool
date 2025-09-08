package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Toint
 * @dete 2025/9/9
 */
@Data
public class BaseOcrResponse {
    /**
     * 用于定位问题
     */
    @JsonProperty("log_id")
    private Long logId;

    /**
     * 错误信息
     */
    @JsonProperty("error_code")
    private String errorCode;

    /**
     * 错误信息
     */
    @JsonProperty("error_msg")
    private String errorMsg;

    @SuppressWarnings("unchecked")
    public <T> T checkStatus() {
        if (StringUtils.isNotBlank(errorCode)) {
            throw new RuntimeException(errorMsg);
        }
        return (T) this;
    }
}
