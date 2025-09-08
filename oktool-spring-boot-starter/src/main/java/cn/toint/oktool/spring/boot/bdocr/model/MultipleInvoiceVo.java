package cn.toint.oktool.spring.boot.bdocr.model;

import cn.toint.oktool.util.Assert;
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
    private List<Object> taxiOnlineTicket;

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

    public void add(TypeEnum typeEnum, Object value) {
        Assert.notNull(typeEnum, "typeEnum must not be null");
        Assert.notNull(value, "value must not be null");

        // 增值税发票
        if (TypeEnum.VAT_INVOICE.equals(typeEnum)) {
            if (vatInvoice == null) {
                vatInvoice = new ArrayList<>();
            }
            vatInvoice.add((VatInvoice) value);
            return;
        }
    }
}
