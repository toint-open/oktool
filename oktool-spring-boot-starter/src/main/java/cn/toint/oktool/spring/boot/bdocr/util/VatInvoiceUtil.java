package cn.toint.oktool.spring.boot.bdocr.util;

import cn.hutool.v7.core.collection.CollUtil;
import cn.toint.oktool.spring.boot.bdocr.model.VatInvoiceResponse;
import cn.toint.oktool.spring.boot.bdocr.model.VatInvoiceVo;
import cn.toint.oktool.spring.boot.bdocr.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * 增值税发票工具
 *
 * @author Toint
 * @dete 2025/9/9
 */
public class VatInvoiceUtil {
    public static VatInvoiceVo convert(VatInvoiceResponse vatInvoiceResponse) {
        return convert(vatInvoiceResponse, null);
    }

    public static VatInvoiceVo convert(VatInvoiceResponse vatInvoiceResponse, String ocrFlag) {
        VatInvoiceVo vatInvoiceVo = new VatInvoiceVo();
        vatInvoiceVo.setLogId(vatInvoiceResponse.getLogId());
        vatInvoiceVo.setWordsResultNum(vatInvoiceResponse.getWordsResultNum());

        VatInvoiceResponse.WordsResult originWordsResult = vatInvoiceResponse.getWordsResult();
        VatInvoiceVo.WordsResult wordsResultVo = new VatInvoiceVo.WordsResult();
        vatInvoiceVo.setWordsResult(wordsResultVo);
        wordsResultVo.setOcrFlag(ocrFlag);
        wordsResultVo.setServiceType(originWordsResult.getServiceType());
        wordsResultVo.setInvoiceType(originWordsResult.getInvoiceType());
        wordsResultVo.setInvoiceTypeOrg(originWordsResult.getInvoiceTypeOrg());
        wordsResultVo.setInvoiceCode(originWordsResult.getInvoiceCode());
        wordsResultVo.setInvoiceNum(originWordsResult.getInvoiceNum());
        wordsResultVo.setInvoiceCodeConfirm(originWordsResult.getInvoiceCodeConfirm());
        wordsResultVo.setInvoiceNumConfirm(originWordsResult.getInvoiceNumConfirm());
        wordsResultVo.setInvoiceNumDigit(originWordsResult.getInvoiceNumDigit());
        wordsResultVo.setInvoiceTag(originWordsResult.getInvoiceTag());
        wordsResultVo.setMachineNum(originWordsResult.getMachineNum());
        wordsResultVo.setMachineCode(originWordsResult.getMachineCode());
        wordsResultVo.setCheckCode(originWordsResult.getCheckCode());
        wordsResultVo.setInvoiceDate(originWordsResult.getInvoiceDate());
        wordsResultVo.setPurchaserName(originWordsResult.getPurchaserName());
        wordsResultVo.setPurchaserRegisterNum(originWordsResult.getPurchaserRegisterNum());
        wordsResultVo.setPurchaserAddress(originWordsResult.getPurchaserAddress());
        wordsResultVo.setPurchaserBank(originWordsResult.getPurchaserBank());
        wordsResultVo.setPassword(originWordsResult.getPassword());
        wordsResultVo.setProvince(originWordsResult.getProvince());
        wordsResultVo.setCity(originWordsResult.getCity());
        wordsResultVo.setSheetNum(originWordsResult.getSheetNum());
        wordsResultVo.setAgent(originWordsResult.getAgent());
        wordsResultVo.setOnlinePay(originWordsResult.getOnlinePay());
        wordsResultVo.setSellerName(originWordsResult.getSellerName());
        wordsResultVo.setSellerRegisterNum(originWordsResult.getSellerRegisterNum());
        wordsResultVo.setSellerAddress(originWordsResult.getSellerAddress());
        wordsResultVo.setSellerBank(originWordsResult.getSellerBank());
        wordsResultVo.setTotalAmount(originWordsResult.getTotalAmount());
        wordsResultVo.setTotalTax(originWordsResult.getTotalTax());
        wordsResultVo.setAmountInWords(originWordsResult.getAmountInWords());
        wordsResultVo.setAmountInFigures(originWordsResult.getAmountInFigures());
        wordsResultVo.setPayee(originWordsResult.getPayee());
        wordsResultVo.setChecker(originWordsResult.getChecker());
        wordsResultVo.setNoteDrawer(originWordsResult.getNoteDrawer());
        wordsResultVo.setRemarks(originWordsResult.getRemarks());
        wordsResultVo.setCompanySeal(originWordsResult.getCompanySeal());
        wordsResultVo.setSealInfo(originWordsResult.getSealInfo());
        wordsResultVo.setSupervisionSeal(originWordsResult.getSupervisionSeal());
        wordsResultVo.setSupervisionSealInfo(originWordsResult.getSupervisionSealInfo());

        List<VatInvoiceVo.WordsResult.Detail> details = new ArrayList<>();
        wordsResultVo.setDetails(details);

        List<Word> commodityName = originWordsResult.getCommodityName();
        if (CollUtil.isNotEmpty(commodityName)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityName.size()) {
                details.add(new VatInvoiceVo.WordsResult.Detail());
            }
            for (int i = 0; i < commodityName.size(); i++) {
                Word word = commodityName.get(i);
                if (word != null) {
                    details.get(i).setCommodityName(word.getWord());
                }
            }
        }

