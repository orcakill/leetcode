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
	public static boolean fightEnd () throws InterruptedException, AWTException {
		String file1 = "scenario/御魂/角色头像";
		String file2 = "scenario/御魂/退出挑战";
		String file3 = "scenario/结界突破/失败";
		String file4 = "scenario/御魂/宠物奖励";
		Map<String,String> map=new HashMap<> ();
		String mapString;
		//数据预处理
		map.put ("角色头像",file1);
		map.put ("退出挑战",file2);
		map.put ("失败",file3);
		map.put ("宠物奖励",file3);
		
		logger.info ("准备点击角色头像、点击退出挑战、失败、宠物奖励");
		mapString= ImageService.imagesClickBack (map);
		if(mapString.equals ("角色头像")){
			logger.info ("点击角色头像");
			ImageService.imagesClickBack (file1);
			logger.info ("退出挑战");
		}
		if(mapString.equals ("失败")){
			logger.info ("战斗失败");
			return  false;
		}
		if(mapString.equals ("宠物奖励")){
			logger.info ("宠物奖励");
			ImageService.imagesClickBack (file1);
			logger.info ("退出挑战");
		}
		logger.info ("退出挑战完成");
		return  true;
	}
	
	public static void soulBack (Integer num) throws InterruptedException, AWTException {
		sleep (3000);
		logger.info ("开始");
		String file1 = "scenario/御魂/挑战";
		//		开始挑战,处理剩余次数的御魂
		for (int i = 0; num > 0; i++) {
			long a = System.currentTimeMillis ();//获取当前系统时间(毫秒)
			logger.info ("准备开始挑战");
			sleep (1000);
			ImageService.imagesClickBack (file1, 120);
			logger.info ("第" + (i + 1) + "次挑战中，等待挑战完成");
			fightEnd ();
			num--;
			logger.info ("第" + (i + 1) + "次挑战完成，剩余" + (num) + "次");
			logger.info ("该次挑战使用时间为" + (System.currentTimeMillis () - a) / 1000 + "秒");
		}
	}
}
