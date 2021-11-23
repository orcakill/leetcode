package other.scenario.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.service.ImageService;
import other.scenario.util.ImageRecognition;
import other.scenario.util.ImagesBackRec;
import other.scenario.util.MouseClick;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName AutoLogin.java
 * @Description TODO
 * @createTime 2021年11月23日 11:31:00
 */
public class AutoLogin {
	private static final Logger logger = LogManager.getLogger (AutoLogin.class);
	
	public static   void  spirit() throws Exception {
		//后台登录
//      单击阴阳师图标
		String file1 = "scenario/阴阳师图标";
		logger.info ("单击阴阳师图标，进入登录页面");
		ImageService.imagesClickBack(file1);
        //进入游戏
		logger.info ("查找适龄提示的坐标");
		String file2= "scenario/适龄提示";
		PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (file2);
		if (pictureIdentifyWorkPO1.getX ()==null){
			logger.info ("没找到适龄提示的坐标");
		}
//		反馈的坐标
		logger.info ("查找反馈的坐标");
		String file3= "scenario/反馈";
		PictureIdentifyWorkPO pictureIdentifyWorkPO2 = ImagesBackRec.imagesRecognitionMouse (file3);
		if (pictureIdentifyWorkPO2 .getX ()==null){
			logger.info ("没找到反馈的坐标");
		}
		int y=pictureIdentifyWorkPO1.getY ();
		int x= pictureIdentifyWorkPO1.getX () +
		       (int) ((pictureIdentifyWorkPO2.getX () - pictureIdentifyWorkPO1.getX ()) * 0.5);
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList=new ArrayList<> ();
		PictureIdentifyWorkPO pictureIdentifyWorkPO3=new PictureIdentifyWorkPO ();
		pictureIdentifyWorkPO3.setX (x);
		pictureIdentifyWorkPO3.setY (y);
		pictureIdentifyWorkPOList.add (pictureIdentifyWorkPO3);
//      鼠标点击
		MouseClick.mouseClickBack (pictureIdentifyWorkPOList);
		logger.info ("进入游戏");
        //进入探索
		
	    //进入御灵
	    //进入御灵挑战
	    //选择御灵三层
        //开始挑战
	}
}
