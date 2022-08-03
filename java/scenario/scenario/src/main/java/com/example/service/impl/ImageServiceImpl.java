package com.example.service.impl;

import java.awt.*;
import java.io.File;

import com.example.util.ImagesRecognition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageServiceImpl.java
 * @Description TODO
 * @createTime 2022年08月03日 14:22:00
 */
public class ImageServiceImpl {
	public  static  final Logger logger = LogManager.getLogger (ImageServiceImpl.class);
	
	public static void imagesClickBack (String folder, int re_num, int i1, int i2, boolean b) throws
	                                                                                          InterruptedException,
	                                                                                          AWTException {
		File file = new File (
				System.getProperty ("user.dir") + "/src/main/resources/image/" + folder);
		if (file.exists ()) {
			for (int i = 0; i <60; i++) {
				Thread.sleep (5000);
				if (ImagesRecognition.imagesRecognitionIsEmpty (folder)) {
					logger.info ("图片匹配成功");
					Thread.sleep (2000);
					ImagesRecognition.imagesRecognition (folder);
					Thread.sleep (2000);
					logger.info ("操作成功");
					break;
				}
				else {
					logger.error ("在每5秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==60-1){
					logger.info (folder + "路径下，图片未找到");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
		
	}
}
