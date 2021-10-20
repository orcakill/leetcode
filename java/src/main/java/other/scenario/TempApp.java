package other.scenario;

import other.scenario.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.util.RandomUtil;

import java.awt.*;

import static other.scenario.util.RandomUtil.getRandom;

/**
 * @Classname TempApp
 * @Description 临时刷御魂（随机时间）
 * @Date 2021/10/19 22:25
 * @Created by orcakill
 */
public class TempApp {
	
	//	记录日志
	public static final Logger logger = LogManager.getLogger (ScenarioApp.class);

	public static void main (String[] args) throws Exception {
//	     御魂120次
		 soul (30);
	}
	
	public static void soul(Integer num) throws InterruptedException, AWTException {
		Thread.sleep (3000);
		logger.info ("开始");
//		开始挑战,处理剩余次数的御魂
		for (int i = 0; num > 0; i++) {
			String file1 = "scenario/temp/御魂/挑战";
			logger.info ("准备开始御魂挑战");
			ImageService.imagesClick (file1);
			logger.info ("等待挑战完成");
			Thread.sleep (getRandom (25, 35) * 1000L);
			
			String file2 = "scenario/temp/御魂/角色头像";
			logger.info ("准备点击角色头像");
			ImageService.imagesClick (file2);
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
