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

import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Toint
 * @date 2025/7/19
 */
public class GeoUtilTest {

    private static final Logger log = LoggerFactory.getLogger(GeoUtilTest.class);

    @Test
    void calculateDistanceTest() {
        // 北京天安门
        GlobalCoordinates g1 = new GlobalCoordinates(39.909187, 116.397455);
        // 佛山人民政府
        GlobalCoordinates g2 = new GlobalCoordinates(23.021351, 113.121586);

        GeodeticCurve geodeticCurve = GeoUtil.calculateCurveWgs84(g1, g2);
        System.out.println(geodeticCurve.getEllipsoidalDistance());
        log.info("距离(m): {}", GeoUtil.getDistanceM(geodeticCurve));
        log.info("距离(km): {}", GeoUtil.getDistanceKm(geodeticCurve));
    }
}
