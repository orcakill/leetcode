package other.scenario.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.util.ImagesRecognition;

import java.awt.*;
import java.util.Date;

public class IndexEmpty {
	private static final Logger logger = LogManager.getLogger (IndexEmpty.class);

	public  static  Boolean indexEmpty() throws InterruptedException, AWTException {
		String folderName="java/src/main/resources/image/scenario/首页";
		logger.info ("判断是否为首页");
		for(int i=0;i<3;i++){
			Thread.sleep (5000);
			boolean b= ImagesRecognition.imagesRecognitionIsEmpty (folderName);
			if(b){
				logger.info ("当前是首页");
				return b;
			}
			else{
				logger.info ("不是首页");
			}
		}
		logger.info (false);
		return  false;
	}
	
	public  static  void indexBack() throws InterruptedException, AWTException {
		String folderName="java/src/main/resources/image/scenario/返回首页";
		logger.info ("开始返回");
		ImageService.imagesClick (folderName);
		if(indexEmpty ()){
			logger.info ("返回成功");
		}
		else{
			logger.info ("返回首页不成功，继续返回");
			indexBack ();
		}
		logger.info ("进入邮箱");

	}
	//点击头像返回
	public  static  void indexHeadBack() throws InterruptedException, AWTException {
		String folderName="java/src/main/resources/image/scenario/首页";
		logger.info ("开始返回");
		ImageService.imagesClick (folderName);
		if(indexEmpty ()){
			logger.info ("返回成功");
		}
		else{
			logger.info ("返回首页第不成功，继续返回");
			indexBack ();
		}
		logger.info ("进入邮箱");
		
	}
}
