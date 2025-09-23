package cn.toint.oktool.spring.boot.bdocr.model;

import cn.hutool.v7.core.date.DateUtil;
import cn.hutool.v7.core.date.TimeUtil;
import cn.hutool.v7.core.text.StrUtil;
import cn.toint.oktool.spring.boot.bdocr.util.InvoiceTypeConverter;
import cn.toint.oktool.spring.boot.bdocr.util.InvoiceVerifyAmountUtil;
import cn.toint.oktool.util.AmountUtil;
import cn.toint.oktool.util.Assert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author Toint
 * @dete 2025/9/9
 */
@Data
public class VatInvoiceVerificationRequest {
    /**
     * 发票代码。
     * 全电发票（专用发票）、全电发票（普通发票）、全电发票（含通行费标识）、电子发票（机动车销售统一发票）、电子发票（二手车销售统一发票）、电子发票（铁路电子客票）、电子发票（航空运输电子客票行程单）此参数可为空，其他类型发票均不可为空
     */
    @JsonProperty("invoice_code")
    private String invoiceCode;

    /**
     * 发票号码
     */
    @JsonProperty("invoice_num")
    private String invoiceNum;

    /**
     * 开票日期。格式: yyyyMMdd，例：20210101
     */
    @JsonProperty("invoice_date")
    private String invoiceDate;

    /**
     * 发票种类
     * 增值税专用发票：special_vat_invoice
     * 增值税电子专用发票：elec_special_vat_invoice
     * 增值税普通发票：normal_invoice
     * 增值税普通发票（电子）：elec_normal_invoice
     * 增值税普通发票（卷式）：roll_normal_invoice
     * 通行费增值税电子普通发票：toll_elec_normal_invoice
     * 区块链电子发票（目前仅支持深圳地区）：blockchain_invoice
     * 全电发票（专用发票）：elec_invoice_special
     * 全电发票（普通发票）：elec_invoice_normal
     * 货运运输业增值税专用发票：special_freight_transport_invoice
     * 机动车销售发票/电子发票（纸质机动车销售统一发票）/电子发票（机动车销售统一发票）：motor_vehicle_invoice
     * 二手车销售发票/电子发票（纸质二手车销售统一发票）/电子发票（二手车销售统一发票）：used_vehicle_invoice
     * 电子发票（航空运输电子客票行程单）：elec_flight_itinerary_invoice
     * 电子发票（铁路电子客票）： elec_train_ticket_invoice
     * 全电发票（含通行费标识）： elec_toll_invoice
     */
    @JsonProperty("invoice_type")
    private String invoiceType;

    /**
     * 校验码。填写发票校验码后6位。
     * 增值税电子专票、普票、电子普票、卷票、区块链电子发票、通行费增值税电子普通发票此参数必填；
     * 其他类型发票此参数可为空
     */
    @JsonProperty("check_code")
    private String checkCode;

    /**
     * 发票金额。
     * 增值税专票、电子专票、区块链电子发票、机动车销售发票、电子发票（纸质机动车销售统一发票）、货运专票填写不含税金额；
     * 二手车销售发票、电子发票（纸质二手车销售统一发票）、电子发票（二手车销售统一发票）填写车价合计；
     * 全电发票（专用发票）、全电发票（普通发票）、电子发票（铁路电子客票）、电子发票（航空运输电子客票行程单）、电子发票（机动车销售统一发票）、全电发票（含通行费标识）填写价税合计金额，其他类型发票可为空
     */
    @JsonProperty("total_amount")
    private String totalAmount;

    /**
     * 发票日期
     */
    public VatInvoiceVerificationRequest invoiceDate(TemporalAccessor invoiceDate) {
        Assert.notNull(invoiceDate, "invoiceDate must not be null");
        this.invoiceDate = TimeUtil.format(invoiceDate, "yyyyMMdd");
        return this;
    }

    /**
     * 发票日期
     */
    public VatInvoiceVerificationRequest invoiceDate(Date invoiceDate) {
        Assert.notNull(invoiceDate, "invoiceDate must not be null");
        this.invoiceDate = DateUtil.format(invoiceDate, "yyyyMMdd");
        return this;
    }

    /**
     * 发票日期(自动识别)
     */
    public VatInvoiceVerificationRequest invoiceDate(String invoiceDateStr) {
        Assert.notBlank(invoiceDateStr, "invoiceDate must not be blank");
        this.invoiceDate = DateUtil.parse(invoiceDateStr).toString("yyyyMMdd");
        return this;
    }

    /**
     * 发票类型
     */
    public VatInvoiceVerificationRequest invoiceType(InvoiceVerifyType invoiceVerifyType) {
        Assert.notNull(invoiceVerifyType, "invoiceVerifyType must not be null");
        this.invoiceType = invoiceVerifyType.getCode();
        return this;
    }

    /**
     * 发票类型(自动识别)
     */
    public VatInvoiceVerificationRequest invoiceType(String invoiceVerifyTypeStr) {
        Assert.notBlank(invoiceVerifyTypeStr, "invoiceVerifyType must not be blank");
        InvoiceVerifyType invoiceVerifyType = InvoiceTypeConverter.convertOcrToVerifyType(invoiceVerifyTypeStr);
        Assert.notNull(invoiceVerifyType, "发票类型解析失败");
        this.invoiceType = invoiceVerifyType.getCode();
        return this;
    }

    /**
     * 金额(自动识别)
     */
    public VatInvoiceVerificationRequest totalAmount(String totalAmount) {
        BigDecimal bigDecimal = AmountUtil.toBigDecimal(totalAmount);
        Assert.notNull(bigDecimal, "金额转换失败");
        this.totalAmount = bigDecimal.toPlainString();
        return this;
    }

    /**
     * 金额(自动识别)
     *
     * @param invoiceType        发票类型
     * @param totalAmount        不含税金额/车价合计
     * @param totalAmountInclTax 价税合计
     */
    public VatInvoiceVerificationRequest totalAmount(String invoiceType, String totalAmount, String totalAmountInclTax) {
        // 识别选用的金额, 当识别成功进行赋值金额, 否则不赋值
        String amount = InvoiceVerifyAmountUtil.getAmountByInvoiceType(invoiceType, totalAmount, totalAmountInclTax);
        if (amount != null) {
            return totalAmount(amount);
        }
        return this;
    }

    public VatInvoiceVerificationRequest checkCode(String checkCode) {
        Assert.notBlank(checkCode, "checkCode must not be blank");
        String subCheckCode = StrUtil.subSufByLength(checkCode, 6);
        Assert.isTrue(subCheckCode.length() == 6, "校验码长度必须为6位");
        this.checkCode = subCheckCode;
        return this;
    }
}
