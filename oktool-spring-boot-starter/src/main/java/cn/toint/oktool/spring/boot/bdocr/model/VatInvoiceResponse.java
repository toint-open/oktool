package cn.toint.oktool.spring.boot.bdocr.model;

import cn.toint.oktool.spring.boot.bdocr.util.VatInvoiceUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 增值税发票识别结果
 *
 * @author Toint
 * @dete 2025/9/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VatInvoiceResponse extends BaseOcrResponse{
    /**
     * 识别结果数，表示words_result的元素个数
     */
    @JsonProperty("words_result_num")
    private Integer wordsResultNum;

    /**
     * 识别结果
     */
    @JsonProperty("words_result")
    private WordsResult wordsResult;

    @Data
    public static class WordsResult {
        /**
         * 发票消费类型。不同消费类型输出：餐饮、电器设备、通讯、服务、日用品食品、医疗、交通、其他
         */
        @JsonProperty("ServiceType")
        private String serviceType;

        /**
         * 发票种类。不同类型发票输出：普通发票、专用发票、电子普通发票、电子专用发票、通行费电子普票、区块链发票、通用机打电子发票、电子发票(专用发票)、电子发票(普通发票)
         */
        @JsonProperty("InvoiceType")
        private String invoiceType;

        /**
         * 发票名称
         */
        @JsonProperty("InvoiceTypeOrg")
        private String invoiceTypeOrg;

        /**
         * 发票代码
         */
        @JsonProperty("InvoiceCode")
        private String invoiceCode;

        /**
         * 发票号码
         */
        @JsonProperty("InvoiceNum")
        private String invoiceNum;

        /**
         * 发票代码的辅助校验码，一般业务情景可忽略
         */
        @JsonProperty("InvoiceCodeConfirm")
        private String invoiceCodeConfirm;

        /**
         * 发票号码的辅助校验码，一般业务情景可忽略
         */
        @JsonProperty("InvoiceNumConfirm")
        private String invoiceNumConfirm;

        /**
         * 数电票号，仅针对纸质的全电发票，在密码区有数电票号码的字段输出
         */
        @JsonProperty("InvoiceNumDigit")
        private String invoiceNumDigit;

        /**
         * 增值税发票左上角标志。包含：通行费、销项负数、代开、收购、成品油、其他
         */
        @JsonProperty("InvoiceTag")
        private String invoiceTag;

        /**
         * 机打号码。仅增值税卷票含有此参数
         */
        @JsonProperty("MachineNum")
        private String machineNum;

        /**
         * 机器编号。仅增值税卷票含有此参数
         */
        @JsonProperty("MachineCode")
        private String machineCode;

        /**
         * 校验码
         */
        @JsonProperty("CheckCode")
        private String checkCode;

        /**
         * 开票日期
         */
        @JsonProperty("InvoiceDate")
        private String invoiceDate;

        /**
         * 购方名称
         */
        @JsonProperty("PurchaserName")
        private String purchaserName;

        /**
         * 购方纳税人识别号
         */
        @JsonProperty("PurchaserRegisterNum")
        private String purchaserRegisterNum;

        /**
         * 购方地址及电话
         */
        @JsonProperty("PurchaserAddress")
        private String purchaserAddress;

        /**
         * 购方开户行及账号
         */
        @JsonProperty("PurchaserBank")
        private String purchaserBank;

        /**
         * 密码区
         */
        @JsonProperty("Password")
        private String password;

        /**
         * 省
         */
        @JsonProperty("Province")
        private String province;

        /**
         * 市
         */
        @JsonProperty("City")
        private String city;

        /**
         * 联次信息。专票第一联到第三联分别输出：第一联：记账联、第二联：抵扣联、第三联：发票联；
         * 普通发票第一联到第二联分别输出：第一联：记账联、第二联：发票联
         */
        @JsonProperty("SheetNum")
        private String sheetNum;

        /**
         * 是否代开
         */
        @JsonProperty("Agent")
        private String agent;

        /**
         * 货物名称
         */
        @JsonProperty("CommodityName")
        private List<Word> commodityName;

        /**
         * 规格型号
         */
        @JsonProperty("CommodityType")
        private List<Word> commodityType;

        /**
         * 单位
         */
        @JsonProperty("CommodityUnit")
        private List<Word> commodityUnit;

        /**
         * 数量
         */
        @JsonProperty("CommodityNum")
        private List<Word> commodityNum;

        /**
         * 单价
         */
        @JsonProperty("CommodityPrice")
        private List<Word> commodityPrice;

        /**
         * 金额
         */
        @JsonProperty("CommodityAmount")
        private List<Word> commodityAmount;

        /**
         * 税率
         */
        @JsonProperty("CommodityTaxRate")
        private List<Word> commodityTaxRate;

        /**
         * 税额
         */
        @JsonProperty("CommodityTax")
        private List<Word> commodityTax;

        /**
         * 电子支付标识。仅区块链发票含有此参数
         */
        @JsonProperty("OnlinePay")
        private String onlinePay;

        /**
         * 销售方名称
         */
        @JsonProperty("SellerName")
        private String sellerName;

        /**
         * 销售方纳税人识别号
         */
        @JsonProperty("SellerRegisterNum")
        private String sellerRegisterNum;

        /**
         * 销售方地址及电话
         */
        @JsonProperty("SellerAddress")
        private String sellerAddress;

        /**
         * 销售方开户行及账号
         */
        @JsonProperty("SellerBank")
        private String sellerBank;

        /**
         * 合计金额
         */
        @JsonProperty("TotalAmount")
        private String totalAmount;

        /**
         * 合计税额
         */
        @JsonProperty("TotalTax")
        private String totalTax;

        /**
         * 价税合计(大写)
         */
        @JsonProperty("AmountInWords")
        private String amountInWords;

        /**
         * 价税合计(小写)
         */
        @JsonProperty("AmountInFiguers")
        private String amountInFigures;

        /**
         * 收款人
         */
        @JsonProperty("Payee")
        private String payee;

        /**
         * 复核人
         */
        @JsonProperty("Checker")
        private String checker;

        /**
         * 开票人
         */
        @JsonProperty("NoteDrawer")
        private String noteDrawer;

        /**
         * 备注信息
         */
        @JsonProperty("Remarks")
        private String remarks;

        /**
         * 判断是否存在公司印章。返回"0"或"1"，当seal_tag=true时返回该字段
         * 1：代表存在公司印章；0：代表不存在公司印章
         */
        @JsonProperty("company_seal")
        private String companySeal;

        /**
         * 公司印章识别结果内容。当seal_tag=true时返回该字段
         */
        @JsonProperty("seal_info")
        private String sealInfo;

        /**
         * 判断是否存在监制印章。返回"0"或"1"，当seal_tag=true时返回该字段
         * 1：代表存在监制印章；0：代表不存在监制印章
         */
        @JsonProperty("supervision_seal")
        private String supervisionSeal;

        /**
         * 监制印章识别结果内容。当seal_tag=true时返回该字段
         */
        @JsonProperty("supervision_seal_info")
        private String supervisionSealInfo;
    }

    public VatInvoiceVo toVo() {
        return VatInvoiceUtil.convert(this);
    }
}
