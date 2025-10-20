package cn.toint.oktool.spring.boot.bdocr.model;

import java.util.Objects;

/**
 * 文件识别标识
 *
 * @author Toint
 * @dete 2025/9/12
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
