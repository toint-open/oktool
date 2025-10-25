package cn.toint.oktool.util;

import cn.hutool.v7.core.math.NumberUtil;
import cn.hutool.v7.core.text.StrPool;
import cn.hutool.v7.core.text.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 金额工具
 *
 * @author Toint
 * @since 2025/9/9
 */
public class AmountUtil {

    private static final Logger log = LoggerFactory.getLogger(AmountUtil.class);

    /**
     * 金额转换数字
     *
     * @param amountStr 金额文本
     * @return 金额数字, 转换失败返回null
     */
    public static BigDecimal toBigDecimal(String amountStr) {
        if (StringUtils.isBlank(amountStr)) return null;

        // 去除多余字符
        amountStr = StrUtil.trim(amountStr);
        amountStr = StrUtil.removeAll(amountStr, "圆", "元", "$", "¥", "￥", "USD", "CNY", "RMB", "人民币", "*", StrPool.SPACE, StrPool.COMMA);

        // 再次检查空字符
        if (StringUtils.isBlank(amountStr)) return null;

        try {
            return NumberUtil.toBigDecimal(amountStr);
        } catch (IllegalArgumentException e) {
            log.warn("金额文本转数字失败: {}", e.getMessage(), e);
            return null;
        }

    }
}
