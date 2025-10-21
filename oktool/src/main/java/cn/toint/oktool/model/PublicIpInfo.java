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

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/3/18
 */
public class PublicIpInfo {
    /**
     * 当前设备公网ip
     */
    private String ip;

    /**
     * 当前设备位置信息
     */
    private Location location;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PublicIpInfo that = (PublicIpInfo) o;
        return Objects.equals(ip, that.ip) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, location);
    }

    @Override
    public String toString() {
        return "PublicIpInfo{" +
                "ip='" + ip + '\'' +
                ", location=" + location +
                '}';
    }

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

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return Objects.equals(countryCode, location.countryCode) && Objects.equals(countryName, location.countryName) && Objects.equals(province, location.province) && Objects.equals(city, location.city) && Objects.equals(longitude, location.longitude) && Objects.equals(latitude, location.latitude);
        }

        @Override
        public int hashCode() {
            return Objects.hash(countryCode, countryName, province, city, longitude, latitude);
        }

        @Override
        public String toString() {
            return "Location{" +
                    "countryCode='" + countryCode + '\'' +
                    ", countryName='" + countryName + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    '}';
        }
    }

}