        List<Word> commodityType = originWordsResult.getCommodityType();
        if (CollUtil.isNotEmpty(commodityType)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityType.size()) {
                details.add(new VatInvoiceVo.WordsResult.Detail());
            }
            for (int i = 0; i < commodityType.size(); i++) {
                Word word = commodityType.get(i);
                if (word != null) {
                    details.get(i).setCommodityType(word.getWord());
                }
            }
        }

        List<Word> commodityUnit = originWordsResult.getCommodityUnit();
        if (CollUtil.isNotEmpty(commodityUnit)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityUnit.size()) {
                details.add(new VatInvoiceVo.WordsResult.Detail());
            }
            for (int i = 0; i < commodityUnit.size(); i++) {
                Word word = commodityUnit.get(i);
                if (word != null) {
                    details.get(i).setCommodityUnit(word.getWord());
                }
            }
        }

        List<Word> commodityNum = originWordsResult.getCommodityNum();
        if (CollUtil.isNotEmpty(commodityNum)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityNum.size()) {
                details.add(new VatInvoiceVo.WordsResult.Detail());
            }
            for (int i = 0; i < commodityNum.size(); i++) {
                Word word = commodityNum.get(i);
                if (word != null) {
                    details.get(i).setCommodityNum(word.getWord());
                }
            }
        }

        List<Word> commodityPrice = originWordsResult.getCommodityPrice();
        if (CollUtil.isNotEmpty(commodityPrice)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityPrice.size()) {
                details.add(new VatInvoiceVo.WordsResult.Detail());
            }
            for (int i = 0; i < commodityPrice.size(); i++) {
                Word word = commodityPrice.get(i);
                if (word != null) {
                    details.get(i).setCommodityPrice(word.getWord());
                }
            }
        }

        List<Word> commodityAmount = originWordsResult.getCommodityAmount();
        if (CollUtil.isNotEmpty(commodityAmount)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityAmount.size()) {
                details.add(new VatInvoiceVo.WordsResult.Detail());
            }
            for (int i = 0; i < commodityAmount.size(); i++) {
                Word word = commodityAmount.get(i);
                if (word != null) {
                    details.get(i).setCommodityAmount(word.getWord());
                }
            }
        }

        List<Word> commodityTaxRate = originWordsResult.getCommodityTaxRate();
        if (CollUtil.isNotEmpty(commodityTaxRate)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityTaxRate.size()) {
                details.add(new VatInvoiceVo.WordsResult.Detail());
            }
            for (int i = 0; i < commodityTaxRate.size(); i++) {
                Word word = commodityTaxRate.get(i);
                if (word != null) {
                    details.get(i).setCommodityTaxRate(word.getWord());
                }
            }
        }

        List<Word> commodityTax = originWordsResult.getCommodityTax();
        if (CollUtil.isNotEmpty(commodityTax)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityTax.size()) {
                details.add(new VatInvoiceVo.WordsResult.Detail());
            }
            for (int i = 0; i < commodityTax.size(); i++) {
                Word word = commodityTax.get(i);
                if (word != null) {
                    details.get(i).setCommodityTax(word.getWord());
                }
            }
        }
        
        return vatInvoiceVo;
    }
}
