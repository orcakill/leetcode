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
	;
	
	ArrangeEnums(String value) {
		this.value = value;
	}
	
	private final String value;
	
	public String getValue() {
		return value;
	}
}
