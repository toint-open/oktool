package cn.toint.oktool.spring.boot.bdocr.model;

import lombok.Data;

/**
 * 火车票
 * 
 * @author Toint
 * @dete 2025/9/8
 */
@Data
public class TrainTicketResponse {
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
}
