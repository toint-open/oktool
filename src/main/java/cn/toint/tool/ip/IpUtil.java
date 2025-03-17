package cn.toint.tool.ip;

import cn.toint.tool.ip.model.PublicIpInfo;
import cn.toint.tool.json.JacksonUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.http.HttpUtil;

import java.util.Optional;

/**
 * @author Toint
 * @date 2025/3/18
 */
public class IpUtil {
    /**
     * 获取当前设备公网 ip
     */
    public static Optional<PublicIpInfo> getPublicIpInfo() {
        return Optional.ofNullable(HttpUtil.get("http://api.myip.la/cn?json"))
                .filter(StrUtil::isNotBlank)
                .map(json -> JacksonUtil.readValue(json, PublicIpInfo.class));
    }
}
