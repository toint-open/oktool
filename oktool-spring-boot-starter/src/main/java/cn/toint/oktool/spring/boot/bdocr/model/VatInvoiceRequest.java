package cn.toint.oktool.spring.boot.bdocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Toint
 * @dete 2025/9/8
 */
@Data
public class VatInvoiceRequest {
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
     * 进行识别的增值税发票类型，默认为 normal，可缺省
     * - normal：可识别增值税普票、专票、电子发票
     * - roll：可识别增值税卷票
     */
    private String type = "normal";

    /**
     * 是否开启印章判断功能，并返回印章内容的识别结果
     * - true：开启
     * - false：不开启
     */
    @JsonProperty("seal_tag")
    private boolean sealTag;
}
