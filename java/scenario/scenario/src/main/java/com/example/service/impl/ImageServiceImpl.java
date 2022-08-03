package com.example.service.impl;

import java.awt.*;
import java.io.File;

import com.example.util.ImagesBackRec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.util.RandomUtil.getRandom;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageServiceImpl.java
 * @Description TODO
 * @createTime 2022年08月03日 14:22:00
 */
public class ImageServiceImpl {
	public  static  final Logger logger = LogManager.getLogger (ImageServiceImpl.class);
	
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
	                                       boolean isClick) throws
	                                                                                          InterruptedException,
	                                                                                          AWTException {
		File file = new File (
				System.getProperty ("user.dir") + "/src/main/resources/image/" + folder);
		int  num_time=0;
		if (file.exists ()) {
			for (int i = 0; i <re_num; i++) {
				num_time=getRandom (i1,i2);
				Thread.sleep ( num_time* 1000L);
				if (ImagesBackRec.imagesRecognition (folder,process,isClick)) {
					logger.info ("图片匹配成功,已点击");
					return  true;
				}
				else {
					logger.error ("在"+num_time+"5秒的检测中，第" + (i + 1) + "次检查未发现"+folder+"的图片");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
		return  false;
	}
}
