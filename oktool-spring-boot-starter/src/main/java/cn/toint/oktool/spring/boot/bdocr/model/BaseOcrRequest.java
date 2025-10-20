package cn.toint.oktool.spring.boot.bdocr.model;

import cn.hutool.v7.core.codec.binary.Base64;
import cn.hutool.v7.core.io.IoUtil;
import cn.hutool.v7.core.io.file.FileTypeUtil;
import cn.hutool.v7.core.net.url.UrlEncoder;
import cn.hutool.v7.http.HttpUtil;
import cn.hutool.v7.http.client.Request;
import cn.hutool.v7.http.client.Response;
import cn.hutool.v7.http.meta.HeaderName;
import cn.hutool.v7.http.meta.HttpHeaderUtil;
import cn.hutool.v7.http.meta.HttpStatus;
import cn.toint.oktool.util.Assert;
import cn.toint.oktool.util.FileNameUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 优先级：image > url > pdf_file > ofd_file ，当image字段存在时，url、pdf_file、ofd_file 字段失效
 *
 * @author Toint
 * @date 2025/9/9
 */
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
    private int pdfFileNum = 1;

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
    private int ofdFileNum = 1;

    public BaseOcrRequest() {
    }

    public BaseOcrRequest(String url) {
        file(url);
    }

    public BaseOcrRequest(byte[] bytes) {
        file(bytes);
    }

    /**
     * @param fileUrl 文件下载链接, 会校验文件大小并加载进内存.
     */
    public BaseOcrRequest file(String fileUrl) {
        Assert.notBlank(fileUrl, "fileUrl must not be blank");

        // 最大重定向次数, 避免一直重定向
        AtomicInteger maxRedirectSize = new AtomicInteger(5);
        Request request = HttpUtil.createGet(fileUrl);
        // hutool会给默认的user-agent, 默认的user-agent存在一些问题, 所以这里去掉
        request.header(HeaderName.USER_AGENT, null);

        try (Response response = request.send()) {
            final int code = response.getStatus();

            // http框架存在重定向资源泄露问题, 所以由自己实现重定向.
            if (HttpStatus.isRedirected(code)) {
                Assert.isTrue(maxRedirectSize.decrementAndGet() >= 0, "文件下载达到最大重定向次数, 已拦截");
                return file(response.header(HeaderName.LOCATION.getValue()));
            }

            // 校验头信息
            long contentLength = response.contentLength();
            checkFileSize(contentLength);

            // 尝试通过响应头拿到文件类型
            String extName = null;
            String fileName = HttpHeaderUtil.getFileNameFromDisposition(response.headers(), null);
            if (StringUtils.isNotBlank(fileName)) {
                extName = FileNameUtil.getSuffix(fileName);
            }

            // 尝试通过二进制头获取
            byte[] fileBytes = response.bodyBytes();
            return file(fileBytes, extName);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * @param fileBytes 文件字节, 自动识别文件类型并赋值对应字段
     */
    public BaseOcrRequest file(byte[] fileBytes) {
        file(fileBytes, null);
        return this;
    }

    /**
     * @param fileBytes 文件字节, 会校验字节大小
     * @param extName 文件类型(后缀), 传空值则通过二进制头获取文件类型
     */
    public BaseOcrRequest file(byte[] fileBytes, String extName) {
        Assert.notNull(fileBytes, "fileBytes must not be null");
        checkFileSize(fileBytes.length);

        // 文件类型空值, 则通过二进制头获取文件类型
        if (StringUtils.isBlank(extName)) {
            try (ByteArrayInputStream byteArrayInputStream = IoUtil.toStream(fileBytes)) {
                extName = FileTypeUtil.getType(byteArrayInputStream);
                Assert.notBlank(extName, "无法读取文件格式");
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        if ("pdf".equalsIgnoreCase(extName)) {
            setPdfFile(urlEncodeAndBase64(fileBytes));
        } else if ("ofd".contains(extName)) {
            setOfdFile(urlEncodeAndBase64(fileBytes));
        } else if (List.of("jpg", "jpeg", "png", "bmp").contains(extName)) {
            setImage(urlEncodeAndBase64(fileBytes));
        } else {
            throw new RuntimeException("不支持的格式: " + extName);
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

    public void checkFile() {
        if (StringUtils.isAllBlank(getUrl(), getImage(), getPdfFile(), getOfdFile())) {
            throw new RuntimeException("image/url/pdf_file/ofd_file, 4选1");
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }

    public int getPdfFileNum() {
        return pdfFileNum;
    }

    public void setPdfFileNum(int pdfFileNum) {
        this.pdfFileNum = pdfFileNum;
    }

    public String getOfdFile() {
        return ofdFile;
    }

    public void setOfdFile(String ofdFile) {
        this.ofdFile = ofdFile;
    }

    public int getOfdFileNum() {
        return ofdFileNum;
    }

    public void setOfdFileNum(int ofdFileNum) {
        this.ofdFileNum = ofdFileNum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseOcrRequest that = (BaseOcrRequest) o;
        return pdfFileNum == that.pdfFileNum && ofdFileNum == that.ofdFileNum && Objects.equals(image, that.image) && Objects.equals(url, that.url) && Objects.equals(pdfFile, that.pdfFile) && Objects.equals(ofdFile, that.ofdFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, url, pdfFile, pdfFileNum, ofdFile, ofdFileNum);
    }

    @Override
    public String toString() {
        return "BaseOcrRequest{" +
                "image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", pdfFile='" + pdfFile + '\'' +
                ", pdfFileNum=" + pdfFileNum +
                ", ofdFile='" + ofdFile + '\'' +
                ", ofdFileNum=" + ofdFileNum +
                '}';
    }
}
