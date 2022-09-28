package com.example.controller;

import com.example.service.FightService;
import com.example.service.ImageService;
import com.example.util.MouseClick;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @Classname ActivityController
 * @Description TODO
 * @Date 2022/9/21 19:30
 * @Created by orcakill
 */
public class ActivityController {
	public static final Logger logger = LogManager.getLogger ("ActivityController ");
	

	
	public static void ActivitySelect (String str,Integer num) throws InterruptedException, AWTException {
	   if(str.equals ("20220921")){
		   Activity20220921(num);
	   }
	
	}
	
	public static void Activity20220921 (Integer num) throws InterruptedException, AWTException {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		String file_ZD="scenario/活动/20220921/战斗";
		String file_HDJL="scenario/活动/20220921/获得奖励";
		String file_SL="scenario/活动/20220921/胜利";
		String file_QR="scenario/活动/20220921/确认";
		String file_LSQY="scenario/活动/20220921/雷神契印";
		boolean boolean_file_ZD;
		boolean boolean_file_HDJL;
		boolean boolean_file_SL;
		boolean boolean_file_QR;
		boolean boolean_file_LSQY;
		if(windows_width==1920&&windows_height==1080){
			//   进入千年之守
			MouseClick.mouseClickBack (722,192,"夜神模拟器");
			Thread.sleep (4000);
		    //	 进入星神游
			MouseClick.mouseClickBack (585,636,"夜神模拟器");
			Thread.sleep (2000);
		    //	 进入风暴试炼
			MouseClick.mouseClickBack (1105,538,"夜神模拟器");
			Thread.sleep (2000);
		    //   进入风暴试炼战斗准备
			MouseClick.mouseClickBack (1699,803,"夜神模拟器");
			Thread.sleep (5000);
			//   进入风暴试炼战斗再准备
			MouseClick.mouseClickBack (1503,736,"夜神模拟器");
			Thread.sleep (2000);
			boolean_file_LSQY=ImageService.imagesClickBackIsEmpty (file_LSQY,5);
			if(boolean_file_LSQY){
				MouseClick.mouseClickBack (1851,780,"夜神模拟器");
			}
			Thread.sleep (2000);
			//   战斗循环
            for(int i=1;i<=num;i++){
	            long a = System.currentTimeMillis ();//获取当前系统时间(毫秒)
				boolean_file_ZD=ImageService.imagesClickBackIsEmpty (file_ZD,5);
				if(boolean_file_ZD){
					ImageService.imagesClickBack (file_ZD);
				}
	            Thread.sleep (13*1000);
	            boolean_file_HDJL=ImageService.imagesClickBackIsEmpty (file_HDJL,20);
	            if(boolean_file_HDJL){
		            MouseClick.mouseClickBack (984,882,"夜神模拟器");
	            }
	            Thread.sleep (2000);
	            boolean_file_SL=ImageService.imagesClickBackIsEmpty  (file_SL,5);
	            if(boolean_file_SL){
		            ImageService.imagesClickBack (file_SL);
	            }
				logger.info ("战斗"+i+"次，剩余"+(num-i)+"次");
				long b=(System.currentTimeMillis () - a) / 1000;
	            logger.info ("该次挑战使用时间为" + b+ "秒");
            }
		//	开始退出
			MouseClick.mouseClickBack (89,106,"夜神模拟器");
			Thread.sleep (2000);
			boolean_file_QR=ImageService.imagesClickBackIsEmpty  (file_QR,5);
			if(boolean_file_QR){
				ImageService.imagesClickBack (file_QR);
			}
			MouseClick.mouseClickBack (51,82,"夜神模拟器");
			Thread.sleep (2000);
			MouseClick.mouseClickBack (51,82,"夜神模拟器");
			Thread.sleep (2000);
			MouseClick.mouseClickBack (51,82,"夜神模拟器");
			Thread.sleep (2000);
		}
		if(windows_width==2560&&windows_height==1600){
			//   进入千年之守
			MouseClick.mouseClickBack (964,326,"夜神模拟器");
			Thread.sleep (2000);
			//	 进入神武行
			MouseClick.mouseClickBack (1882,934,"夜神模拟器");
			Thread.sleep (2000);
			//	 进入风暴试炼
			MouseClick.mouseClickBack (1444,982,"夜神模拟器");
			Thread.sleep (2000);
			//   进入风暴试炼战斗准备
			MouseClick.mouseClickBack (2209,1286,"夜神模拟器");
			Thread.sleep (5000);
			//   进入风暴试炼战斗再准备
			MouseClick.mouseClickBack (2002,1073,"夜神模拟器");
			Thread.sleep (2000);
			boolean_file_LSQY=ImageService.imagesClickBackIsEmpty (file_LSQY,5);
			if(boolean_file_LSQY){
				MouseClick.mouseClickBack (2479,1129,"夜神模拟器");
			}
			Thread.sleep (2000);
			//   战斗循环
			for(int i=0;i<num;i++){
				long a = System.currentTimeMillis ();//获取当前系统时间(毫秒)
				boolean_file_ZD=ImageService.imagesClickBackIsEmpty (file_ZD,5);
				if(boolean_file_ZD){
					ImageService.imagesClickBack (file_ZD);
				}
				Thread.sleep (20*1000);
				boolean_file_HDJL=ImageService.imagesClickBackIsEmpty (file_HDJL,20);
				if(boolean_file_HDJL){
					MouseClick.mouseClickBack (1280,1500,"夜神模拟器");
				}
				Thread.sleep (2000);
				boolean_file_SL=ImageService.imagesClickBackIsEmpty  (file_SL,5);
				if(boolean_file_SL){
					ImageService.imagesClickBack (file_SL);
				}
				logger.info ("战斗"+i+"次，剩余"+(num-i)+"次");
				logger.info ("该次挑战使用时间为" + (System.currentTimeMillis () - a) / 1000 + "秒");
			}
			//	开始退出
			MouseClick.mouseClickBack (116,181,"夜神模拟器");
			Thread.sleep (2000);
			boolean_file_QR=ImageService.imagesClickBackIsEmpty  (file_QR,5);
			if(boolean_file_QR){
				ImageService.imagesClickBack (file_QR);
			}
			MouseClick.mouseClickBack (67,140,"夜神模拟器");
			Thread.sleep (2000);
			MouseClick.mouseClickBack (67,140,"夜神模拟器");
			Thread.sleep (2000);
			MouseClick.mouseClickBack (67,140,"夜神模拟器");
			Thread.sleep (2000);
		}
		
		FightService.returnHome ();
	}
}
