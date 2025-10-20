package cn.toint.oktool.spring.boot.bdocr.model;

import cn.toint.oktool.spring.boot.bdocr.util.MultipleInvoiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Toint
 * @dete 2025/9/8
 */
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
    private List<VatInvoice> vatInvoice = new ArrayList<>();

    /**
     * 出租车票
     */
    private List<Object> taxiReceipt = new ArrayList<>();

    /**
     * 火车票
     */
    private List<TrainTicket> trainTicket = new ArrayList<>();

    /**
     * 定额发票
     */
    private List<Object> quotaInvoice = new ArrayList<>();

    /**
     * 飞机行程单
     */
    private List<AirTicket> airTicket = new ArrayList<>();

    /**
     * 卷票
     */
    private List<Object> rollNormalInvoice = new ArrayList<>();

    /**
     * 机打发票
     */
    private List<Object> printedInvoice = new ArrayList<>();

    /**
     * 机打电子发票
     */
    private List<Object> printedElecInvoice = new ArrayList<>();

    /**
     * 汽车票
     */
    private List<Object> busTicket = new ArrayList<>();

    /**
     * 过路过桥费发票
     */
    private List<Object> tollInvoice = new ArrayList<>();

    /**
     * 船票
     */
    private List<Object> ferryTicket = new ArrayList<>();

    /**
     * 机动车销售发票
     */
    private List<Object> motorVehicleInvoice = new ArrayList<>();

    /**
     * 二手车销售发票
     */
    private List<Object> usedVehicleInvoice = new ArrayList<>();

    /**
     * 网约车行程单
     */
    private List<TaxiOnlineTicket> taxiOnlineTicket = new ArrayList<>();

    /**
     * 限额发票
     */
    private List<Object> limitInvoice = new ArrayList<>();

    /**
     * 购物小票
     */
    private List<Object> shoppingReceipt = new ArrayList<>();

    /**
     * POS小票
     */
    private List<Object> posInvoice = new ArrayList<>();

    /**
     * 其他票据
     */
    private List<Object> others = new ArrayList<>();

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Integer getWordsResultNum() {
        return wordsResultNum;
    }

    public void setWordsResultNum(Integer wordsResultNum) {
        this.wordsResultNum = wordsResultNum;
    }

    public List<VatInvoice> getVatInvoice() {
        return vatInvoice;
    }

    public void setVatInvoice(List<VatInvoice> vatInvoice) {
        this.vatInvoice = vatInvoice;
    }

    public List<Object> getTaxiReceipt() {
        return taxiReceipt;
    }

    public void setTaxiReceipt(List<Object> taxiReceipt) {
        this.taxiReceipt = taxiReceipt;
    }

    public List<TrainTicket> getTrainTicket() {
        return trainTicket;
    }

    public void setTrainTicket(List<TrainTicket> trainTicket) {
        this.trainTicket = trainTicket;
    }

    public List<Object> getQuotaInvoice() {
        return quotaInvoice;
    }

    public void setQuotaInvoice(List<Object> quotaInvoice) {
        this.quotaInvoice = quotaInvoice;
    }

    public List<AirTicket> getAirTicket() {
        return airTicket;
    }

    public void setAirTicket(List<AirTicket> airTicket) {
        this.airTicket = airTicket;
    }

    public List<Object> getRollNormalInvoice() {
        return rollNormalInvoice;
    }

    public void setRollNormalInvoice(List<Object> rollNormalInvoice) {
        this.rollNormalInvoice = rollNormalInvoice;
    }

    public List<Object> getPrintedInvoice() {
        return printedInvoice;
    }

    public void setPrintedInvoice(List<Object> printedInvoice) {
        this.printedInvoice = printedInvoice;
    }

    public List<Object> getPrintedElecInvoice() {
        return printedElecInvoice;
    }

    public void setPrintedElecInvoice(List<Object> printedElecInvoice) {
        this.printedElecInvoice = printedElecInvoice;
    }

    public List<Object> getBusTicket() {
        return busTicket;
    }

    public void setBusTicket(List<Object> busTicket) {
        this.busTicket = busTicket;
    }

    public List<Object> getTollInvoice() {
        return tollInvoice;
    }

    public void setTollInvoice(List<Object> tollInvoice) {
        this.tollInvoice = tollInvoice;
    }

    public List<Object> getFerryTicket() {
        return ferryTicket;
    }

    public void setFerryTicket(List<Object> ferryTicket) {
        this.ferryTicket = ferryTicket;
    }

    public List<Object> getMotorVehicleInvoice() {
        return motorVehicleInvoice;
    }

    public void setMotorVehicleInvoice(List<Object> motorVehicleInvoice) {
        this.motorVehicleInvoice = motorVehicleInvoice;
    }

    public List<Object> getUsedVehicleInvoice() {
        return usedVehicleInvoice;
    }

    public void setUsedVehicleInvoice(List<Object> usedVehicleInvoice) {
        this.usedVehicleInvoice = usedVehicleInvoice;
    }

    public List<TaxiOnlineTicket> getTaxiOnlineTicket() {
        return taxiOnlineTicket;
    }

    public void setTaxiOnlineTicket(List<TaxiOnlineTicket> taxiOnlineTicket) {
        this.taxiOnlineTicket = taxiOnlineTicket;
    }

    public List<Object> getLimitInvoice() {
        return limitInvoice;
    }

    public void setLimitInvoice(List<Object> limitInvoice) {
        this.limitInvoice = limitInvoice;
    }

    public List<Object> getShoppingReceipt() {
        return shoppingReceipt;
    }

    public void setShoppingReceipt(List<Object> shoppingReceipt) {
        this.shoppingReceipt = shoppingReceipt;
    }

    public List<Object> getPosInvoice() {
        return posInvoice;
    }

    public void setPosInvoice(List<Object> posInvoice) {
        this.posInvoice = posInvoice;
    }

    public List<Object> getOthers() {
        return others;
    }

    public void setOthers(List<Object> others) {
        this.others = others;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MultipleInvoiceVo that = (MultipleInvoiceVo) o;
        return Objects.equals(logId, that.logId) && Objects.equals(wordsResultNum, that.wordsResultNum) && Objects.equals(vatInvoice, that.vatInvoice) && Objects.equals(taxiReceipt, that.taxiReceipt) && Objects.equals(trainTicket, that.trainTicket) && Objects.equals(quotaInvoice, that.quotaInvoice) && Objects.equals(airTicket, that.airTicket) && Objects.equals(rollNormalInvoice, that.rollNormalInvoice) && Objects.equals(printedInvoice, that.printedInvoice) && Objects.equals(printedElecInvoice, that.printedElecInvoice) && Objects.equals(busTicket, that.busTicket) && Objects.equals(tollInvoice, that.tollInvoice) && Objects.equals(ferryTicket, that.ferryTicket) && Objects.equals(motorVehicleInvoice, that.motorVehicleInvoice) && Objects.equals(usedVehicleInvoice, that.usedVehicleInvoice) && Objects.equals(taxiOnlineTicket, that.taxiOnlineTicket) && Objects.equals(limitInvoice, that.limitInvoice) && Objects.equals(shoppingReceipt, that.shoppingReceipt) && Objects.equals(posInvoice, that.posInvoice) && Objects.equals(others, that.others);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, wordsResultNum, vatInvoice, taxiReceipt, trainTicket, quotaInvoice, airTicket, rollNormalInvoice, printedInvoice, printedElecInvoice, busTicket, tollInvoice, ferryTicket, motorVehicleInvoice, usedVehicleInvoice, taxiOnlineTicket, limitInvoice, shoppingReceipt, posInvoice, others);
    }

    @Override
    public String toString() {
        return "MultipleInvoiceVo{" +
                "logId=" + logId +
                ", wordsResultNum=" + wordsResultNum +
                ", vatInvoice=" + vatInvoice +
                ", taxiReceipt=" + taxiReceipt +
                ", trainTicket=" + trainTicket +
                ", quotaInvoice=" + quotaInvoice +
                ", airTicket=" + airTicket +
                ", rollNormalInvoice=" + rollNormalInvoice +
                ", printedInvoice=" + printedInvoice +
                ", printedElecInvoice=" + printedElecInvoice +
                ", busTicket=" + busTicket +
                ", tollInvoice=" + tollInvoice +
                ", ferryTicket=" + ferryTicket +
                ", motorVehicleInvoice=" + motorVehicleInvoice +
                ", usedVehicleInvoice=" + usedVehicleInvoice +
                ", taxiOnlineTicket=" + taxiOnlineTicket +
                ", limitInvoice=" + limitInvoice +
                ", shoppingReceipt=" + shoppingReceipt +
                ", posInvoice=" + posInvoice +
                ", others=" + others +
                '}';
    }

    /**
     * 增值税发票识别结果
     *
     * @author Toint
     * @dete 2025/9/8
     */
    public static class VatInvoice extends OcrFlag {
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

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getInvoiceTypeOrg() {
            return invoiceTypeOrg;
        }

        public void setInvoiceTypeOrg(String invoiceTypeOrg) {
            this.invoiceTypeOrg = invoiceTypeOrg;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getInvoiceTag() {
            return invoiceTag;
        }

        public void setInvoiceTag(String invoiceTag) {
            this.invoiceTag = invoiceTag;
        }

        public String getInvoiceCode() {
            return invoiceCode;
        }

        public void setInvoiceCode(String invoiceCode) {
            this.invoiceCode = invoiceCode;
        }

        public String getInvoiceNum() {
            return invoiceNum;
        }

        public void setInvoiceNum(String invoiceNum) {
            this.invoiceNum = invoiceNum;
        }

        public String getInvoiceCodeConfirm() {
            return invoiceCodeConfirm;
        }

        public void setInvoiceCodeConfirm(String invoiceCodeConfirm) {
            this.invoiceCodeConfirm = invoiceCodeConfirm;
        }

        public String getInvoiceNumConfirm() {
            return invoiceNumConfirm;
        }

        public void setInvoiceNumConfirm(String invoiceNumConfirm) {
            this.invoiceNumConfirm = invoiceNumConfirm;
        }

        public String getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(String checkCode) {
            this.checkCode = checkCode;
        }

        public String getInvoiceNumDigit() {
            return invoiceNumDigit;
        }

        public void setInvoiceNumDigit(String invoiceNumDigit) {
            this.invoiceNumDigit = invoiceNumDigit;
        }

        public String getInvoiceDate() {
            return invoiceDate;
        }

        public void setInvoiceDate(String invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public String getPurchaserName() {
            return purchaserName;
        }

        public void setPurchaserName(String purchaserName) {
            this.purchaserName = purchaserName;
        }

        public String getPurchaserRegisterNum() {
            return purchaserRegisterNum;
        }

        public void setPurchaserRegisterNum(String purchaserRegisterNum) {
            this.purchaserRegisterNum = purchaserRegisterNum;
        }

        public String getPurchaserAddress() {
            return purchaserAddress;
        }

        public void setPurchaserAddress(String purchaserAddress) {
            this.purchaserAddress = purchaserAddress;
        }

        public String getPurchaserBank() {
            return purchaserBank;
        }

        public void setPurchaserBank(String purchaserBank) {
            this.purchaserBank = purchaserBank;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSheetNum() {
            return sheetNum;
        }

        public void setSheetNum(String sheetNum) {
            this.sheetNum = sheetNum;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getOnlinePay() {
            return onlinePay;
        }

        public void setOnlinePay(String onlinePay) {
            this.onlinePay = onlinePay;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerRegisterNum() {
            return sellerRegisterNum;
        }

        public void setSellerRegisterNum(String sellerRegisterNum) {
            this.sellerRegisterNum = sellerRegisterNum;
        }

        public String getSellerAddress() {
            return sellerAddress;
        }

        public void setSellerAddress(String sellerAddress) {
            this.sellerAddress = sellerAddress;
        }

        public String getSellerBank() {
            return sellerBank;
        }

        public void setSellerBank(String sellerBank) {
            this.sellerBank = sellerBank;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getTotalTax() {
            return totalTax;
        }

        public void setTotalTax(String totalTax) {
            this.totalTax = totalTax;
        }

        public String getAmountInWords() {
            return amountInWords;
        }

        public void setAmountInWords(String amountInWords) {
            this.amountInWords = amountInWords;
        }

        public String getAmountInFiguers() {
            return amountInFiguers;
        }

        public void setAmountInFiguers(String amountInFiguers) {
            this.amountInFiguers = amountInFiguers;
        }

        public String getPayee() {
            return payee;
        }

        public void setPayee(String payee) {
            this.payee = payee;
        }

        public String getChecker() {
            return checker;
        }

        public void setChecker(String checker) {
            this.checker = checker;
        }

        public String getNoteDrawer() {
            return noteDrawer;
        }

        public void setNoteDrawer(String noteDrawer) {
            this.noteDrawer = noteDrawer;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(String totalPage) {
            this.totalPage = totalPage;
        }

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public String getSubTotalAmount() {
            return subTotalAmount;
        }

        public void setSubTotalAmount(String subTotalAmount) {
            this.subTotalAmount = subTotalAmount;
        }

        public String getSubTotalTax() {
            return subTotalTax;
        }

        public void setSubTotalTax(String subTotalTax) {
            this.subTotalTax = subTotalTax;
        }

        public List<Detail> getDetails() {
            return details;
        }

        public void setDetails(List<Detail> details) {
            this.details = details;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            VatInvoice that = (VatInvoice) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(invoiceTypeOrg, that.invoiceTypeOrg) && Objects.equals(invoiceType, that.invoiceType) && Objects.equals(invoiceTag, that.invoiceTag) && Objects.equals(invoiceCode, that.invoiceCode) && Objects.equals(invoiceNum, that.invoiceNum) && Objects.equals(invoiceCodeConfirm, that.invoiceCodeConfirm) && Objects.equals(invoiceNumConfirm, that.invoiceNumConfirm) && Objects.equals(checkCode, that.checkCode) && Objects.equals(invoiceNumDigit, that.invoiceNumDigit) && Objects.equals(invoiceDate, that.invoiceDate) && Objects.equals(purchaserName, that.purchaserName) && Objects.equals(purchaserRegisterNum, that.purchaserRegisterNum) && Objects.equals(purchaserAddress, that.purchaserAddress) && Objects.equals(purchaserBank, that.purchaserBank) && Objects.equals(password, that.password) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(sheetNum, that.sheetNum) && Objects.equals(agent, that.agent) && Objects.equals(onlinePay, that.onlinePay) && Objects.equals(sellerName, that.sellerName) && Objects.equals(sellerRegisterNum, that.sellerRegisterNum) && Objects.equals(sellerAddress, that.sellerAddress) && Objects.equals(sellerBank, that.sellerBank) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(totalTax, that.totalTax) && Objects.equals(amountInWords, that.amountInWords) && Objects.equals(amountInFiguers, that.amountInFiguers) && Objects.equals(payee, that.payee) && Objects.equals(checker, that.checker) && Objects.equals(noteDrawer, that.noteDrawer) && Objects.equals(remarks, that.remarks) && Objects.equals(totalPage, that.totalPage) && Objects.equals(currentPage, that.currentPage) && Objects.equals(subTotalAmount, that.subTotalAmount) && Objects.equals(subTotalTax, that.subTotalTax) && Objects.equals(details, that.details);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), serviceType, invoiceTypeOrg, invoiceType, invoiceTag, invoiceCode, invoiceNum, invoiceCodeConfirm, invoiceNumConfirm, checkCode, invoiceNumDigit, invoiceDate, purchaserName, purchaserRegisterNum, purchaserAddress, purchaserBank, password, province, city, sheetNum, agent, onlinePay, sellerName, sellerRegisterNum, sellerAddress, sellerBank, totalAmount, totalTax, amountInWords, amountInFiguers, payee, checker, noteDrawer, remarks, totalPage, currentPage, subTotalAmount, subTotalTax, details);
        }

        @Override
        public String toString() {
            return "VatInvoice{" +
                    "serviceType='" + serviceType + '\'' +
                    ", invoiceTypeOrg='" + invoiceTypeOrg + '\'' +
                    ", invoiceType='" + invoiceType + '\'' +
                    ", invoiceTag='" + invoiceTag + '\'' +
                    ", invoiceCode='" + invoiceCode + '\'' +
                    ", invoiceNum='" + invoiceNum + '\'' +
                    ", invoiceCodeConfirm='" + invoiceCodeConfirm + '\'' +
                    ", invoiceNumConfirm='" + invoiceNumConfirm + '\'' +
                    ", checkCode='" + checkCode + '\'' +
                    ", invoiceNumDigit='" + invoiceNumDigit + '\'' +
                    ", invoiceDate='" + invoiceDate + '\'' +
                    ", purchaserName='" + purchaserName + '\'' +
                    ", purchaserRegisterNum='" + purchaserRegisterNum + '\'' +
                    ", purchaserAddress='" + purchaserAddress + '\'' +
                    ", purchaserBank='" + purchaserBank + '\'' +
                    ", password='" + password + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", sheetNum='" + sheetNum + '\'' +
                    ", agent='" + agent + '\'' +
                    ", onlinePay='" + onlinePay + '\'' +
                    ", sellerName='" + sellerName + '\'' +
                    ", sellerRegisterNum='" + sellerRegisterNum + '\'' +
                    ", sellerAddress='" + sellerAddress + '\'' +
                    ", sellerBank='" + sellerBank + '\'' +
                    ", totalAmount='" + totalAmount + '\'' +
                    ", totalTax='" + totalTax + '\'' +
                    ", amountInWords='" + amountInWords + '\'' +
                    ", amountInFiguers='" + amountInFiguers + '\'' +
                    ", payee='" + payee + '\'' +
                    ", checker='" + checker + '\'' +
                    ", noteDrawer='" + noteDrawer + '\'' +
                    ", remarks='" + remarks + '\'' +
                    ", totalPage='" + totalPage + '\'' +
                    ", currentPage='" + currentPage + '\'' +
                    ", subTotalAmount='" + subTotalAmount + '\'' +
                    ", subTotalTax='" + subTotalTax + '\'' +
                    ", details=" + details +
                    "} " + super.toString();
        }

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

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityType() {
                return commodityType;
            }

            public void setCommodityType(String commodityType) {
                this.commodityType = commodityType;
            }

            public String getCommodityUnit() {
                return commodityUnit;
            }

            public void setCommodityUnit(String commodityUnit) {
                this.commodityUnit = commodityUnit;
            }

            public String getCommodityNum() {
                return commodityNum;
            }

            public void setCommodityNum(String commodityNum) {
                this.commodityNum = commodityNum;
            }

            public String getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(String commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public String getCommodityAmount() {
                return commodityAmount;
            }

            public void setCommodityAmount(String commodityAmount) {
                this.commodityAmount = commodityAmount;
            }

            public String getCommodityTaxRate() {
                return commodityTaxRate;
            }

            public void setCommodityTaxRate(String commodityTaxRate) {
                this.commodityTaxRate = commodityTaxRate;
            }

            public String getCommodityTax() {
                return commodityTax;
            }

            public void setCommodityTax(String commodityTax) {
                this.commodityTax = commodityTax;
            }

            @Override
            public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Detail detail = (Detail) o;
                return Objects.equals(commodityName, detail.commodityName) && Objects.equals(commodityType, detail.commodityType) && Objects.equals(commodityUnit, detail.commodityUnit) && Objects.equals(commodityNum, detail.commodityNum) && Objects.equals(commodityPrice, detail.commodityPrice) && Objects.equals(commodityAmount, detail.commodityAmount) && Objects.equals(commodityTaxRate, detail.commodityTaxRate) && Objects.equals(commodityTax, detail.commodityTax);
            }

            @Override
            public int hashCode() {
                return Objects.hash(commodityName, commodityType, commodityUnit, commodityNum, commodityPrice, commodityAmount, commodityTaxRate, commodityTax);
            }

            @Override
            public String toString() {
                return "Detail{" +
                        "commodityName='" + commodityName + '\'' +
                        ", commodityType='" + commodityType + '\'' +
                        ", commodityUnit='" + commodityUnit + '\'' +
                        ", commodityNum='" + commodityNum + '\'' +
                        ", commodityPrice='" + commodityPrice + '\'' +
                        ", commodityAmount='" + commodityAmount + '\'' +
                        ", commodityTaxRate='" + commodityTaxRate + '\'' +
                        ", commodityTax='" + commodityTax + '\'' +
                        '}';
            }
        }
    }

    /**
     * 火车票
     *
     * @author Toint
     * @dete 2025/9/8
     */
    public static class TrainTicket extends OcrFlag {
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

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getTicketNum() {
            return ticketNum;
        }

        public void setTicketNum(String ticketNum) {
            this.ticketNum = ticketNum;
        }

        public String getStartingStation() {
            return startingStation;
        }

        public void setStartingStation(String startingStation) {
            this.startingStation = startingStation;
        }

        public String getTrainNum() {
            return trainNum;
        }

        public void setTrainNum(String trainNum) {
            this.trainNum = trainNum;
        }

        public String getDestinationStation() {
            return destinationStation;
        }

        public void setDestinationStation(String destinationStation) {
            this.destinationStation = destinationStation;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTicketRates() {
            return ticketRates;
        }

        public void setTicketRates(String ticketRates) {
            this.ticketRates = ticketRates;
        }

        public String getSeatCategory() {
            return seatCategory;
        }

        public void setSeatCategory(String seatCategory) {
            this.seatCategory = seatCategory;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getSalesStation() {
            return salesStation;
        }

        public void setSalesStation(String salesStation) {
            this.salesStation = salesStation;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSeatNum() {
            return seatNum;
        }

        public void setSeatNum(String seatNum) {
            this.seatNum = seatNum;
        }

        public String getWaitingArea() {
            return waitingArea;
        }

        public void setWaitingArea(String waitingArea) {
            this.waitingArea = waitingArea;
        }

        public String getRefundFlag() {
            return refundFlag;
        }

        public void setRefundFlag(String refundFlag) {
            this.refundFlag = refundFlag;
        }

        public String getInvoiceNum() {
            return invoiceNum;
        }

        public void setInvoiceNum(String invoiceNum) {
            this.invoiceNum = invoiceNum;
        }

        public String getInvoiceDate() {
            return invoiceDate;
        }

        public void setInvoiceDate(String invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public String getFare() {
            return fare;
        }

        public void setFare(String fare) {
            this.fare = fare;
        }

        public String getTaxRate() {
            return taxRate;
        }

        public void setTaxRate(String taxRate) {
            this.taxRate = taxRate;
        }

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public String getElecTicketNum() {
            return elecTicketNum;
        }

        public void setElecTicketNum(String elecTicketNum) {
            this.elecTicketNum = elecTicketNum;
        }

        public String getPurchaserName() {
            return purchaserName;
        }

        public void setPurchaserName(String purchaserName) {
            this.purchaserName = purchaserName;
        }

        public String getPurchaserRegisterNum() {
            return purchaserRegisterNum;
        }

        public void setPurchaserRegisterNum(String purchaserRegisterNum) {
            this.purchaserRegisterNum = purchaserRegisterNum;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            TrainTicket that = (TrainTicket) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(ticketNum, that.ticketNum) && Objects.equals(startingStation, that.startingStation) && Objects.equals(trainNum, that.trainNum) && Objects.equals(destinationStation, that.destinationStation) && Objects.equals(date, that.date) && Objects.equals(ticketRates, that.ticketRates) && Objects.equals(seatCategory, that.seatCategory) && Objects.equals(name, that.name) && Objects.equals(idCard, that.idCard) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(salesStation, that.salesStation) && Objects.equals(time, that.time) && Objects.equals(seatNum, that.seatNum) && Objects.equals(waitingArea, that.waitingArea) && Objects.equals(refundFlag, that.refundFlag) && Objects.equals(invoiceNum, that.invoiceNum) && Objects.equals(invoiceDate, that.invoiceDate) && Objects.equals(fare, that.fare) && Objects.equals(taxRate, that.taxRate) && Objects.equals(tax, that.tax) && Objects.equals(elecTicketNum, that.elecTicketNum) && Objects.equals(purchaserName, that.purchaserName) && Objects.equals(purchaserRegisterNum, that.purchaserRegisterNum);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), serviceType, ticketNum, startingStation, trainNum, destinationStation, date, ticketRates, seatCategory, name, idCard, serialNumber, salesStation, time, seatNum, waitingArea, refundFlag, invoiceNum, invoiceDate, fare, taxRate, tax, elecTicketNum, purchaserName, purchaserRegisterNum);
        }

        @Override
        public String toString() {
            return "TrainTicket{" +
                    "serviceType='" + serviceType + '\'' +
                    ", ticketNum='" + ticketNum + '\'' +
                    ", startingStation='" + startingStation + '\'' +
                    ", trainNum='" + trainNum + '\'' +
                    ", destinationStation='" + destinationStation + '\'' +
                    ", date='" + date + '\'' +
                    ", ticketRates='" + ticketRates + '\'' +
                    ", seatCategory='" + seatCategory + '\'' +
                    ", name='" + name + '\'' +
                    ", idCard='" + idCard + '\'' +
                    ", serialNumber='" + serialNumber + '\'' +
                    ", salesStation='" + salesStation + '\'' +
                    ", time='" + time + '\'' +
                    ", seatNum='" + seatNum + '\'' +
                    ", waitingArea='" + waitingArea + '\'' +
                    ", refundFlag='" + refundFlag + '\'' +
                    ", invoiceNum='" + invoiceNum + '\'' +
                    ", invoiceDate='" + invoiceDate + '\'' +
                    ", fare='" + fare + '\'' +
                    ", taxRate='" + taxRate + '\'' +
                    ", tax='" + tax + '\'' +
                    ", elecTicketNum='" + elecTicketNum + '\'' +
                    ", purchaserName='" + purchaserName + '\'' +
                    ", purchaserRegisterNum='" + purchaserRegisterNum + '\'' +
                    "} " + super.toString();
        }
    }

    /**
     * 网约车行程单识别结果
     */
    public static class TaxiOnlineTicket extends OcrFlag {
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

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getServiceProvider() {
            return serviceProvider;
        }

        public void setServiceProvider(String serviceProvider) {
            this.serviceProvider = serviceProvider;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getDestinationTime() {
            return destinationTime;
        }

        public void setDestinationTime(String destinationTime) {
            this.destinationTime = destinationTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getApplicationDate() {
            return applicationDate;
        }

        public void setApplicationDate(String applicationDate) {
            this.applicationDate = applicationDate;
        }

        public String getTotalFare() {
            return totalFare;
        }

        public void setTotalFare(String totalFare) {
            this.totalFare = totalFare;
        }

        public String getItemNum() {
            return itemNum;
        }

        public void setItemNum(String itemNum) {
            this.itemNum = itemNum;
        }

        public List<Detail> getDetails() {
            return details;
        }

        public void setDetails(List<Detail> details) {
            this.details = details;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            TaxiOnlineTicket that = (TaxiOnlineTicket) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(serviceProvider, that.serviceProvider) && Objects.equals(startTime, that.startTime) && Objects.equals(destinationTime, that.destinationTime) && Objects.equals(phone, that.phone) && Objects.equals(applicationDate, that.applicationDate) && Objects.equals(totalFare, that.totalFare) && Objects.equals(itemNum, that.itemNum) && Objects.equals(details, that.details);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), serviceType, serviceProvider, startTime, destinationTime, phone, applicationDate, totalFare, itemNum, details);
        }

        @Override
        public String toString() {
            return "TaxiOnlineTicket{" +
                    "serviceType='" + serviceType + '\'' +
                    ", serviceProvider='" + serviceProvider + '\'' +
                    ", startTime='" + startTime + '\'' +
                    ", destinationTime='" + destinationTime + '\'' +
                    ", phone='" + phone + '\'' +
                    ", applicationDate='" + applicationDate + '\'' +
                    ", totalFare='" + totalFare + '\'' +
                    ", itemNum='" + itemNum + '\'' +
                    ", details=" + details +
                    "} " + super.toString();
        }

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

            public String getItemId() {
                return itemId;
            }

            public void setItemId(String itemId) {
                this.itemId = itemId;
            }

            public String getItemProvider() {
                return itemProvider;
            }

            public void setItemProvider(String itemProvider) {
                this.itemProvider = itemProvider;
            }

            public String getPickupTime() {
                return pickupTime;
            }

            public void setPickupTime(String pickupTime) {
                this.pickupTime = pickupTime;
            }

            public String getPickupDate() {
                return pickupDate;
            }

            public void setPickupDate(String pickupDate) {
                this.pickupDate = pickupDate;
            }

            public String getCarType() {
                return carType;
            }

            public void setCarType(String carType) {
                this.carType = carType;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getStartPlace() {
                return startPlace;
            }

            public void setStartPlace(String startPlace) {
                this.startPlace = startPlace;
            }

            public String getDestinationPlace() {
                return destinationPlace;
            }

            public void setDestinationPlace(String destinationPlace) {
                this.destinationPlace = destinationPlace;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getFare() {
                return fare;
            }

            public void setFare(String fare) {
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
                        "itemId='" + itemId + '\'' +
                        ", itemProvider='" + itemProvider + '\'' +
                        ", pickupTime='" + pickupTime + '\'' +
                        ", pickupDate='" + pickupDate + '\'' +
                        ", carType='" + carType + '\'' +
                        ", distance='" + distance + '\'' +
                        ", startPlace='" + startPlace + '\'' +
                        ", destinationPlace='" + destinationPlace + '\'' +
                        ", city='" + city + '\'' +
                        ", fare='" + fare + '\'' +
                        '}';
            }
        }
    }

    /**
     * 飞机行程单
     */
    public static class AirTicket extends OcrFlag {
        /**
         * 发票消费类型。飞机行程单此字段固定输出：交通
         */
        private String serviceType;

        /**
         * 姓名
         */
        private String name;

        /**
         * 始发站
         */
        private String startingStation;

        /**
         * 目的站
         */
        private String destinationStation;

        /**
         * 航班号
         */
        private String flight;

        /**
         * 日期
         */
        private String date;

        /**
         * 电子客票号码
         */
        private String ticketNumber;

        /**
         * 票价
         */
        private String fare;

        /**
         * 民航发展基金/机建费
         */
        private String devFund;

        /**
         * 燃油附加费
         */
        private String oilMoney;

        /**
         * 其他税费
         */
        private String otherTax;

        /**
         * 合计金额
         */
        private String ticketRates;

        /**
         * 填开日期
         */
        private String startDate;

        /**
         * 身份证号
         */
        private String idNo;

        /**
         * 承运人
         */
        private String carrier;

        /**
         * 时间
         */
        private String time;

        /**
         * 填开单位
         */
        private String issuedBy;

        /**
         * 印刷序号
         */
        private String serialNumber;

        /**
         * 保险费
         */
        private String insurance;

        /**
         * 客票级别
         */
        private String fareBasis;

        /**
         * 座位等级
         */
        private String clazz;

        /**
         * 销售单位号
         */
        private String agentCode;

        /**
         * 签注
         */
        private String endorsement;

        /**
         * 免费行李
         */
        private String allow;

        /**
         * 验证码
         */
        private String ck;

        /**
         * 客票生效日期
         */
        private String effectiveDate;

        /**
         * 有效期截止日期
         */
        private String expirationDate;

        /**
         * 发票名称
         */
        private String invoiceTypeOrg;

        /**
         * 国内国际标识
         */
        private String identification;

        /**
         * 开票状态
         */
        private String invoiceStatus;

        /**
         * 发票号码
         */
        private String invoiceNum;

        /**
         * 增值税税率
         */
        private String commodityTaxRate;

        /**
         * 增值税税额
         */
        private String commodityTax;

        /**
         * 购买方名称
         */
        private String purchaserName;

        /**
         * 统一社会信用代码/纳税人识别号
         */
        private String purchaserRegisterNum;

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartingStation() {
            return startingStation;
        }

        public void setStartingStation(String startingStation) {
            this.startingStation = startingStation;
        }

        public String getDestinationStation() {
            return destinationStation;
        }

        public void setDestinationStation(String destinationStation) {
            this.destinationStation = destinationStation;
        }

        public String getFlight() {
            return flight;
        }

        public void setFlight(String flight) {
            this.flight = flight;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTicketNumber() {
            return ticketNumber;
        }

        public void setTicketNumber(String ticketNumber) {
            this.ticketNumber = ticketNumber;
        }

        public String getFare() {
            return fare;
        }

        public void setFare(String fare) {
            this.fare = fare;
        }

        public String getDevFund() {
            return devFund;
        }

        public void setDevFund(String devFund) {
            this.devFund = devFund;
        }

        public String getOilMoney() {
            return oilMoney;
        }

        public void setOilMoney(String oilMoney) {
            this.oilMoney = oilMoney;
        }

        public String getOtherTax() {
            return otherTax;
        }

        public void setOtherTax(String otherTax) {
            this.otherTax = otherTax;
        }

        public String getTicketRates() {
            return ticketRates;
        }

        public void setTicketRates(String ticketRates) {
            this.ticketRates = ticketRates;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIssuedBy() {
            return issuedBy;
        }

        public void setIssuedBy(String issuedBy) {
            this.issuedBy = issuedBy;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public String getFareBasis() {
            return fareBasis;
        }

        public void setFareBasis(String fareBasis) {
            this.fareBasis = fareBasis;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getAgentCode() {
            return agentCode;
        }

        public void setAgentCode(String agentCode) {
            this.agentCode = agentCode;
        }

        public String getEndorsement() {
            return endorsement;
        }

        public void setEndorsement(String endorsement) {
            this.endorsement = endorsement;
        }

        public String getAllow() {
            return allow;
        }

        public void setAllow(String allow) {
            this.allow = allow;
        }

        public String getCk() {
            return ck;
        }

        public void setCk(String ck) {
            this.ck = ck;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        public String getInvoiceTypeOrg() {
            return invoiceTypeOrg;
        }

        public void setInvoiceTypeOrg(String invoiceTypeOrg) {
            this.invoiceTypeOrg = invoiceTypeOrg;
        }

        public String getIdentification() {
            return identification;
        }

        public void setIdentification(String identification) {
            this.identification = identification;
        }

        public String getInvoiceStatus() {
            return invoiceStatus;
        }

        public void setInvoiceStatus(String invoiceStatus) {
            this.invoiceStatus = invoiceStatus;
        }

        public String getInvoiceNum() {
            return invoiceNum;
        }

        public void setInvoiceNum(String invoiceNum) {
            this.invoiceNum = invoiceNum;
        }

        public String getCommodityTaxRate() {
            return commodityTaxRate;
        }

        public void setCommodityTaxRate(String commodityTaxRate) {
            this.commodityTaxRate = commodityTaxRate;
        }

        public String getCommodityTax() {
            return commodityTax;
        }

        public void setCommodityTax(String commodityTax) {
            this.commodityTax = commodityTax;
        }

        public String getPurchaserName() {
            return purchaserName;
        }

        public void setPurchaserName(String purchaserName) {
            this.purchaserName = purchaserName;
        }

        public String getPurchaserRegisterNum() {
            return purchaserRegisterNum;
        }

        public void setPurchaserRegisterNum(String purchaserRegisterNum) {
            this.purchaserRegisterNum = purchaserRegisterNum;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            AirTicket airTicket = (AirTicket) o;
            return Objects.equals(serviceType, airTicket.serviceType) && Objects.equals(name, airTicket.name) && Objects.equals(startingStation, airTicket.startingStation) && Objects.equals(destinationStation, airTicket.destinationStation) && Objects.equals(flight, airTicket.flight) && Objects.equals(date, airTicket.date) && Objects.equals(ticketNumber, airTicket.ticketNumber) && Objects.equals(fare, airTicket.fare) && Objects.equals(devFund, airTicket.devFund) && Objects.equals(oilMoney, airTicket.oilMoney) && Objects.equals(otherTax, airTicket.otherTax) && Objects.equals(ticketRates, airTicket.ticketRates) && Objects.equals(startDate, airTicket.startDate) && Objects.equals(idNo, airTicket.idNo) && Objects.equals(carrier, airTicket.carrier) && Objects.equals(time, airTicket.time) && Objects.equals(issuedBy, airTicket.issuedBy) && Objects.equals(serialNumber, airTicket.serialNumber) && Objects.equals(insurance, airTicket.insurance) && Objects.equals(fareBasis, airTicket.fareBasis) && Objects.equals(clazz, airTicket.clazz) && Objects.equals(agentCode, airTicket.agentCode) && Objects.equals(endorsement, airTicket.endorsement) && Objects.equals(allow, airTicket.allow) && Objects.equals(ck, airTicket.ck) && Objects.equals(effectiveDate, airTicket.effectiveDate) && Objects.equals(expirationDate, airTicket.expirationDate) && Objects.equals(invoiceTypeOrg, airTicket.invoiceTypeOrg) && Objects.equals(identification, airTicket.identification) && Objects.equals(invoiceStatus, airTicket.invoiceStatus) && Objects.equals(invoiceNum, airTicket.invoiceNum) && Objects.equals(commodityTaxRate, airTicket.commodityTaxRate) && Objects.equals(commodityTax, airTicket.commodityTax) && Objects.equals(purchaserName, airTicket.purchaserName) && Objects.equals(purchaserRegisterNum, airTicket.purchaserRegisterNum);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), serviceType, name, startingStation, destinationStation, flight, date, ticketNumber, fare, devFund, oilMoney, otherTax, ticketRates, startDate, idNo, carrier, time, issuedBy, serialNumber, insurance, fareBasis, clazz, agentCode, endorsement, allow, ck, effectiveDate, expirationDate, invoiceTypeOrg, identification, invoiceStatus, invoiceNum, commodityTaxRate, commodityTax, purchaserName, purchaserRegisterNum);
        }

        @Override
        public String toString() {
            return "AirTicket{" +
                    "serviceType='" + serviceType + '\'' +
                    ", name='" + name + '\'' +
                    ", startingStation='" + startingStation + '\'' +
                    ", destinationStation='" + destinationStation + '\'' +
                    ", flight='" + flight + '\'' +
                    ", date='" + date + '\'' +
                    ", ticketNumber='" + ticketNumber + '\'' +
                    ", fare='" + fare + '\'' +
                    ", devFund='" + devFund + '\'' +
                    ", oilMoney='" + oilMoney + '\'' +
                    ", otherTax='" + otherTax + '\'' +
                    ", ticketRates='" + ticketRates + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", idNo='" + idNo + '\'' +
                    ", carrier='" + carrier + '\'' +
                    ", time='" + time + '\'' +
                    ", issuedBy='" + issuedBy + '\'' +
                    ", serialNumber='" + serialNumber + '\'' +
                    ", insurance='" + insurance + '\'' +
                    ", fareBasis='" + fareBasis + '\'' +
                    ", clazz='" + clazz + '\'' +
                    ", agentCode='" + agentCode + '\'' +
                    ", endorsement='" + endorsement + '\'' +
                    ", allow='" + allow + '\'' +
                    ", ck='" + ck + '\'' +
                    ", effectiveDate='" + effectiveDate + '\'' +
                    ", expirationDate='" + expirationDate + '\'' +
                    ", invoiceTypeOrg='" + invoiceTypeOrg + '\'' +
                    ", identification='" + identification + '\'' +
                    ", invoiceStatus='" + invoiceStatus + '\'' +
                    ", invoiceNum='" + invoiceNum + '\'' +
                    ", commodityTaxRate='" + commodityTaxRate + '\'' +
                    ", commodityTax='" + commodityTax + '\'' +
                    ", purchaserName='" + purchaserName + '\'' +
                    ", purchaserRegisterNum='" + purchaserRegisterNum + '\'' +
                    "} " + super.toString();
        }
    }
}
