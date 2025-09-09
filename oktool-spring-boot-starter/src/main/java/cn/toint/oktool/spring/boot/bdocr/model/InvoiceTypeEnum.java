package cn.toint.oktool.spring.boot.bdocr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发票类型
 *
 * @author Toint
 * @dete 2025/9/10
 */
@AllArgsConstructor
@Getter
public enum InvoiceTypeEnum {
    /**
     * 增值税专用发票
     */
    SPECIAL_VAT_INVOICE("special_vat_invoice", "增值税专用发票"),

    /**
     * 增值税电子专用发票
     */
    ELEC_SPECIAL_VAT_INVOICE("elec_special_vat_invoice", "增值税电子专用发票"),

    /**
     * 增值税普通发票
     */
    NORMAL_INVOICE("normal_invoice", "增值税普通发票"),

    /**
     * 增值税普通发票（电子）
     */
    ELEC_NORMAL_INVOICE("elec_normal_invoice", "增值税普通发票（电子）"),

    /**
     * 增值税普通发票（卷式）
     */
    ROLL_NORMAL_INVOICE("roll_normal_invoice", "增值税普通发票（卷式）"),

    /**
     * 通行费增值税电子普通发票
     */
    TOLL_ELEC_NORMAL_INVOICE("toll_elec_normal_invoice", "通行费增值税电子普通发票"),

    /**
     * 区块链电子发票（目前仅支持深圳地区）
     */
    BLOCKCHAIN_INVOICE("blockchain_invoice", "区块链电子发票"),

    /**
     * 全电发票（专用发票）
     */
    ELEC_INVOICE_SPECIAL("elec_invoice_special", "全电发票（专用发票）"),

    /**
     * 全电发票（普通发票）
     */
    ELEC_INVOICE_NORMAL("elec_invoice_normal", "全电发票（普通发票）"),

    /**
     * 货物运输业增值税专用发票
     */
    SPECIAL_FREIGHT_TRANSPORT_INVOICE("special_freight_transport_invoice", "货物运输业增值税专用发票"),

    /**
     * 机动车销售统一发票 / 电子发票
     */
    MOTOR_VEHICLE_INVOICE("motor_vehicle_invoice", "机动车销售统一发票/电子发票"),

    /**
     * 二手车销售统一发票 / 电子发票
     */
    USED_VEHICLE_INVOICE("used_vehicle_invoice", "二手车销售统一发票/电子发票"),

    /**
     * 航空运输电子客票行程单
     */
    ELEC_FLIGHT_ITINERARY_INVOICE("elec_flight_itinerary_invoice", "航空运输电子客票行程单"),

    /**
     * 铁路电子客票
     */
    ELEC_TRAIN_TICKET_INVOICE("elec_train_ticket_invoice", "铁路电子客票"),

    /**
     * 全电发票（含通行费标识）
     */
    ELEC_TOLL_INVOICE("elec_toll_invoice", "全电发票（含通行费标识）");

    /**
     * 发票类型代码
     */
    private final String typeCode;

    /**
     * 发票类型描述
     */
    private final String description;
}
