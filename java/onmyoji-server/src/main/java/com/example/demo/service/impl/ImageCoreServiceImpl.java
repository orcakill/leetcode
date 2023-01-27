package com.example.demo.service.impl;

import com.example.demo.utils.ImagesBackRec;
import com.example.demo.utils.ImagesOpenCV;
import com.example.demo.utils.ImagesOpenCVSIFT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;

import static com.example.demo.utils.RandomUtil.getRandom;

/**
 * @Classname ImageCoreServiceImpl
 * @Description 图像识别核心实现类
 * @Date 2023/1/26 22:33
 * @Created by orcakill
 */
public class ImageCoreServiceImpl {
	public static final Logger logger = LogManager.getLogger ("ImageCoreServiceImpl");
	
	/***
	 * @description: 识别并点击成功或识别成功返回true、识别失败返回false
	 * @param identificationAlgorithmType  图像识别算法类型（目前3种）
	 * @param folder  文件夹
	 * @param process 进程名
	 * @param re_num  识别次数
	 * @param i1      识别开始间隔 如 1
	 * @param i2      识别结束间隔 如 2
	 * @param b       是否显示日志 如 true 或false
	 * @param isClick       是否点击如 true 或false
	 * @param coefficient      相似系数  （1，2 算法专用）
	 * @param characteristicPoint 特征点 （2 算法专用）
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/1/26 22:26
	 */
	public static boolean imagesBackClick (String folder, int identificationAlgorithmType, String process, int re_num,
	                                       int i1, int i2, boolean b, boolean isClick, Double coefficient,
	                                       int characteristicPoint)
			throws InterruptedException, AWTException, IOException {
		int num_time;
		boolean result = false;
		for (int i = 0; i < re_num; i++) {
			num_time = getRandom (i1, i2);
			Thread.sleep (num_time * 1000L);
			if (identificationAlgorithmType == 0) {
				result = ImagesBackRec.imagesRecognition (folder, process, isClick);
			}
			if (identificationAlgorithmType == 1) {
				result = ImagesOpenCV.imagesRecognitionOpenCv (folder, process, isClick, coefficient);
			}
			if (identificationAlgorithmType == 2) {
				result =
						ImagesOpenCVSIFT.imagesRecognition (folder, process, isClick, coefficient,
						                                    characteristicPoint);
			}
			if (result) {
				logger.info ("图片识别成功");
				return true;
			}
			else {
				if (b) {
					logger.info ("在{}秒的检测中，第{}次检查未发现{}的图片", num_time, (i + 1), folder);
				}
			}
		}
		return false;
	}
	
}

