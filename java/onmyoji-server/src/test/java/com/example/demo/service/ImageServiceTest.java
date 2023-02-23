package com.example.demo.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.IOException;

import static com.example.demo.model.param.ImageRecParam.paramSIFT;
import static com.example.demo.model.var.CommVar.login_XSJ;

/**
 * @Classname ImageServiceTest
 * @Description 图像识别服务 测试类
 * @Date 2023/2/20 20:28
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class ImageServiceTest {
	@Test
	void imageServiceTest () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		ImageService.imagesBack (login_XSJ, paramSIFT);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}