package com.example.model.enums;

/**
 * @Classname FightResultEnums
 * @Description 战斗结果
 * @Date 2023/1/8 22:58
 * @Created by orcakill
 */
public enum FightResultEnums {
	result_TS ("探索"),
	;
	
	private final String value;
	
	FightResultEnums (String value) {
		this.value = value;
	}
	
	public String getValue () {
		return value;
	}
}
