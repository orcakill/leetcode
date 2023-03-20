package com.example.demo.utils;

import com.example.demo.model.entity.PictureIdentifyWorkPO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MouseClickUtilsTest
 * @Description 鼠标点击事件 测试类
 * @Date 2023/2/20 19:45
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class MouseClickUtilsTest {
	@Test
	void mouseTest1 () throws InterruptedException, AWTException {
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
			PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ((int) (point.getX () * bl),
			                                                                         (int) (point.getY () * bl));
			List<PictureIdentifyWorkPO> pictureIdentifyWorkPOS = new ArrayList<> ();
			pictureIdentifyWorkPOS.add (pictureIdentifyWorkPO);
			MouseClickUtils.mouseClickBack (pictureIdentifyWorkPOS, "夜神模拟器", true);
		}
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void mouseTest2 () throws InterruptedException, AWTException {
		long startTime = System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		MouseClickUtils.mouseClickBack (new PictureIdentifyWorkPO (435,440),"夜神模拟器");
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}