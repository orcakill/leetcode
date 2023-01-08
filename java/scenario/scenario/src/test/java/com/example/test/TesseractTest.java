package com.example.test;

import com.example.model.entity.ScreenshotPointPO;
import com.example.model.map.ScreenshotPointMap;
import com.example.service.FightService;
import com.example.util.ImageTesseract;
import com.example.util.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname tess4jTest
 * @Description TODO
 * @Date 2023/1/7 21:42
 * @Created by orcakill
 */

public class TesseractTest {
	private static final Logger logger = LogManager.getLogger ("TesseractTest");
	
	@Test
	public void test1 () throws IOException {
		String imgPath = "D:/a.png";
		File imageFile = new File (imgPath);
		if (!imageFile.exists ()) {
			System.out.println ("图片不存在");
		}
		BufferedImage textImage = ImageIO.read (imageFile);
		String text = ImageTesseract.findOCR (textImage, true);
		System.out.println (text);
	}
	
	@Test
	public void test2 () {
		//模拟器截屏处理
		BufferedImage Window = Screenshot.screenshotBack ("夜神模拟器");
		//截图 1 2 3
		ScreenshotPointPO screenshotPointPO1 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第一部分");
		ScreenshotPointPO screenshotPointPO2 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第二部分");
		ScreenshotPointPO screenshotPointPO3 = ScreenshotPointMap.getScreenshotPoint ("御魂等级强化第三部分");
		BufferedImage bufferedImage1 =
				Window.getSubimage (screenshotPointPO1.getX (), screenshotPointPO1.getY (), screenshotPointPO1.getW (),
				                    screenshotPointPO1.getH ());
		BufferedImage bufferedImage2 =
				Window.getSubimage (screenshotPointPO2.getX (), screenshotPointPO2.getY (), screenshotPointPO2.getW (),
				                    screenshotPointPO2.getH ());
		BufferedImage bufferedImage3 =
				Window.getSubimage (screenshotPointPO3.getX (), screenshotPointPO3.getY (), screenshotPointPO3.getW (),
				                    screenshotPointPO3.getH ());
		String s1 = ImageTesseract.findOCR (bufferedImage1, true);
		String s2 = ImageTesseract.findOCR (bufferedImage2, false);
		String s3 = ImageTesseract.findOCR (bufferedImage3, false);
		List<String> list1 = Arrays.asList (s1.split ("\n"));
		List<String> list2 = Arrays.asList (s2.split ("\n"));
		List<String> list3 = Arrays.asList (s3.split ("\n"));
		if (list1.size () == 5 && list1.size () == list2.size () || list2.size () == list3.size ()) {
			for (int i = 1; i < 5; i++) {
				if (!list2.get (i).equals (list3.get (i))) {
					logger.info (list1.get (i));
				}
			}
		}
		logger.info ("结束");
	}
	
	@Test
	public void test3 () throws InterruptedException, AWTException {
		logger.info (FightService.soulLevelEnhancementRecognition ());
	}
}
