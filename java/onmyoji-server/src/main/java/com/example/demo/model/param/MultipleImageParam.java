package com.example.demo.model.param;

import lombok.Data;

/**
 * @Classname MultipleImageParam
 * @Description 用于多个图片以不同算法识别
 * @Date 2023/2/19 1:44
 * @Created by orcakill
 */
@Data
public class MultipleImageParam {
	//文件夹 简称
	private String folder;
	//算法参数
	ImageRecParam imageRecParam;
	
	public MultipleImageParam (String folder, ImageRecParam imageRecParam) {
		this.folder = folder;
		this.imageRecParam = imageRecParam;
	}
}
