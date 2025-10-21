package cn.toint.oktool.spring.boot.bdocr.util;

import cn.toint.oktool.spring.boot.bdocr.model.InvoiceVerifyType;

import java.util.Set;

/**
 * 发票验真金额工具
 *
 * @author Toint
 * @since 2025/9/23
 */
public class InvoiceVerifyAmountUtil {
    /**
     * 根据发票类型选中金额
     *
     * @param invoiceType        发票类型
     * @param totalAmount        不含税金额/车价合计
     * @param totalAmountInclTax 价税合计
     * @return 选中金额, 识别失败返回null
     */
    public static String getAmountByInvoiceType(String invoiceType, String totalAmount, String totalAmountInclTax) {
        InvoiceVerifyType invoiceVerifyType = InvoiceTypeConverter.convertOcrToVerifyType(invoiceType);
        if (invoiceVerifyType == null) return null;
        return getAmountByInvoiceType(invoiceVerifyType, totalAmount, totalAmountInclTax);
    }

    /**
     * 根据发票类型选中金额
     *
     * @param invoiceType        发票类型
     * @param totalAmount        不含税金额/车价合计
     * @param totalAmountInclTax 价税合计
     * @return 选中金额, 识别失败返回null
     */
    public static String getAmountByInvoiceType(InvoiceVerifyType invoiceType, String totalAmount, String totalAmountInclTax) {
        // 规则如下:
        // 增值税专票、电子专票、区块链电子发票、机动车销售发票、电子发票（纸质机动车销售统一发票）、货运专票填写不含税金额；
        // 二手车销售发票、电子发票（纸质二手车销售统一发票）、电子发票（二手车销售统一发票）填写车价合计；
        // 全电发票（专用发票）、全电发票（普通发票）、电子发票（铁路电子客票）、电子发票（航空运输电子客票行程单）、电子发票（机动车销售统一发票）、全电发票（含通行费标识）填写价税合计金额，其他类型发票可为空

        // 不确定: 电子发票（机动车销售统一发票）

        // 返回不含税金额
        if (Set.of(
                // 增值税专票
                InvoiceVerifyType.SPECIAL_VAT_INVOICE,
                // 电子专票
                InvoiceVerifyType.ELEC_SPECIAL_VAT_INVOICE,
                // 区块链电子发票
                InvoiceVerifyType.BLOCKCHAIN_INVOICE,
                // 机动车销售发票, 电子发票（纸质机动车销售统一发票）
                InvoiceVerifyType.MOTOR_VEHICLE_INVOICE,
                // 货运专票
                InvoiceVerifyType.SPECIAL_FREIGHT_TRANSPORT_INVOICE,
                // 二手车销售发票, 电子发票（纸质二手车销售统一发票, 电子发票（二手车销售统一发票）
                InvoiceVerifyType.USED_VEHICLE_INVOICE
        ).contains(invoiceType)) {
            return totalAmount;
        }

        // 返回价税合计
        if (Set.of(
                // 全电发票（专用发票）
                InvoiceVerifyType.ELEC_INVOICE_SPECIAL,
                // 全电发票（普通发票）
                InvoiceVerifyType.ELEC_INVOICE_NORMAL,
                // 电子发票（铁路电子客票）
                InvoiceVerifyType.ELEC_TRAIN_TICKET_INVOICE,
                // 电子发票（航空运输电子客票行程单）
                InvoiceVerifyType.ELEC_FLIGHT_ITINERARY_INVOICE,
                // 全电发票（含通行费标识）
                InvoiceVerifyType.ELEC_TOLL_INVOICE).contains(invoiceType)) {
            return totalAmountInclTax;
        }

        return null;
    }
}
