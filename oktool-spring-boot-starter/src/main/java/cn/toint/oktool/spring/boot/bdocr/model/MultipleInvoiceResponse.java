package cn.toint.oktool.spring.boot.bdocr.model;

import cn.hutool.v7.core.util.EnumUtil;
import cn.toint.oktool.spring.boot.bdocr.util.MultipleInvoiceUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 识别结果
 *
 * @author Toint
 * @since 2025/9/8
 */
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

    public Integer getWordsResultNum() {
        return wordsResultNum;
    }

    public void setWordsResultNum(Integer wordsResultNum) {
        this.wordsResultNum = wordsResultNum;
    }

    public List<WordsResult> getWordsResult() {
        return wordsResult;
    }

    public void setWordsResult(List<WordsResult> wordsResult) {
        this.wordsResult = wordsResult;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MultipleInvoiceResponse that = (MultipleInvoiceResponse) o;
        return Objects.equals(wordsResultNum, that.wordsResultNum) && Objects.equals(wordsResult, that.wordsResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wordsResultNum, wordsResult);
    }

    @Override
    public String toString() {
        return "MultipleInvoiceResponse{" +
                "wordsResultNum=" + wordsResultNum +
                ", wordsResult=" + wordsResult +
                "} " + super.toString();
    }


    public static class WordsResult {
        /**
         * 每一张票据的种类
         */
        private String type;

        /**
         * 单张票据的识别结果
         */
        private JsonNode result;

        public TicketTypeEnum typeEnum() {
            if (StringUtils.isBlank(type)) return null;
            return EnumUtil.getBy(TicketTypeEnum::getCode, type);
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public JsonNode getResult() {
            return result;
        }

        public void setResult(JsonNode result) {
            this.result = result;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            WordsResult that = (WordsResult) o;
            return Objects.equals(type, that.type) && Objects.equals(result, that.result);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, result);
        }

        @Override
        public String toString() {
            return "WordsResult{" +
                    "type='" + type + '\'' +
                    ", result=" + result +
                    '}';
        }
    }

    /**
     * 增值税发票识别结果
     *
     * @author Toint
     * @since 2025/9/8
     */
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

        public List<Word> getServiceType() {
            return serviceType;
        }

        public void setServiceType(List<Word> serviceType) {
            this.serviceType = serviceType;
        }

        public List<Word> getInvoiceTypeOrg() {
            return invoiceTypeOrg;
        }

        public void setInvoiceTypeOrg(List<Word> invoiceTypeOrg) {
            this.invoiceTypeOrg = invoiceTypeOrg;
        }

        public List<Word> getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(List<Word> invoiceType) {
            this.invoiceType = invoiceType;
        }

        public List<Word> getInvoiceTag() {
            return invoiceTag;
        }

        public void setInvoiceTag(List<Word> invoiceTag) {
            this.invoiceTag = invoiceTag;
        }

        public List<Word> getInvoiceCode() {
            return invoiceCode;
        }

        public void setInvoiceCode(List<Word> invoiceCode) {
            this.invoiceCode = invoiceCode;
        }

        public List<Word> getInvoiceNum() {
            return invoiceNum;
        }

        public void setInvoiceNum(List<Word> invoiceNum) {
            this.invoiceNum = invoiceNum;
        }

        public List<Word> getInvoiceCodeConfirm() {
            return invoiceCodeConfirm;
        }

        public void setInvoiceCodeConfirm(List<Word> invoiceCodeConfirm) {
            this.invoiceCodeConfirm = invoiceCodeConfirm;
        }

        public List<Word> getInvoiceNumConfirm() {
            return invoiceNumConfirm;
        }

        public void setInvoiceNumConfirm(List<Word> invoiceNumConfirm) {
            this.invoiceNumConfirm = invoiceNumConfirm;
        }

        public List<Word> getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(List<Word> checkCode) {
            this.checkCode = checkCode;
        }

        public List<Word> getInvoiceNumDigit() {
            return invoiceNumDigit;
        }

        public void setInvoiceNumDigit(List<Word> invoiceNumDigit) {
            this.invoiceNumDigit = invoiceNumDigit;
        }

        public List<Word> getInvoiceDate() {
            return invoiceDate;
        }

        public void setInvoiceDate(List<Word> invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public List<Word> getPurchaserName() {
            return purchaserName;
        }

        public void setPurchaserName(List<Word> purchaserName) {
            this.purchaserName = purchaserName;
        }

        public List<Word> getPurchaserRegisterNum() {
            return purchaserRegisterNum;
        }

        public void setPurchaserRegisterNum(List<Word> purchaserRegisterNum) {
            this.purchaserRegisterNum = purchaserRegisterNum;
        }

        public List<Word> getPurchaserAddress() {
            return purchaserAddress;
        }

        public void setPurchaserAddress(List<Word> purchaserAddress) {
            this.purchaserAddress = purchaserAddress;
        }

        public List<Word> getPurchaserBank() {
            return purchaserBank;
        }

        public void setPurchaserBank(List<Word> purchaserBank) {
            this.purchaserBank = purchaserBank;
        }

        public List<Word> getPassword() {
            return password;
        }

        public void setPassword(List<Word> password) {
            this.password = password;
        }

        public List<Word> getProvince() {
            return province;
        }

        public void setProvince(List<Word> province) {
            this.province = province;
        }

        public List<Word> getCity() {
            return city;
        }

        public void setCity(List<Word> city) {
            this.city = city;
        }

        public List<Word> getSheetNum() {
            return sheetNum;
        }

        public void setSheetNum(List<Word> sheetNum) {
            this.sheetNum = sheetNum;
        }

        public List<Word> getAgent() {
            return agent;
        }

        public void setAgent(List<Word> agent) {
            this.agent = agent;
        }

        public List<Word> getOnlinePay() {
            return onlinePay;
        }

        public void setOnlinePay(List<Word> onlinePay) {
            this.onlinePay = onlinePay;
        }

        public List<Word> getSellerName() {
            return sellerName;
        }

        public void setSellerName(List<Word> sellerName) {
            this.sellerName = sellerName;
        }

        public List<Word> getSellerRegisterNum() {
            return sellerRegisterNum;
        }

        public void setSellerRegisterNum(List<Word> sellerRegisterNum) {
            this.sellerRegisterNum = sellerRegisterNum;
        }

        public List<Word> getSellerAddress() {
            return sellerAddress;
        }

        public void setSellerAddress(List<Word> sellerAddress) {
            this.sellerAddress = sellerAddress;
        }

        public List<Word> getSellerBank() {
            return sellerBank;
        }

        public void setSellerBank(List<Word> sellerBank) {
            this.sellerBank = sellerBank;
        }

        public List<Word> getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(List<Word> totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<Word> getTotalTax() {
            return totalTax;
        }

        public void setTotalTax(List<Word> totalTax) {
            this.totalTax = totalTax;
        }

        public List<Word> getAmountInWords() {
            return amountInWords;
        }

        public void setAmountInWords(List<Word> amountInWords) {
            this.amountInWords = amountInWords;
        }

        public List<Word> getAmountInFiguers() {
            return amountInFiguers;
        }

        public void setAmountInFiguers(List<Word> amountInFiguers) {
            this.amountInFiguers = amountInFiguers;
        }

        public List<Word> getPayee() {
            return payee;
        }

        public void setPayee(List<Word> payee) {
            this.payee = payee;
        }

        public List<Word> getChecker() {
            return checker;
        }

        public void setChecker(List<Word> checker) {
            this.checker = checker;
        }

        public List<Word> getNoteDrawer() {
            return noteDrawer;
        }

        public void setNoteDrawer(List<Word> noteDrawer) {
            this.noteDrawer = noteDrawer;
        }

        public List<Word> getRemarks() {
            return remarks;
        }

        public void setRemarks(List<Word> remarks) {
            this.remarks = remarks;
        }

        public List<Word> getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(List<Word> totalPage) {
            this.totalPage = totalPage;
        }

        public List<Word> getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(List<Word> currentPage) {
            this.currentPage = currentPage;
        }

        public List<Word> getSubTotalAmount() {
            return subTotalAmount;
        }

        public void setSubTotalAmount(List<Word> subTotalAmount) {
            this.subTotalAmount = subTotalAmount;
        }

        public List<Word> getSubTotalTax() {
            return subTotalTax;
        }

        public void setSubTotalTax(List<Word> subTotalTax) {
            this.subTotalTax = subTotalTax;
        }

        public List<Word> getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(List<Word> commodityName) {
            this.commodityName = commodityName;
        }

        public List<Word> getCommodityType() {
            return commodityType;
        }

        public void setCommodityType(List<Word> commodityType) {
            this.commodityType = commodityType;
        }

        public List<Word> getCommodityUnit() {
            return commodityUnit;
        }

        public void setCommodityUnit(List<Word> commodityUnit) {
            this.commodityUnit = commodityUnit;
        }

        public List<Word> getCommodityNum() {
            return commodityNum;
        }

        public void setCommodityNum(List<Word> commodityNum) {
            this.commodityNum = commodityNum;
        }

        public List<Word> getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(List<Word> commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public List<Word> getCommodityAmount() {
            return commodityAmount;
        }

        public void setCommodityAmount(List<Word> commodityAmount) {
            this.commodityAmount = commodityAmount;
        }

        public List<Word> getCommodityTaxRate() {
            return commodityTaxRate;
        }

        public void setCommodityTaxRate(List<Word> commodityTaxRate) {
            this.commodityTaxRate = commodityTaxRate;
        }

        public List<Word> getCommodityTax() {
            return commodityTax;
        }

        public void setCommodityTax(List<Word> commodityTax) {
            this.commodityTax = commodityTax;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            VatInvoiceResult that = (VatInvoiceResult) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(invoiceTypeOrg, that.invoiceTypeOrg) && Objects.equals(invoiceType, that.invoiceType) && Objects.equals(invoiceTag, that.invoiceTag) && Objects.equals(invoiceCode, that.invoiceCode) && Objects.equals(invoiceNum, that.invoiceNum) && Objects.equals(invoiceCodeConfirm, that.invoiceCodeConfirm) && Objects.equals(invoiceNumConfirm, that.invoiceNumConfirm) && Objects.equals(checkCode, that.checkCode) && Objects.equals(invoiceNumDigit, that.invoiceNumDigit) && Objects.equals(invoiceDate, that.invoiceDate) && Objects.equals(purchaserName, that.purchaserName) && Objects.equals(purchaserRegisterNum, that.purchaserRegisterNum) && Objects.equals(purchaserAddress, that.purchaserAddress) && Objects.equals(purchaserBank, that.purchaserBank) && Objects.equals(password, that.password) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(sheetNum, that.sheetNum) && Objects.equals(agent, that.agent) && Objects.equals(onlinePay, that.onlinePay) && Objects.equals(sellerName, that.sellerName) && Objects.equals(sellerRegisterNum, that.sellerRegisterNum) && Objects.equals(sellerAddress, that.sellerAddress) && Objects.equals(sellerBank, that.sellerBank) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(totalTax, that.totalTax) && Objects.equals(amountInWords, that.amountInWords) && Objects.equals(amountInFiguers, that.amountInFiguers) && Objects.equals(payee, that.payee) && Objects.equals(checker, that.checker) && Objects.equals(noteDrawer, that.noteDrawer) && Objects.equals(remarks, that.remarks) && Objects.equals(totalPage, that.totalPage) && Objects.equals(currentPage, that.currentPage) && Objects.equals(subTotalAmount, that.subTotalAmount) && Objects.equals(subTotalTax, that.subTotalTax) && Objects.equals(commodityName, that.commodityName) && Objects.equals(commodityType, that.commodityType) && Objects.equals(commodityUnit, that.commodityUnit) && Objects.equals(commodityNum, that.commodityNum) && Objects.equals(commodityPrice, that.commodityPrice) && Objects.equals(commodityAmount, that.commodityAmount) && Objects.equals(commodityTaxRate, that.commodityTaxRate) && Objects.equals(commodityTax, that.commodityTax);
        }

        @Override
        public int hashCode() {
            return Objects.hash(serviceType, invoiceTypeOrg, invoiceType, invoiceTag, invoiceCode, invoiceNum, invoiceCodeConfirm, invoiceNumConfirm, checkCode, invoiceNumDigit, invoiceDate, purchaserName, purchaserRegisterNum, purchaserAddress, purchaserBank, password, province, city, sheetNum, agent, onlinePay, sellerName, sellerRegisterNum, sellerAddress, sellerBank, totalAmount, totalTax, amountInWords, amountInFiguers, payee, checker, noteDrawer, remarks, totalPage, currentPage, subTotalAmount, subTotalTax, commodityName, commodityType, commodityUnit, commodityNum, commodityPrice, commodityAmount, commodityTaxRate, commodityTax);
        }

        @Override
        public String toString() {
            return "VatInvoiceResult{" +
                    "serviceType=" + serviceType +
                    ", invoiceTypeOrg=" + invoiceTypeOrg +
                    ", invoiceType=" + invoiceType +
                    ", invoiceTag=" + invoiceTag +
                    ", invoiceCode=" + invoiceCode +
                    ", invoiceNum=" + invoiceNum +
                    ", invoiceCodeConfirm=" + invoiceCodeConfirm +
                    ", invoiceNumConfirm=" + invoiceNumConfirm +
                    ", checkCode=" + checkCode +
                    ", invoiceNumDigit=" + invoiceNumDigit +
                    ", invoiceDate=" + invoiceDate +
                    ", purchaserName=" + purchaserName +
                    ", purchaserRegisterNum=" + purchaserRegisterNum +
                    ", purchaserAddress=" + purchaserAddress +
                    ", purchaserBank=" + purchaserBank +
                    ", password=" + password +
                    ", province=" + province +
                    ", city=" + city +
                    ", sheetNum=" + sheetNum +
                    ", agent=" + agent +
                    ", onlinePay=" + onlinePay +
                    ", sellerName=" + sellerName +
                    ", sellerRegisterNum=" + sellerRegisterNum +
                    ", sellerAddress=" + sellerAddress +
                    ", sellerBank=" + sellerBank +
                    ", totalAmount=" + totalAmount +
                    ", totalTax=" + totalTax +
                    ", amountInWords=" + amountInWords +
                    ", amountInFiguers=" + amountInFiguers +
                    ", payee=" + payee +
                    ", checker=" + checker +
                    ", noteDrawer=" + noteDrawer +
                    ", remarks=" + remarks +
                    ", totalPage=" + totalPage +
                    ", currentPage=" + currentPage +
                    ", subTotalAmount=" + subTotalAmount +
                    ", subTotalTax=" + subTotalTax +
                    ", commodityName=" + commodityName +
                    ", commodityType=" + commodityType +
                    ", commodityUnit=" + commodityUnit +
                    ", commodityNum=" + commodityNum +
                    ", commodityPrice=" + commodityPrice +
                    ", commodityAmount=" + commodityAmount +
                    ", commodityTaxRate=" + commodityTaxRate +
                    ", commodityTax=" + commodityTax +
                    '}';
        }
    }

    /**
     * 网约车行程单识别结果
     */
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

        public List<Word> getServiceType() {
            return serviceType;
        }

        public void setServiceType(List<Word> serviceType) {
            this.serviceType = serviceType;
        }

        public List<Word> getServiceProvider() {
            return serviceProvider;
        }

        public void setServiceProvider(List<Word> serviceProvider) {
            this.serviceProvider = serviceProvider;
        }

        public List<Word> getStartTime() {
            return startTime;
        }

        public void setStartTime(List<Word> startTime) {
            this.startTime = startTime;
        }

        public List<Word> getDestinationTime() {
            return destinationTime;
        }

        public void setDestinationTime(List<Word> destinationTime) {
            this.destinationTime = destinationTime;
        }

        public List<Word> getPhone() {
            return phone;
        }

        public void setPhone(List<Word> phone) {
            this.phone = phone;
        }

        public List<Word> getApplicationDate() {
            return applicationDate;
        }

        public void setApplicationDate(List<Word> applicationDate) {
            this.applicationDate = applicationDate;
        }

        public List<Word> getTotalFare() {
            return totalFare;
        }

        public void setTotalFare(List<Word> totalFare) {
            this.totalFare = totalFare;
        }

        public List<Word> getItemNum() {
            return itemNum;
        }

        public void setItemNum(List<Word> itemNum) {
            this.itemNum = itemNum;
        }

        public List<Detail> getDetails() {
            return Details;
        }

        public void setDetails(List<Detail> details) {
            Details = details;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            TaxiOnlineTicketResult that = (TaxiOnlineTicketResult) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(serviceProvider, that.serviceProvider) && Objects.equals(startTime, that.startTime) && Objects.equals(destinationTime, that.destinationTime) && Objects.equals(phone, that.phone) && Objects.equals(applicationDate, that.applicationDate) && Objects.equals(totalFare, that.totalFare) && Objects.equals(itemNum, that.itemNum) && Objects.equals(Details, that.Details);
        }

        @Override
        public int hashCode() {
            return Objects.hash(serviceType, serviceProvider, startTime, destinationTime, phone, applicationDate, totalFare, itemNum, Details);
        }

        @Override
        public String toString() {
            return "TaxiOnlineTicketResult{" +
                    "serviceType=" + serviceType +
                    ", serviceProvider=" + serviceProvider +
                    ", startTime=" + startTime +
                    ", destinationTime=" + destinationTime +
                    ", phone=" + phone +
                    ", applicationDate=" + applicationDate +
                    ", totalFare=" + totalFare +
                    ", itemNum=" + itemNum +
                    ", Details=" + Details +
                    '}';
        }

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

            public Word getItemId() {
                return itemId;
            }

            public void setItemId(Word itemId) {
                this.itemId = itemId;
            }

            public Word getItemProvider() {
                return itemProvider;
            }

            public void setItemProvider(Word itemProvider) {
                this.itemProvider = itemProvider;
            }

            public Word getPickupTime() {
                return pickupTime;
            }

            public void setPickupTime(Word pickupTime) {
                this.pickupTime = pickupTime;
            }

            public Word getPickupDate() {
                return pickupDate;
            }

            public void setPickupDate(Word pickupDate) {
                this.pickupDate = pickupDate;
            }

            public Word getCarType() {
                return carType;
            }

            public void setCarType(Word carType) {
                this.carType = carType;
            }

            public Word getDistance() {
                return distance;
            }

            public void setDistance(Word distance) {
                this.distance = distance;
            }

            public Word getStartPlace() {
                return startPlace;
            }

            public void setStartPlace(Word startPlace) {
                this.startPlace = startPlace;
            }

            public Word getDestinationPlace() {
                return destinationPlace;
            }

            public void setDestinationPlace(Word destinationPlace) {
                this.destinationPlace = destinationPlace;
            }

            public Word getCity() {
                return city;
            }

            public void setCity(Word city) {
                this.city = city;
            }

            public Word getFare() {
                return fare;
            }

            public void setFare(Word fare) {
                this.fare = fare;
            }

            @Override
            public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Detail detail = (Detail) o;
                return Objects.equals(itemId, detail.itemId) && Objects.equals(itemProvider, detail.itemProvider) && Objects.equals(pickupTime, detail.pickupTime) && Objects.equals(pickupDate, detail.pickupDate) && Objects.equals(carType, detail.carType) && Objects.equals(distance, detail.distance) && Objects.equals(startPlace, detail.startPlace) && Objects.equals(destinationPlace, detail.destinationPlace) && Objects.equals(city, detail.city) && Objects.equals(fare, detail.fare);
            }

            @Override
            public int hashCode() {
                return Objects.hash(itemId, itemProvider, pickupTime, pickupDate, carType, distance, startPlace, destinationPlace, city, fare);
            }

            @Override
            public String toString() {
                return "Detail{" +
                        "itemId=" + itemId +
                        ", itemProvider=" + itemProvider +
                        ", pickupTime=" + pickupTime +
                        ", pickupDate=" + pickupDate +
                        ", carType=" + carType +
                        ", distance=" + distance +
                        ", startPlace=" + startPlace +
                        ", destinationPlace=" + destinationPlace +
                        ", city=" + city +
                        ", fare=" + fare +
                        '}';
            }
        }
    }

    /**
     * 火车票识别结果
     */
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
         * 开票日期
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

        public List<Word> getServiceType() {
            return serviceType;
        }

        public void setServiceType(List<Word> serviceType) {
            this.serviceType = serviceType;
        }

        public List<Word> getTicketNum() {
            return ticketNum;
        }

        public void setTicketNum(List<Word> ticketNum) {
            this.ticketNum = ticketNum;
        }

        public List<Word> getStartingStation() {
            return startingStation;
        }

        public void setStartingStation(List<Word> startingStation) {
            this.startingStation = startingStation;
        }

        public List<Word> getTrainNum() {
            return trainNum;
        }

        public void setTrainNum(List<Word> trainNum) {
            this.trainNum = trainNum;
        }

        public List<Word> getDestinationStation() {
            return destinationStation;
        }

        public void setDestinationStation(List<Word> destinationStation) {
            this.destinationStation = destinationStation;
        }

        public List<Word> getDate() {
            return date;
        }

        public void setDate(List<Word> date) {
            this.date = date;
        }

        public List<Word> getTicketRates() {
            return ticketRates;
        }

        public void setTicketRates(List<Word> ticketRates) {
            this.ticketRates = ticketRates;
        }

        public List<Word> getSeatCategory() {
            return seatCategory;
        }

        public void setSeatCategory(List<Word> seatCategory) {
            this.seatCategory = seatCategory;
        }

        public List<Word> getName() {
            return name;
        }

        public void setName(List<Word> name) {
            this.name = name;
        }

        public List<Word> getIdCard() {
            return idCard;
        }

        public void setIdCard(List<Word> idCard) {
            this.idCard = idCard;
        }

        public List<Word> getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(List<Word> serialNumber) {
            this.serialNumber = serialNumber;
        }

        public List<Word> getSalesStation() {
            return salesStation;
        }

        public void setSalesStation(List<Word> salesStation) {
            this.salesStation = salesStation;
        }

        public List<Word> getTime() {
            return time;
        }

        public void setTime(List<Word> time) {
            this.time = time;
        }

        public List<Word> getSeatNum() {
            return seatNum;
        }

        public void setSeatNum(List<Word> seatNum) {
            this.seatNum = seatNum;
        }

        public List<Word> getWaitingArea() {
            return waitingArea;
        }

        public void setWaitingArea(List<Word> waitingArea) {
            this.waitingArea = waitingArea;
        }

        public List<Word> getRefundFlag() {
            return refundFlag;
        }

        public void setRefundFlag(List<Word> refundFlag) {
            this.refundFlag = refundFlag;
        }

        public List<Word> getInvoiceNum() {
            return invoiceNum;
        }

        public void setInvoiceNum(List<Word> invoiceNum) {
            this.invoiceNum = invoiceNum;
        }

        public List<Word> getInvoiceDate() {
            return invoiceDate;
        }

        public void setInvoiceDate(List<Word> invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public List<Word> getFare() {
            return fare;
        }

        public void setFare(List<Word> fare) {
            this.fare = fare;
        }

        public List<Word> getTaxRate() {
            return taxRate;
        }

        public void setTaxRate(List<Word> taxRate) {
            this.taxRate = taxRate;
        }

        public List<Word> getTax() {
            return tax;
        }

        public void setTax(List<Word> tax) {
            this.tax = tax;
        }

        public List<Word> getElecTicketNum() {
            return elecTicketNum;
        }

        public void setElecTicketNum(List<Word> elecTicketNum) {
            this.elecTicketNum = elecTicketNum;
        }

        public List<Word> getPurchaserName() {
            return purchaserName;
        }

        public void setPurchaserName(List<Word> purchaserName) {
            this.purchaserName = purchaserName;
        }

        public List<Word> getPurchaserRegisterNum() {
            return purchaserRegisterNum;
        }

        public void setPurchaserRegisterNum(List<Word> purchaserRegisterNum) {
            this.purchaserRegisterNum = purchaserRegisterNum;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            TrainTicketResult that = (TrainTicketResult) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(ticketNum, that.ticketNum) && Objects.equals(startingStation, that.startingStation) && Objects.equals(trainNum, that.trainNum) && Objects.equals(destinationStation, that.destinationStation) && Objects.equals(date, that.date) && Objects.equals(ticketRates, that.ticketRates) && Objects.equals(seatCategory, that.seatCategory) && Objects.equals(name, that.name) && Objects.equals(idCard, that.idCard) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(salesStation, that.salesStation) && Objects.equals(time, that.time) && Objects.equals(seatNum, that.seatNum) && Objects.equals(waitingArea, that.waitingArea) && Objects.equals(refundFlag, that.refundFlag) && Objects.equals(invoiceNum, that.invoiceNum) && Objects.equals(invoiceDate, that.invoiceDate) && Objects.equals(fare, that.fare) && Objects.equals(taxRate, that.taxRate) && Objects.equals(tax, that.tax) && Objects.equals(elecTicketNum, that.elecTicketNum) && Objects.equals(purchaserName, that.purchaserName) && Objects.equals(purchaserRegisterNum, that.purchaserRegisterNum);
        }

        @Override
        public int hashCode() {
            return Objects.hash(serviceType, ticketNum, startingStation, trainNum, destinationStation, date, ticketRates, seatCategory, name, idCard, serialNumber, salesStation, time, seatNum, waitingArea, refundFlag, invoiceNum, invoiceDate, fare, taxRate, tax, elecTicketNum, purchaserName, purchaserRegisterNum);
        }

        @Override
        public String toString() {
            return "TrainTicketResult{" +
                    "serviceType=" + serviceType +
                    ", ticketNum=" + ticketNum +
                    ", startingStation=" + startingStation +
                    ", trainNum=" + trainNum +
                    ", destinationStation=" + destinationStation +
                    ", date=" + date +
                    ", ticketRates=" + ticketRates +
                    ", seatCategory=" + seatCategory +
                    ", name=" + name +
                    ", idCard=" + idCard +
                    ", serialNumber=" + serialNumber +
                    ", salesStation=" + salesStation +
                    ", time=" + time +
                    ", seatNum=" + seatNum +
                    ", waitingArea=" + waitingArea +
                    ", refundFlag=" + refundFlag +
                    ", invoiceNum=" + invoiceNum +
                    ", invoiceDate=" + invoiceDate +
                    ", fare=" + fare +
                    ", taxRate=" + taxRate +
                    ", tax=" + tax +
                    ", elecTicketNum=" + elecTicketNum +
                    ", purchaserName=" + purchaserName +
                    ", purchaserRegisterNum=" + purchaserRegisterNum +
                    '}';
        }
    }

    /**
     * 飞机行程单
     */
    public static class AirTicketResult {
        /**
         * 发票消费类型。飞机行程单此字段固定输出：交通
         */
        @JsonProperty("ServiceType")
        private List<Word> serviceType;

        /**
         * 姓名
         */
        @JsonProperty("name")
        private List<Word> name;

        /**
         * 始发站
         */
        @JsonProperty("starting_station")
        private List<Word> startingStation;

        /**
         * 目的站
         */
        @JsonProperty("destination_station")
        private List<Word> destinationStation;

        /**
         * 航班号
         */
        @JsonProperty("flight")
        private List<Word> flight;

        /**
         * 日期
         */
        @JsonProperty("date")
        private List<Word> date;

        /**
         * 电子客票号码
         */
        @JsonProperty("ticket_number")
        private List<Word> ticketNumber;

        /**
         * 票价
         */
        @JsonProperty("fare")
        private List<Word> fare;

        /**
         * 民航发展基金/机建费
         */
        @JsonProperty("dev_fund")
        private List<Word> devFund;

        /**
         * 燃油附加费
         */
        @JsonProperty("oil_money")
        private List<Word> oilMoney;

        /**
         * 其他税费
         */
        @JsonProperty("other_tax")
        private List<Word> otherTax;

        /**
         * 合计金额
         */
        @JsonProperty("ticket_rates")
        private List<Word> ticketRates;

        /**
         * 填开日期
         */
        @JsonProperty("start_date")
        private List<Word> startDate;

        /**
         * 身份证号
         */
        @JsonProperty("id_no")
        private List<Word> idNo;

        /**
         * 承运人
         */
        @JsonProperty("carrier")
        private List<Word> carrier;

        /**
         * 时间
         */
        @JsonProperty("time")
        private List<Word> time;

        /**
         * 填开单位
         */
        @JsonProperty("issued_by")
        private List<Word> issuedBy;

        /**
         * 印刷序号
         */
        @JsonProperty("serial_number")
        private List<Word> serialNumber;

        /**
         * 保险费
         */
        @JsonProperty("insurance")
        private List<Word> insurance;

        /**
         * 客票级别
         */
        @JsonProperty("fare_basis")
        private List<Word> fareBasis;

        /**
         * 座位等级
         */
        @JsonProperty("class")
        private List<Word> clazz;

        /**
         * 销售单位号
         */
        @JsonProperty("agent_code")
        private List<Word> agentCode;

        /**
         * 签注
         */
        @JsonProperty("endorsement")
        private List<Word> endorsement;

        /**
         * 免费行李
         */
        @JsonProperty("allow")
        private List<Word> allow;

        /**
         * 验证码
         */
        @JsonProperty("ck")
        private List<Word> ck;

        /**
         * 客票生效日期
         */
        @JsonProperty("effective_date")
        private List<Word> effectiveDate;

        /**
         * 有效期截止日期
         */
        @JsonProperty("expiration_date")
        private List<Word> expirationDate;

        /**
         * 发票名称
         */
        @JsonProperty("invoice_type_org")
        private List<Word> invoiceTypeOrg;

        /**
         * 国内国际标识
         */
        @JsonProperty("identification")
        private List<Word> identification;

        /**
         * 开票状态
         */
        @JsonProperty("invoice_status")
        private List<Word> invoiceStatus;

        /**
         * 发票号码
         */
        @JsonProperty("invoice_num")
        private List<Word> invoiceNum;

        /**
         * 增值税税率
         */
        @JsonProperty("commodity_tax_rate")
        private List<Word> commodityTaxRate;

        /**
         * 增值税税额
         */
        @JsonProperty("commodity_tax")
        private List<Word> commodityTax;

        /**
         * 购买方名称
         */
        @JsonProperty("purchaser_name")
        private List<Word> purchaserName;

        /**
         * 统一社会信用代码/纳税人识别号
         */
        @JsonProperty("purchaser_register_num")
        private List<Word> purchaserRegisterNum;

        public List<Word> getServiceType() {
            return serviceType;
        }

        public void setServiceType(List<Word> serviceType) {
            this.serviceType = serviceType;
        }

        public List<Word> getName() {
            return name;
        }

        public void setName(List<Word> name) {
            this.name = name;
        }

        public List<Word> getStartingStation() {
            return startingStation;
        }

        public void setStartingStation(List<Word> startingStation) {
            this.startingStation = startingStation;
        }

        public List<Word> getDestinationStation() {
            return destinationStation;
        }

        public void setDestinationStation(List<Word> destinationStation) {
            this.destinationStation = destinationStation;
        }

        public List<Word> getFlight() {
            return flight;
        }

        public void setFlight(List<Word> flight) {
            this.flight = flight;
        }

        public List<Word> getDate() {
            return date;
        }

        public void setDate(List<Word> date) {
            this.date = date;
        }

        public List<Word> getTicketNumber() {
            return ticketNumber;
        }

        public void setTicketNumber(List<Word> ticketNumber) {
            this.ticketNumber = ticketNumber;
        }

        public List<Word> getFare() {
            return fare;
        }

        public void setFare(List<Word> fare) {
            this.fare = fare;
        }

        public List<Word> getDevFund() {
            return devFund;
        }

        public void setDevFund(List<Word> devFund) {
            this.devFund = devFund;
        }

        public List<Word> getOilMoney() {
            return oilMoney;
        }

        public void setOilMoney(List<Word> oilMoney) {
            this.oilMoney = oilMoney;
        }

        public List<Word> getOtherTax() {
            return otherTax;
        }

        public void setOtherTax(List<Word> otherTax) {
            this.otherTax = otherTax;
        }

        public List<Word> getTicketRates() {
            return ticketRates;
        }

        public void setTicketRates(List<Word> ticketRates) {
            this.ticketRates = ticketRates;
        }

        public List<Word> getStartDate() {
            return startDate;
        }

        public void setStartDate(List<Word> startDate) {
            this.startDate = startDate;
        }

        public List<Word> getIdNo() {
            return idNo;
        }

        public void setIdNo(List<Word> idNo) {
            this.idNo = idNo;
        }

        public List<Word> getCarrier() {
            return carrier;
        }

        public void setCarrier(List<Word> carrier) {
            this.carrier = carrier;
        }

        public List<Word> getTime() {
            return time;
        }

        public void setTime(List<Word> time) {
            this.time = time;
        }

        public List<Word> getIssuedBy() {
            return issuedBy;
        }

        public void setIssuedBy(List<Word> issuedBy) {
            this.issuedBy = issuedBy;
        }

        public List<Word> getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(List<Word> serialNumber) {
            this.serialNumber = serialNumber;
        }

        public List<Word> getInsurance() {
            return insurance;
        }

        public void setInsurance(List<Word> insurance) {
            this.insurance = insurance;
        }

        public List<Word> getFareBasis() {
            return fareBasis;
        }

        public void setFareBasis(List<Word> fareBasis) {
            this.fareBasis = fareBasis;
        }

        public List<Word> getClazz() {
            return clazz;
        }

        public void setClazz(List<Word> clazz) {
            this.clazz = clazz;
        }

        public List<Word> getAgentCode() {
            return agentCode;
        }

        public void setAgentCode(List<Word> agentCode) {
            this.agentCode = agentCode;
        }

        public List<Word> getEndorsement() {
            return endorsement;
        }

        public void setEndorsement(List<Word> endorsement) {
            this.endorsement = endorsement;
        }

        public List<Word> getAllow() {
            return allow;
        }

        public void setAllow(List<Word> allow) {
            this.allow = allow;
        }

        public List<Word> getCk() {
            return ck;
        }

        public void setCk(List<Word> ck) {
            this.ck = ck;
        }

        public List<Word> getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(List<Word> effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public List<Word> getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(List<Word> expirationDate) {
            this.expirationDate = expirationDate;
        }

        public List<Word> getInvoiceTypeOrg() {
            return invoiceTypeOrg;
        }

        public void setInvoiceTypeOrg(List<Word> invoiceTypeOrg) {
            this.invoiceTypeOrg = invoiceTypeOrg;
        }

        public List<Word> getIdentification() {
            return identification;
        }

        public void setIdentification(List<Word> identification) {
            this.identification = identification;
        }

        public List<Word> getInvoiceStatus() {
            return invoiceStatus;
        }

        public void setInvoiceStatus(List<Word> invoiceStatus) {
            this.invoiceStatus = invoiceStatus;
        }

        public List<Word> getInvoiceNum() {
            return invoiceNum;
        }

        public void setInvoiceNum(List<Word> invoiceNum) {
            this.invoiceNum = invoiceNum;
        }

        public List<Word> getCommodityTaxRate() {
            return commodityTaxRate;
        }

        public void setCommodityTaxRate(List<Word> commodityTaxRate) {
            this.commodityTaxRate = commodityTaxRate;
        }

        public List<Word> getCommodityTax() {
            return commodityTax;
        }

        public void setCommodityTax(List<Word> commodityTax) {
            this.commodityTax = commodityTax;
        }

        public List<Word> getPurchaserName() {
            return purchaserName;
        }

        public void setPurchaserName(List<Word> purchaserName) {
            this.purchaserName = purchaserName;
        }

        public List<Word> getPurchaserRegisterNum() {
            return purchaserRegisterNum;
        }

        public void setPurchaserRegisterNum(List<Word> purchaserRegisterNum) {
            this.purchaserRegisterNum = purchaserRegisterNum;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            AirTicketResult that = (AirTicketResult) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(name, that.name) && Objects.equals(startingStation, that.startingStation) && Objects.equals(destinationStation, that.destinationStation) && Objects.equals(flight, that.flight) && Objects.equals(date, that.date) && Objects.equals(ticketNumber, that.ticketNumber) && Objects.equals(fare, that.fare) && Objects.equals(devFund, that.devFund) && Objects.equals(oilMoney, that.oilMoney) && Objects.equals(otherTax, that.otherTax) && Objects.equals(ticketRates, that.ticketRates) && Objects.equals(startDate, that.startDate) && Objects.equals(idNo, that.idNo) && Objects.equals(carrier, that.carrier) && Objects.equals(time, that.time) && Objects.equals(issuedBy, that.issuedBy) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(insurance, that.insurance) && Objects.equals(fareBasis, that.fareBasis) && Objects.equals(clazz, that.clazz) && Objects.equals(agentCode, that.agentCode) && Objects.equals(endorsement, that.endorsement) && Objects.equals(allow, that.allow) && Objects.equals(ck, that.ck) && Objects.equals(effectiveDate, that.effectiveDate) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(invoiceTypeOrg, that.invoiceTypeOrg) && Objects.equals(identification, that.identification) && Objects.equals(invoiceStatus, that.invoiceStatus) && Objects.equals(invoiceNum, that.invoiceNum) && Objects.equals(commodityTaxRate, that.commodityTaxRate) && Objects.equals(commodityTax, that.commodityTax) && Objects.equals(purchaserName, that.purchaserName) && Objects.equals(purchaserRegisterNum, that.purchaserRegisterNum);
        }

        @Override
        public int hashCode() {
            return Objects.hash(serviceType, name, startingStation, destinationStation, flight, date, ticketNumber, fare, devFund, oilMoney, otherTax, ticketRates, startDate, idNo, carrier, time, issuedBy, serialNumber, insurance, fareBasis, clazz, agentCode, endorsement, allow, ck, effectiveDate, expirationDate, invoiceTypeOrg, identification, invoiceStatus, invoiceNum, commodityTaxRate, commodityTax, purchaserName, purchaserRegisterNum);
        }

        @Override
        public String toString() {
            return "AirTicketResult{" +
                    "serviceType=" + serviceType +
                    ", name=" + name +
                    ", startingStation=" + startingStation +
                    ", destinationStation=" + destinationStation +
                    ", flight=" + flight +
                    ", date=" + date +
                    ", ticketNumber=" + ticketNumber +
                    ", fare=" + fare +
                    ", devFund=" + devFund +
                    ", oilMoney=" + oilMoney +
                    ", otherTax=" + otherTax +
                    ", ticketRates=" + ticketRates +
                    ", startDate=" + startDate +
                    ", idNo=" + idNo +
                    ", carrier=" + carrier +
                    ", time=" + time +
                    ", issuedBy=" + issuedBy +
                    ", serialNumber=" + serialNumber +
                    ", insurance=" + insurance +
                    ", fareBasis=" + fareBasis +
                    ", clazz=" + clazz +
                    ", agentCode=" + agentCode +
                    ", endorsement=" + endorsement +
                    ", allow=" + allow +
                    ", ck=" + ck +
                    ", effectiveDate=" + effectiveDate +
                    ", expirationDate=" + expirationDate +
                    ", invoiceTypeOrg=" + invoiceTypeOrg +
                    ", identification=" + identification +
                    ", invoiceStatus=" + invoiceStatus +
                    ", invoiceNum=" + invoiceNum +
                    ", commodityTaxRate=" + commodityTaxRate +
                    ", commodityTax=" + commodityTax +
                    ", purchaserName=" + purchaserName +
                    ", purchaserRegisterNum=" + purchaserRegisterNum +
                    '}';
        }
    }

    public MultipleInvoiceVo toVo() {
        return MultipleInvoiceUtil.convert(this);
    }

    /**
     * @param ocrFlag 识别标识, 用于定位识别结果, 会赋值进每一个识别结果对象
     */
    public MultipleInvoiceVo toVo(String ocrFlag) {
        return MultipleInvoiceUtil.convert(this, ocrFlag);
    }
}
