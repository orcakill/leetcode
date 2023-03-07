package com.example.demo.model.param;

import lombok.Data;

/**
 * @Classname projectParam
 * @Description 项目参数 ，controller层传入
 * @Date 2023/3/8 1:39
 * @Created by orcakill
 */
@Data
public class projectParam {
	private  String projectName;
	private  Integer projectNum;
	private  Integer projectStartTime;
	private  Integer projectEndTime;
}
