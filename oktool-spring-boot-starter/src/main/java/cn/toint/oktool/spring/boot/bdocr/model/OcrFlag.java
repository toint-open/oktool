package cn.toint.oktool.spring.boot.bdocr.model;

import lombok.Data;

/**
 * 文件识别标识
 *
 * @author Toint
 * @dete 2025/9/12
 */
@Data
public class OcrFlag {
    /**
     * 文件识别后, 可根据本字段寻找对应结果
     */
    private String ocrFlag;
}
