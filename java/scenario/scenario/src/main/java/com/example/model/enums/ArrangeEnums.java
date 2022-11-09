package com.example.model.enums;

/**
 * @Classname ArrangeEnums
 * @Description TODO
 * @Date 2022/11/7 23:02
 * @Created by orcakill
 */
public enum ArrangeEnums {
	
	arrange_DZL_WDJ ("scenario/御魂整理/待整理/未点击"),
	arrange_GHYH("scenario/御魂整理/更换御魂"),
	arrange_ZL_WDJ("scenario/御魂整理/整理/未点击"),
	arrange_FA_WDJ("scenario/御魂整理/方案/未点击"),
	arrange_FAXQH_WDJ("scenario/御魂整理/方案下强化/未点击"),
	arrange_FASDTS_WDJ("scenario/御魂整理/方案速度提升/未点击"),
	arrange_PX("scenario/御魂整理/排序"),
	arrange_DZL_YDJ ("scenario/御魂整理/待整理/已点击"),
	arrange_JRQH ("scenario/御魂整理/进入强化"),
	arrange_SXQJG ("scenario/御魂整理/四星青吉鬼"),
	arrange_QH ("scenario/御魂整理/强化"),
	arrange_DJTS ("scenario/御魂整理/等级提升"),
	arrange_ZSJYH ("scenario/御魂整理/左上角御魂"),
	arrange_YHQHSX_SD ("scenario/御魂整理/御魂强化属性/速度"),
	arrange_YHQHSX_XGDK ("scenario/御魂整理/御魂强化属性/效果抵抗"),
	arrange_YHQHSX_XGMZ ("scenario/御魂整理/御魂强化属性/效果命中"),
	arrange_YHQHSX_BJ ("scenario/御魂整理/御魂强化属性/暴击"),
	arrange_YHQHSX_BJSH ("scenario/御魂整理/御魂强化属性/暴击伤害"),
	arrange_YHQHSX_GJ ("scenario/御魂整理/御魂强化属性/攻击"),
	arrange_YHQHSX_GJJC ("scenario/御魂整理/御魂强化属性/攻击加成"),
	arrange_YHQHSX_SM ("scenario/御魂整理/御魂强化属性/生命"),
	arrange_YHQHSX_SMJC ("scenario/御魂整理/御魂强化属性/生命加成"),
	arrange_YHQHSX_FY ("scenario/御魂整理/御魂强化属性/防御"),
	arrange_YHQHSX_FYJC ("scenario/御魂整理/御魂强化属性/防御加成"),
	arrange_QD ("scenario/御魂整理/确定"),
	arrange_QZ ("scenario/御魂整理/弃置"),
	;
	
	ArrangeEnums(String value) {
		this.value = value;
	}
	
	private final String value;
	
	public String getValue() {
		return value;
	}
}
