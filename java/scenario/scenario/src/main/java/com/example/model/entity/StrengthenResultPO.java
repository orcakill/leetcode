package com.example.model.entity;

import lombok.Data;

/**
 * @Classname StrengthenResultPO
 * @Description TODO
 * @Date 2023/1/6 21:36
 * @Created by orcakill
 */
@Data
public class StrengthenResultPO {
	
	private String strengtheningAttribute; //强化属性
	private String strengtheningAddress; //强化属性对应的路径
	private int strengthenAttributeFeaturePoints; /*强化属性特征点*/
	
	public StrengthenResultPO (String strengtheningAttribute, String strengtheningAddress, int strengthenAttributeFeaturePoints) {
		this.strengtheningAttribute = strengtheningAttribute;
		this.strengtheningAddress = strengtheningAddress;
		this.strengthenAttributeFeaturePoints = strengthenAttributeFeaturePoints;
	}
}
