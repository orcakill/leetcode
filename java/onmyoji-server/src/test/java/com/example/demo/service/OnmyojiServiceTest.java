package com.example.demo.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.IOException;

import static com.example.demo.model.enums.GameEnum.return_FH;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName OnmyojiServiceTest.java
 * @Description 测试类
 * @createTime 2023年02月15日 11:28:00
 */
@SpringBootTest
@Log4j2
class OnmyojiServiceTest {
	@Autowired
	private  OnmyojiService onmyojiService;
	@Test
	void initializationState () throws IOException, InterruptedException, AWTException {
		long startTime=System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		onmyojiService.initializationState ("1");
		log.info ("测试结束");
		log.info ("用时{}毫秒",System.currentTimeMillis ()-startTime);
	}
	
	@Test
	void initializationStateBak () throws IOException, InterruptedException, AWTException {
		long startTime=System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		ImageService.imagesBackIsEmpty (return_FH.getValue (),1,1);
		log.info ("测试结束");
		log.info ("用时{}毫秒",System.currentTimeMillis ()-startTime);
	}
}