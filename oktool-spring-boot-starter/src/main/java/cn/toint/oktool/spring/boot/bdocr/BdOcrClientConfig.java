package cn.toint.oktool.spring.boot.bdocr;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

/**
 * 百度ocr配置
 *
 * @author Toint
 * @dete 2025/9/7
 */
public class BdOcrClientConfig {
    @NotBlank
    private String apiKey;

    @NotBlank
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BdOcrClientConfig that = (BdOcrClientConfig) o;
        return Objects.equals(apiKey, that.apiKey) && Objects.equals(secretKey, that.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, secretKey);
    }

    @Override
    public String toString() {
        return "BdOcrClientConfig{" +
                "apiKey='" + apiKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
