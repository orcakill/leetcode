package com.example.demo.service;

import com.example.demo.model.param.ImageRecParam;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.IOException;

import static com.example.demo.model.param.ImageRecParam.paramSIFT;
import static com.example.demo.model.var.CommVar.*;
import static com.example.demo.service.ImageService.imagesBack;

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
		imagesBack (soul_JC_BQDS, paramSIFT (process, 1, 4));
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void imageServiceTest1 () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		ImageService.imagesBack (return_FH, ImageRecParam.paramSIFT("夜神模拟器",0,1,4));
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	/***
	 * @description: 契灵
	 * @param
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/6/9 15:26
	 */
	
	@Test
	void qiLing() throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		
		log.info ("阴阳师-契灵");
		for(int i=0;i<50;i++){
			log.info ("{}次",i+1);
			//探查
			imagesBack (deed_TC,paramSIFT ("夜神模拟器",10,4));
			Thread.sleep (35*1000);
			//退出挑战
			imagesBack (soul_TCTZ,paramSIFT ("夜神模拟器",10,4));
		}
		
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}