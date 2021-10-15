package other.scenario.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.service.ImageService;
import other.scenario.service.IndexService;

import java.awt.*;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName IndexEmptyImpl.java
 * @Description TODO
 * @createTime 2021年10月15日 11:19:00
 */
public class IndexServiceImpl {
	private static final Logger logger = LogManager.getLogger (IndexService.class);

	

	public static Boolean indexEmpty () throws InterruptedException, AWTException {
		String folderName="scenario/首页";
		logger.info ("判断是否为首页");
		for(int i=0;i<3;i++){
			Thread.sleep (5000);
			boolean b= ImageService.imagesClickIsEmpty (folderName);
			if(b){
				logger.info ("当前页是首页");
				return b;
			}
			else{
				logger.info ("当前页不是首页");
			}
		}
		return  false;
	}
	

	public static void indexBack () throws InterruptedException, AWTException {
		String folderName="scenario/返回首页";
		logger.info ("开始返回");
		ImageService.imagesClick (folderName);
		if(indexEmpty ()){
			logger.info ("返回成功");
		}
		else{
			logger.info ("返回首页不成功，继续返回");
			indexBack ();
		}
		logger.info ("已返回首页");
		
	}
	
	//点击头像返回
	public static void indexHeadBack () throws InterruptedException, AWTException {
		String folderName="scenario/头像";
		logger.info ("开始点击头像返回");
		ImageService.imagesClick (folderName);
		if(indexEmpty ()){
			logger.info ("通过点击头像返回成功");
		}
		else{
			logger.info ("通过点击头像，返回首页第不成功，继续返回");
			indexBack ();
		}
		logger.info ("返回首页");
		
	}
	
}
