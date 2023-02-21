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

import static com.example.demo.model.var.CommVar.home_TS;
import static com.example.demo.utils.ImagesOpenCVSIFTUtils.FindAllImgDataOpenCvAll;

/**
 * @Classname ImagesOpenCVSIFTUtilsTest
 * @Description SIFT 算法测试类
 * @Date 2023/2/21 1:41
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class ImagesOpenCVSIFTUtilsTest {
	
	@Test
	void findAllImgDataOpenCvAll () throws IOException {
		long startTime = System.currentTimeMillis ();
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		BufferedImage Window = ScreenshotUtils.screenshotBack ("夜神模拟器");
		//String str_tem = "D:/b.png";
		//File file_tem = new File (str_tem);
		//BufferedImage bufferedImageTem = ImageIO.read (file_tem);
		//List<PictureCollectionPO> pictureCollectionPOList=new ArrayList<> ();
		//PictureCollectionPO pictureCollectionPO=new PictureCollectionPO (1,"1","1",bufferedImageTem,null);
		//pictureCollectionPOList.add (pictureCollectionPO);
		String path = FolderPathMap.folderPath ("图片总路径");
		List<PictureCollectionPO> pictureCollectionPOList =
				ReadFileUtils.readPictureCollectionPOList (path,home_TS, "SIFT");
		//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY = FindAllImgDataOpenCvAll (Window, pictureCollectionPOList, 0.7,
		                                                               4,true);
		assert mouseXY != null;
		log.info (mouseXY.toString ());
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}