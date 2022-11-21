package com.example.controller;

import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.service.FightService;
import com.example.service.ImageService;
import com.example.util.ImagesBackRec;
import com.example.util.MouseClick;
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
		String file8 = "scenario/首页/庭院异常";
		String downloadIllustration="scenario/首页/下载插画";
		String donTPrompt="scenario/首页/不再提示";
		String download="scenario/首页/下载";
		//如皋已经是游戏首页，不需要登录
		logger.info ("判断是否是游戏首页");
		boolean b1 = ImageService.imagesClickBackIsEmpty (file1, 3);
		boolean b2;
		boolean b3 = false;//底部菜单未打开
		boolean b4;
		boolean b5;
		//判断是否有下载插画
		boolean boolean_XZCH;
		if (!b1) {
			logger.info ("不是游戏首页");
			logger.info ("判断是否存在返回按钮");
			b4 = ImageService.imagesClickBackIsEmpty (file7, 3);
			logger.info ("判断是否存在庭院异常");
			b5 = ImageService.imagesClickBackIsEmpty (file8, 3);
			if(b4||b5){
				logger.info ("当前存在返回按钮或者庭院异常时，初始化到首页");
				FightService.returnHome ();
			}
			else {
				//单击阴阳师图标
				logger.info ("单击阴阳师图标，进入登录页面");
				ImageService.imagesClickBack (file2);
				while (!ImageService.imagesClickBackIsEmpty (file3,10)) {
					Thread.sleep (10000);
					logger.info ("单击一下，防止有开场动画");
					MouseClick.mouseClickBack (500,500,"夜神模拟器");
					logger.info ("判断是否有公告");
					b2 = ImageService.imagesClickBackIsEmpty (file4, 1);
					if (b2) {
						logger.info ("有公告，关闭公告");
						ImageService.imagesClickBack (file4);
					}
					else{
						logger.info ("无公告");
					}
				}
				logger.info ("已进入游戏登录界面");
				Thread.sleep (3000);
				loginHome (0);
				while (!b3) {
					logger.info ("打开底部菜单");
					ImageService.imagesClickBack (file5,5);
					Thread.sleep (1000);
					logger.info ("点击返回按钮");
					ImageService.imagesClickBack (file7,5);
					Thread.sleep (1000);
					//判断是否有下载插画
					logger.info ("判断是否有下载插画");
					boolean_XZCH=ImageService.imagesClickBackIsEmpty (downloadIllustration,3);
					while (boolean_XZCH){
						logger.info ("有下载插画,30天不再提示");
						ImageService.imagesClickBack (donTPrompt,3);
						Thread.sleep (1000);
						logger.info ("确定下载");
						ImageService.imagesClickBack (download,3);
						Thread.sleep (1000);
						logger.info ("再次判断是否有下载插画");
						boolean_XZCH=ImageService.imagesClickBackIsEmpty (downloadIllustration,3);
						
					}
					logger.info ("判断底部菜单是否打开");
					b3 = ImageService.imagesClickBackIsEmpty (file6,5);
					Thread.sleep (5000);
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
		String fileBack = "scenario/返回";
		PictureIdentifyWorkPO pictureIdentifyWorkPO1;
		boolean booleanSLTS=false;/*是否存在适龄提示*/
		boolean booleanHome=false;//进入首页
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		//默认0 直接登录，不进行账号、大区切换
		if (num == 0) {
			while (!booleanSLTS){
				Thread.sleep (1000);
				logger.info ("确定适龄提示存在");
				booleanSLTS=ImageService.imagesClickBackIsEmpty (file1);
			}
			logger.info ("存在适龄提示");
			pictureIdentifyWorkPO1=ImagesBackRec.imagesRecognitionMouse (file1,"夜神模拟器");
			pictureIdentifyWorkPO1.setX (windows_width/2);
			MouseClick.mouseClickBack (pictureIdentifyWorkPO1,"夜神模拟器");
			while (!booleanHome){
				Thread.sleep (1000);
				logger.info ("未进入首页");
				logger.info ("点击返回按钮");
				ImageService.imagesClickBack (fileBack,5);
				booleanHome=ImageService.imagesClickBackIsEmpty (file2,3);
			}
			logger.info ("进入游戏首页");
		}
		
	}
	
	public static void loginExplore () throws InterruptedException, AWTException {
		String file1 = "scenario/首页/首页勾玉";
		String file2 = "scenario/御魂/御魂图标";
		double  x;
		double  y;
		//默认0 直接登录，不进行账号、大区切换
		boolean boole=false;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		if(windows_width==2560&&windows_height==1600){
			x=1;
			y=3.5;
			logger.info ("x和y系数（"+x+","+y+")");
			boole = ImageService.imagesClickBackCount (file1, file2, "探索", x, y);
		}
		if(windows_width==1920&&windows_height==1080){
			x=0.9;
			y=9;
			logger.info ("x和y系数（"+x+","+y+")");
			boole = ImageService.imagesClickBackCount (file1, file2, "探索", x, y);
		}
		if (boole) {
			logger.info ("进入游戏探索界面");
		}
		
	}
	
	public static void loginTown () throws InterruptedException, AWTException {
		String file1 = "scenario/首页/首页勾玉";
		String file2 = "scenario/斗技/町中武馆";
		double  x;
		double  y;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		boolean boole=false;
		if(windows_width==2560&&windows_height==1600){
			x=1.1;
			y=4.7;
			logger.info ("x和y系数（"+x+","+y+")");
			boole = ImageService.imagesClickBackCount (file1, file2, "町中", 1.1, 4.7);
		}
		if(windows_width==1920&&windows_height==1080){
			x=1.1;
			y=10;
			logger.info ("x和y系数（"+x+","+y+")");
			boole = ImageService.imagesClickBackCount (file1, file2, "町中", 1.1, 10);
		}
		if (boole) {
			logger.info ("进入町中");
		}
		
	}
}

