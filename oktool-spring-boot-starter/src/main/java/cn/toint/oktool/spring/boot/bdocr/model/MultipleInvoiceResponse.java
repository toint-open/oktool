package cn.toint.oktool.spring.boot.bdocr.model;

import cn.hutool.v7.core.util.EnumUtil;
import cn.toint.oktool.spring.boot.bdocr.util.MultipleInvoiceUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 识别结果
 *
 * @author Toint
 * @dete 2025/9/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MultipleInvoiceResponse extends BaseOcrResponse {
    /**
     * 识别结果数，表示words_result的元素个数
     */
    @JsonProperty("words_result_num")
    private Integer wordsResultNum;

    /**
     * 识别结果
     */
    @JsonProperty("words_result")
    private List<WordsResult> wordsResult;


    @Data
    public static class WordsResult {
        /**
         * 每一张票据的种类
         */
        private String type;

        /**
         * 单张票据的识别结果
         */
        private JsonNode result;

        public TypeEnum typeEnum() {
            if (StringUtils.isBlank(type)) return null;
            return EnumUtil.getBy(TypeEnum::getCode, type);
        }
    }

    /**
     * 增值税发票识别结果
     *
     * @author Toint
     * @dete 2025/9/8
     */
    @Data
    public static class VatInvoiceResult {
        /**
         * 发票消费类型
         */
        @JsonProperty("ServiceType")
        private List<Word> serviceType;

        /**
         * 发票名称
         */
        @JsonProperty("InvoiceTypeOrg")
        private List<Word> invoiceTypeOrg;

        /**
         * 增值税发票的细分类型
         */
        @JsonProperty("InvoiceType")
        private List<Word> invoiceType;

        /**
         * 增值税发票左上角标志
         */
        @JsonProperty("InvoiceTag")
        private List<Word> invoiceTag;

        /**
         * 发票代码
         */
        @JsonProperty("InvoiceCode")
        private List<Word> invoiceCode;

        /**
         * 发票号码
         */
        @JsonProperty("InvoiceNum")
        private List<Word> invoiceNum;

        /**
         * 发票代码的辅助校验码，一般业务情景可忽略
         */
        @JsonProperty("InvoiceCodeConfirm")
        private List<Word> invoiceCodeConfirm;

        /**
         * 发票号码的辅助校验码，一般业务情景可忽略
         */
        @JsonProperty("InvoiceNumConfirm")
        private List<Word> invoiceNumConfirm;

        /**
         * 校验码。增值税专票无此参数
         */
        @JsonProperty("CheckCode")
        private List<Word> checkCode;

        /**
         * 数电票号码。密码区部分的数电票号码，仅在纸质的数电票上出现
         */
        @JsonProperty("InvoiceNumDigit")
        private List<Word> invoiceNumDigit;

        /**
         * 开票日期
         */
        @JsonProperty("InvoiceDate")
        private List<Word> invoiceDate;

        /**
         * 购方名称
         */
        @JsonProperty("PurchaserName")
        private List<Word> purchaserName;

        /**
         * 购方纳税人识别号
         */
        @JsonProperty("PurchaserRegisterNum")
        private List<Word> purchaserRegisterNum;

        /**
         * 购方地址及电话
         */
        @JsonProperty("PurchaserAddress")
        private List<Word> purchaserAddress;

        /**
         * 购方开户行及账号
         */
        @JsonProperty("PurchaserBank")
        private List<Word> purchaserBank;

        /**
         * 密码区
         */
        @JsonProperty("Password")
        private List<Word> password;

        /**
         * 省
         */
        @JsonProperty("Province")
        private List<Word> province;

        /**
         * 市
         */
        @JsonProperty("City")
        private List<Word> city;

        /**
         * 联次信息。专票第一联到第三联分别输出：第一联：记账联、第二联：抵扣联、第三联：发票联；普通发票第一联到第二联分别输出：第一联：记账联、第二联：发票联
         */
        @JsonProperty("SheetNum")
        private List<Word> sheetNum;

        /**
         * 是否代开
         */
        @JsonProperty("Agent")
        private List<Word> agent;

        /**
         * 电子支付标识。仅区块链发票含有此参数
         */
        @JsonProperty("OnlinePay")
        private List<Word> onlinePay;

        /**
         * 销售方名称
         */
        @JsonProperty("SellerName")
        private List<Word> sellerName;

        /**
         * 销售方纳税人识别号
         */
        @JsonProperty("SellerRegisterNum")
        private List<Word> sellerRegisterNum;

        /**
         * 销售方地址及电话
         */
        @JsonProperty("SellerAddress")
        private List<Word> sellerAddress;

        /**
         * 销售方开户行及账号
         */
        @JsonProperty("SellerBank")
        private List<Word> sellerBank;

        /**
         * 合计金额
         */
        @JsonProperty("TotalAmount")
        private List<Word> totalAmount;

        /**
         * 合计税额
         */
        @JsonProperty("TotalTax")
        private List<Word> totalTax;

        /**
         * 价税合计(大写)
         */
        @JsonProperty("AmountInWords")
        private List<Word> amountInWords;

        /**
         * 价税合计(小写)
         */
        @JsonProperty("AmountInFiguers")
        private List<Word> amountInFiguers;

        /**
         * 收款人
         */
        @JsonProperty("Payee")
        private List<Word> payee;

        /**
         * 复核人
         */
        @JsonProperty("Checker")
        private List<Word> checker;

        /**
         * 开票人
         */
        @JsonProperty("NoteDrawer")
        private List<Word> noteDrawer;

        /**
         * 备注
         */
        @JsonProperty("Remarks")
        private List<Word> remarks;

        /**
         * 总页码
         */
        @JsonProperty("TotalPage")
        private List<Word> totalPage;

        /**
         * 当前页码
         */
        @JsonProperty("CurrentPage")
        private List<Word> currentPage;

        /**
         * 小计金额
         */
        @JsonProperty("SubTotalAmount")
        private List<Word> subTotalAmount;

        /**
         * 小计稅额
         */
        @JsonProperty("SubTotalTax")
        private List<Word> subTotalTax;

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
    }

    /**
     * 网约车行程单识别结果
     */
    @Data
    public static class TaxiOnlineTicketResult {
        /**
         * 发票消费类型。网约车行程单此字段固定输出：交通
         */
        @JsonProperty("ServiceType")
        private List<Word> serviceType;

        /**
         * 服务商
         */
        @JsonProperty("service_provider")
        private List<Word> serviceProvider;

        /**
         * 行程开始时间
         */
        @JsonProperty("start_time")
        private List<Word> startTime;

        /**
         * 行程结束时间
         */
        @JsonProperty("destination_time")
        private List<Word> destinationTime;

        /**
         * 行程人手机号
         */
        @JsonProperty("phone")
        private List<Word> phone;

        /**
         * 申请日期
         */
        @JsonProperty("application_date")
        private List<Word> applicationDate;

        /**
         * 总金额
         */
        @JsonProperty("total_fare")
        private List<Word> totalFare;

        /**
         * 行程信息中包含的行程数量
         */
        @JsonProperty("item_num")
        private List<Word> itemNum;

        /**
         * 网约车行程信息
         */
        @JsonProperty("items")
        private List<Detail> Details;

        @Data
        public static class Detail {
            /**
             * 行程信息的对应序号
             */
            @JsonProperty("item_id")
            private Word itemId;

            /**
             * 行程信息的对应服务商
             */
            @JsonProperty("item_provider")
            private Word itemProvider;

            /**
             * 上车时间
             */
            @JsonProperty("pickup_time")
            private Word pickupTime;

            /**
             * 上车日期
             */
            @JsonProperty("pickup_date")
            private Word pickupDate;

            /**
             * 车型
             */
            @JsonProperty("car_type")
            private Word carType;

            /**
             * 里程
             */
            @JsonProperty("distance")
            private Word distance;

            /**
             * 起点
             */
            @JsonProperty("start_place")
            private Word startPlace;

            /**
             * 终点
             */
            @JsonProperty("destination_place")
            private Word destinationPlace;

            /**
             * 城市
             */
            @JsonProperty("city")
            private Word city;

            /**
             * 金额
             */
            @JsonProperty("fare")
            private Word fare;
        }
    }

    /**
     * 火车票识别结果
     */
    @Data
    public static class TrainTicketResult {
        /**
         * 发票消费类型。火车票此字段固定输出：交通
         */
        @JsonProperty("ServiceType")
        private List<Word> serviceType;

        /**
         * 车票号
         */
        @JsonProperty("ticket_num")
        private List<Word> ticketNum;

        /**
         * 始发站
         */
        @JsonProperty("starting_station")
        private List<Word> startingStation;

        /**
         * 车次号
         */
        @JsonProperty("train_num")
        private List<Word> trainNum;

        /**
         * 到达站
         */
        @JsonProperty("destination_station")
        private List<Word> destinationStation;

        /**
         * 出发日期
         */
        @JsonProperty("date")
        private List<Word> date;

        /**
         * 车票金额，当火车票为退票时，该字段表示退票费
         */
        @JsonProperty("ticket_rates")
        private List<Word> ticketRates;

        /**
         * 席别
         */
        @JsonProperty("seat_category")
        private List<Word> seatCategory;

        /**
         * 乘客姓名
         */
        @JsonProperty("name")
        private List<Word> name;

        /**
         * 身份证号
         */
        @JsonProperty("ID_card")
        private List<Word> idCard;

        /**
         * 序列号
         */
        @JsonProperty("serial_number")
        private List<Word> serialNumber;

        /**
         * 售站
         */
        @JsonProperty("sales_station")
        private List<Word> salesStation;

        /**
         * 时间
         */
        @JsonProperty("time")
        private List<Word> time;

        /**
         * 座位号
         */
        @JsonProperty("seat_num")
        private List<Word> seatNum;

        /**
         * 候检区
         */
        @JsonProperty("Waiting_area")
        private List<Word> waitingArea;

        /**
         * 标识，仅在输入为铁路电子客票时返回值，包括“退票”、“换开”、“始发改签”等
         */
        @JsonProperty("refund_flag")
        private List<Word> refundFlag;

        /**
         * 发票号码
         */
        @JsonProperty("invoice_num")
        private List<Word> invoiceNum;

        /**
         * 	开票日期
         */
        @JsonProperty("invoice_date")
        private List<Word> invoiceDate;

        /**
         * 不含税金额
         */
        @JsonProperty("fare")
        private List<Word> fare;

        /**
         * 税率
         */
        @JsonProperty("tax_rate")
        private List<Word> taxRate;

        /**
         * 税额
         */
        @JsonProperty("tax")
        private List<Word> tax;

        /**
         * 电子客票号
         */
        @JsonProperty("elec_ticket_num")
        private List<Word> elecTicketNum;

        /**
         * 购买方名称
         */
        @JsonProperty("purchaser_name")
        private List<Word> purchaserName;

        /**
         * 购买方统一社会信用代码
         */
        @JsonProperty("purchaser_register_num")
        private List<Word> purchaserRegisterNum;
    }

    public MultipleInvoiceVo toVo() {
        return MultipleInvoiceUtil.convert(this);
    }
}
