package cn.toint.oktool.spring.boot.bdocr.model;

import cn.hutool.v7.core.codec.binary.Base64;
import cn.hutool.v7.core.io.IoUtil;
import cn.hutool.v7.core.io.file.FileTypeUtil;
import cn.hutool.v7.core.net.url.UrlEncoder;
import cn.hutool.v7.http.HttpUtil;
import cn.hutool.v7.http.client.Response;
import cn.toint.oktool.util.Assert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 优先级：image > url > pdf_file > ofd_file ，当image字段存在时，url、pdf_file、ofd_file 字段失效
 *
 * @author Toint
 * @dete 2025/9/9
 */
@Data
public class BaseOcrRequest {
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
    private String pdfFileNum;

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
    private String ofdFileNum;

    public BaseOcrRequest() {
    }

    public BaseOcrRequest(String url) {
        file(url);
    }

    public BaseOcrRequest(byte[] bytes) {
        file(bytes);
    }

    /**
     * @param fileUrl 文件下载链接, 会校验文件大小并加载仅内存.
     */
    public BaseOcrRequest file(String fileUrl) {
        Assert.notBlank(fileUrl, "fileUrl must not be blank");
        try (Response response = HttpUtil.createGet(fileUrl).setMaxRedirects(3).send()) {
            long contentLength = response.contentLength();
            checkFileSize(contentLength);
            byte[] fileBytes = response.bodyBytes();
            checkFileSize(fileBytes.length);
            return file(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * @param fileBytes 文件字节, 自动识别文件类型并赋值对应字段
     */
    public BaseOcrRequest file(byte[] fileBytes) {
        String type;
        try (ByteArrayInputStream byteArrayInputStream = IoUtil.toStream(fileBytes)) {
            type = FileTypeUtil.getType(byteArrayInputStream);
            Assert.notBlank(type, "无法读取文件格式");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        if ("pdf".equalsIgnoreCase(type)) {
            setPdfFile(urlEncodeAndBase64(fileBytes));
        } else if ("ofd".equalsIgnoreCase(type)) {
            setOfdFile(urlEncodeAndBase64(fileBytes));
        } else if (List.of("jpg", "jpeg", "png", "bmp").contains(type)) {
            setImage(urlEncodeAndBase64(fileBytes));
        } else {
            throw new RuntimeException("不支持的格式: " + type);
        }

        return this;
    }

    private void checkFileSize(long fileSize) {
        if (fileSize <= 0) {
            throw new RuntimeException("无效的文件");
        }

        if (fileSize > 4 * 1024 * 1024) {
            throw new RuntimeException("文件过大");
        }
    }

    private String urlEncodeAndBase64(byte[] fileBytes) {
        return UrlEncoder.encodeQuery(Base64.encode(fileBytes));
    }

    private static String urlEncodeAndBase64(File file) {
        return UrlEncoder.encodeQuery(Base64.encode(file));
    }
}
