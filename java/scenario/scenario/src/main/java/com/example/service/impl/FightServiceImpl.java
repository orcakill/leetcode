package com.example.service.impl;

import com.example.model.entity.ScreenshotPointPO;
import com.example.model.map.ScreenshotPointMap;
import com.example.service.ImageService;
import com.example.util.ImageTesseract;
import com.example.util.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.*;

import static java.lang.Thread.sleep;

/**
 * @Classname FightServiceImpl
 * @Description TODO
 * @Date 2022/8/5 23:05
 * @Created by orcakill
 */
public class FightServiceImpl {
	public static final Logger logger = LogManager.getLogger ("FightServiceImpl");
	
	//退出挑战，结束战斗
	public static boolean fightEnd (Integer begin_num,Integer start_num,Integer end_num) throws InterruptedException, AWTException {
		String file1 = "scenario/御魂/角色头像";
		String file2 = "scenario/御魂/退出挑战";
		String file3 = "scenario/结界突破/失败";
		Map<String,String> map=new HashMap<> ();
		String mapString;
		//数据预处理
		map.put ("角色头像",file1);
		map.put ("退出挑战",file2);
		map.put ("失败",file3);
		logger.info ("等待"+begin_num+"秒");
		Thread.sleep (begin_num*1000);
		logger.info ("准备点击角色头像、点击退出挑战、失败、宠物奖励");
		mapString= ImageService.imagesClickBack (map,start_num,end_num);
		if(mapString.equals ("角色头像")){
			logger.info ("点击角色头像");
			ImageService.imagesClickBack (file2);
			logger.info ("退出挑战");
		}
		if(mapString.equals ("失败")){
			logger.info ("战斗失败");
			return  false;
		}
		logger.info ("退出挑战完成");
		return  true;
	}
	
	public static void soulBack (Integer begin_num,Integer num) throws InterruptedException, AWTException {
		sleep (3000);
		logger.info ("开始");
		String fileTZ = "scenario/御魂/挑战";
		String fileCWJL = "scenario/御魂/宠物奖励";
		String fileTCTZ = "scenario/御魂/退出挑战";
		boolean b1;
		//		开始挑战,处理剩余次数的御魂
		for (int i = 0; num > 0; i++) {
			long a = System.currentTimeMillis ();//获取当前系统时间(毫秒)
			logger.info ("准备开始挑战");
			sleep (1000);
			b1=ImageService.imagesClickBack (fileTZ, 5);
			while (!b1){
				logger.info ("判断是否有宠物奖励");
				ImageService.imagesClickBack (fileCWJL, 5);
				logger.info ("判断是否没退出挑战成功");
				ImageService.imagesClickBack (fileTCTZ, 5);
				b1=ImageService.imagesClickBackIsEmpty (fileTZ, 5);
				if(b1){
					logger.info ("再次点击挑战");
					ImageService.imagesClickBack (fileTZ, 5);
				}
			}
			logger.info ("第" + (i + 1) + "次挑战中，等待挑战完成");
			fightEnd (begin_num,1,2);
			num--;
			logger.info ("第" + (i + 1) + "次挑战完成，剩余" + (num) + "次");
			long b=(System.currentTimeMillis () - a) / 1000;
			logger.info ("该次挑战使用时间为" + b+ "秒");
		}
		b1=ImageService.imagesClickBackIsEmpty (fileTZ, 5);
		while (!b1){
			logger.info ("判断是否有宠物奖励");
			ImageService.imagesClickBack (fileCWJL, 5);
			logger.info ("判断是否没退出挑战成功");
			ImageService.imagesClickBack (fileTCTZ, 5);
			b1=ImageService.imagesClickBackIsEmpty (fileTZ, 5);
		}
		logger.info ("结束挑战");
	}
	
	public static void returnHome () throws InterruptedException, AWTException, IOException {
		String file1 = "scenario/首页/首页勾玉";
		String file2 = "scenario/返回";
		String file3 = "scenario/首页/庭院异常";
		logger.info ("初始化首页");
		boolean booleanSFSY = ImageService.imagesClickBackIsEmpty (file1, 1);
		boolean booleanSFFH;
		boolean booleanTYRQ;
		while (!booleanSFSY) {
			Thread.sleep (2000);
			logger.info ("不在首页，判断有无返回按钮");
			booleanSFFH = ImageService.imagesClickBackIsEmpty (file2, 2);
			if (booleanSFFH) {
				logger.info ("有返回按钮");
				ImageService.imagesClickBack (file2, 2);
				logger.info ("返回上一页");
			}
			//else {
			//	logger.info ("不在首页，判断有无庭院异常");
			//	booleanTYRQ = ImageService.imagesClickBackIsEmpty (file3, 2);
			//	if (booleanTYRQ) {
			//		logger.info ("有庭院异常");
			//		ImageService.imagesClickBack (file3, 2);
			//		logger.info ("去除异常");
			//	}
			//}
			Thread.sleep (2000);
			logger.info ("判断是否回到首页");
			booleanSFSY = ImageService.imagesClickBackIsEmpty (file1, 2);
		}
		logger.info ("返回到首页");
		
	}
	
