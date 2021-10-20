package other.scenario.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.dao.OnmyojiInfoMapper;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.map.ExeAddress;
import other.scenario.service.ImageService;
import other.scenario.service.IndexService;
import other.scenario.service.LoginService;
import other.scenario.util.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName LoginServiceImpl.java
 * @Description TODO
 * @createTime 2021年10月15日 11:26:00
 */
public class LoginServiceImpl{
	private static final Logger logger = LogManager.getLogger (LoginServiceImpl.class);
	
	
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
		String file1 = "scenario/阴阳师图标";
		logger.info ("单击阴阳师图标，进入登录页面");
		ImageService.imagesClick (file1);
//      模拟器全屏
//		File file2 = new File ("java/src/main/resources/image/scenario/模拟器全屏.png");
//		logger.info ("单击窗口最大化");
//		ImageService.imageClick (file2);
//		logger.info ("窗口最大化最大化成功");
	
	}

	public static void loginAreaService (String userName) throws Exception {
//	    切换
		String folderName1 = "scenario/切换";
		logger.info ("准备单击切换");
		ImageService.imagesClick (folderName1);
		logger.info ("进入账号列表");
//		点击三角形，打开账号大区列表
		File file1 = new File ("java/src/main/resources/image/scenario/大区切换三角图标.png");
		logger.info ("准备单击大区切换三角");
		ImageService.imageClick (file1);
		logger.info ("进入大区列表");
//      获取到当前任务用户所在大区
		String address = OnmyojiInfoMapper.findById (userName)
		                                  .getUserAddress ();
//		选择用户所在大区
		String folderName2= "scenario/" + address;
		logger.info ("准备单击大区");
		ImageService.imagesClick (folderName2);
//		登录,通过适龄提示的纵坐标，适龄提示的横坐标—+（反馈的横坐标-适龄提示横坐标）*0.5  通过和切换的横坐标确定比例系数
//		适龄提示的坐标
		Thread.sleep (2000);
		logger.info ("查找适龄提示的坐标");
		File file3 = new File ("java/src/main/resources/image/scenario/适龄提示.png");
		PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImageRecognition.imageRecognitionMouse (file3);
		if (pictureIdentifyWorkPO1.getX ()==null){
			logger.info ("没找到适龄提示的坐标");
		}
//		反馈的坐标
		logger.info ("查找反馈的坐标");
		File file4 = new File ("java/src/main/resources/image/scenario/反馈.png");
		PictureIdentifyWorkPO pictureIdentifyWorkPO2 = ImageRecognition.imageRecognitionMouse (file4);
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
		//		鼠标点击
		MouseClick.mouseClicks (pictureIdentifyWorkPOList);
		logger.info ("进入游戏");
		
		
	}
	
	public static void loginBackService () throws InterruptedException, AWTException {
//		判断当前是首页
		boolean b= IndexService.indexEmpty ();
		if(b){
//		   当前是首页，点击首页头像
			String folderName1="scenario/首页";
			logger.info ("开始单击首页头像");
			ImageService.imagesClick (folderName1);
			logger.info ("单击首页头像完成");
			
			File  file1=new File ("java/src/main/resources/image/scenario/用户中心.png");
			logger.info ("单击用户中心");
			ImageService.imageClick (file1);
			logger.info ("单击用户中心完成");
			
			File  file2=new File ("java/src/main/resources/image/scenario/切换账号.png");
			logger.info ("单击切换账号");
			ImageService.imageClick (file2);
			logger.info ("单击切换账号完成");
			
			File  file3=new File ("java/src/main/resources/image/scenario/登录.png");
			logger.info ("单击登录");
			ImageService.imageClick (file3);
			logger.info ("单击登录完成");
			
			String folderName2="scenario/切换";
//		   判断是否回到了切换页面
			for(int i=0;i<3;i++){
				Thread.sleep (5000);
				boolean b1= ImageService.imagesClickIsEmpty (folderName2);
				if(b1) logger.info ("成功返回切换页面");
			}
		}
	}
}
