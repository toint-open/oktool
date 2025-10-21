/*
 * Copyright 2025 Toint (599818663@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.toint.oktool.util;

import cn.toint.oktool.model.PublicIpInfo;
import cn.hutool.v7.http.HttpUtil;

import java.util.Optional;

/**
 * IP 工具
 *
 * @author Toint
 * @since 2025/3/18
 */
public class IpUtil {
    /**
     * 获取当前设备公网 ip
     *
     * @return ip 信息
     */
    public static Optional<PublicIpInfo> getPublicIpInfo() {
        final String result = HttpUtil.get("http://api.myip.la/cn?json");
        return Optional.ofNullable(JacksonUtil.tryReadValue(result, PublicIpInfo.class));
    }
}
