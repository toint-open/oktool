package cn.toint.oktool.spring.boot.bdocr.converter;

import cn.hutool.v7.core.collection.CollUtil;
import cn.toint.oktool.spring.boot.bdocr.model.MultipleInvoiceResult;
import cn.toint.oktool.spring.boot.bdocr.model.TypeEnum;
import cn.toint.oktool.spring.boot.bdocr.model.VatInvoiceResponse;
import cn.toint.oktool.spring.boot.bdocr.model.Word;
import cn.toint.oktool.spring.boot.bdocr.util.MultipleInvoiceUtil;
import cn.toint.oktool.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 增值税发票转换器
 *
 * @author Toint
 * @dete 2025/9/8
 */
public class VatInvoiceConverter implements OcrResultConverter<VatInvoiceResponse> {
    @Override
    public VatInvoiceResponse convert(MultipleInvoiceResult value) {
        Assert.notNull(value, "value must not be null");
        VatInvoiceResponse vatInvoice = new VatInvoiceResponse();
        vatInvoice.setServiceType(MultipleInvoiceUtil.getFirstWord(value.getServiceType()));
        vatInvoice.setInvoiceTypeOrg(MultipleInvoiceUtil.getFirstWord(value.getInvoiceTypeOrg()));
        vatInvoice.setInvoiceType(MultipleInvoiceUtil.getFirstWord(value.getInvoiceType()));
        vatInvoice.setInvoiceTag(MultipleInvoiceUtil.getFirstWord(value.getInvoiceTag()));
        vatInvoice.setInvoiceCode(MultipleInvoiceUtil.getFirstWord(value.getInvoiceCode()));
        vatInvoice.setInvoiceNum(MultipleInvoiceUtil.getFirstWord(value.getInvoiceNum()));
        vatInvoice.setInvoiceCodeConfirm(MultipleInvoiceUtil.getFirstWord(value.getInvoiceCodeConfirm()));
        vatInvoice.setInvoiceNumConfirm(MultipleInvoiceUtil.getFirstWord(value.getInvoiceNumConfirm()));
        vatInvoice.setCheckCode(MultipleInvoiceUtil.getFirstWord(value.getCheckCode()));
        vatInvoice.setInvoiceNumDigit(MultipleInvoiceUtil.getFirstWord(value.getInvoiceNumDigit()));
        vatInvoice.setInvoiceDate(MultipleInvoiceUtil.getFirstWord(value.getInvoiceDate()));
        vatInvoice.setPurchaserName(MultipleInvoiceUtil.getFirstWord(value.getPurchaserName()));
        vatInvoice.setPurchaserRegisterNum(MultipleInvoiceUtil.getFirstWord(value.getPurchaserRegisterNum()));
        vatInvoice.setPurchaserAddress(MultipleInvoiceUtil.getFirstWord(value.getPurchaserAddress()));
        vatInvoice.setPurchaserBank(MultipleInvoiceUtil.getFirstWord(value.getPurchaserBank()));
        vatInvoice.setPassword(MultipleInvoiceUtil.getFirstWord(value.getPassword()));
        vatInvoice.setProvince(MultipleInvoiceUtil.getFirstWord(value.getProvince()));
        vatInvoice.setCity(MultipleInvoiceUtil.getFirstWord(value.getCity()));
        vatInvoice.setSheetNum(MultipleInvoiceUtil.getFirstWord(value.getSheetNum()));
        vatInvoice.setAgent(MultipleInvoiceUtil.getFirstWord(value.getAgent()));
        vatInvoice.setOnlinePay(MultipleInvoiceUtil.getFirstWord(value.getOnlinePay()));
        vatInvoice.setSellerName(MultipleInvoiceUtil.getFirstWord(value.getSellerName()));
        vatInvoice.setSellerRegisterNum(MultipleInvoiceUtil.getFirstWord(value.getSellerRegisterNum()));
        vatInvoice.setSellerAddress(MultipleInvoiceUtil.getFirstWord(value.getSellerAddress()));
        vatInvoice.setSellerBank(MultipleInvoiceUtil.getFirstWord(value.getSellerBank()));
        vatInvoice.setTotalAmount(MultipleInvoiceUtil.getFirstWord(value.getTotalAmount()));
        vatInvoice.setTotalTax(MultipleInvoiceUtil.getFirstWord(value.getTotalTax()));
        vatInvoice.setAmountInWords(MultipleInvoiceUtil.getFirstWord(value.getAmountInWords()));
        vatInvoice.setAmountInFiguers(MultipleInvoiceUtil.getFirstWord(value.getAmountInFiguers()));
        vatInvoice.setPayee(MultipleInvoiceUtil.getFirstWord(value.getPayee()));
        vatInvoice.setChecker(MultipleInvoiceUtil.getFirstWord(value.getChecker()));
        vatInvoice.setNoteDrawer(MultipleInvoiceUtil.getFirstWord(value.getNoteDrawer()));
        vatInvoice.setRemarks(MultipleInvoiceUtil.getFirstWord(value.getRemarks()));
        vatInvoice.setTotalPage(MultipleInvoiceUtil.getFirstWord(value.getTotalPage()));
        vatInvoice.setCurrentPage(MultipleInvoiceUtil.getFirstWord(value.getCurrentPage()));
        vatInvoice.setSubTotalAmount(MultipleInvoiceUtil.getFirstWord(value.getSubTotalAmount()));
        vatInvoice.setSubTotalTax(MultipleInvoiceUtil.getFirstWord(value.getSubTotalTax()));

        //===发票详情列表===
        List<VatInvoiceResponse.Detail> details = new ArrayList<>();
        vatInvoice.setDetails(details);

        List<Word> commodityName = value.getCommodityName();
        if (CollUtil.isNotEmpty(commodityName)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityName.size()) {
                details.add(new VatInvoiceResponse.Detail());
            }
            for (int i = 0; i < commodityName.size(); i++) {
                Word word = commodityName.get(i);
                if (word != null) {
                    details.get(i).setCommodityName(word.getWord());
                }
            }
        }

