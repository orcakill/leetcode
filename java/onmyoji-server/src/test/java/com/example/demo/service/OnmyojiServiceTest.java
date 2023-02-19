package com.example.demo.service;

import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.utils.ComputerScalingUtils;
import com.example.demo.utils.MouseClickUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.enums.GameEnum.*;
import static com.example.demo.model.param.ImageRecParam.*;

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
	private OnmyojiService onmyojiService;
	
	@Test
	void imageServiceTest () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		ImageService.imagesBack (login_KSYX.getValue (), paramTM_SQDIFF_NORMED (3E-10));
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void mouseTest () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		double bl = ComputerScalingUtils.getScale ();
		log.info ("准备开始");
		Thread.sleep (5000);
		for (int i = 0; i < 1; i++) {
			Point point = MouseInfo.getPointerInfo ()
			                       .getLocation ();
			log.info ("当前x坐标：" + point.getX () * bl + "    当前y坐标" + point.getY () * bl);
			PictureIdentifyWorkPO pictureIdentifyWorkPO=new PictureIdentifyWorkPO ((int) (point.getX () * bl),
			                                                                       (int) (point.getY ()*bl));
			List<PictureIdentifyWorkPO> pictureIdentifyWorkPOS=new ArrayList<> ();
			pictureIdentifyWorkPOS.add (pictureIdentifyWorkPO);
			MouseClickUtils.mouseClickBack (pictureIdentifyWorkPOS, "夜神模拟器", true);
		}
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void initializationState () throws IOException, InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		onmyojiService.initializationState ("1");
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
}