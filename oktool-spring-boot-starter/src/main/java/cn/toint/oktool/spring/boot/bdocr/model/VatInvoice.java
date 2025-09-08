package cn.toint.oktool.spring.boot.bdocr.model;

import lombok.Data;

import java.util.List;

/**
 * 增值税发票
 *
 * @author Toint
 * @dete 2025/9/8
 */
@Data
public class VatInvoice {
    /**
     * 发票消费类型
     */
    private String serviceType;

    /**
     * 发票名称
     */
    private String invoiceTypeOrg;

    /**
     * 增值税发票的细分类型
     */
    private String invoiceType;

    /**
     * 增值税发票左上角标志
     */
    private String invoiceTag;

    /**
     * 发票代码
     */
    private String invoiceCode;

    /**
     * 发票号码
     */
    private String invoiceNum;

    /**
     * 发票代码的辅助校验码，一般业务情景可忽略
     */
    private String invoiceCodeConfirm;

    /**
     * 发票号码的辅助校验码，一般业务情景可忽略
     */
    private String invoiceNumConfirm;

    /**
     * 校验码。增值税专票无此参数
     */
    private String checkCode;

    /**
     * 数电票号码。密码区部分的数电票号码，仅在纸质的数电票上出现
     */
    private String invoiceNumDigit;

    /**
     * 开票日期
     */
    private String invoiceDate;

    /**
     * 购方名称
     */
    private String purchaserName;

    /**
     * 购方纳税人识别号
     */
    private String purchaserRegisterNum;

    /**
     * 购方地址及电话
     */
    private String purchaserAddress;

    /**
     * 购方开户行及账号
     */
    private String purchaserBank;

    /**
     * 密码区
     */
    private String password;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 联次信息。专票第一联到第三联分别输出：第一联：记账联、第二联：抵扣联、第三联：发票联；普通发票第一联到第二联分别输出：第一联：记账联、第二联：发票联
     */
    private String sheetNum;

    /**
     * 是否代开
     */
    private String agent;

    /**
     * 电子支付标识。仅区块链发票含有此参数
     */
    private String onlinePay;

    /**
     * 销售方名称
     */
    private String sellerName;

    /**
     * 销售方纳税人识别号
     */
    private String sellerRegisterNum;

    /**
     * 销售方地址及电话
     */
    private String sellerAddress;

    /**
     * 销售方开户行及账号
     */
    private String sellerBank;

    /**
     * 合计金额
     */
    private String totalAmount;

    /**
     * 合计税额
     */
    private String totalTax;

    /**
     * 价税合计(大写)
     */
    private String amountInWords;

    /**
     * 价税合计(小写)
     */
    private String amountInFiguers;

    /**
     * 收款人
     */
    private String payee;

    /**
     * 复核人
     */
    private String checker;

    /**
     * 开票人
     */
    private String noteDrawer;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 总页码
     */
    private String totalPage;

    /**
     * 当前页码
     */
    private String currentPage;

    /**
     * 小计金额
     */
    private String subTotalAmount;

    /**
     * 小计稅额
     */
    private String subTotalTax;

    private List<Detail> details;

    @Data
    public static class Detail {
        /**
         * 货物名称
         */
        private String commodityName;

        /**
         * 规格型号
         */
        private String commodityType;

        /**
         * 单位
         */
        private String commodityUnit;

        /**
         * 数量
         */
        private String commodityNum;

        /**
         * 单价
         */
        private String commodityPrice;

        /**
         * 金额
         */
        private String commodityAmount;

        /**
         * 税率
         */
        private String commodityTaxRate;

        /**
         * 税额
         */
        private String commodityTax;
    }
}
