package com.example.demo.model.param;

import lombok.Data;

import java.util.List;

/**
 * @Classname MultipleImageParam
 * @Description 用于多个图片以不同算法识别
 * @Date 2023/2/19 1:44
 * @Created by orcakill
 */
@Data
public class MultipleImagesParam {
	//文件夹 简称
	private String process="夜神模拟器";
	// 识别次数
	private Integer re_num=20;
	// 识别开始间隔  1s
	private Integer start_time=1;
	// 识别结束间隔  2s
	private Integer end_time=2;
	//  是否显示不识别的日志
	private Boolean boole=true;
	//  是否点击图片
	private boolean isClick=true;
	//图片组及算法
	List<MultipleImageParam> multipleImageParamList;
	
	public MultipleImagesParam () {
	
	}
}
