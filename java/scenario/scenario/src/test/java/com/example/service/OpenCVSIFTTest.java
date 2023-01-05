package com.example.service;

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
import java.util.HashMap;
import java.util.Map;

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
		ImagesOpenCVSIFT.matchImage (bufferedImageSrc, bufferedImageTem, 0.7, true, 4);
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
		ImagesOpenCVSIFT.matchImage (bufferedImageSrc, bufferedImageTem, 0.7, true, 4);
	}
	
	@Test
	public void sift2 () throws IOException, InterruptedException, AWTException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		String folder = ArrangeEnums.arrange_XX.getValue ();
		ImageOpenCVService.imagesOpenCV (folder);
	}
	
	@Test
	public void sift3 () throws IOException, InterruptedException, AWTException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		//御魂强化属性结果
		Map<String, String> strengthenResultSet = new HashMap<> ();
		//初始化御魂强化结果
		strengthenResultSet.put ("速度", ArrangeEnums.arrange_YHQHSX_SD.getValue ());
		strengthenResultSet.put ("效果命中", ArrangeEnums.arrange_YHQHSX_XGMZ.getValue ());
		strengthenResultSet.put ("效果抵抗", ArrangeEnums.arrange_YHQHSX_XGDK.getValue ());
		strengthenResultSet.put ("暴击", ArrangeEnums.arrange_YHQHSX_BJ.getValue ());
		strengthenResultSet.put ("暴击伤害", ArrangeEnums.arrange_YHQHSX_BJSH.getValue ());
		strengthenResultSet.put ("攻击", ArrangeEnums.arrange_YHQHSX_GJ.getValue ());
		strengthenResultSet.put ("攻击加成", ArrangeEnums.arrange_YHQHSX_GJJC.getValue ());
		strengthenResultSet.put ("生命", ArrangeEnums.arrange_YHQHSX_SM.getValue ());
		strengthenResultSet.put ("生命加成", ArrangeEnums.arrange_YHQHSX_SMJC.getValue ());
		strengthenResultSet.put ("防御", ArrangeEnums.arrange_YHQHSX_FY.getValue ());
		strengthenResultSet.put ("防御加成", ArrangeEnums.arrange_YHQHSX_FYJC.getValue ());
		String soulSubduingEnhancementAttribute = ImageOpenCVService.imagesOpenCV (strengthenResultSet, 2, 0.7, 5, 10, 120);
		logger.info ("御魂强化属性为{}", soulSubduingEnhancementAttribute);
	}
	
}
