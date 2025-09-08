package cn.toint.oktool.spring.boot.bdocr.util;

import cn.hutool.v7.core.collection.CollUtil;
import cn.toint.oktool.spring.boot.bdocr.converter.OcrResultConverter;
import cn.toint.oktool.spring.boot.bdocr.converter.OcrResultConverterManager;
import cn.toint.oktool.spring.boot.bdocr.model.*;
import cn.toint.oktool.util.Assert;

import java.util.List;

/**
 * 智能财务票据识别工具
 *
 * @author Toint
 * @dete 2025/9/8
 */
public class MultipleInvoiceUtil {
    /**
     * 解析识别结果
     *
     * @param multipleInvoiceResponse 识别结果
     * @return 解析结果
     */
    public static MultipleInvoiceVo convert(MultipleInvoiceResponse multipleInvoiceResponse) {
        Assert.notNull(multipleInvoiceResponse, "multipleInvoiceResponse must not be null");

        // 视图对象
        MultipleInvoiceVo multipleInvoiceVo = new MultipleInvoiceVo();
        multipleInvoiceVo.setLogId(multipleInvoiceResponse.getLogId());

        // 识别结果数量, 如果为空则无需转换
        Integer wordsResultNum = multipleInvoiceResponse.getWordsResultNum();
        multipleInvoiceVo.setWordsResultNum(wordsResultNum);
        if (wordsResultNum == null || wordsResultNum < 1) {
            return multipleInvoiceVo;
        }

        // 转换每一个识别结果
        List<MultipleInvoiceResponse.WordsResult> wordsResult = multipleInvoiceResponse.getWordsResult();
        if (CollUtil.isEmpty(wordsResult)) return multipleInvoiceVo;
        wordsResult.forEach(item -> {
            // 识别结果类型
            TypeEnum typeEnum = item.typeEnum();
            if (typeEnum == null) return;

            // 转换识别结果
            MultipleInvoiceResult result = item.getResult();
            if (result == null) return;
            Object newResult = convert(typeEnum, result);
            if (newResult == null) return;
            multipleInvoiceVo.add(typeEnum, newResult);
        });

        return multipleInvoiceVo;
    }

    public static Object convert(TypeEnum typeEnum, MultipleInvoiceResult multipleInvoiceResult) {
        Assert.notNull(typeEnum, "typeEnum must not be null");
        Assert.notNull(multipleInvoiceResult, "multipleInvoiceResult must not be null");

        // 获取转换器
        OcrResultConverter<?> ocrResultConverter = OcrResultConverterManager.get(typeEnum.getCode());
        if (ocrResultConverter == null) return null;

        // 转换识别结果
        return ocrResultConverter.convert(multipleInvoiceResult);
    }

    public static String getFirstWord(List<Word> words) {
        if (CollUtil.isEmpty(words)) return null;
        return words.getFirst().getWord();
    }
}
