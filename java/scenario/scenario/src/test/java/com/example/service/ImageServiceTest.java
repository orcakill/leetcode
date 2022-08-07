package com.example.service;

import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.util.ComputerScaling;
import com.example.util.ImagesBackRec;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import org.junit.Test;

/**
 * @Classname ImageServiceTest
 * @Description TODO
 * @Date 2022/8/6 21:12
 * @Created by orcakill
 */
public class ImageServiceTest extends TestCase {
	
	public static final Logger logger = LogManager.getLogger ("ImageServiceTest ");
	
	@Test
	public void   testImagesClick1() throws InterruptedException, AWTException {
		Double bl = ComputerScaling.getScale ();
		logger.info ("准备开始");
		Thread.sleep (5000);
		for(int i=0;i<10;i++){
			String folderName="scenario/首页/首页勾玉";
			File file= new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName);
			if(file.exists ()){
				PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (folderName,
						"夜神模拟器");
				logger.info ("初始图片x坐标："+pictureIdentifyWorkPO1.getX ()+"    初始y坐标"+pictureIdentifyWorkPO1.getY ());
				Point point=MouseInfo.getPointerInfo().getLocation();
				logger.info ("当前x坐标："+point.getX ()*bl+"    当前y坐标"+point.getY ()*bl);
				double x=point.getX ()*bl/pictureIdentifyWorkPO1.getX ();
				double y=point.getY ()*bl/pictureIdentifyWorkPO1.getY ();
				logger.info ("当前x系数："+x+"    当前y系数"+y);
			}
			else{
				System.out.println ("图片文件路径不存在");
			}
		}
	}
}