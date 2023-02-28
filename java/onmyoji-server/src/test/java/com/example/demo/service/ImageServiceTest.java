package com.example.demo.service;

import com.example.demo.model.param.ImageRecParam;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.IOException;

import static com.example.demo.model.param.ImageRecParam.paramRGB;
import static com.example.demo.model.var.CommVar.*;

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
		String process="夜神模拟器";
		ImageService.imagesBack (soul_TCTZ, paramRGB(process));
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void imageServiceTest1 () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		ImageService.imagesBack (home_TS, ImageRecParam.paramSIFT("夜神模拟器1",0,1,4));
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}