package com.example.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageVisits.java
 * @Description 图片访问次数统计
 * @createTime 2023年01月13日 11:14:00
 */
@Data
public class ImageVisitsPO {
	//图片名称
	private  String ImageName;
	//图片路径
	private  String ImageAddress;
	//机器ID
	private  String MachineId;
	//访问时间
	private  Date visitTime;
	//识别结果
	private  int recognizeTrue;
}
