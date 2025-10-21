package cn.toint.oktool.spring.boot.bdocr.model;

import java.util.List;
import java.util.Objects;

/**
 * 增值税发票识别结果
 *
 * @author Toint
 * @since 2025/9/8
 */
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

    public WordsResult getWordsResult() {
        return wordsResult;
    }

    public void setWordsResult(WordsResult wordsResult) {
        this.wordsResult = wordsResult;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VatInvoiceVo that = (VatInvoiceVo) o;
        return Objects.equals(logId, that.logId) && Objects.equals(wordsResultNum, that.wordsResultNum) && Objects.equals(wordsResult, that.wordsResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, wordsResultNum, wordsResult);
    }

    @Override
    public String toString() {
        return "VatInvoiceVo{" +
                "logId=" + logId +
                ", wordsResultNum=" + wordsResultNum +
                ", wordsResult=" + wordsResult +
                '}';
    }

    public static class WordsResult extends OcrFlag {
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

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getInvoiceTypeOrg() {
            return invoiceTypeOrg;
        }

        public void setInvoiceTypeOrg(String invoiceTypeOrg) {
            this.invoiceTypeOrg = invoiceTypeOrg;
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

        public String getInvoiceNumDigit() {
            return invoiceNumDigit;
        }

        public void setInvoiceNumDigit(String invoiceNumDigit) {
            this.invoiceNumDigit = invoiceNumDigit;
        }

        public String getInvoiceTag() {
            return invoiceTag;
        }

        public void setInvoiceTag(String invoiceTag) {
            this.invoiceTag = invoiceTag;
        }

        public String getMachineNum() {
            return machineNum;
        }

        public void setMachineNum(String machineNum) {
            this.machineNum = machineNum;
        }

        public String getMachineCode() {
            return machineCode;
        }

        public void setMachineCode(String machineCode) {
            this.machineCode = machineCode;
        }

        public String getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(String checkCode) {
            this.checkCode = checkCode;
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

        public List<Detail> getDetails() {
            return details;
        }

        public void setDetails(List<Detail> details) {
            this.details = details;
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

        public String getAmountInFigures() {
            return amountInFigures;
        }

        public void setAmountInFigures(String amountInFigures) {
            this.amountInFigures = amountInFigures;
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

        public String getCompanySeal() {
            return companySeal;
        }

        public void setCompanySeal(String companySeal) {
            this.companySeal = companySeal;
        }

        public String getSealInfo() {
            return sealInfo;
        }

        public void setSealInfo(String sealInfo) {
            this.sealInfo = sealInfo;
        }

        public String getSupervisionSeal() {
            return supervisionSeal;
        }

        public void setSupervisionSeal(String supervisionSeal) {
            this.supervisionSeal = supervisionSeal;
        }

        public String getSupervisionSealInfo() {
            return supervisionSealInfo;
        }

        public void setSupervisionSealInfo(String supervisionSealInfo) {
            this.supervisionSealInfo = supervisionSealInfo;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            WordsResult that = (WordsResult) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(invoiceType, that.invoiceType) && Objects.equals(invoiceTypeOrg, that.invoiceTypeOrg) && Objects.equals(invoiceCode, that.invoiceCode) && Objects.equals(invoiceNum, that.invoiceNum) && Objects.equals(invoiceCodeConfirm, that.invoiceCodeConfirm) && Objects.equals(invoiceNumConfirm, that.invoiceNumConfirm) && Objects.equals(invoiceNumDigit, that.invoiceNumDigit) && Objects.equals(invoiceTag, that.invoiceTag) && Objects.equals(machineNum, that.machineNum) && Objects.equals(machineCode, that.machineCode) && Objects.equals(checkCode, that.checkCode) && Objects.equals(invoiceDate, that.invoiceDate) && Objects.equals(purchaserName, that.purchaserName) && Objects.equals(purchaserRegisterNum, that.purchaserRegisterNum) && Objects.equals(purchaserAddress, that.purchaserAddress) && Objects.equals(purchaserBank, that.purchaserBank) && Objects.equals(password, that.password) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(sheetNum, that.sheetNum) && Objects.equals(agent, that.agent) && Objects.equals(details, that.details) && Objects.equals(onlinePay, that.onlinePay) && Objects.equals(sellerName, that.sellerName) && Objects.equals(sellerRegisterNum, that.sellerRegisterNum) && Objects.equals(sellerAddress, that.sellerAddress) && Objects.equals(sellerBank, that.sellerBank) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(totalTax, that.totalTax) && Objects.equals(amountInWords, that.amountInWords) && Objects.equals(amountInFigures, that.amountInFigures) && Objects.equals(payee, that.payee) && Objects.equals(checker, that.checker) && Objects.equals(noteDrawer, that.noteDrawer) && Objects.equals(remarks, that.remarks) && Objects.equals(companySeal, that.companySeal) && Objects.equals(sealInfo, that.sealInfo) && Objects.equals(supervisionSeal, that.supervisionSeal) && Objects.equals(supervisionSealInfo, that.supervisionSealInfo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), serviceType, invoiceType, invoiceTypeOrg, invoiceCode, invoiceNum, invoiceCodeConfirm, invoiceNumConfirm, invoiceNumDigit, invoiceTag, machineNum, machineCode, checkCode, invoiceDate, purchaserName, purchaserRegisterNum, purchaserAddress, purchaserBank, password, province, city, sheetNum, agent, details, onlinePay, sellerName, sellerRegisterNum, sellerAddress, sellerBank, totalAmount, totalTax, amountInWords, amountInFigures, payee, checker, noteDrawer, remarks, companySeal, sealInfo, supervisionSeal, supervisionSealInfo);
        }

        @Override
        public String toString() {
            return "WordsResult{" +
                    "serviceType='" + serviceType + '\'' +
                    ", invoiceType='" + invoiceType + '\'' +
                    ", invoiceTypeOrg='" + invoiceTypeOrg + '\'' +
                    ", invoiceCode='" + invoiceCode + '\'' +
                    ", invoiceNum='" + invoiceNum + '\'' +
                    ", invoiceCodeConfirm='" + invoiceCodeConfirm + '\'' +
                    ", invoiceNumConfirm='" + invoiceNumConfirm + '\'' +
                    ", invoiceNumDigit='" + invoiceNumDigit + '\'' +
                    ", invoiceTag='" + invoiceTag + '\'' +
                    ", machineNum='" + machineNum + '\'' +
                    ", machineCode='" + machineCode + '\'' +
                    ", checkCode='" + checkCode + '\'' +
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
                    ", details=" + details +
                    ", onlinePay='" + onlinePay + '\'' +
                    ", sellerName='" + sellerName + '\'' +
                    ", sellerRegisterNum='" + sellerRegisterNum + '\'' +
                    ", sellerAddress='" + sellerAddress + '\'' +
                    ", sellerBank='" + sellerBank + '\'' +
                    ", totalAmount='" + totalAmount + '\'' +
                    ", totalTax='" + totalTax + '\'' +
                    ", amountInWords='" + amountInWords + '\'' +
                    ", amountInFigures='" + amountInFigures + '\'' +
                    ", payee='" + payee + '\'' +
                    ", checker='" + checker + '\'' +
                    ", noteDrawer='" + noteDrawer + '\'' +
                    ", remarks='" + remarks + '\'' +
                    ", companySeal='" + companySeal + '\'' +
                    ", sealInfo='" + sealInfo + '\'' +
                    ", supervisionSeal='" + supervisionSeal + '\'' +
                    ", supervisionSealInfo='" + supervisionSealInfo + '\'' +
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
}
