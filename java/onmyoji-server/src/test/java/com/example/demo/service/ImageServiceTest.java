package com.example.demo.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.IOException;

import static com.example.demo.model.enums.GameEnum.*;

/**
 * @Classname ImageServiceTest
 * @Description ImageServiceTest
 * @Date 2023/1/28 15:59
 * @Created by orcakill
 */
@Log4j2
@SpringBootTest
class ImageServiceTest {
	//默认图像识别算法，RGB模板匹配
	@Test
	void imagesBack0 () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		log.info ("测试开始");
		boolean b = ImageService.imagesBack0 (home_TS.getValue ());
		log.info (b);
	}
	
	@Test
	void imagesBack () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBack (home_TS.getValue (), 2);
		log.info (b);
	}
	
	@Test
	void imagesBackSingleHide () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBackSingleHide (home_TS.getValue (), 2, 1, true);
		log.info (b);
	}
	
	@Test
	void imagesBackSingleHideIsEmpty () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBackSingleHideIsEmpty (home_TS.getValue (), 2, 1, true);
		log.info (b);
	}
}