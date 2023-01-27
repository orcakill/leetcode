package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Classname ScreenshotTest
 * @Description 截图测试方法
 * @Date 2023/1/26 21:42
 * @Created by orcakill
 */
@Slf4j
class ScreenshotTest {
	
	@Test
	void screenshot () throws AWTException {
		BufferedImage bufferedImage=Screenshot.screenshot ();
		log.info (bufferedImage.toString ());
	}
}