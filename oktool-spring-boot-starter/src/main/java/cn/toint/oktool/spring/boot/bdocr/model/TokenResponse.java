package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author Toint
 * @date 2025/9/8
 */
public class TokenResponse {
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    @JsonProperty("access_token")
    private String accessToken;

    /**
     * Access Token的有效期(秒为单位，有效期30天)；
     */
    @JsonProperty("expires_in")
    private Long expires_in;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TokenResponse that = (TokenResponse) o;
        return Objects.equals(accessToken, that.accessToken) && Objects.equals(expires_in, that.expires_in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, expires_in);
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
