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

import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * 百度坐标（BD09）、国测局坐标（火星坐标，GCJ02）、和WGS84坐标系之间的转换的工具
 *
 * @author Toint
 * @date 2025/7/19
 * @see <a href="https://gist.github.com/jp1017/71bd0976287ce163c11a7cb963b04dd8">源码仓库</a>
 */
public class CoordinateTransformUtil {
    static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    // π
    static double pi = 3.1415926535897932384626;
    // 长半轴
    static double a = 6378245.0;
    // 扁率
    static double ee = 0.00669342162296594323;

    /**
     * 百度坐标系(BD-09)转WGS坐标
     *
     * @param lng 百度坐标纬度
     * @param lat 百度坐标经度
     * @return WGS84坐标数组
     */
    public static double[] bd09ToWgs84(double lng, double lat) {
        double[] gcj = bd09ToGcj02(lng, lat);
        return gcj02ToWgs84(gcj[0], gcj[1]);
    }

    /**
     * WGS坐标转百度坐标系(BD-09)
     *
     * @param lng WGS84坐标系的经度
     * @param lat WGS84坐标系的纬度
     * @return 百度坐标数组
     */
    public static double[] wgs84ToBd09(double lng, double lat) {
        double[] gcj = wgs84ToGcj02(lng, lat);
        return gcj02ToBd09(gcj[0], gcj[1]);
    }

    /**
     * 火星坐标系(GCJ-02)转百度坐标系(BD-09)
     * <p>
     * 谷歌、高德——>百度
     *
     * @param lng 火星坐标经度
     * @param lat 火星坐标纬度
     * @return 百度坐标数组
     */
    public static double[] gcj02ToBd09(double lng, double lat) {
        double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_pi);
        double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_pi);
        double bd_lng = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[]{bd_lng, bd_lat};
    }

    /**
     * 百度坐标系(BD-09)转火星坐标系(GCJ-02)
     * <p>
     * 百度——>谷歌、高德
     *
     * @param bd_lon 百度坐标纬度
     * @param bd_lat 百度坐标经度
     * @return 火星坐标数组
     */
    public static double[] bd09ToGcj02(double bd_lon, double bd_lat) {
        double x = bd_lon - 0.0065;
        double y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lng = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new double[]{gg_lng, gg_lat};
    }

    /**
     * WGS84转GCJ02(火星坐标系)
     *
     * @param lng WGS84坐标系的经度
     * @param lat WGS84坐标系的纬度
     * @return 火星坐标数组
     */
    public static double[] wgs84ToGcj02(double lng, double lat) {
        if (outOfChina(lng, lat)) {
            return new double[]{lng, lat};
        }
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * pi;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        return new double[]{mglng, mglat};
    }

    /**
     * GCJ02(火星坐标系)转WGS84
     *
     * @param globalCoordinates GCJ02(火星坐标系)
     * @return WGS84坐标
     */
    public static GlobalCoordinates gcj02ToWgs84(GlobalCoordinates globalCoordinates) {
        double latitude = globalCoordinates.getLatitude();
        double longitude = globalCoordinates.getLongitude();
        double[] result = gcj02ToWgs84(longitude, latitude);
        longitude = result[0];
        latitude = result[1];
        return new GlobalCoordinates(latitude, longitude);
    }

    /**
     * GCJ02(火星坐标系)转GPS84
     *
     * @param lng 火星坐标系的经度
     * @param lat 火星坐标系纬度
     * @return WGS84坐标数组
     */
    public static double[] gcj02ToWgs84(double lng, double lat) {
        if (outOfChina(lng, lat)) {
            return new double[]{lng, lat};
        }
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * pi;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        return new double[]{lng * 2 - mglng, lat * 2 - mglat};
    }

    /**
     * 纬度转换
     */
    public static double transformlat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 * Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * pi) + 40.0 * Math.sin(lat / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * pi) + 320 * Math.sin(lat * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 经度转换
     */
    public static double transformlng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 * Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * pi) + 40.0 * Math.sin(lng / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * pi) + 300.0 * Math.sin(lng / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 判断是否在国内，不在国内不做偏移
     */
    public static boolean outOfChina(double lng, double lat) {
        if (lng < 72.004 || lng > 137.8347) {
            return true;
        } else return lat < 0.8293 || lat > 55.8271;
    }
}
