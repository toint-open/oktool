package cn.toint.oktool.util;

import cn.hutool.v7.core.codec.binary.Base64;
import cn.hutool.v7.core.net.url.UrlBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Toint
 * @date 2025/9/10
 */
public class KkFileViewUtil {
    /**
     * 构建文件预览链接
     *
     * @param previewServerUrl 预览服务链接
     * @param fileUrl       文件地址
     * @param fileName      文件名称(选填), 不同的文件, 文件名称一致的, 会导致预览服务器渲染历史缓存, 建议使用随机值作为文件主名称
     * @return kkfileview预览链接
     * @see <a href="https://kkview.cn/zh-cn/docs/usage.html">kkfileview使用指南</a>
     */
    public static String buildPreviewUrl(String previewServerUrl, String fileUrl, String fileName) {
        Assert.notBlankParam(previewServerUrl, "文件预览服务地址");
        Assert.notBlankParam(fileUrl, "文件链接");

        UrlBuilder previewServerUrlBuilder = UrlBuilder.ofHttpWithoutEncode(previewServerUrl);
        UrlBuilder fileUrlBuilder = UrlBuilder.ofHttpWithoutEncode(fileUrl);

        // 很多系统内不是直接暴露文件下载地址
        // 而是请求通过id、code等参数到通过统一的接口, 后端通过id或code等参数定位文件
        // 再通过OutputStream输出下载, 此时下载url是不带文件后缀名的
        // 预览时需要拿到文件名, 传一个参数fullfilename=xxx.xxx来指定文件名
        if (StringUtils.isNotBlank(fileName)) {
            fileUrlBuilder.addQuery("fullfilename", fileName);
        }

        String urlSafeBase64FileUrl = Base64.encodeUrlSafe(fileUrlBuilder.build());
        return previewServerUrlBuilder.addQuery("url", urlSafeBase64FileUrl).build();
    }
}
