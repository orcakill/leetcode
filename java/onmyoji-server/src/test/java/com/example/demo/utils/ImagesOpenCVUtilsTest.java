package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.map.FolderPathMap;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static com.example.demo.model.var.CommVar.login_KSYX;

/**
 * @Classname ImagesOpenCVUtilsTest
 * @Description TODO
 * @Date 2023/2/20 19:30
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class ImagesOpenCVUtilsTest {
	//测试 openCV的模板匹配算法
	@Test
	void findAllImgDataOpenCv () throws IOException {
		long startTime = System.currentTimeMillis ();
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		BufferedImage Window = ScreenshotUtils.screenshotBack ("夜神模拟器");
		String path = FolderPathMap.folderPath ("图片总路径");
		List<PictureCollectionPO> pictureCollectionPOList =
				ReadFileUtils.readPictureCollectionPOList (path, login_KSYX, "TM_SQDIFF_NORMED");
		//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY =
				ImagesOpenCVUtils.FindAllImgDataOpenCv (Window, pictureCollectionPOList, 4E-10, true,"TM_SQDIFF_NORMED");
		log.info (mouseXY.toString ());
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}