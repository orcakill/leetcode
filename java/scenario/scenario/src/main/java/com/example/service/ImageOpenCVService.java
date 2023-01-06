package com.example.service;

import com.example.model.entity.StrengthenResultPO;
import com.example.service.impl.ImageOpenCVServiceImpl;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * @Classname ImageOpenCVService
 * @Description TODO
 * @Date 2022/11/6 0:29
 * @Created by orcakill
 */
public interface ImageOpenCVService {
	
	/*多张图片后台识别,参数默认，识别次数1，识别起始间隔1，识别结束间隔2，是否返回日志信息是,点击*/
	static void imagesOpenCV (String folder) throws InterruptedException, AWTException, IOException {
		//默认算法  特诊匹配 SIFT
		// 1 模板匹配 相关系数平方差法
		// 2 特征匹配 SIFT
		int identificationAlgorithmType = 2;
		imagesOpenCV0 (folder, identificationAlgorithmType, "夜神模拟器", 60, 1, 2, true, true, null, 4);
	}
	
	static void imagesOpenCV (String folder, Integer re_num) throws InterruptedException, AWTException, IOException {
		int identificationAlgorithmType = 2;
		imagesOpenCV0 (folder, identificationAlgorithmType, "夜神模拟器", re_num, 1, 2, true, true, null, 4);
	}
	
	/*多张图片后台识别,参数默认，识别次数1，识别起始间隔1，识别结束间隔2，是否返回日志信息是,点击*/
	static boolean imagesOpenCVIsEmpty (String folder, boolean isClick) throws InterruptedException, AWTException, IOException {
		int identificationAlgorithmType = 2;
		return imagesOpenCV0 (folder, identificationAlgorithmType, "夜神模拟器", 60, 1, 2, true, isClick, null, 4);
	}
	
	static boolean imagesOpenCVIsEmpty (String folder, Integer re_num) throws InterruptedException, AWTException, IOException {
		int identificationAlgorithmType = 2;
		return imagesOpenCV0 (folder, identificationAlgorithmType, "夜神模拟器", re_num, 1, 2, true, false, null, 4);
	}
	
	/*多张图片后台识别,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息,点击*/
	static void imagesOpenCV (String folder, Double coefficient) throws InterruptedException, AWTException, IOException {
		int identificationAlgorithmType = 2;
		int characteristicPoint = 4;
		imagesOpenCV0 (folder, identificationAlgorithmType, "夜神模拟器", 60, 1, 2, true, true, coefficient, characteristicPoint);
	}
	
	static String imagesOpenCV (List<StrengthenResultPO> files, Double coefficient, Integer start_num, Integer end_num)
			throws InterruptedException, AWTException, IOException {
		int identificationAlgorithmType = 2;
		int characteristicPoint = 4;
		return imagesOpenCV0 (files, identificationAlgorithmType, "夜神模拟器", 60, start_num, end_num, true, true, coefficient);
	}
	
	static String imagesOpenCV (List<StrengthenResultPO> files, int identificationAlgorithmType, Double coefficient, Integer start_num,
	                            Integer end_num) throws InterruptedException, AWTException, IOException {
		return imagesOpenCV0 (files, identificationAlgorithmType, "夜神模拟器", 60, start_num, end_num, true, true, coefficient);
	}
	
	/*多张图片后台识别,手动设置参数*/
	static boolean imagesOpenCV0 (String folder, int identificationAlgorithmType, String process, Integer re_num, Integer start_time,
	                              Integer end_time, Boolean boole, boolean isClick, Double coefficient, int characteristicPoint)
			throws InterruptedException, AWTException, IOException {
		return ImageOpenCVServiceImpl.imagesClickBack (folder, identificationAlgorithmType, process, re_num, start_time, end_time, boole,
		                                               isClick, coefficient, characteristicPoint);
	}
	
	static String imagesOpenCV0 (List<StrengthenResultPO> files, int identificationAlgorithmType, String process, Integer re_num,
	                             Integer start_time, Integer end_time, Boolean boole, boolean isClick, Double coefficient)
			throws InterruptedException, AWTException, IOException {
		return ImageOpenCVServiceImpl.imagesClickBack (files, identificationAlgorithmType, process, re_num, start_time, end_time, boole,
		                                               isClick, coefficient);
	}
}
