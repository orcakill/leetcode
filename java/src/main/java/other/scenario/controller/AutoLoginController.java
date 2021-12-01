package other.scenario.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.service.FightAutoService;
import other.scenario.service.ImageService;
import other.scenario.util.ImagesBackRec;
import other.scenario.util.MouseClick;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName AutoLogin.java
 * @Description TODO
 * @createTime 2021年11月23日 11:31:00
 */
public class AutoLoginController {
	private static final Logger logger = LogManager.getLogger (AutoLoginController.class);
	
	public static void login () throws Exception {
		String fileOne = "scenario/首页/探索妖字";
		if (ImageService.imagesClickBackIsEmpty (fileOne, 3)) {
			logger.info ("当前已是探索界面");
		}
		else {
			//后台登录
			//单击阴阳师图标
			String file1 = "scenario/登录/阴阳师图标";
			logger.info ("单击阴阳师图标，进入登录页面");
			ImageService.imagesClickBack (file1);
			//进入游戏
			logger.info ("查找适龄提示的坐标");
			String file2 = "scenario/登录/适龄提示";
			for (int i = 0; i < 30; i++) {
				if (ImagesBackRec.imagesRecognitionIsEmpty (file2)) {
					logger.info ("找到适龄提示的图片，确定进入登录界面");
					break;
				}
				else {
					logger.info ("没找到适龄提示的图片，尚未进入游戏");
				}
				Thread.sleep (2000);
			}
			PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (file2);
			if (pictureIdentifyWorkPO1.getX () == null) {
				logger.info ("没找到适龄提示的坐标");
			}
			//		反馈的坐标
			logger.info ("查找反馈的坐标");
			String file3 = "scenario/登录/反馈";
			PictureIdentifyWorkPO pictureIdentifyWorkPO2 = ImagesBackRec.imagesRecognitionMouse (file3);
			if (pictureIdentifyWorkPO2.getX () == null) {
				logger.info ("没找到反馈的坐标");
			}
			int y = pictureIdentifyWorkPO1.getY ();
			int x = pictureIdentifyWorkPO1.getX () +
			        (int) ((pictureIdentifyWorkPO2.getX () - pictureIdentifyWorkPO1.getX ()) * 0.5);
			List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList = new ArrayList<> ();
			PictureIdentifyWorkPO pictureIdentifyWorkPO3 = new PictureIdentifyWorkPO ();
			pictureIdentifyWorkPO3.setX (x);
			pictureIdentifyWorkPO3.setY (y);
			pictureIdentifyWorkPOList.add (pictureIdentifyWorkPO3);
			//      鼠标点击
			MouseClick.mouseClickBack (pictureIdentifyWorkPOList);
			logger.info ("进入游戏");
			//进入探索
			//进入首页底部功能菜单
			String file = "scenario/首页/底部菜单";
			logger.info ("准备点击底部菜单栏");
			ImageService.imagesClickBack (file);
			logger.info ("打开底部菜单栏成功");
			//进入探索
			String file4 = "scenario/首页/首页勾玉";
			PictureIdentifyWorkPO pictureIdentifyWorkPO4 = ImagesBackRec.imagesRecognitionMouse (file4);
			String file5 = "scenario/首页/首页体力";
			PictureIdentifyWorkPO pictureIdentifyWorkPO5 = ImagesBackRec.imagesRecognitionMouse (file5);
			if (pictureIdentifyWorkPO4 != null && pictureIdentifyWorkPO5 != null) {
				//进入探索，通过首页勾玉和首页体力图标判断探索的位置点击按比例计算的探索
				int x1 = pictureIdentifyWorkPO4.getX ();
				int y1 =
						pictureIdentifyWorkPO4.getY () + (pictureIdentifyWorkPO5.getX () - pictureIdentifyWorkPO4.getX ());
				List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList1 = new ArrayList<> ();
				PictureIdentifyWorkPO pictureIdentifyWorkPO6 = new PictureIdentifyWorkPO ();
				pictureIdentifyWorkPO6.setX (x1);
				pictureIdentifyWorkPO6.setY (y1);
				pictureIdentifyWorkPOList1.add (pictureIdentifyWorkPO6);
				logger.info ("准备点击探索");
				MouseClick.mouseClickBack (pictureIdentifyWorkPOList1);
				logger.info ("进入探索");
				Thread.sleep (3 * 1000);
			}
		}
	}
	
	public static void soulEleven (Integer num) throws InterruptedException, AWTException {
		for (int i = 0; i < num; i++) {
			//处理结界挑战劵
			FightAutoService.borderCheck ();
		}
		
	}
}
