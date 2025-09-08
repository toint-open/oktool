package cn.toint.oktool.spring.boot.bdocr;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 百度ocr配置
 *
 * @author Toint
 * @dete 2025/9/7
 */
@Data
public class BdOcrClientConfig {
    @NotBlank
    private String apiKey;
    @NotBlank
    private String secretKey;
}
