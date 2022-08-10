package com.example.controller;

import com.example.service.FightService;
import com.example.service.ImageService;
import com.example.util.ImagesBackRec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description TODO
 * @createTime 2022年08月04日 11:25:00
 */
public class LoginController {
	
	public static final Logger logger = LogManager.getLogger ("LoginController");
	
	//阴阳师游戏账号登录，进入游戏登录界面
	
	public static void loginGame () throws InterruptedException, AWTException {
		String file1 = "scenario/首页/首页勾玉";
		String file2 = "scenario/登录/阴阳师图标";
		String file3 = "scenario/登录/适龄提示";
		String file4 = "scenario/登录/公告关闭";
		String file5 = "scenario/首页/底部菜单";
		String file6 = "scenario/首页/底部菜单打开";
		String file7 = "scenario/返回";
		//如皋已经是游戏首页，不需要登录
		logger.info ("判断是否是游戏首页");
		boolean b1 = ImageService.imagesClickBackIsEmpty (file1, 3);
		boolean b2;
		boolean b3 = false;
		boolean b4;
		if (!b1) {
			logger.info ("不是游戏首页");
			logger.info ("判断是否存在返回按钮");
			//单击阴阳师图标
			b4 = ImageService.imagesClickBackIsEmpty (file7, 3);
			if(b4){
				logger.info ("当前存在返回按钮，开始返回");
				FightService.returnHome ();
			}
			else {
				logger.info ("单击阴阳师图标，进入登录页面");
				ImageService.imagesClickBack (file2);
				while (!ImageService.imagesClickBackIsEmpty (file3)) {
					Thread.sleep (5000);
				}
				logger.info ("已进入游戏登录界面");
				Thread.sleep (3000);
				logger.info ("判断是否有公告");
				b2 = ImageService.imagesClickBackIsEmpty (file4, 1);
				if (b2) {
					logger.info ("有公告，关闭公告");
					ImageService.imagesClickBack (file4);
				}
				loginHome (0);
				logger.info ("打开底部菜单");
				while (!b3) {
					ImageService.imagesClickBack (file5);
					b3 = ImageService.imagesClickBackIsEmpty (file6);
					Thread.sleep (3000);
				}
				logger.info ("进入首页");
			}
		}
		else {
			logger.info ("当前已是首页");
		}
		
	}
	
	//阴阳师游戏账号登录，进入游戏首页
	public static void loginHome (Integer num) throws InterruptedException, AWTException {
		String file1 = "scenario/登录/适龄提示";
		String file2 = "scenario/首页/底部菜单";
		String file3 = "scenario/登录/新服集结";
		boolean booleanXFJJ=false;/*是否存在新服集结*/
		boolean booleanHome=false;//进入首页
		//默认0 直接登录，不进行账号、大区切换
		if (num == 0) {
			logger.info ("判断是否有新服集结");
			booleanXFJJ= ImageService.imagesClickBack (file3,2);
			if(booleanXFJJ){
				logger.info ("有新服集结，适龄提示坐标有变化,改为新服集结的坐标");
				booleanHome = ImageService.imagesClickBackCount (file3, file2, "首页", 25, 1);
			}
			else {
				logger.info ("正常识别适龄提示");
				booleanHome = ImageService.imagesClickBackCount (file1, file2, "首页", 25, 1);
			}
			if (booleanHome) {
				logger.info ("进入游戏首页");
			}
		}
		
	}
	
	public static void loginExplore () throws InterruptedException, AWTException {
		String file1 = "scenario/首页/首页勾玉";
		String file2 = "scenario/御魂/御魂图标";
		//默认0 直接登录，不进行账号、大区切换
		boolean boole = ImageService.imagesClickBackCount (file1, file2, "探索", 1, 3.5);
		if (boole) {
			logger.info ("进入游戏探索界面");
		}
		
	}
	
	public static void loginTown () throws InterruptedException, AWTException {
		String file1 = "scenario/首页/首页勾玉";
		String file2 = "scenario/斗技/町中武馆";
		boolean boole = ImageService.imagesClickBackCount (file1, file2, "町中", 1.2, 5);
		if (boole) {
			logger.info ("进入町中");
		}
		
	}
}

