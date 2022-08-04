package com.example.controller;

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
	
	public  static  final Logger logger = LogManager.getLogger ("LoginController");
	
	
	//阴阳师游戏账号登录，进入游戏登录界面
	
	public  static void loginGame() throws InterruptedException, AWTException {
		String file1 ="scenario/首页/首页勾玉";
		String file2 = "scenario/登录/阴阳师图标";
		String file3 = "scenario/登录/适龄提示";
		String file4 = "scenario/登录/公告关闭";
	    //如皋已经是游戏首页，不需要登录
		boolean boole1= ImageService.imagesClickBackIsEmpty (file1,3);
		boolean boole2;
		if(!boole1){
			//单击阴阳师图标
			logger.info ("单击阴阳师图标，进入登录页面");
			ImageService.imagesClickBack (file2);
			while (!ImageService.imagesClickBackIsEmpty (file3)){
				boole2=ImageService.imagesClickBackIsEmpty (file4,1);
				if(boole2){
					logger.info ("有公告，关闭公告");
					ImageService.imagesClickBack (file4);
				}
				Thread.sleep (5000);
			}
			logger.info ("已进入游戏登录界面");
			loginHome(0);
		}
		else{
			logger.info ("当前已是首页");
		}
	}
	
	//阴阳师游戏账号登录，进入游戏首页
	public  static void loginHome(Integer num) throws InterruptedException, AWTException {
		String file1 = "scenario/登录/适龄提示";
		String file2 = "scenario/首页/首页勾玉";
		//默认0 直接登录，不进行账号、大区切换
		if(num==0){
			boolean boole=ImageService.imagesClickBackCount (file1,file2,"首页",5,1);
			if(boole){
				logger.info ("进入游戏首页");
			}
		}

	}
}

