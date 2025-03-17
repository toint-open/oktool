package cn.toint.tool.ip.model;

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
