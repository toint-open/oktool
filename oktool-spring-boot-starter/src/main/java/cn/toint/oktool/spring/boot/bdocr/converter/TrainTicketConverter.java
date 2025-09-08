package cn.toint.oktool.spring.boot.bdocr.converter;

import cn.toint.oktool.spring.boot.bdocr.model.MultipleInvoiceResult;
import cn.toint.oktool.spring.boot.bdocr.model.TrainTicket;
import cn.toint.oktool.spring.boot.bdocr.model.TypeEnum;
import cn.toint.oktool.spring.boot.bdocr.util.MultipleInvoiceUtil;
import cn.toint.oktool.util.Assert;

/**
 * 火车票转换器
 *
 * @author Toint
 * @dete 2025/9/8
 */
public class TrainTicketConverter implements OcrResultConverter<TrainTicket> {
    @Override
    public TrainTicket convert(MultipleInvoiceResult value) {
        Assert.notNull(value, "value must not be null");

        TrainTicket trainTicket = new TrainTicket();
        trainTicket.setTicketNum(MultipleInvoiceUtil.getFirstWord(value.getTicketNum()));
        trainTicket.setStartingStation(MultipleInvoiceUtil.getFirstWord(value.getStartingStation()));
        trainTicket.setTrainNum(MultipleInvoiceUtil.getFirstWord(value.getTrainNum()));
        trainTicket.setDestinationStation(MultipleInvoiceUtil.getFirstWord(value.getDestinationStation()));
        trainTicket.setDate(MultipleInvoiceUtil.getFirstWord(value.getDate()));
        trainTicket.setTicketRates(MultipleInvoiceUtil.getFirstWord(value.getTicketRates()));
        trainTicket.setSeatCategory(MultipleInvoiceUtil.getFirstWord(value.getSeatCategory()));
        trainTicket.setName(MultipleInvoiceUtil.getFirstWord(value.getName()));
        trainTicket.setIdCard(MultipleInvoiceUtil.getFirstWord(value.getIdCard()));
        trainTicket.setSerialNumber(MultipleInvoiceUtil.getFirstWord(value.getSerialNumber()));
        trainTicket.setSalesStation(MultipleInvoiceUtil.getFirstWord(value.getSalesStation()));
        trainTicket.setTime(MultipleInvoiceUtil.getFirstWord(value.getTime()));
        trainTicket.setSeatNum(MultipleInvoiceUtil.getFirstWord(value.getSeatNum()));
        trainTicket.setWaitingArea(MultipleInvoiceUtil.getFirstWord(value.getWaitingArea()));
        trainTicket.setRefundFlag(MultipleInvoiceUtil.getFirstWord(value.getRefundFlag()));
        trainTicket.setFare(MultipleInvoiceUtil.getFirstWord(value.getFare()));
        trainTicket.setTaxRate(MultipleInvoiceUtil.getFirstWord(value.getTaxRate()));
        trainTicket.setTax(MultipleInvoiceUtil.getFirstWord(value.getTax()));
        trainTicket.setElecTicketNum(MultipleInvoiceUtil.getFirstWord(value.getElecTicketNum()));
        return trainTicket;
    }

    @Override
    public String getType() {
        return TypeEnum.TRAIN_TICKET.getCode();
    }
}
