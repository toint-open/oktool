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

package cn.toint.oktool.oss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Toint
 * @date 2025/7/15
 */
@Getter
@AllArgsConstructor
public enum RegionAndEndpointEnum {
    // ========== 公共云 - 亚太-中国 ==========
    HANG_ZHOU("cn-hangzhou", "oss-cn-hangzhou.aliyuncs.com", "oss-cn-hangzhou-internal.aliyuncs.com"),
    SHANG_HAI("cn-shanghai", "oss-cn-shanghai.aliyuncs.com", "oss-cn-shanghai-internal.aliyuncs.com"),
    NAN_JING("cn-nanjing", "oss-cn-nanjing.aliyuncs.com", "oss-cn-nanjing-internal.aliyuncs.com"),
    FU_ZHOU("cn-fuzhou", "oss-cn-fuzhou.aliyuncs.com", "oss-cn-fuzhou-internal.aliyuncs.com"),
    WU_HAN("cn-wuhan-lr", "oss-cn-wuhan-lr.aliyuncs.com", "oss-cn-wuhan-lr-internal.aliyuncs.com"),
    QING_DAO("cn-qingdao", "oss-cn-qingdao.aliyuncs.com", "oss-cn-qingdao-internal.aliyuncs.com"),
    BEI_JING("cn-beijing", "oss-cn-beijing.aliyuncs.com", "oss-cn-beijing-internal.aliyuncs.com"),
    ZHANG_JIA_KOU("cn-zhangjiakou", "oss-cn-zhangjiakou.aliyuncs.com", "oss-cn-zhangjiakou-internal.aliyuncs.com"),
    HU_HE_HAO_TE("cn-huhehaote", "oss-cn-huhehaote.aliyuncs.com", "oss-cn-huhehaote-internal.aliyuncs.com"),
    WU_LAN_CHA_BU("cn-wulanchabu", "oss-cn-wulanchabu.aliyuncs.com", "oss-cn-wulanchabu-internal.aliyuncs.com"),
    SHEN_ZHEN("cn-shenzhen", "oss-cn-shenzhen.aliyuncs.com", "oss-cn-shenzhen-internal.aliyuncs.com"),
    HE_YUAN("cn-heyuan", "oss-cn-heyuan.aliyuncs.com", "oss-cn-heyuan-internal.aliyuncs.com"),
    GUANG_ZHOU("cn-guangzhou", "oss-cn-guangzhou.aliyuncs.com", "oss-cn-guangzhou-internal.aliyuncs.com"),
    CHENG_DU("cn-chengdu", "oss-cn-chengdu.aliyuncs.com", "oss-cn-chengdu-internal.aliyuncs.com"),
    HONG_KONG("cn-hongkong", "oss-cn-hongkong.aliyuncs.com", "oss-cn-hongkong-internal.aliyuncs.com"),
    CHINA_MAINLAND("rg-china-mainland", "oss-rg-china-mainland.aliyuncs.com", null),

    // ========== 公共云 - 亚太-其他 ==========
    TOKYO("ap-northeast-1", "oss-ap-northeast-1.aliyuncs.com", "oss-ap-northeast-1-internal.aliyuncs.com"),
    SEOUL("ap-northeast-2", "oss-ap-northeast-2.aliyuncs.com", "oss-ap-northeast-2-internal.aliyuncs.com"),
    SINGAPORE("ap-southeast-1", "oss-ap-southeast-1.aliyuncs.com", "oss-ap-southeast-1-internal.aliyuncs.com"),
    KUALA_LUMPUR("ap-southeast-3", "oss-ap-southeast-3.aliyuncs.com", "oss-ap-southeast-3-internal.aliyuncs.com"),
    JAKARTA("ap-southeast-5", "oss-ap-southeast-5.aliyuncs.com", "oss-ap-southeast-5-internal.aliyuncs.com"),
    MANILA("ap-southeast-6", "oss-ap-southeast-6.aliyuncs.com", "oss-ap-southeast-6-internal.aliyuncs.com"),
    BANGKOK("ap-southeast-7", "oss-ap-southeast-7.aliyuncs.com", "oss-ap-southeast-7-internal.aliyuncs.com"),

    // ========== 公共云 - 欧洲与美洲 ==========
    FRANKFURT("eu-central-1", "oss-eu-central-1.aliyuncs.com", "oss-eu-central-1-internal.aliyuncs.com"),
    LONDON("eu-west-1", "oss-eu-west-1.aliyuncs.com", "oss-eu-west-1-internal.aliyuncs.com"),
    SILICON_VALLEY("us-west-1", "oss-us-west-1.aliyuncs.com", "oss-us-west-1-internal.aliyuncs.com"),
    VIRGINIA("us-east-1", "oss-us-east-1.aliyuncs.com", "oss-us-east-1-internal.aliyuncs.com"),
    MEXICO("na-south-1", "oss-na-south-1.aliyuncs.com", "oss-na-south-1-internal.aliyuncs.com"),

    // ========== 公共云 - 中东 ==========
    DUBAI("me-east-1", "oss-me-east-1.aliyuncs.com", "oss-me-east-1-internal.aliyuncs.com"),

    // ========== 金融云 ==========
    HANG_ZHOU_FINANCE("cn-hangzhou-finance", null, "oss-cn-hzjbp-a-internal.aliyuncs.com"),
    SHANG_HAI_FINANCE("cn-shanghai-finance-1", null, "oss-cn-shanghai-finance-1-internal.aliyuncs.com"),
    SHEN_ZHEN_FINANCE("cn-shenzhen-finance-1", null, "oss-cn-shenzhen-finance-1-internal.aliyuncs.com"),
    BEI_JING_FINANCE("cn-beijing-finance-1", null, "oss-cn-beijing-finance-1-internal.aliyuncs.com"),
    HANG_ZHOU_FINANCE_PUBLIC("cn-hangzhou-finance", "oss-cn-hzfinance.aliyuncs.com", "oss-cn-hzfinance-internal.aliyuncs.com"),
    SHANG_HAI_FINANCE_PUBLIC("cn-shanghai-finance-1", "oss-cn-shanghai-finance-1-pub.aliyuncs.com", "oss-cn-shanghai-finance-1-pub-internal.aliyuncs.com"),
    SHEN_ZHEN_FINANCE_PUBLIC("cn-shenzhen-finance-1", "oss-cn-szfinance.aliyuncs.com", "oss-cn-szfinance-internal.aliyuncs.com"),
    BEI_JING_FINANCE_PUBLIC("cn-beijing-finance-1", "oss-cn-beijing-finance-1-pub.aliyuncs.com", "oss-cn-beijing-finance-1-pub-internal.aliyuncs.com"),

    // ========== 政务云 ==========
    NORTH_GOV("cn-north-2-gov-1", null, "oss-cn-north-2-gov-1-internal.aliyuncs.com");

    private final String region;
    private final String endpoint;
    private final String internalEndpoint;
}
