package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 优先级：image > url > pdf_file > ofd_file ，当image字段存在时，url、pdf_file、ofd_file 字段失效
 *
 * @author Toint
 * @dete 2025/9/7
 */
@Data
public class MultipleInvoiceRequest {
    /**
     * 图像数据，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px，支持jpg/jpeg/png/bmp格式
     * <br>
     * image/url/pdf_file/ofd_file, 4选1
     */
    private String image;

    /**
     * 图片完整url，url长度不超过1024字节，url对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px，支持jpg/jpeg/png/bmp格式
     * <br>
     * 优先级：image > url > pdf_file > ofd_file，当image字段存在时，url字段失效
     * <br>
     * 请注意关闭URL防盗链
     */
    private String url;

    /**
     * PDF文件，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px
     * <br>
     * 优先级：image > url > pdf_file > ofd_file，当image、url字段存在时，pdf_file字段失效
     * <br>
     * image/url/pdf_file/ofd_file, 4选1
     */
    @JsonProperty("pdf_file")
    private String pdfFile;

    /**
     * 需要识别的PDF文件的对应页码，当 pdf_file 参数有效时，识别传入页码的对应页面内容，若不传入，则默认识别第 1 页
     */
    @JsonProperty("pdf_file_num")
    private Integer pdfFileNum;

    /**
     * OFD文件，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px
     * <br>
     * 优先级：image > url > pdf_file > ofd_file，当image、url、pdf_file字段存在时，ofd_file字段失效
     * <br>
     * image/url/pdf_file/ofd_file, 4选1
     */
    @JsonProperty("ofd_file")
    private String ofdFile;

    /**
     * 需要识别的OFD文件的对应页码，当 ofd_file 参数有效时，识别传入页码的对应页面内容，若不传入，则默认识别第 1 页
     */
    @JsonProperty("ofd_file_num")
    private Integer ofdFileNum;

    /**
     * 是否开启验真，默认为 false，即不开启，当为 true 时，返回匹配发票验真接口所需的6要素信息，具体返回信息详见末尾说明
     */
    @JsonProperty("verify_parameter")
    private boolean verifyParameter;

    /**
     * 是否返回字段置信度，默认为 false ，即不返回
     */
    private boolean probability;

    /**
     * 是否返回字段位置坐标，默认为 false，即不返回
     */
    private boolean location;
}
