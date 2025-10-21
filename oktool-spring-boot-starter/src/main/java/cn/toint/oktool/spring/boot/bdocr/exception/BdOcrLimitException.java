package cn.toint.oktool.spring.boot.bdocr.exception;

/**
 * 百度OCR接口QPS超出限制
 *
 * @author Toint
 * @since 2025/10/21
 */
public class BdOcrLimitException extends RuntimeException{
    public BdOcrLimitException(String message) {
        super(message);
    }
}
