package com.example.service;

import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.util.ComputerScaling;
import com.example.util.ImagesBackRec;
import com.example.util.ImagesOpenCVSIFT;
import com.example.util.MouseClick;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.opencv.core.Core;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.example.service.FightService.soulBack;

/**
 * @Classname ImageServiceTest
 * @Description TODO
 * @Date 2022/8/6 21:12
 * @Created by orcakill
 */
public class ImageServiceTest {
	
	public static final Logger logger = LogManager.getLogger ("ImageServiceTest ");
	
	public static boolean b1 = true;//是否开启测试
	
	@Test
	public void testImagesClick1 () throws InterruptedException, AWTException {
		double bl = ComputerScaling.getScale ();
		if (b1) {
			logger.info ("准备开始");
			for (int i = 0; i < 10; i++) {
				String folderName = "scenario/御魂整理/御魂强化属性/生命加成";
				File file = new File (
						System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName);
				if (file.exists ()) {
					PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (folderName,
							"夜神模拟器");
					logger.info ("初始图片x坐标：" + pictureIdentifyWorkPO1.getX () + "    初始y坐标" + pictureIdentifyWorkPO1.getY ());
					Point point = MouseInfo.getPointerInfo ()
					                       .getLocation ();
					logger.info ("当前x坐标：" + point.getX () * bl + "    当前y坐标" + point.getY () * bl / 1.1);
					double x = point.getX () * bl / pictureIdentifyWorkPO1.getX ();
					double y = point.getY () * bl / pictureIdentifyWorkPO1.getY ();
					logger.info ("当前x系数：" + x + "    当前y系数" + y);
				}
				else {
					System.out.println ("图片文件路径不存在");
				}
			}
		}
	}
	
	@Test
	public void testImagesClick2 () throws InterruptedException, AWTException {
		double bl = ComputerScaling.getScale ();
		String file1 = "scenario/寄养/好友标题";
		logger.info ("准备开始");
		if (b1) {
			Thread.sleep (5000);
			for (int i = 0; i < 10; i++) {
				File file = new File (
						System.getProperty ("user.dir") + "/src/main/resources/image/" + file1);
				if (file.exists ()) {
					PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (file1,
							"夜神模拟器");
					logger.info ("初始图片x坐标：" + pictureIdentifyWorkPO1.getX () + "    初始y坐标" + pictureIdentifyWorkPO1.getY ());
					PictureIdentifyWorkPO pictureIdentifyWorkPO2 = new PictureIdentifyWorkPO ();
					pictureIdentifyWorkPO2.setX ((int) (pictureIdentifyWorkPO1.getX () * 1.0));
					pictureIdentifyWorkPO2.setY ((int) (pictureIdentifyWorkPO1.getY () * 1.6));
					PictureIdentifyWorkPO pictureIdentifyWorkPO3 = new PictureIdentifyWorkPO ();
					pictureIdentifyWorkPO3.setX ((int) (pictureIdentifyWorkPO1.getX () * 1.0));
					pictureIdentifyWorkPO3.setY ((int) (pictureIdentifyWorkPO1.getY () * 2.3));
					ImageService.imagesClickBackDrag (pictureIdentifyWorkPO2, pictureIdentifyWorkPO1, "夜神模拟器");
				}
				else {
					System.out.println ("图片文件路径不存在");
				}
			}
		}
	}
	
	@Test
	public void testImagesClick3 () throws InterruptedException, AWTException {
		double bl = ComputerScaling.getScale ();
		logger.info ("准备开始");
		if (b1) {
			Thread.sleep (5000);
			for (int i = 0; i < 1; i++) {
				Point point = MouseInfo.getPointerInfo ()
				                       .getLocation ();
				logger.info ("当前x坐标：" + point.getX () * bl + "    当前y坐标" + point.getY () * bl);
				MouseClick.mouseClickBack (point.getX () * bl, point.getY () * bl, "夜神模拟器");
			}
		}
	}
	/***
	 * @description: 直接点击坐标，尝试是否可以点击
	 * @param
	 * @return: void
	 * @author: orcakill
	 * @date: 2022/11/6 2:26
	 */
	
	@Test
	public void testImagesClick4 () throws InterruptedException, AWTException {
		double bl = ComputerScaling.getScale ();
		logger.info ("准备开始");
		if (b1) {
			Thread.sleep (5000);
			for (int i = 0; i < 1; i++) {
				MouseClick.mouseClickBack (1200, 600, "夜神模拟器");
			}
		}
	}
	
