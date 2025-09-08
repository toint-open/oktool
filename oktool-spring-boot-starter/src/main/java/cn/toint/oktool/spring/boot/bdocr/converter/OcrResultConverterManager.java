package cn.toint.oktool.spring.boot.bdocr.converter;

import cn.toint.oktool.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Toint
 * @dete 2025/9/8
 */
public class OcrResultConverterManager {

    private static final Map<String, OcrResultConverter<?>> converterMap = new HashMap<>();

    static {
        // 增值税发票转换器
        VatInvoiceConverter vatInvoiceConverter = new VatInvoiceConverter();
        register(vatInvoiceConverter.getType(), vatInvoiceConverter);
    }

    public static void register(String converterType, OcrResultConverter<?> converter) {
        Assert.notBlank(converterType, "converterType must not be blank");
        Assert.notNull(converter, "converter must not be null");
        converterMap.put(converterType, converter);
    }

    public static OcrResultConverter<?> get(String converterType) {
        return converterMap.get(converterType);
    }
}
