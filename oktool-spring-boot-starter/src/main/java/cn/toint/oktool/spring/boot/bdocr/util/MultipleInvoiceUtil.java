package cn.toint.oktool.spring.boot.bdocr.util;

import cn.hutool.v7.core.collection.CollUtil;
import cn.toint.oktool.spring.boot.bdocr.model.MultipleInvoiceResponse;
import cn.toint.oktool.spring.boot.bdocr.model.MultipleInvoiceVo;
import cn.toint.oktool.spring.boot.bdocr.model.TypeEnum;
import cn.toint.oktool.spring.boot.bdocr.model.Word;
import cn.toint.oktool.util.Assert;
import cn.toint.oktool.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 智能财务票据识别工具
 *
 * @author Toint
 * @dete 2025/9/8
 */
public class MultipleInvoiceUtil {
    /**
     * 解析识别结果
     *
     * @param multipleInvoiceResponse 识别结果
     * @return 解析结果
     */
    public static MultipleInvoiceVo convert(MultipleInvoiceResponse multipleInvoiceResponse) {
        Assert.notNull(multipleInvoiceResponse, "multipleInvoiceResponse must not be null");

        // 视图对象
        MultipleInvoiceVo multipleInvoiceVo = new MultipleInvoiceVo();
        multipleInvoiceVo.setLogId(multipleInvoiceResponse.getLogId());

        // 识别结果数量, 如果为空则无需转换
        Integer wordsResultNum = multipleInvoiceResponse.getWordsResultNum();
        multipleInvoiceVo.setWordsResultNum(wordsResultNum);
        if (wordsResultNum == null || wordsResultNum < 1) {
            return multipleInvoiceVo;
        }

        // 转换每一个识别结果
        List<MultipleInvoiceResponse.WordsResult> wordsResult = multipleInvoiceResponse.getWordsResult();
        if (CollUtil.isEmpty(wordsResult)) return multipleInvoiceVo;
        wordsResult.forEach(item -> {
            // 识别结果类型
            TypeEnum typeEnum = item.typeEnum();
            if (typeEnum == null) return;

            // 原始识别结果
            JsonNode result = item.getResult();
            if (JacksonUtil.isNull(result)) return;

            // 增值税发票
            if (TypeEnum.VAT_INVOICE.equals(typeEnum)) {
                MultipleInvoiceVo.VatInvoice vatInvoice = convertVatInvoice(JacksonUtil.convertValue(result, MultipleInvoiceResponse.VatInvoiceResult.class));
                multipleInvoiceVo.vatInvoice().add(vatInvoice);
                return;
            }

            // 火车票
            if (TypeEnum.TRAIN_TICKET.equals(typeEnum)) {
                MultipleInvoiceVo.TrainTicket trainTicket = convertVatTrainTicket(JacksonUtil.convertValue(result, MultipleInvoiceResponse.TrainTicketResult.class));
                multipleInvoiceVo.trainTicket().add(trainTicket);
                return;
            }

            // 网约车
            if (TypeEnum.TAXI_ONLINE_TICKET.equals(typeEnum)) {
                MultipleInvoiceVo.TaxiOnlineTicket taxiOnlineTicket = convertTaxiOnlineTicket(JacksonUtil.convertValue(result, MultipleInvoiceResponse.TaxiOnlineTicketResult.class));
                multipleInvoiceVo.taxiOnlineTicket().add(taxiOnlineTicket);
                return;
            }
        });

        return multipleInvoiceVo;
    }

