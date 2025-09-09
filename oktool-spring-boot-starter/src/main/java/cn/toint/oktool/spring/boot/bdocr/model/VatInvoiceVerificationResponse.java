package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Toint
 * @dete 2025/9/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VatInvoiceVerificationResponse extends BaseOcrResponse {
    /**
     * 识别结果数，表示words_result的元素个数
     */
    @JsonProperty("words_result_num")
    private Integer wordsResultNum;

    /**
     * 查验结果。查验成功返回“0001”，查验失败返回对应查验结果错误码.
     * 9999	查验失败	查验失败，业务出现异常，请提交工单咨询
     * 0002	超过该张票当天查验次数	此发票今日查询次数已达上限（5次），请次日查询
     * 0005	请求不合法	发票入参格式有误，请核对后再查询
     * 0006	发票信息不一致	发票信息有误，请核对后再查询
     * 0009	发票不存在	所查发票不存在
     * 1004	已超过最大查验量	已超过最大查验量，请提交工单咨询
     * 1005	查询发票不规范	信息有误，请核对后再查询
     * 1006	查验异常	发票信息有误，请核对后再查询
     * 1008	字段不能为空	发票请求参数不能为空
     * 1009	参数长度不正确	参数长度不符合规范，确认参数，再次查验
     * 1014	日期当天的不能查验	日期当天的不能查验，请隔天再查
     * 1015	超过5年的不能查验	超过5年的不能查验
     * 1020	没有查验权限	没有查验权限，请提交工单咨询
     * 1021	网络超时	税局维护升级，暂时无法查验，请提交工单咨询
     */
    @JsonProperty("VerifyResult")
    private String verifyResult;

    /**
     * 查验结果信息。查验成功且发票为真返回“查验成功发票一致“，查验失败返回对应错误原因
     */
    @JsonProperty("VerifyMessage")
    private String verifyMessage;

    /**
     * 查验次数。为历史查验次数
     */
    @JsonProperty("VerifyFrequency")
    private String verifyFrequency;

    /**
     * 发票状态。Y：已作废；H：已冲红；N：未作废；BH：部分红冲；QH：全额红冲
     */
    @JsonProperty("InvalidSign")
    private String invalidSign;
}
