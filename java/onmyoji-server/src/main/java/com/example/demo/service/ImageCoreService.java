package com.example.demo.service;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.service.impl.ImageCoreServiceImpl;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * @Classname ImageCoreService
 * @Description 图片识别核心服务接口
 * @Date 2023/1/26 22:31
 * @Created by orcakill
 */
public interface ImageCoreService {
	//后台图像识别及点击，支持RGB、openCV模板匹配、openCV sift特征匹配
	/***
	 * @description: 识别并点击成功或识别成功返回true、识别失败返回false
	 * @param identificationAlgorithmType  图像识别算法类型（目前3种）
	 * @param folder 文件夹
	 * @param process 进程名
	 * @param re_num  识别次数
	 * @param start_time      识别开始间隔 如 1
	 * @param end_time       识别结束间隔 如 2
	 * @param boole       是否显示日志 如 true 或false
	 * @param isClick       是否点击如 true 或false
	 * @param coefficient      相似系数  （1，2 算法专用）
	 * @param characteristicPoint 特征点 （2 算法专用）
	 * @return: boolean
	 * {@code @author:} orcakill
	 * @date: 2023/1/26 22:26
	 */
	static boolean imagesBackClick (String folder, int identificationAlgorithmType, String process, Integer re_num,
	                                Integer start_time,
	                                Integer end_time, Boolean boole, boolean isClick, Double coefficient, int characteristicPoint)
			throws AWTException, IOException, InterruptedException {
		return ImageCoreServiceImpl.imagesBackClick (folder, identificationAlgorithmType, process, re_num, start_time,
		                                             end_time, boole,
		                                             isClick, coefficient, characteristicPoint);
	}
	
	//后台图像识别及点击，支持RGB、openCV模板匹配、openCV sift特征匹配
	/***
	 * @description: 识别并点击成功或识别成功返回true、识别失败返回false
	 * @param identificationAlgorithmType  图像识别算法类型（目前3种）
	 * @param folderList 文件夹
	 * @param process 进程名
	 * @param re_num  识别次数
	 * @param start_time      识别开始间隔 如 1
	 * @param end_time       识别结束间隔 如 2
	 * @param boole       是否显示日志 如 true 或false
	 * @param isClick       是否点击如 true 或false
	 * @param coefficient      相似系数  （1，2 算法专用）
	 * @param characteristicPoint 特征点 （2 算法专用）
	 * @return: boolean
	 * {@code @author:} orcakill
	 * @date: 2023/1/26 22:26
	 */
	static String imagesBackClickList (List<String> folderList, int identificationAlgorithmType, String process,
	                                Integer re_num,
	                                Integer start_time,
	                                Integer end_time, Boolean boole, boolean isClick, Double coefficient, int characteristicPoint)
			throws AWTException, IOException, InterruptedException {
		return ImageCoreServiceImpl.imagesBackClick (folderList, identificationAlgorithmType, process, re_num, start_time,
		                                             end_time, boole,
		                                             isClick, coefficient, characteristicPoint);
	}
}
