package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Toint
 * @dete 2025/9/8
 */
@Data
public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * Access Token的有效期(秒为单位，有效期30天)；
     */
    @JsonProperty("expires_in")
    private Long expires_in;
}
