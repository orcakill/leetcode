package com.example.model.enums;

/**
 * @Classname ActivityEnums
 * @Description TODO
 * @Date 2022/9/21 19:48
 * @Created by orcakill
 */
public enum ActivityEnums {
	
	//返回
	activity_202220921 ("scenario/活动/20220921千年之守") ;
	
	ActivityEnums(String value) {
		this.value = value;
	}
	
	private final String value;
	
	public String getValue() {
		return value;
	}
}
