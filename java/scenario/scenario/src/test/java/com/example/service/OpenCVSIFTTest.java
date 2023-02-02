package com.example.service;

import com.example.model.entity.BufferedImagePO;
import com.example.model.entity.StrengthenResultPO;
import com.example.model.enums.ArrangeEnums;
import com.example.util.ImagesOpenCVSIFT;
import com.example.util.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.opencv.core.Core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName OpenCVSIFTTest.java
 * @Description OpenCVSIFTTest
 * @createTime 2023年01月05日 08:46:00
 */
public class OpenCVSIFTTest {
	public static final Logger logger = LogManager.getLogger ("OpenCVSIFTTest");
	
	@Test
	public void sift1 () throws IOException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		String str_src = "D:/a.png";
		String str_tem = "D:/b.png";
		File file_src = new File (str_src);
		File file_tem = new File (str_tem);
		BufferedImage bufferedImageSrc = ImageIO.read (file_src);
		BufferedImage bufferedImageTem = ImageIO.read (file_tem);
		ImagesOpenCVSIFT.matchImage (bufferedImageSrc, new BufferedImagePO (1, "图片", bufferedImageTem), 0.7, true, 4);
	}
	
	@Test
	public void sift11 () throws IOException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		////模拟器截屏处理
		BufferedImage Window = Screenshot.screenshotBack ("夜神模拟器");
		String str_tem = "D:/b.png";
		File file_tem = new File (str_tem);
		BufferedImage bufferedImageSrc = Window;
		BufferedImage bufferedImageTem = ImageIO.read (file_tem);
		ImagesOpenCVSIFT.matchImage (bufferedImageSrc, new BufferedImagePO (1, "图片", bufferedImageTem), 0.7, true, 4);
	}
	
	@Test
	public void sift2 () throws IOException, InterruptedException, AWTException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		String folder = ArrangeEnums.arrange_XX.getValue ();
		ImageOpenCVService.imagesOpenCV (folder);
	}
	
	@Test
	public void sift3 () throws IOException, InterruptedException, AWTException {
		logger.info ("开始");
		long start = System.currentTimeMillis ();
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		//御魂强化属性结果
		List<StrengthenResultPO> strengthenResultSet = new ArrayList<> ();
		//初始化御魂强化结果
		strengthenResultSet.add (new StrengthenResultPO ("速度", ArrangeEnums.arrange_YHQHSX_SD.getValue (), 100));
		strengthenResultSet.add (new StrengthenResultPO ("暴击", ArrangeEnums.arrange_YHQHSX_BJ.getValue (), 100));
		strengthenResultSet.add (new StrengthenResultPO ("攻击", ArrangeEnums.arrange_YHQHSX_GJ.getValue (), 100));
		strengthenResultSet.add (new StrengthenResultPO ("生命", ArrangeEnums.arrange_YHQHSX_SM.getValue (), 100));
		strengthenResultSet.add (new StrengthenResultPO ("防御", ArrangeEnums.arrange_YHQHSX_FY.getValue (), 100));
		strengthenResultSet.add (new StrengthenResultPO ("生命加成", ArrangeEnums.arrange_YHQHSX_SMJC.getValue (), 120));
		strengthenResultSet.add (new StrengthenResultPO ("攻击加成", ArrangeEnums.arrange_YHQHSX_GJJC.getValue (), 120));
		strengthenResultSet.add (new StrengthenResultPO ("暴击伤害", ArrangeEnums.arrange_YHQHSX_BJSH.getValue (), 120));
		strengthenResultSet.add (new StrengthenResultPO ("效果命中", ArrangeEnums.arrange_YHQHSX_XGMZ.getValue (), 120));
		strengthenResultSet.add (new StrengthenResultPO ("效果抵抗", ArrangeEnums.arrange_YHQHSX_XGDK.getValue (), 120));
		strengthenResultSet.add (new StrengthenResultPO ("防御加成", ArrangeEnums.arrange_YHQHSX_FYJC.getValue (), 120));
		String soulSubduingEnhancementAttribute = ImageOpenCVService.imagesOpenCV (strengthenResultSet, 2, 0.7, 1, 1);
		logger.info ("御魂强化属性为{}", soulSubduingEnhancementAttribute);
		long end = System.currentTimeMillis ();
		logger.info ("总时间 = " + (end - start) / 1000);
	}
	
	@Test
	public void sift4 () throws IOException, InterruptedException, AWTException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		String file = "scenario/阴阳寮/结界";
		ImageOpenCVService.imagesOpenCV (file);
	}
	
	@Test
	public void sift5() throws IOException, InterruptedException, AWTException {
		logger.info ("等待1秒,输出10个数");
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Hello world!");
			}
		};
		
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool (2);
		
		scheduledThreadPool.schedule (timerTask, 1000, TimeUnit.MILLISECONDS);
		scheduledThreadPool.scheduleAtFixedRate(timerTask, 1000, 1000, TimeUnit.MILLISECONDS);
		logger.info ("结束");
	}
}