	public static boolean fightEndPVP (Integer begin_num, Integer start_num, Integer end_num) throws InterruptedException, AWTException {
		String file1 = "scenario/斗技/头筹";
		String file2= "scenario/斗技/战斗胜利";
		String file3 = "scenario/斗技/战斗失败";
		Map<String,String> map=new HashMap<> ();
		String mapString;
		//数据预处理
		map.put ("拔得头筹",file1);
		map.put ("战斗胜利",file2);
		map.put ("战斗失败",file3);
		logger.info ("等待"+begin_num+"秒");
		Thread.sleep (begin_num*1000);
		logger.info ("准备点击拔的头筹、战斗胜利、失败");
		mapString= ImageService.imagesClickBack (map,start_num,end_num);
		if(mapString.equals ("拔得头筹")){
			logger.info ("战斗胜利");
			ImageService.imagesClickBack (file2);
			logger.info ("退出挑战");
		}
		if(mapString.equals ("战斗失败")){
			logger.info ("战斗失败");
			return  false;
		}
		logger.info ("斗技完成");
		return  true;
	}
	
	public static boolean tryAgain (String file1, String file2) throws InterruptedException, AWTException {
		ImageService.imagesClickBack (file1);
		Thread.sleep (2000);
		logger.info ("判断"+file2+"是否存在");
		boolean b1;
		boolean b2=ImageService.imagesClickBackIsEmpty (file2,5);
		while (b2){
			logger.info (file2+"不存在,判断"+file1+"是否存在");
			b1=ImageService.imagesClickBackIsEmpty (file1,5);
			if (b1) {
				logger.info (file1 + "存在，重新点击");
				ImageService.imagesClickBack (file1);
			}
			Thread.sleep (1000);
			logger.info ("再次判断" + file2 + "是否存在");
			b2 = ImageService.imagesClickBackIsEmpty (file2, 5);
		}
		return true;
	}
	
	public static String soulLevelEnhancementRecognition () {
		//模拟器截屏处理
		BufferedImage Window = Screenshot.screenshotBack ("夜神模拟器");
		//截图 1 2 3 的裁剪信息
		ScreenshotPointPO screenshotPointPO1 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第一部分");
		ScreenshotPointPO screenshotPointPO2 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第二部分");
		ScreenshotPointPO screenshotPointPO3 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第三部分");
		//截图 1 2 3 裁剪
		BufferedImage bufferedImage1 =
				Window.getSubimage (screenshotPointPO1.getX (), screenshotPointPO1.getY (), screenshotPointPO1.getW (),
				                    screenshotPointPO1.getH ());
		BufferedImage bufferedImage2 =
				Window.getSubimage (screenshotPointPO2.getX (), screenshotPointPO2.getY (), screenshotPointPO2.getW (),
				                    screenshotPointPO2.getH ());
		BufferedImage bufferedImage3 =
				Window.getSubimage (screenshotPointPO3.getX (), screenshotPointPO3.getY (), screenshotPointPO3.getW (),
				                    screenshotPointPO3.getH ());
		//截图 1 2 3 图像文字识别
		String s1 = ImageTesseract.findOCR (bufferedImage1, true).replaceAll ("(?m)^\\s*$(\\n|\\r\\n)", "");
		String s2 = ImageTesseract.findOCR (bufferedImage2, false).replaceAll ("(?m)^\\s*$(\\n|\\r\\n)", "");
		String s3 = ImageTesseract.findOCR (bufferedImage3, false).replaceAll ("(?m)^\\s*$(\\n|\\r\\n)", "");
		//识别结果 1  2 3 string 转 list
		List<String> list1 = Arrays.asList (s1.split ("\n"));
		List<String> list2 = Arrays.asList (s2.split ("\n"));
		List<String> list3 = Arrays.asList (s3.split ("\n"));
		list1.removeAll (Collections.singleton (null));
		list2.removeAll (Collections.singleton (null));
		list3.removeAll (Collections.singleton (null));
		//识别结果 1  2 3 识别出现等级变化的御魂属性
		if (list1.size () == 5 && list1.size () == list2.size () || list2.size () == list3.size ()) {
			for (int i = 1; i < 5; i++) {
				if (!list2.get (i).equals (list3.get (i))) {
					return list1.get (i);
				}
			}
		}
		return null;
	}
}
