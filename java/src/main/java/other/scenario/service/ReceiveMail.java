package other.scenario.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.util.ImageRecognition;
import other.scenario.util.ImagesRecognition;
import other.scenario.util.MouseClick;

import java.awt.*;
import java.io.File;

public class ReceiveMail {
	private static final Logger logger = LogManager.getLogger (ReceiveMail.class);
	
	//邮件领取
	public  static  void receiveMail() throws InterruptedException, AWTException {
	  //判断当前是否为首页，通过判断头像确定
       if(IndexEmpty.indexEmpty ()){
		   //点击邮件按钮
	       File file1 = new File ("java/src/main/resources/image/scenario/领取邮件.png");
	       logger.info ("点击邮件");
	       ImageService.imageClick (file1);
	       logger.info ("进入邮箱");
	       //判断有无全部领取，无则跳过，有则点击，并更新任务记录
	       File file2 = new File ("java/src/main/resources/image/scenario/全部领取.png");
	       boolean b= ImageRecognition.imageRecognitionIsEmpty (file2);
		   if(b){
			   logger.info ("有邮件，点击全部领取");
			   //判断有无全部领取，无则跳过，有则点击，并更新任务记录
			   File file3 = new File ("java/src/main/resources/image/scenario/全部领取.png");
			   ImageService.imageClick (file3);
			   logger.info ("确认");
			   File file4 = new File ("java/src/main/resources/image/scenario/邮件确认.png");
			   ImageService.imageClick (file4);
			   logger.info ("已领取全部邮件");
		   }
		   else{
			   logger.info ("没有邮件，不领取");
		   }
		   logger.info ("返回首页");
		   IndexEmpty.indexBack ();
		   
       }

	}
	
	//签到、领取每日勾玉、领取御魂加成
	public static  void  singIn() throws InterruptedException, AWTException {
		//判断当前是否为首页，通过判断头像确定
		if(IndexEmpty.indexEmpty ()) {
			//点击签到按钮
			logger.info("检查是否需要签到");
			File file1 = new File ("java/src/main/resources/image/scenario/签到.png");
			boolean b1= ImageRecognition.imageRecognitionIsEmpty (file1);
			if(b1){
				logger.info ("开始签到");
				ImageService.imageClick (file1);
				File file11 = new File ("java/src/main/resources/image/scenario/每日一签.png");
				ImageService.imageClick (file11);
				logger.info ("签到成功");
				//点击返回，返回首页
				IndexEmpty.indexBack ();
			}
			else{
				logger.info ("不需要签到");
			}
			Thread.sleep (5000);
			//点击勾玉按钮
			logger.info("检查是否需要领取勾玉");
			File file2 = new File ("java/src/main/resources/image/scenario/领取勾玉.png");
			boolean b2= ImageRecognition.imageRecognitionIsEmpty (file2);
			if(b2){
				logger.info ("开始领取勾玉");
				ImageService.imageClick (file2);
				logger.info ("领取成功");
				IndexEmpty.indexHeadBack ();
			}
			else{
				logger.info ("不需要领取");
			}
			Thread.sleep (5000);

			
			//点击御魂加成按钮
			logger.info("检查是否需要领取御魂加成");
			File file3 = new File ("java/src/main/resources/image/scenario/御魂加成.png");
			boolean b3= ImageRecognition.imageRecognitionIsEmpty (file3);
			if(b3){
				logger.info ("开始领取御魂加成");
				ImageService.imageClick (file3);
				File file31 = new File ("java/src/main/resources/image/scenario/确认领取御魂加成.png");
				ImageService.imageClick (file31);
				logger.info ("领取成功");
				//向下移动点击，返回首页
				MouseClick.mouseClickNow (0,20);
			}
			else{
				logger.info ("不需要领取御魂加成");
			}
			Thread.sleep (5000);
			IndexEmpty.indexEmpty();
		}
	}
	
	
	
	
}
