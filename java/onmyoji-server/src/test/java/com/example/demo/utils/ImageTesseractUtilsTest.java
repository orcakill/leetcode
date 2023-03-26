package com.example.demo.utils;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * @Classname ImageTesseractTest
 * @Description ImageTesseractTest
 * @Date 2023/1/28 2:37
 * @Created by orcakill
 */

@Log4j2
class ImageTesseractUtilsTest {

	@Test
	void findOCR () throws IOException {
		File file=new File ("D:/a.png");
		BufferedImage bufferedImage= ImageIO.read (file);
		String s= ImageTesseractUtils.findOCR (bufferedImage, true);
		log.info (s);
	}
	@Test
	void findDingTalk () throws IOException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		BufferedImage Window = ScreenshotUtils.screenshotBack ("夜神模拟器");
		String s= ImageTesseractUtils.findOCR (Window, true);
		log.info (s);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}