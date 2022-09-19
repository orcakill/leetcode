package com.example.model.enums;

/**
 * @Classname BackEnums
 * @Description TODO
 * @Date 2022/9/18 18:13
 * @Created by orcakill
 */
public enum BackEnums {
	
	//返回
	back("scenario/返回")
	;
	
	BackEnums(String value) {
		this.value = value;
	}
	
	private final String value;
	
	public String getValue() {
		return value;
	}
}
