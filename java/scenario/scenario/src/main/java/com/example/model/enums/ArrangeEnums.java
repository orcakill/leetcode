package com.example.model.enums;

/**
 * @Classname ArrangeEnums
 * @Description TODO
 * @Date 2022/11/7 23:02
 * @Created by orcakill
 */
public enum ArrangeEnums {
	
	arr_DZL_WDJ ("scenario/御魂整理/待整理/未点击"),
	;
	
	ArrangeEnums(String value) {
		this.value = value;
	}
	
	private final String value;
	
	public String getValue() {
		return value;
	}
}
