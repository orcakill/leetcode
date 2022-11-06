package com.example.service.impl;

import com.example.util.ImagesOpenCV;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.example.util.RandomUtil.getRandom;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageServiceImpl.java
 * @Description TODO
 * @createTime 2022年08月03日 14:22:00
 */
public class ImageOpenCVServiceImpl {
	public  static  final Logger logger = LogManager.getLogger ("ImageOpenCVServiceImpl");
	
	/***
	 * @description: 识别并点击成功或识别成功返回true、识别失败返回false
	 * @param folder  文件夹
	 * @param process 进程名
	 * @param re_num  识别次数
	 * @param i1      识别开始间隔 如 1
	 * @param i2      识别结束间隔 如 2
	 * @param b       是否显示日志 如 true 或false
	 * @param isClick       是否点击如 true 或false
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2022/8/3 22:29
	 */
	public static boolean imagesClickBack (String folder, String process,int re_num, int i1, int i2, boolean b,
	                                       boolean isClick,Double coefficient) throws
	                                                                            InterruptedException,
	                                                                            AWTException, IOException {
		File file = new File (
				"D:/project/leetcode/java/scenario/scenario/src/main/resources/image/" + folder);
		if(!file.exists ()){
			file = new File (
					"D:/study/Project/leetcode/java/scenario/scenario/src/main/resources/image/" + folder);
		}
		int  num_time;
		if (file.exists ()) {
			for (int i = 0; i <re_num; i++) {
				num_time=getRandom (i1,i2);
				Thread.sleep ( num_time* 1000L);
				if (ImagesOpenCV.imagesRecognitionOpenCv (folder,process,isClick,coefficient)) {
					logger.info ("图片匹配成功,已点击");
					return  true;
				}
				else {
					if(b) {
						logger.info ("在" + num_time + "秒的检测中，第" + (i + 1) + "次检查未发现" + folder + "的图片");
					}
				}
			}
		}
		else {
			logger.info (folder+"图标路径不存在");
			System.exit (0);
		}
		return  false;
	}
	
	
}
