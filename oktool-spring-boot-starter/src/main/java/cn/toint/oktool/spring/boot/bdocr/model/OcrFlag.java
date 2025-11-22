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

package cn.toint.oktool.spring.boot.bdocr.model;

import java.util.Objects;

/**
 * 文件识别标识
 *
 * @author Toint
 * @since 2025/9/12
 */
public class OcrFlag {
    public String getOcrFlag() {
        return ocrFlag;
    }

    public void setOcrFlag(String ocrFlag) {
        this.ocrFlag = ocrFlag;
    }

    /**
     * 文件识别后, 可根据本字段寻找对应结果
     */
    private String ocrFlag;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OcrFlag ocrFlag1 = (OcrFlag) o;
        return Objects.equals(ocrFlag, ocrFlag1.ocrFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ocrFlag);
    }

    @Override
    public String toString() {
        return "OcrFlag{" +
                "ocrFlag='" + ocrFlag + '\'' +
                '}';
    }
}
