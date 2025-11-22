/*
 * Copyright 2025 Toint (599818663@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.toint.oktool.spring.boot.bdocr.model;

import cn.toint.oktool.spring.boot.bdocr.util.VatInvoiceUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * 增值税发票识别结果
 *
 * @author Toint
 * @since 2025/9/8
 */
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
        if (!super.equals(o)) return false;
        VatInvoiceResponse that = (VatInvoiceResponse) o;
        return Objects.equals(wordsResultNum, that.wordsResultNum) && Objects.equals(wordsResult, that.wordsResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wordsResultNum, wordsResult);
    }

    @Override
    public String toString() {
        return "VatInvoiceResponse{" +
                "wordsResultNum=" + wordsResultNum +
                ", wordsResult=" + wordsResult +
                "} " + super.toString();
    }

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
            WordsResult that = (WordsResult) o;
            return Objects.equals(serviceType, that.serviceType) && Objects.equals(invoiceType, that.invoiceType) && Objects.equals(invoiceTypeOrg, that.invoiceTypeOrg) && Objects.equals(invoiceCode, that.invoiceCode) && Objects.equals(invoiceNum, that.invoiceNum) && Objects.equals(invoiceCodeConfirm, that.invoiceCodeConfirm) && Objects.equals(invoiceNumConfirm, that.invoiceNumConfirm) && Objects.equals(invoiceNumDigit, that.invoiceNumDigit) && Objects.equals(invoiceTag, that.invoiceTag) && Objects.equals(machineNum, that.machineNum) && Objects.equals(machineCode, that.machineCode) && Objects.equals(checkCode, that.checkCode) && Objects.equals(invoiceDate, that.invoiceDate) && Objects.equals(purchaserName, that.purchaserName) && Objects.equals(purchaserRegisterNum, that.purchaserRegisterNum) && Objects.equals(purchaserAddress, that.purchaserAddress) && Objects.equals(purchaserBank, that.purchaserBank) && Objects.equals(password, that.password) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(sheetNum, that.sheetNum) && Objects.equals(agent, that.agent) && Objects.equals(commodityName, that.commodityName) && Objects.equals(commodityType, that.commodityType) && Objects.equals(commodityUnit, that.commodityUnit) && Objects.equals(commodityNum, that.commodityNum) && Objects.equals(commodityPrice, that.commodityPrice) && Objects.equals(commodityAmount, that.commodityAmount) && Objects.equals(commodityTaxRate, that.commodityTaxRate) && Objects.equals(commodityTax, that.commodityTax) && Objects.equals(onlinePay, that.onlinePay) && Objects.equals(sellerName, that.sellerName) && Objects.equals(sellerRegisterNum, that.sellerRegisterNum) && Objects.equals(sellerAddress, that.sellerAddress) && Objects.equals(sellerBank, that.sellerBank) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(totalTax, that.totalTax) && Objects.equals(amountInWords, that.amountInWords) && Objects.equals(amountInFigures, that.amountInFigures) && Objects.equals(payee, that.payee) && Objects.equals(checker, that.checker) && Objects.equals(noteDrawer, that.noteDrawer) && Objects.equals(remarks, that.remarks) && Objects.equals(companySeal, that.companySeal) && Objects.equals(sealInfo, that.sealInfo) && Objects.equals(supervisionSeal, that.supervisionSeal) && Objects.equals(supervisionSealInfo, that.supervisionSealInfo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(serviceType, invoiceType, invoiceTypeOrg, invoiceCode, invoiceNum, invoiceCodeConfirm, invoiceNumConfirm, invoiceNumDigit, invoiceTag, machineNum, machineCode, checkCode, invoiceDate, purchaserName, purchaserRegisterNum, purchaserAddress, purchaserBank, password, province, city, sheetNum, agent, commodityName, commodityType, commodityUnit, commodityNum, commodityPrice, commodityAmount, commodityTaxRate, commodityTax, onlinePay, sellerName, sellerRegisterNum, sellerAddress, sellerBank, totalAmount, totalTax, amountInWords, amountInFigures, payee, checker, noteDrawer, remarks, companySeal, sealInfo, supervisionSeal, supervisionSealInfo);
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
                    ", commodityName=" + commodityName +
                    ", commodityType=" + commodityType +
                    ", commodityUnit=" + commodityUnit +
                    ", commodityNum=" + commodityNum +
                    ", commodityPrice=" + commodityPrice +
                    ", commodityAmount=" + commodityAmount +
                    ", commodityTaxRate=" + commodityTaxRate +
                    ", commodityTax=" + commodityTax +
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
                    '}';
        }
    }

    public VatInvoiceVo toVo() {
        return VatInvoiceUtil.convert(this);
    }

    /**
     * @param ocrFlag 识别标识, 用于定位识别结果, 会赋值进每一个识别结果对象
     */
    public VatInvoiceVo toVo(String ocrFlag) {
        return VatInvoiceUtil.convert(this, ocrFlag);
    }
}
