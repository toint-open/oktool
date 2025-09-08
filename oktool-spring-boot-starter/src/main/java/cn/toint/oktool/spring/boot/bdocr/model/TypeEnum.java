package cn.toint.oktool.spring.boot.bdocr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 识别类型
 *
 * @author Toint
 * @dete 2025/9/8
 */
@AllArgsConstructor
@Getter
public enum TypeEnum {
    /**
     * 增值税发票（包含专票、普票、电子票等）
     */
    VAT_INVOICE("vat_invoice", "增值税发票"),

    /**
     * 出租车票（传统纸质出租车票）
     */
    TAXI_RECEIPT("taxi_receipt", "出租车票"),

    /**
     * 火车票（铁路客运票据）
     */
    TRAIN_TICKET("train_ticket", "火车票"),

    /**
     * 定额发票（固定面额的发票）
     */
    QUOTA_INVOICE("quota_invoice", "定额发票"),

    /**
     * 飞机行程单（航空运输电子客票行程单）
     */
    AIR_TICKET("air_ticket", "飞机行程单"),

    /**
     * 卷式发票（卷筒式打印的普通发票）
     */
    ROLL_NORMAL_INVOICE("roll_normal_invoice", "卷票"),

    /**
     * 机打发票（通过税控系统打印的发票）
     */
    PRINTED_INVOICE("printed_invoice", "机打发票"),

    /**
     * 机打电子发票（电子形式但采用机打格式的发票）
     */
    PRINTED_ELEC_INVOICE("printed_elec_invoice", "机打电子发票"),

    /**
     * 汽车票（公路客运票据）
     */
    BUS_TICKET("bus_ticket", "汽车票"),

    /**
     * 过路过桥费发票（高速公路等通行费票据）
     */
    TOLL_INVOICE("toll_invoice", "过路过桥费发票"),

    /**
     * 船票（水路客运票据）
     */
    FERRY_TICKET("ferry_ticket", "船票"),

    /**
     * 机动车销售统一发票（新车销售专用发票）
     */
    MOTOR_VEHICLE_INVOICE("motor_vehicle_invoice", "机动车销售发票"),

    /**
     * 二手车销售统一发票（二手车交易专用发票）
     */
    USED_VEHICLE_INVOICE("used_vehicle_invoice", "二手车销售发票"),

    /**
     * 网约车行程单（滴滴等网约车平台电子票据）
     */
    TAXI_ONLINE_TICKET("taxi_online_ticket", "网约车行程单"),

    /**
     * 限额发票（有金额限制的发票）
     */
    LIMIT_INVOICE("limit_invoice", "限额发票"),

    /**
     * 购物小票（商场超市等消费凭证）
     */
    SHOPPING_RECEIPT("shopping_receipt", "购物小票"),

    /**
     * POS小票（刷卡消费的签购单）
     */
    POS_INVOICE("pos_invoice", "POS小票"),

    /**
     * 其他未分类票据
     */
    OTHERS("others", "其他");

    /**
     * 类型代码（用于数据库存储和接口传输）
     */
    private final String code;

    /**
     * 类型中文描述（用于界面展示）
     */
    private final String description;
}
