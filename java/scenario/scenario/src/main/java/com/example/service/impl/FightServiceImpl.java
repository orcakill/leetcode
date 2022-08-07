package com.example.service.impl;

import com.example.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
		String file1 = "scenario/御魂/挑战";
		String file2 = "scenario/御魂/宠物奖励";
		boolean b1;
		//		开始挑战,处理剩余次数的御魂
		for (int i = 0; num > 0; i++) {
			long a = System.currentTimeMillis ();//获取当前系统时间(毫秒)
			logger.info ("准备开始挑战");
			sleep (1000);
			b1=ImageService.imagesClickBack (file1, 5);
			while (!b1){
				ImageService.imagesClickBack (file2, 5);
				b1=ImageService.imagesClickBack (file1, 5);
			}
			logger.info ("第" + (i + 1) + "次挑战中，等待挑战完成");
			fightEnd (begin_num,1,2);
			num--;
			logger.info ("第" + (i + 1) + "次挑战完成，剩余" + (num) + "次");
			logger.info ("该次挑战使用时间为" + (System.currentTimeMillis () - a) / 1000 + "秒");
		}
	}
	
	public static void returnHome () throws InterruptedException, AWTException {
		String file1 = "scenario/返回";
		String file2 = "scenario/首页/首页勾玉";
		logger.info ("判断有无返回");
		boolean b1=ImageService.imagesClickBackIsEmpty (file1,3);
		boolean b2=false;
		while (b1&&!b2){
			logger.info ("有返回，无首页勾玉，开始返回首页");
			b1=ImageService.imagesClickBack (file1,5);
			b2=ImageService.imagesClickBackIsEmpty (file2,5);
		}
		logger.info ("进入首页");
		
	}
}
