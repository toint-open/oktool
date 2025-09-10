package cn.toint.oktool.util;

import cn.hutool.v7.core.codec.binary.Base64;
import cn.hutool.v7.core.net.url.UrlBuilder;
import cn.hutool.v7.core.net.url.UrlEncoder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Toint
 * @dete 2025/9/10
 */
public class KkFileViewUtil {
    /**
     * 构建文件预览链接
     *
     * @param viewServerUrl 预览服务链接
     * @param fileUrl       文件地址
     * @param fileName      文件名称(选填), 文件名称过长可能会导致预览失败, 建议使用随机值作为文件主名称
     * @return kkfileview预览链接
     * @see <a href="https://kkview.cn/zh-cn/docs/usage.html">kkfileview使用指南</a>
     */
    public static String buildViewUrl(String viewServerUrl, String fileUrl, String fileName) {
        Assert.notBlank(viewServerUrl, "viewServerUrl must not be blank");
        Assert.notBlank(fileUrl, "fileUrl must not be blank");

        // 文件链接
        UrlBuilder fileUrlBuilder = UrlBuilder.ofHttp(fileUrl);

        // 很多系统内不是直接暴露文件下载地址
        // 而是请求通过id、code等参数到通过统一的接口, 后端通过id或code等参数定位文件
        // 再通过OutputStream输出下载, 此时下载url是不带文件后缀名的
        // 预览时需要拿到文件名, 传一个参数fullfilename=xxx.xxx来指定文件名
        if (StringUtils.isNotBlank(fileName)) {
            fileUrlBuilder.addQuery("fullfilename", fileName);
        }

        return UrlBuilder.ofHttp(viewServerUrl)
                .addQuery("url", UrlEncoder.encodeQuery(Base64.encode(fileUrlBuilder.build())))
                .build();
    }
}