    public static MultipleInvoiceVo.TaxiOnlineTicket convertTaxiOnlineTicket(MultipleInvoiceResponse.TaxiOnlineTicketResult taxiOnlineTicketResult) {
        Assert.notNull(taxiOnlineTicketResult, "taxiOnlineTicketResult must not be null");
        MultipleInvoiceVo.TaxiOnlineTicket taxiOnlineTicket = new MultipleInvoiceVo.TaxiOnlineTicket();
        taxiOnlineTicket.setServiceType(WordUtil.getFirstWord(taxiOnlineTicketResult.getServiceType()));
        taxiOnlineTicket.setServiceProvider(WordUtil.getFirstWord(taxiOnlineTicketResult.getServiceProvider()));
        taxiOnlineTicket.setStartTime(WordUtil.getFirstWord(taxiOnlineTicketResult.getStartTime()));
        taxiOnlineTicket.setDestinationTime(WordUtil.getFirstWord(taxiOnlineTicketResult.getDestinationTime()));
        taxiOnlineTicket.setPhone(WordUtil.getFirstWord(taxiOnlineTicketResult.getPhone()));
        taxiOnlineTicket.setApplicationDate(WordUtil.getFirstWord(taxiOnlineTicketResult.getApplicationDate()));
        taxiOnlineTicket.setTotalFare(WordUtil.getFirstWord(taxiOnlineTicketResult.getTotalFare()));
        taxiOnlineTicket.setItemNum(WordUtil.getFirstWord(taxiOnlineTicketResult.getItemNum()));

        if (CollUtil.isNotEmpty(taxiOnlineTicketResult.getDetails())) {
            List<MultipleInvoiceVo.TaxiOnlineTicket.Detail> details = taxiOnlineTicketResult.getDetails().stream()
                    .map(item -> {
                        MultipleInvoiceVo.TaxiOnlineTicket.Detail detail = new MultipleInvoiceVo.TaxiOnlineTicket.Detail();
                        detail.setItemId(WordUtil.getWord(item.getItemId()));
                        detail.setItemProvider(WordUtil.getWord(item.getItemProvider()));
                        detail.setPickupTime(WordUtil.getWord(item.getPickupTime()));
                        detail.setPickupDate(WordUtil.getWord(item.getPickupDate()));
                        detail.setCarType(WordUtil.getWord(item.getCarType()));
                        detail.setDistance(WordUtil.getWord(item.getDistance()));
                        detail.setStartPlace(WordUtil.getWord(item.getStartPlace()));
                        detail.setDestinationPlace(WordUtil.getWord(item.getDestinationPlace()));
                        detail.setCity(WordUtil.getWord(item.getCity()));
                        detail.setFare(WordUtil.getWord(item.getFare()));
                        return detail;
                    })
                    .collect(Collectors.toList());
            taxiOnlineTicket.setDetails(details);
        }

        return taxiOnlineTicket;
    }

    public static MultipleInvoiceVo.TrainTicket convertVatTrainTicket(MultipleInvoiceResponse.TrainTicketResult trainTicketResult) {
        Assert.notNull(trainTicketResult, "trainTicketResult must not be null");
        MultipleInvoiceVo.TrainTicket trainTicket = new MultipleInvoiceVo.TrainTicket();
        trainTicket.setServiceType(WordUtil.getFirstWord(trainTicketResult.getServiceType()));
        trainTicket.setTicketNum(WordUtil.getFirstWord(trainTicketResult.getTicketNum()));
        trainTicket.setStartingStation(WordUtil.getFirstWord(trainTicketResult.getStartingStation()));
        trainTicket.setTrainNum(WordUtil.getFirstWord(trainTicketResult.getTrainNum()));
        trainTicket.setDestinationStation(WordUtil.getFirstWord(trainTicketResult.getDestinationStation()));
        trainTicket.setDate(WordUtil.getFirstWord(trainTicketResult.getDate()));
        trainTicket.setTicketRates(WordUtil.getFirstWord(trainTicketResult.getTicketRates()));
        trainTicket.setSeatCategory(WordUtil.getFirstWord(trainTicketResult.getSeatCategory()));
        trainTicket.setName(WordUtil.getFirstWord(trainTicketResult.getName()));
        trainTicket.setIdCard(WordUtil.getFirstWord(trainTicketResult.getIdCard()));
        trainTicket.setSerialNumber(WordUtil.getFirstWord(trainTicketResult.getSerialNumber()));
        trainTicket.setSalesStation(WordUtil.getFirstWord(trainTicketResult.getSalesStation()));
        trainTicket.setTime(WordUtil.getFirstWord(trainTicketResult.getTime()));
        trainTicket.setSeatNum(WordUtil.getFirstWord(trainTicketResult.getSeatNum()));
        trainTicket.setWaitingArea(WordUtil.getFirstWord(trainTicketResult.getWaitingArea()));
        trainTicket.setRefundFlag(WordUtil.getFirstWord(trainTicketResult.getRefundFlag()));
        trainTicket.setInvoiceNum(WordUtil.getFirstWord(trainTicketResult.getInvoiceNum()));
        trainTicket.setInvoiceDate(WordUtil.getFirstWord(trainTicketResult.getInvoiceDate()));
        trainTicket.setFare(WordUtil.getFirstWord(trainTicketResult.getFare()));
        trainTicket.setTaxRate(WordUtil.getFirstWord(trainTicketResult.getTaxRate()));
        trainTicket.setTax(WordUtil.getFirstWord(trainTicketResult.getTax()));
        trainTicket.setElecTicketNum(WordUtil.getFirstWord(trainTicketResult.getElecTicketNum()));
        trainTicket.setPurchaserName(WordUtil.getFirstWord(trainTicketResult.getPurchaserName()));
        trainTicket.setPurchaserRegisterNum(WordUtil.getFirstWord(trainTicketResult.getPurchaserRegisterNum()));
        return trainTicket;
    }

