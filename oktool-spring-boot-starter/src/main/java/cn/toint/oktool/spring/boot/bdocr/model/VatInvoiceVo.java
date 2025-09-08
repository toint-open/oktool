package cn.toint.oktool.spring.boot.bdocr.model;

import lombok.Data;

import java.util.List;

/**
 * 增值税发票识别结果
 *
 * @author Toint
 * @dete 2025/9/8
 */
@Data
public class VatInvoiceVo {
    /**
     * 用于定位问题
     */
    private Long logId;

    /**
     * 识别结果数，表示words_result的元素个数
     */
    private Integer wordsResultNum;

    /**
     * 识别结果
     */
    private WordsResult wordsResult;

    @Data
    public static class WordsResult {
        /**
         * 发票消费类型。不同消费类型输出：餐饮、电器设备、通讯、服务、日用品食品、医疗、交通、其他
         */
        private String serviceType;

        /**
         * 发票种类。不同类型发票输出：普通发票、专用发票、电子普通发票、电子专用发票、通行费电子普票、区块链发票、通用机打电子发票、电子发票(专用发票)、电子发票(普通发票)
         */
        private String invoiceType;

        /**
         * 发票名称
         */
        private String invoiceTypeOrg;

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
         * 数电票号，仅针对纸质的全电发票，在密码区有数电票号码的字段输出
         */
        private String invoiceNumDigit;

        /**
         * 增值税发票左上角标志。包含：通行费、销项负数、代开、收购、成品油、其他
         */
        private String invoiceTag;

        /**
         * 机打号码。仅增值税卷票含有此参数
         */
        private String machineNum;

        /**
         * 机器编号。仅增值税卷票含有此参数
         */
        private String machineCode;

        /**
         * 校验码
         */
        private String checkCode;

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
         * 联次信息。专票第一联到第三联分别输出：第一联：记账联、第二联：抵扣联、第三联：发票联；
         * 普通发票第一联到第二联分别输出：第一联：记账联、第二联：发票联
         */
        private String sheetNum;

        /**
         * 是否代开
         */
        private String agent;

        /**
         * 发票明细
         */
        private List<Detail> details;

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
        private String amountInFigures;

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
         * 备注信息
         */
        private String remarks;

        /**
         * 判断是否存在公司印章。返回"0"或"1"，当seal_tag=true时返回该字段
         * 1：代表存在公司印章；0：代表不存在公司印章
         */
        private String companySeal;

        /**
         * 公司印章识别结果内容。当seal_tag=true时返回该字段
         */
        private String sealInfo;

        /**
         * 判断是否存在监制印章。返回"0"或"1"，当seal_tag=true时返回该字段
         * 1：代表存在监制印章；0：代表不存在监制印章
         */
        private String supervisionSeal;

        /**
         * 监制印章识别结果内容。当seal_tag=true时返回该字段
         */
        private String supervisionSealInfo;

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
}
