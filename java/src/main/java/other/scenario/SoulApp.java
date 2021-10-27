package other.scenario;

import other.scenario.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.service.LoginService;
import other.scenario.util.RandomUtil;
import other.scenario.util.StartUpExeUtils;

import java.awt.*;

import static other.scenario.util.RandomUtil.getRandom;

/**
 * @Classname TempApp
 * @Description 临时刷御魂（随机时间）
 * @Date 2021/10/19 22:25
 * @Created by orcakill
 */
public class SoulApp {
	
	//	记录日志
	public static final Logger logger = LogManager.getLogger (SoulApp.class);

	public static void main (String[] args) throws Exception {
//	     御魂120次
//		 soul (120);
		soulBack (120);
	}
	
	public static void soulBack(Integer num) throws Exception {
		Thread.sleep (3000);
		logger.info ("开始");
//		开始挑战,处理剩余次数的御魂
		for (int i = 0; num > 0; i++) {
			String file1 = "scenario/temp/御魂/挑战";
			logger.info ("准备开始御魂挑战");
			ImageService.imagesClickBack (file1);
			logger.info ("第" + (i + 1) +"次挑战中，等待挑战完成");
			Thread.sleep (getRandom(40, 50) * 1000L);
			
			String file2 = "scenario/temp/御魂/角色头像";
			logger.info ("准备点击角色头像");
			ImageService.imagesClickBackNumber (file2,30);
			logger.info ("点击角色头像完成");
			
			Thread.sleep (getRandom (2,4) * 1000L);
			String file3 = "scenario/temp/御魂/退出挑战";
			logger.info ("准备退出挑战");
			ImageService.imagesClickBack(file3);
			logger.info ("退出挑战完成");
			num--;
			Thread.sleep (getRandom (2,4) * 1000L);
			logger.info ("第" + (i + 1) + "次挑战完成，剩余" + (num) + "次");
			
		}
	}
	
	public static void soul(Integer num) throws Exception {
		Thread.sleep (3000);
		logger.info ("开始");
//		开始挑战,处理剩余次数的御魂
		for (int i = 0; num > 0; i++) {
			String file1 = "scenario/temp/御魂/挑战";
			logger.info ("准备开始御魂挑战");
			ImageService.imagesClick (file1);
			logger.info ("第" + (i + 1) +"挑战中，等待挑战完成");
			Thread.sleep (getRandom (25, 35) * 1000L);
			
			String file2 = "scenario/temp/御魂/角色头像";
			logger.info ("准备点击角色头像");
			ImageService.imagesClickNumber (file2,2 );
			logger.info ("点击角色头像完成");
			
			Thread.sleep (getRandom (2,4) * 1000L);
			String file3 = "scenario/temp/御魂/退出挑战";
			logger.info ("准备退出挑战");
			ImageService.imagesClick (file3);
			logger.info ("退出挑战完成");
			num--;
			Thread.sleep (getRandom (2,4) * 1000L);
			logger.info ("第" + (i + 1) + "次挑战完成，剩余" + (num) + "次");
			
		}
	}
}
