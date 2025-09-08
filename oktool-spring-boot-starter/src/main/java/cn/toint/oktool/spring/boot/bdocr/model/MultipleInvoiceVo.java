package cn.toint.oktool.spring.boot.bdocr.model;

import cn.toint.oktool.spring.boot.bdocr.util.MultipleInvoiceUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Toint
 * @dete 2025/9/8
 */
@Data
public class MultipleInvoiceVo {
    /**
     * 用于定位问题
     */
    private Long logId;

    /**
     * 识别结果数，表示words_result的元素个数
     */
    private Integer wordsResultNum;

    /**
     * 增值税发票
     */
    private List<VatInvoice> vatInvoice;

    /**
     * 出租车票
     */
    private List<Object> taxiReceipt;

    /**
     * 火车票
     */
    private List<TrainTicket> trainTicket;

    /**
     * 定额发票
     */
    private List<Object> quotaInvoice;

    /**
     * 飞机行程单
     */
    private List<Object> airTicket;

    /**
     * 卷票
     */
    private List<Object> rollNormalInvoice;

    /**
     * 机打发票
     */
    private List<Object> printedInvoice;

    /**
     * 机打电子发票
     */
    private List<Object> printedElecInvoice;

    /**
     * 汽车票
     */
    private List<Object> busTicket;

    /**
     * 过路过桥费发票
     */
    private List<Object> tollInvoice;

    /**
     * 船票
     */
    private List<Object> ferryTicket;

    /**
     * 机动车销售发票
     */
    private List<Object> motorVehicleInvoice;

    /**
     * 二手车销售发票
     */
    private List<Object> usedVehicleInvoice;

    /**
     * 网约车行程单
     */
    private List<TaxiOnlineTicket> taxiOnlineTicket;

    /**
     * 限额发票
     */
    private List<Object> limitInvoice;

    /**
     * 购物小票
     */
    private List<Object> shoppingReceipt;

    /**
     * POS小票
     */
    private List<Object> posInvoice;

    /**
     * 其他票据
     */
    private List<Object> others;

    public List<VatInvoice> vatInvoice() {
        if (vatInvoice == null) {
            vatInvoice = new ArrayList<>();
        }
        return vatInvoice;
    }

    public List<TrainTicket> trainTicket() {
        if (trainTicket == null) {
            trainTicket = new ArrayList<>();
        }
        return trainTicket;
    }

    public List<TaxiOnlineTicket> taxiOnlineTicket() {
        if (taxiOnlineTicket == null) {
            taxiOnlineTicket = new ArrayList<>();
        }
        return taxiOnlineTicket;
    }

    /**
     * 增值税发票识别结果
     *
     * @author Toint
     * @dete 2025/9/8
     */
    @Data
    public static class VatInvoice {
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

        public static VatInvoice of(MultipleInvoiceResponse.VatInvoiceResult vatInvoiceResult) {
            return MultipleInvoiceUtil.convertVatInvoice(vatInvoiceResult);
        }

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

    /**
     * 火车票
     *
     * @author Toint
     * @dete 2025/9/8
     */
    @Data
    public static class TrainTicket {
        /**
         * 发票消费类型。火车票此字段固定输出：交通
         */
        private String serviceType;

        /**
         * 车票号
         */
        private String ticketNum;

        /**
         * 始发站
         */
        private String startingStation;

        /**
         * 车次号
         */
        private String trainNum;

        /**
         * 到达站
         */
        private String destinationStation;

        /**
         * 出发日期
         */
        private String date;

        /**
         * 车票金额，当火车票为退票时，该字段表示退票费
         */
        private String ticketRates;

        /**
         * 席别
         */
        private String seatCategory;

        /**
         * 乘客姓名
         */
        private String name;

        /**
         * 身份证号
         */
        private String idCard;

        /**
         * 序列号
         */
        private String serialNumber;

        /**
         * 售站
         */
        private String salesStation;

        /**
         * 时间
         */
        private String time;

        /**
         * 座位号
         */
        private String seatNum;

        /**
         * 候检区
         */
        private String waitingArea;

        /**
         * 标识，仅在输入为铁路电子客票时返回值，包括“退票”、“换开”、“始发改签”等
         */
        private String refundFlag;

        /**
         * 发票号码
         */
        private String invoiceNum;

        /**
         * 开票日期
         */
        private String invoiceDate;

        /**
         * 不含税金额
         */
        private String fare;

        /**
         * 税率
         */
        private String taxRate;

        /**
         * 税额
         */
        private String tax;

        /**
         * 电子客票号
         */
        private String elecTicketNum;

        /**
         * 购买方名称
         */
        private String purchaserName;

        /**
         * 购买方统一社会信用代码
         */
        private String purchaserRegisterNum;
    }

    /**
     * 网约车行程单识别结果
     */
    @Data
    public static class TaxiOnlineTicket {
        /**
         * 发票消费类型。网约车行程单此字段固定输出：交通
         */
        private String serviceType;

        /**
         * 服务商
         */
        private String serviceProvider;

        /**
         * 行程开始时间
         */
        private String startTime;

        /**
         * 行程结束时间
         */
        private String destinationTime;

        /**
         * 行程人手机号
         */
        private String phone;

        /**
         * 申请日期
         */
        private String applicationDate;

        /**
         * 总金额
         */
        private String totalFare;

        /**
         * 行程信息中包含的行程数量
         */
        private String itemNum;

        /**
         * 网约车行程信息
         */
        private List<Detail> details;

        @Data
        public static class Detail {
            /**
             * 行程信息的对应序号
             */
            private String itemId;

            /**
             * 行程信息的对应服务商
             */
            private String itemProvider;

            /**
             * 上车时间
             */
            private String pickupTime;

            /**
             * 上车日期
             */
            private String pickupDate;

            /**
             * 车型
             */
            private String carType;

            /**
             * 里程
             */
            private String distance;

            /**
             * 起点
             */
            private String startPlace;

            /**
             * 终点
             */
            private String destinationPlace;

            /**
             * 城市
             */
            private String city;

            /**
             * 金额
             */
            private String fare;
        }
    }
}