    public static MultipleInvoiceVo.VatInvoice convertVatInvoice(MultipleInvoiceResponse.VatInvoiceResult vatInvoiceResult) {
        Assert.notNull(vatInvoiceResult, "vatInvoiceResult must not be null");
        MultipleInvoiceVo.VatInvoice vatInvoice = new MultipleInvoiceVo.VatInvoice();
        vatInvoice.setServiceType(WordUtil.getFirstWord(vatInvoiceResult.getServiceType()));
        vatInvoice.setInvoiceTypeOrg(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceTypeOrg()));
        vatInvoice.setInvoiceType(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceType()));
        vatInvoice.setInvoiceTag(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceTag()));
        vatInvoice.setInvoiceCode(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceCode()));
        vatInvoice.setInvoiceNum(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceNum()));
        vatInvoice.setInvoiceCodeConfirm(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceCodeConfirm()));
        vatInvoice.setInvoiceNumConfirm(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceNumConfirm()));
        vatInvoice.setCheckCode(WordUtil.getFirstWord(vatInvoiceResult.getCheckCode()));
        vatInvoice.setInvoiceNumDigit(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceNumDigit()));
        vatInvoice.setInvoiceDate(WordUtil.getFirstWord(vatInvoiceResult.getInvoiceDate()));
        vatInvoice.setPurchaserName(WordUtil.getFirstWord(vatInvoiceResult.getPurchaserName()));
        vatInvoice.setPurchaserRegisterNum(WordUtil.getFirstWord(vatInvoiceResult.getPurchaserRegisterNum()));
        vatInvoice.setPurchaserAddress(WordUtil.getFirstWord(vatInvoiceResult.getPurchaserAddress()));
        vatInvoice.setPurchaserBank(WordUtil.getFirstWord(vatInvoiceResult.getPurchaserBank()));
        vatInvoice.setPassword(WordUtil.getFirstWord(vatInvoiceResult.getPassword()));
        vatInvoice.setProvince(WordUtil.getFirstWord(vatInvoiceResult.getProvince()));
        vatInvoice.setCity(WordUtil.getFirstWord(vatInvoiceResult.getCity()));
        vatInvoice.setSheetNum(WordUtil.getFirstWord(vatInvoiceResult.getSheetNum()));
        vatInvoice.setAgent(WordUtil.getFirstWord(vatInvoiceResult.getAgent()));
        vatInvoice.setOnlinePay(WordUtil.getFirstWord(vatInvoiceResult.getOnlinePay()));
        vatInvoice.setSellerName(WordUtil.getFirstWord(vatInvoiceResult.getSellerName()));
        vatInvoice.setSellerRegisterNum(WordUtil.getFirstWord(vatInvoiceResult.getSellerRegisterNum()));
        vatInvoice.setSellerAddress(WordUtil.getFirstWord(vatInvoiceResult.getSellerAddress()));
        vatInvoice.setSellerBank(WordUtil.getFirstWord(vatInvoiceResult.getSellerBank()));
        vatInvoice.setTotalAmount(WordUtil.getFirstWord(vatInvoiceResult.getTotalAmount()));
        vatInvoice.setTotalTax(WordUtil.getFirstWord(vatInvoiceResult.getTotalTax()));
        vatInvoice.setAmountInWords(WordUtil.getFirstWord(vatInvoiceResult.getAmountInWords()));
        vatInvoice.setAmountInFiguers(WordUtil.getFirstWord(vatInvoiceResult.getAmountInFiguers()));
        vatInvoice.setPayee(WordUtil.getFirstWord(vatInvoiceResult.getPayee()));
        vatInvoice.setChecker(WordUtil.getFirstWord(vatInvoiceResult.getChecker()));
        vatInvoice.setNoteDrawer(WordUtil.getFirstWord(vatInvoiceResult.getNoteDrawer()));
        vatInvoice.setRemarks(WordUtil.getFirstWord(vatInvoiceResult.getRemarks()));
        vatInvoice.setTotalPage(WordUtil.getFirstWord(vatInvoiceResult.getTotalPage()));
        vatInvoice.setCurrentPage(WordUtil.getFirstWord(vatInvoiceResult.getCurrentPage()));
        vatInvoice.setSubTotalAmount(WordUtil.getFirstWord(vatInvoiceResult.getSubTotalAmount()));
        vatInvoice.setSubTotalTax(WordUtil.getFirstWord(vatInvoiceResult.getSubTotalTax()));

        //===发票详情列表===
        List<MultipleInvoiceVo.VatInvoice.Detail> details = new ArrayList<>();
        vatInvoice.setDetails(details);

        List<Word> commodityName = vatInvoiceResult.getCommodityName();
        if (CollUtil.isNotEmpty(commodityName)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityName.size()) {
                details.add(new MultipleInvoiceVo.VatInvoice.Detail());
            }
            for (int i = 0; i < commodityName.size(); i++) {
                Word word = commodityName.get(i);
                if (word != null) {
                    details.get(i).setCommodityName(word.getWord());
                }
            }
        }

        List<Word> commodityType = vatInvoiceResult.getCommodityType();
        if (CollUtil.isNotEmpty(commodityType)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityType.size()) {
                details.add(new MultipleInvoiceVo.VatInvoice.Detail());
            }
            for (int i = 0; i < commodityType.size(); i++) {
                Word word = commodityType.get(i);
                if (word != null) {
                    details.get(i).setCommodityType(word.getWord());
                }
            }
        }

        List<Word> commodityUnit = vatInvoiceResult.getCommodityUnit();
        if (CollUtil.isNotEmpty(commodityUnit)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityUnit.size()) {
                details.add(new MultipleInvoiceVo.VatInvoice.Detail());
            }
            for (int i = 0; i < commodityUnit.size(); i++) {
                Word word = commodityUnit.get(i);
                if (word != null) {
                    details.get(i).setCommodityUnit(word.getWord());
                }
            }
        }

        List<Word> commodityNum = vatInvoiceResult.getCommodityNum();
        if (CollUtil.isNotEmpty(commodityNum)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityNum.size()) {
                details.add(new MultipleInvoiceVo.VatInvoice.Detail());
            }
            for (int i = 0; i < commodityNum.size(); i++) {
                Word word = commodityNum.get(i);
                if (word != null) {
                    details.get(i).setCommodityNum(word.getWord());
                }
            }
        }

        List<Word> commodityPrice = vatInvoiceResult.getCommodityPrice();
        if (CollUtil.isNotEmpty(commodityPrice)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityPrice.size()) {
                details.add(new MultipleInvoiceVo.VatInvoice.Detail());
            }
            for (int i = 0; i < commodityPrice.size(); i++) {
                Word word = commodityPrice.get(i);
                if (word != null) {
                    details.get(i).setCommodityPrice(word.getWord());
                }
            }
        }

        List<Word> commodityAmount = vatInvoiceResult.getCommodityAmount();
        if (CollUtil.isNotEmpty(commodityAmount)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityAmount.size()) {
                details.add(new MultipleInvoiceVo.VatInvoice.Detail());
            }
            for (int i = 0; i < commodityAmount.size(); i++) {
                Word word = commodityAmount.get(i);
                if (word != null) {
                    details.get(i).setCommodityAmount(word.getWord());
                }
            }
        }

        List<Word> commodityTaxRate = vatInvoiceResult.getCommodityTaxRate();
        if (CollUtil.isNotEmpty(commodityTaxRate)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityTaxRate.size()) {
                details.add(new MultipleInvoiceVo.VatInvoice.Detail());
            }
            for (int i = 0; i < commodityTaxRate.size(); i++) {
                Word word = commodityTaxRate.get(i);
                if (word != null) {
                    details.get(i).setCommodityTaxRate(word.getWord());
                }
            }
        }

        List<Word> commodityTax = vatInvoiceResult.getCommodityTax();
        if (CollUtil.isNotEmpty(commodityTax)) {
            // 确保 details 有足够的大小
            while (details.size() < commodityTax.size()) {
                details.add(new MultipleInvoiceVo.VatInvoice.Detail());
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

}
