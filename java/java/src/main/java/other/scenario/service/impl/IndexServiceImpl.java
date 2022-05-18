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
	private static final Logger logger = LogManager.getLogger (IndexServiceImpl.class);
	
	public static Boolean indexEmpty () throws InterruptedException, AWTException {
		String folderName="scenario/首页";
		logger.info ("判断是否为首页");
		for(int i=0;i<1;i++){
			Thread.sleep (3000);
			boolean b= ImageService.imagesClickIsEmpty (folderName);
			if(b){
				logger.info ("当前页是首页");
				return true;
			}
			else{
				logger.info ("当前页不是首页");
			}
		}
		return  false;
	}
	

	public static void indexBack () throws InterruptedException, AWTException {
		String folderName="scenario/返回首页";
		logger.info ("准备点击返回按钮返回");
		ImageService.imagesClick (folderName);
		logger.info ("点击返回成功");
	}
	
	//点击头像返回
	public static void indexHeadBack () throws InterruptedException, AWTException {
		String folderName="scenario/头像";
		logger.info ("开始点击头像返回");
		ImageService.imagesClick (folderName);
		logger.info ("点击头像返回成功");
	}
}
