package cn.toint.oktool.spring.boot.bdocr.converter;

import cn.toint.oktool.spring.boot.bdocr.model.MultipleInvoiceResult;

/**
 * 识别结果转换器
 *
 * @author Toint
 * @dete 2025/9/8
 */
public interface OcrResultConverter<R> {
    /**
     * 识别结果执行转换
     *
     * @param value 百度原始数据结构
     * @return 转换后的数据结构
     */
    R convert(MultipleInvoiceResult value);

    String getType();
}
