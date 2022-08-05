package com.example.controller;

import com.example.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.example.controller.LoginController.loginExplore;
import static java.lang.Thread.sleep;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName FightAutoController.java
 * @Description TODO
 * @createTime 2022年08月05日 09:49:00
 */
public class FightAutoController {
	public  static  final Logger logger = LogManager.getLogger ("FightAutoController");
	
	//阴阳寮战斗
	public  static void fightHome() throws InterruptedException, AWTException {
		//变量赋值
		String file = "scenario/结界突破/结界突破";
		String file1 = "scenario/结界突破/阴阳寮";
		String file2 = "scenario/结界突破/挑战次数";
		String file3 = "scenario/结界突破/寮结界";
		String file4 = "scenario/结界突破/进攻";
		String file5 = "scenario/御魂/角色头像";
		String file6 = "scenario/御魂/退出挑战";
		String file7 = "scenario/结界突破/失败";
		String file8 = "scenario/结界突破/退出进攻";
		String file41 = "scenario/返回";
		String file9 = "scenario/首页";
		int num = 0;
		int num1 = 0;
		int num2 = 0;
		boolean b;
		boolean b1=true;
		Map<String,String> map=new HashMap<> ();
		String mapString;
		
		//数据预处理
		
		map.put ("角色头像",file5);
		map.put ("退出挑战",file6);
		map.put ("失败",file7);
		
		//流程开始
		logger.info ("准备进入探索");
		loginExplore ();
		logger.info ("准备进入结界突破");
		ImageService.imagesClickBack (file);
		sleep (2000);
		logger.info ("进入结界突破，准备点击阴阳寮");
		ImageService.imagesClickBack (file1);
		sleep (2000);
		logger.info ("进入寮突破，判断当前有无挑战次数");
		//退出到探索

		while (!ImageService.imagesClickBackIsEmpty (file2, 5) && ImageService.imagesClickBackIsEmpty (file3, 5)) {
			logger.info ("存在可攻打结界，且存在挑战次数");
			logger.info ("准备选择结界");
			ImageService.imagesClickBack (file3);
			logger.info ("选择结界成功，准备进攻");
			ImageService.imagesClickBack (file4);
			logger.info ("开始进攻");
			b = ImageService.imagesClickBackIsEmpty (file4, 3);
			if (b) {
				ImageService.imagesClickBack (file8);
				logger.info ("结界已被攻破，退出进攻");
				ImageService.imagesClickBack (file41);
				logger.info ("退出到探索");
				logger.info ("准备重新进入结界突破");
				ImageService.imagesClickBack (file);
				logger.info ("进入结界突破，准备点击阴阳寮");
				ImageService.imagesClickBack (file1);
				logger.info ("重新判断是否有结界可以攻打");
				continue;
			}
			else {
				logger.info ("结界未被攻破");
			}
			logger.info ("准备点击角色头像、点击退出挑战、失败");
			mapString= ImageService.imagesClickBack (map);
			if(mapString.equals ("角色头像")){
				ImageService.imagesClickBack (file6);
			}
			if(mapString.equals ("失败")){
				b1=false;
			}
			logger.info ("退出挑战完成");
			if (b1) {
				num1++;
			}
			else {
				num2++;
			}
			num++;
			if (num == 8) {
				logger.info ("阴阳寮挑战到达8次");
				break;
			}
			logger.info ("阴阳寮挑战第" + num + "次,成功" + num1 + "次，失败" + num2 + "次");
		}
		logger.info ("无挑战次数或无可攻打结界");
		ImageService.imagesClickBack (file41);
		logger.info ("退出到探索");
		ImageService.imagesClickBack (file41);
		logger.info ("退出到首页");
	
	}
}
