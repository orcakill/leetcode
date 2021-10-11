package other.scenario.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.dao.OnmyojiInfoMapper;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.map.ExeAddress;
import other.scenario.util.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
	private static final Logger logger = LogManager.getLogger (LoginService.class);
	
	private static double bl;
	
	static {
		try {
			bl = ComputerScaling.getScale ();
		} catch (AWTException e) {
			e.printStackTrace ();
		}
	}
	
	public static void loginService () throws Exception {
//	    启动程序
		StartUpExeUtils.startUpExe ("CMD /C " + ExeAddress.exeAddress (), "Nox.exe");

//      单击阴阳师图标
		File file1 = new File ("java/src/main/resources/image/scenario/阴阳师图标.png");
		logger.info ("单击阴阳师图标，进入登录页面");
		ImageService.imageClick (file1);
//      模拟器全屏
//		File file2 = new File ("java/src/main/resources/image/scenario/模拟器全屏.png");
//		logger.info ("单击窗口最大化");
//		ImageService.imageClick (file2);
//		logger.info ("窗口最大化最大化成功");
		
	}
	
	/***
	 * @description:登录账号对应的大区
	 * @param userName
	 * @return: void
	 * @author: orcakill
	 * @date: 2021/10/9 15:06
	 */
	public static void loginAreaService (String userName) throws Exception {

//	    点击切换
		String folderName="java/src/main/resources/image/scenario/切换";
		logger.info ("单击切换，进入大区选择");
		ImageService.imagesClick (folderName);
//		点击三角形，打开账号大区列表
		File file1 = new File ("java/src/main/resources/image/scenario/大区切换三角图标.png");
		logger.info ("单击大区切换三角，进入选择大区");
		ImageService.imageClick (file1);
		logger.info ("进入选择");
//      获取到当前任务用户所在大区
		String address = OnmyojiInfoMapper.findById (userName)
		                                  .getUserAddress ();
//		选择用户所在大区
		File file2=new File ("java/src/main/resources/image/scenario/"+address+".png");
		logger.info ("单击大区");
		ImageService.imageClick (file2);
//		登录,通过适龄提示的纵坐标，适龄提示的横坐标—+（反馈的横坐标-适龄提示横坐标）*0.5  通过和切换的横坐标确定比例系数
//		适龄提示的坐标
		logger.info ("查找适龄提示的坐标");
		File file3=new File ("java/src/main/resources/image/scenario/适龄提示.png");
		PictureIdentifyWorkPO pictureIdentifyWorkPO1= ImageRecognition.imageRecognitionMouse (file3);
//		反馈的坐标
		logger.info ("查找反馈的坐标");
		File file4=new File ("java/src/main/resources/image/scenario/反馈.png");
		PictureIdentifyWorkPO pictureIdentifyWorkPO2= ImageRecognition.imageRecognitionMouse (file4);
		logger.info ("查找切换的坐标");
		PictureIdentifyWorkPO pictureIdentifyWorkPOcs= ImagesRecognition.imagesRecognitionMouse (folderName);
		int y=pictureIdentifyWorkPO1.getY ();
		int x=pictureIdentifyWorkPO1.getX ()+
				(int) ((pictureIdentifyWorkPO2.getX () - pictureIdentifyWorkPO1.getX ()) * 0.5);
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList=new ArrayList<> ();
		PictureIdentifyWorkPO pictureIdentifyWorkPO3=new PictureIdentifyWorkPO ();
		pictureIdentifyWorkPO3.setX (x);
		pictureIdentifyWorkPO3.setY (y);
		pictureIdentifyWorkPOList.add (pictureIdentifyWorkPO3);
		//		鼠标点击
		MouseClick.mouseClicks (pictureIdentifyWorkPOList);
		logger.info ("进入游戏");


	}
	
}