        List<Word> commodityType = value.getCommodityType();
        if (CollUtil.isNotEmpty(commodityType)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityType.size()) {
                details.add(new VatInvoiceResponse.Detail());
            }
            for (int i = 0; i < commodityType.size(); i++) {
                Word word = commodityType.get(i);
                if (word != null) {
                    details.get(i).setCommodityType(word.getWord());
                }
            }
        }

        List<Word> commodityUnit = value.getCommodityUnit();
        if (CollUtil.isNotEmpty(commodityUnit)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityUnit.size()) {
                details.add(new VatInvoiceResponse.Detail());
            }
            for (int i = 0; i < commodityUnit.size(); i++) {
                Word word = commodityUnit.get(i);
                if (word != null) {
                    details.get(i).setCommodityUnit(word.getWord());
                }
            }
        }

        List<Word> commodityNum = value.getCommodityNum();
        if (CollUtil.isNotEmpty(commodityNum)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityNum.size()) {
                details.add(new VatInvoiceResponse.Detail());
            }
            for (int i = 0; i < commodityNum.size(); i++) {
                Word word = commodityNum.get(i);
                if (word != null) {
                    details.get(i).setCommodityNum(word.getWord());
                }
            }
        }

        List<Word> commodityPrice = value.getCommodityPrice();
        if (CollUtil.isNotEmpty(commodityPrice)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityPrice.size()) {
                details.add(new VatInvoiceResponse.Detail());
            }
            for (int i = 0; i < commodityPrice.size(); i++) {
                Word word = commodityPrice.get(i);
                if (word != null) {
                    details.get(i).setCommodityPrice(word.getWord());
                }
            }
        }

        List<Word> commodityAmount = value.getCommodityAmount();
        if (CollUtil.isNotEmpty(commodityAmount)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityAmount.size()) {
                details.add(new VatInvoiceResponse.Detail());
            }
            for (int i = 0; i < commodityAmount.size(); i++) {
                Word word = commodityAmount.get(i);
                if (word != null) {
                    details.get(i).setCommodityAmount(word.getWord());
                }
            }
        }

        List<Word> commodityTaxRate = value.getCommodityTaxRate();
        if (CollUtil.isNotEmpty(commodityTaxRate)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityTaxRate.size()) {
                details.add(new VatInvoiceResponse.Detail());
            }
            for (int i = 0; i < commodityTaxRate.size(); i++) {
                Word word = commodityTaxRate.get(i);
                if (word != null) {
                    details.get(i).setCommodityTaxRate(word.getWord());
                }
            }
        }

        List<Word> commodityTax = value.getCommodityTax();
        if (CollUtil.isNotEmpty(commodityTax)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityTax.size()) {
                details.add(new VatInvoiceResponse.Detail());
            }
            for (int i = 0; i < commodityTax.size(); i++) {
                Word word = commodityTax.get(i);
                if (word != null) {
                    details.get(i).setCommodityTax(word.getWord());
                }
            }
        }

        return vatInvoice;
    }

    @Override
    public String getType() {
        return TypeEnum.VAT_INVOICE.getCode();
    }
}
