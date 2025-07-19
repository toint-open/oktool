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

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 地理工具
 *
 * <p>坐标系转换可使用{@link CoordinateTransformUtil}</p>
 *
 * @author Toint
 * @date 2025/7/19
 */
public class GeoUtil {

    private static final GeodeticCalculator instance = new GeodeticCalculator();

    /**
     * 计算两个地点之间的距离
     *
     * @param reference 坐标系
     * @param g1        位置1
     * @param g2        位置2
     * @return 距离信息
     * <p>获取距离: curve.getEllipsoidalDistance()</p>
     * <p>获取方位角: curve.getAzimuth()</p>
     * <p>获取反向方位角: curve.getReverseAzimuth()</p>
     */
    public static GeodeticCurve calculateCurve(Ellipsoid reference, GlobalCoordinates g1, GlobalCoordinates g2) {
        Assert.notNull(reference, "坐标系不能为空");
        Assert.notNull(g1, "位置不能为空");
        Assert.notNull(g2, "位置不能为空");
        return instance.calculateGeodeticCurve(reference, g1, g2);
    }

    /**
     * 计算两个地点之间的距离
     *
     * @param g1 位置1, WGS84
     * @param g2 位置2, WGS84
     * @return 距离信息
     * <p>获取距离: curve.getEllipsoidalDistance()</p>
     * <p>获取方位角: curve.getAzimuth()</p>
     * <p>获取反向方位角: curve.getReverseAzimuth()</p>
     */
    public static GeodeticCurve calculateCurveWgs84(GlobalCoordinates g1, GlobalCoordinates g2) {
        Assert.notNull(g1, "位置不能为空");
        Assert.notNull(g2, "位置不能为空");
        return instance.calculateGeodeticCurve(Ellipsoid.WGS84, g1, g2);
    }

    /**
     * 获取距离(单位米)
     *
     * @param geodeticCurve 距离信息
     * @return 距离距离(单位米)
     */
    public static long getDistanceM(GeodeticCurve geodeticCurve) {
        Assert.notNull(geodeticCurve, "距离信息不能为空");
        double ellipsoidalDistance = geodeticCurve.getEllipsoidalDistance();
        return Math.round(ellipsoidalDistance);
    }

    /**
     * 获取距离(单位千米)
     *
     * @param geodeticCurve 距离信息
     * @return 距离距离(单位千米), 保留3位小数
     */
    public static BigDecimal getDistanceKm(GeodeticCurve geodeticCurve) {
        Assert.notNull(geodeticCurve, "距离信息不能为空");
        double ellipsoidalDistance = geodeticCurve.getEllipsoidalDistance();
        return BigDecimal.valueOf(ellipsoidalDistance)
                .divide(BigDecimal.valueOf(1000), 3, RoundingMode.HALF_UP);
    }
}
