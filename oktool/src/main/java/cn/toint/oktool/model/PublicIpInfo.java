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

package cn.toint.oktool.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Toint
 * @date 2025/3/18
 */
@Data
public class PublicIpInfo {
    /**
     * 当前设备公网ip
     */
    private String ip;

    /**
     * 当前设备位置信息
     */
    private Location location;

    @Data
    public static class Location {
        /**
         * 国家代码
         */
        @JsonProperty("country_code")
        private String  countryCode;

        /**
         * 国家名称
         */
        @JsonProperty("country_name")
        private String  countryName;

        /**
         * 省份
         */
        private String province;

        /**
         * 城市
         */
        private String city;

        /**
         * 经度
         */
        private String  longitude;

        /**
         * 维度
         */
        private String  latitude;
    }
}
