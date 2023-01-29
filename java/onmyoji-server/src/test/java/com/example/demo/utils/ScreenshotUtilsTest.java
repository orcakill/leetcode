package com.example.demo.utils;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Classname ScreenshotTest
 * @Description 截图测试方法
 * @Date 2023/1/26 21:42
 * @Created by orcakill
 */
@Log4j2
class ScreenshotUtilsTest {
	
	@Test
	void screenshot () throws AWTException {
		BufferedImage bufferedImage= ScreenshotUtils.screenshot ();
		log.info (bufferedImage.toString ());
	}
	
	@Test
	void screenshotBack () {
		BufferedImage bufferedImage= ScreenshotUtils.screenshotBack ("夜神模拟器");
		log.info (bufferedImage.toString ());
	}
}