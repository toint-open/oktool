package cn.toint.oktool.spring.boot.bdocr.model;

import cn.toint.oktool.spring.boot.bdocr.exception.BdOcrLimitException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/9/9
 */
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
        // 错误码为空, 说明响应正常
        if (StringUtils.isBlank(errorCode)) {
            return (T) this;
        }

        if ("18".equals(errorCode)) {
            // QPS频率限制
            throw new BdOcrLimitException(errorMsg);
        } else {
            // 其他错误
            throw new RuntimeException(errorMsg);
        }
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseOcrResponse that = (BaseOcrResponse) o;
        return Objects.equals(logId, that.logId) && Objects.equals(errorCode, that.errorCode) && Objects.equals(errorMsg, that.errorMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, errorCode, errorMsg);
    }

    @Override
    public String toString() {
        return "BaseOcrResponse{" +
                "logId=" + logId +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
