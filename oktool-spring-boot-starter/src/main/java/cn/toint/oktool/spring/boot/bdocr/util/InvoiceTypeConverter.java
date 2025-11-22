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

package cn.toint.oktool.spring.boot.bdocr.util;

import cn.hutool.v7.core.text.StrUtil;
import cn.toint.oktool.spring.boot.bdocr.model.InvoiceVerifyType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 发票类型转换器
 * 将OCR识别的发票类型转换为发票验真所需的参数
 */
public class InvoiceTypeConverter {

    // OCR识别结果到发票验真类型的映射表
    private static final Map<String, InvoiceVerifyType> OCR_TO_VERIFY_TYPE_MAP = new HashMap<>();

    static {
        // 1. 首先添加枚举自身的code和description映射
        for (InvoiceVerifyType value : InvoiceVerifyType.values()) {
            String code = value.getCode();
            OCR_TO_VERIFY_TYPE_MAP.put(code.toLowerCase(), value);
            OCR_TO_VERIFY_TYPE_MAP.put(code.toUpperCase(), value);
            OCR_TO_VERIFY_TYPE_MAP.put(value.getDescription(), value);
        }

        // 2. 增值税普通发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("普票", InvoiceVerifyType.NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("普通发票", InvoiceVerifyType.NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普通发票", InvoiceVerifyType.NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普票", InvoiceVerifyType.NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增普票", InvoiceVerifyType.NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("纸质普通发票", InvoiceVerifyType.NORMAL_INVOICE);

        // 3. 增值税专用发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("专票", InvoiceVerifyType.SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("专用发票", InvoiceVerifyType.SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税专用发票", InvoiceVerifyType.SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税专票", InvoiceVerifyType.SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增专票", InvoiceVerifyType.SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("纸质专用发票", InvoiceVerifyType.SPECIAL_VAT_INVOICE);

        // 4. 电子普通发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("电子普票", InvoiceVerifyType.ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子普通发票", InvoiceVerifyType.ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普通发票（电子）", InvoiceVerifyType.ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普通发票(电子)", InvoiceVerifyType.ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税电子普通发票", InvoiceVerifyType.ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子增值税普通发票", InvoiceVerifyType.ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("通用机打电子发票", InvoiceVerifyType.ELEC_NORMAL_INVOICE);

        // 5. 电子专用发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("电子专票", InvoiceVerifyType.ELEC_SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子专用发票", InvoiceVerifyType.ELEC_SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税电子专用发票", InvoiceVerifyType.ELEC_SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税专用发票（电子）", InvoiceVerifyType.ELEC_SPECIAL_VAT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税专用发票(电子)", InvoiceVerifyType.ELEC_SPECIAL_VAT_INVOICE);

        // 6. 卷式发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("卷式发票", InvoiceVerifyType.ROLL_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("卷票", InvoiceVerifyType.ROLL_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普通发票卷式", InvoiceVerifyType.ROLL_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普通发票（卷式）", InvoiceVerifyType.ROLL_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普通发票(卷式)", InvoiceVerifyType.ROLL_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普通发票（卷票）", InvoiceVerifyType.ROLL_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税普通发票(卷票)", InvoiceVerifyType.ROLL_NORMAL_INVOICE);

        // 7. 通行费发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("通行费发票", InvoiceVerifyType.TOLL_ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("通行费电子发票", InvoiceVerifyType.TOLL_ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("通行费电子普票", InvoiceVerifyType.TOLL_ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("通行费增值税电子普通发票", InvoiceVerifyType.TOLL_ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税电子普通发票（通行费）", InvoiceVerifyType.TOLL_ELEC_NORMAL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("增值税电子普通发票(通行费)", InvoiceVerifyType.TOLL_ELEC_NORMAL_INVOICE);

        // 8. 区块链发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("区块链发票", InvoiceVerifyType.BLOCKCHAIN_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("区块链电子发票", InvoiceVerifyType.BLOCKCHAIN_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("区块链电子普通发票", InvoiceVerifyType.BLOCKCHAIN_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("深圳区块链电子发票", InvoiceVerifyType.BLOCKCHAIN_INVOICE);

        // 9. 全电发票（专用）相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("全电专票", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票专用发票", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票(专用发票)", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票（专用发票）", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);
        OCR_TO_VERIFY_TYPE_MAP.put("数电专票", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);
        OCR_TO_VERIFY_TYPE_MAP.put("数字化电子发票（专用发票）", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);
        OCR_TO_VERIFY_TYPE_MAP.put("数字化电子发票(专用发票)", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票（专用发票）", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票(专用发票)", InvoiceVerifyType.ELEC_INVOICE_SPECIAL);

        // 10. 全电发票（普通）相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("全电普票", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票普通发票", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票(普通发票)", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票（普通发票）", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("数电普票", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("数电票", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("数字化电子发票", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("数字化电子发票（普通发票）", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("数字化电子发票(普通发票)", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票（普通发票）", InvoiceVerifyType.ELEC_INVOICE_NORMAL);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票(普通发票)", InvoiceVerifyType.ELEC_INVOICE_NORMAL);

        // 11. 货运发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("货运发票", InvoiceVerifyType.SPECIAL_FREIGHT_TRANSPORT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("货运专票", InvoiceVerifyType.SPECIAL_FREIGHT_TRANSPORT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("货运运输业增值税专用发票", InvoiceVerifyType.SPECIAL_FREIGHT_TRANSPORT_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("货物运输业增值税专用发票", InvoiceVerifyType.SPECIAL_FREIGHT_TRANSPORT_INVOICE);

        // 12. 机动车发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("机动车发票", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("机动车销售发票", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("机动车销售统一发票", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("机动车销售统一发票（电子）", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("机动车销售统一发票(电子)", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票（机动车销售统一发票）", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票(机动车销售统一发票)", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("纸质机动车销售统一发票", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票（纸质机动车销售统一发票）", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票(纸质机动车销售统一发票)", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("新车发票", InvoiceVerifyType.MOTOR_VEHICLE_INVOICE);

        // 13. 二手车发票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("二手车发票", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("二手车销售发票", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("二手车销售统一发票", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("二手车销售统一发票（电子）", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("二手车销售统一发票(电子)", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票（二手车销售统一发票）", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票(二手车销售统一发票)", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("纸质二手车销售统一发票", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票（纸质二手车销售统一发票）", InvoiceVerifyType.USED_VEHICLE_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票(纸质二手车销售统一发票)", InvoiceVerifyType.USED_VEHICLE_INVOICE);

        // 14. 航空客票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("航空客票", InvoiceVerifyType.ELEC_FLIGHT_ITINERARY_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("航空电子客票", InvoiceVerifyType.ELEC_FLIGHT_ITINERARY_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("航空运输电子客票行程单", InvoiceVerifyType.ELEC_FLIGHT_ITINERARY_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票（航空运输电子客票行程单）", InvoiceVerifyType.ELEC_FLIGHT_ITINERARY_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票(航空运输电子客票行程单)", InvoiceVerifyType.ELEC_FLIGHT_ITINERARY_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("飞机票", InvoiceVerifyType.ELEC_FLIGHT_ITINERARY_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("机票行程单", InvoiceVerifyType.ELEC_FLIGHT_ITINERARY_INVOICE);

        // 15. 铁路客票相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("铁路客票", InvoiceVerifyType.ELEC_TRAIN_TICKET_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("铁路电子客票", InvoiceVerifyType.ELEC_TRAIN_TICKET_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票（铁路电子客票）", InvoiceVerifyType.ELEC_TRAIN_TICKET_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("电子发票(铁路电子客票)", InvoiceVerifyType.ELEC_TRAIN_TICKET_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("火车票", InvoiceVerifyType.ELEC_TRAIN_TICKET_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("高铁票", InvoiceVerifyType.ELEC_TRAIN_TICKET_INVOICE);

        // 16. 全电发票（含通行费）相关映射
        OCR_TO_VERIFY_TYPE_MAP.put("全电通行费发票", InvoiceVerifyType.ELEC_TOLL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票含通行费标识", InvoiceVerifyType.ELEC_TOLL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票（含通行费标识）", InvoiceVerifyType.ELEC_TOLL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("全电发票(含通行费标识)", InvoiceVerifyType.ELEC_TOLL_INVOICE);
        OCR_TO_VERIFY_TYPE_MAP.put("数电发票（通行费）", InvoiceVerifyType.ELEC_TOLL_INVOICE);
    }

    /**
     * 将OCR识别的发票类型转换为发票验真所需的类型参数
     *
     * @param ocrResult OCR识别的发票类型文本
     * @return 发票验真类型枚举. 无法识别则返回{@code null}
     */
    public static InvoiceVerifyType convertOcrToVerifyType(String ocrResult) {
        ocrResult = StrUtil.trim(ocrResult);

        if (StringUtils.isBlank(ocrResult)) {
            return null;
        }

        return OCR_TO_VERIFY_TYPE_MAP.get(ocrResult);
    }

    /**
     * 将OCR识别的发票类型转换为发票验真所需的类型参数
     *
     * @param ocrResult OCR识别的发票类型文本
     * @return 发票验真类型枚举. 无法识别则返回{@link InvoiceVerifyType#UNKNOWN}
     */
    public static InvoiceVerifyType convertOcrToVerifyTypeNotNull(String ocrResult) {
        return Optional.ofNullable(convertOcrToVerifyType(ocrResult))
                .orElse(InvoiceVerifyType.UNKNOWN);
    }
}