	//重复挑战
	@Test
	public void testImagesClick5 () throws InterruptedException, AWTException {
		logger.info ("准备开始");
		if (b1) {
			Thread.sleep (5000);
			soulBack (16, 150);
		}
	}
	

	
	//好友邀请超鬼王
	@Test
	public void testImagesClick6 () throws InterruptedException, AWTException {
		logger.info ("准备开始");
		String file_YXXX = "scenario/活动/20221019/御香寻行";
		boolean boolean_YXXX = false;
		String file_JJ = "scenario/活动/20221019/集结";
		boolean boolean_JJ = false;
		String file_HY = "scenario/活动/20221019/好友";
		boolean boolean_HY = false;
		String file_YQ = "scenario/活动/20221019/邀请";
		boolean boolean_YQ = false;
		if (b1) {
			for (int i = 1; i <= 600; i++) {
				logger.info ("判断是否有御香寻行，有则点击寻找超鬼王");
				boolean_YXXX = ImageService.imagesClickBackIsEmpty (file_YXXX, 5);
				if (boolean_YXXX) {
					ImageService.imagesClickBack (file_YXXX);
					Thread.sleep (5000);
					logger.info ("判断是否有集结,有则准备点击集结");
					boolean_JJ = ImageService.imagesClickBackIsEmpty (file_JJ, 5);
					if (boolean_JJ) {
						ImageService.imagesClickBack (file_JJ);
					}
					Thread.sleep (2000);
					logger.info ("判断是否有好友,有则准备点击好友邀请");
					boolean_HY = ImageService.imagesClickBackIsEmpty (file_HY, 5);
					if (boolean_HY) {
						ImageService.imagesClickBack (file_HY);
					}
					Thread.sleep (2000);
					logger.info ("判断是否有邀请,有则准备点击邀请");
					boolean_YQ = ImageService.imagesClickBackIsEmpty (file_YQ, 5);
					if (boolean_YQ) {
						ImageService.imagesClickBack (file_YQ);
					}
				}
				logger.info ("等待一分钟");
				Thread.sleep (60 * 1000);
			}
		}
	}
	
	//重复挑战,需要再点击挑战
	@Test
	public void testImagesClick7 () throws InterruptedException, AWTException {
		logger.info ("准备开始");
		String exploration="scenario/活动/20221123/探查";
		String challenge="scenario/活动/20221123/挑战";
		String prepare="scenario/活动/20221123/准备";
		String exitTheChallenge="scenario/活动/20221123/退出挑战";
		if (b1) {
			Thread.sleep (5000);
			for(int i=0;i<20;i++){
				ImageService.imagesClickBack (exploration,10);
				Thread.sleep (1000);
				ImageService.imagesClickBack (challenge,10);
				Thread.sleep (1000);
				ImageService.imagesClickBack (prepare,10);
				Thread.sleep (3*60*1000);
				ImageService.imagesClickBack (exitTheChallenge,20);
				Thread.sleep (1000);
			}
		}
	}
	
	//重复挑战,需要点击胜利和获取奖励
	@Test
	public void testImagesClick8 () throws InterruptedException, AWTException {
		logger.info ("准备开始");
		String exploration="scenario/活动/20221123/探查";
		String challenge="scenario/活动/20221123/挑战";
		String prepare="scenario/活动/20221123/准备";
		String exitTheChallenge="scenario/活动/20221123/退出挑战";
		if (b1) {
			Thread.sleep (5000);
			for(int i=0;i<20;i++){
				ImageService.imagesClickBack (exploration,10);
				Thread.sleep (1000);
				ImageService.imagesClickBack (challenge,10);
				Thread.sleep (1000);
				ImageService.imagesClickBack (prepare,10);
				Thread.sleep (3*60*1000);
				ImageService.imagesClickBack (exitTheChallenge,20);
				Thread.sleep (1000);
			}
		}
	}
	
	@Test
	public void testImagesClick9() throws InterruptedException, AWTException, IOException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		logger.info ("准备开始");
		String challenge="scenario/活动/20230226/挑战";
		String exitTheChallenge="scenario/活动/20221123/退出挑战";
		if (b1) {
			Thread.sleep (5000);
			for(int i=0;i<90;i++){
				Thread.sleep (1000);
				ImageOpenCVService.imagesOpenCV (challenge, 10);
				Thread.sleep (10*1000);
				ImageService.imagesClickBack (exitTheChallenge,10);
				Thread.sleep (1000);
			}
		}
	}
	
}