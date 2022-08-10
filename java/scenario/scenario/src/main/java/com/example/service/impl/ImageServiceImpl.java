package com.example.service.impl;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.service.ImageService;
import com.example.util.ImagesBackRec;
import com.example.util.MouseClick;
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
	public  static  final Logger logger = LogManager.getLogger ("ImageServiceImpl");
	
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
		int  num_time;
		if (file.exists ()) {
			for (int i = 0; i <re_num; i++) {
				num_time=getRandom (i1,i2);
				Thread.sleep ( num_time* 1000L);
				if (ImagesBackRec.imagesRecognition (folder,process,isClick)) {
					logger.info ("图片匹配成功,已点击");
					return  true;
				}
				else {
					if(b) {
						logger.error ("在" + num_time + "秒的检测中，第" + (i + 1) + "次检查未发现" + folder + "的图片");
					}
				}
			}
		}
		else {
			logger.info (folder+"图标路径不存在");
		}
		return  false;
	}
	
	public static String imagesClickBack (Map<String,String> files, String process, int re_num, int i1, int i2, boolean b,
	                                      boolean isClick) throws InterruptedException, AWTException {
		boolean b1=true;
		boolean b2=true;
		int  num_time;
		for(Map.Entry folder:files.entrySet ()){
			File file = new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folder.getValue ());
			if(!file.exists ()){
				b1=false;
			}
			
		}
		if (b1) {
			for (int i = 0; i <re_num; i++) {
				num_time=getRandom (i1,i2);
				Thread.sleep ( num_time* 1000L);
				for(Map.Entry file:files.entrySet ()){
					b2=ImagesBackRec.imagesRecognition (file.getValue ().toString (),process,isClick);
					if(b2){
						logger.info ("图片匹配成功,已点击");
						return  file.getKey ().toString ();
					}
					else {
						if(b) {
							logger.error ("在"+num_time+"秒的检测中，第" + (i + 1) + "次检查未发现"+file+"的图片");
						}
					}

				}
				
			}
		}
		else {
			logger.info (files+"集合中图标路径不存在");
		}
		return  null;
	}
	
	public static boolean imagesClickBackCount (String file1, String file2, String name, double x, double y,
	                                            String process) throws AWTException, InterruptedException {
		//获取图片1的坐标
		boolean boole;
		boolean b1=false;
		while (!b1){
			logger.info ("等待2秒钟");
			Thread.sleep (2000);
			logger.info ("准备识别"+file1);
			b1=ImageService.imagesClickBackIsEmpty (file1,1);
		}
		PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (file1,process);
		int x1 = (int)(pictureIdentifyWorkPO1.getX ()*x);
		int y1 = (int)(pictureIdentifyWorkPO1.getY ()*y);
		logger.info (file1+"的坐标（"+pictureIdentifyWorkPO1.getX ()+","+pictureIdentifyWorkPO1.getY ());
		logger.info ("目标坐标（"+x1+","+y1+")");
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList1 = new ArrayList<> ();
		PictureIdentifyWorkPO pictureIdentifyWorkPO3 = new PictureIdentifyWorkPO ();
		pictureIdentifyWorkPO3.setX (x1);
		pictureIdentifyWorkPO3.setY (y1);
		pictureIdentifyWorkPOList1.add (pictureIdentifyWorkPO3);
		logger.info ("准备点击"+name);
		MouseClick.mouseClickBack (pictureIdentifyWorkPOList1,"夜神模拟器");
		logger.info ("进入"+name);
		Thread.sleep (2000);
		boole= ImageService.imagesClickBackIsEmpty (file2,2);
        if(boole){
			logger.info ("进入"+file2+"成功");
			return  true;
        }
		else {
	        logger.info ("进入"+file2+"失败");
			return  false;
        }
		
	}
	
	public static boolean imagesClickBackDrag (PictureIdentifyWorkPO pictureIdentifyWorkPO1, PictureIdentifyWorkPO pictureIdentifyWorkPO2,
	                                           String process) throws AWTException, InterruptedException {
		MouseClick.mouseClickBackDrag (pictureIdentifyWorkPO1,pictureIdentifyWorkPO2,process);
		return  false;
	}
}
