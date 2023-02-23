package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.map.FolderPathMap;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.example.demo.model.var.CommVar.home_TS;
import static org.opencv.imgcodecs.Imgcodecs.imread;

/**
 * @Classname ImagesOpenCVORBUtilsTest
 * @Description ImagesOpenCVORBUtilsTest
 * @Date 2023/2/23 20:51
 * @Created by orcakill
 */
@SpringBootTest
@Log4j2
class ImagesOpenCVORBUtilsTest {
	
	@Test
	void  test1(){
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		Mat src = imread ("D:\\b.png");
		ImagesOpenCVORBUtils.imageRecognitionORB (src);
	}
	@Test
	void findAllImgDataOpenCvAll () throws IOException {
		long startTime = System.currentTimeMillis ();
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		//BufferedImage Window = ScreenshotUtils.screenshotBack ("夜神模拟器");
		String str_tem = "D:/a.jpg";
		File file_tem = new File (str_tem);
		BufferedImage Window = ImageIO.read (file_tem);
		//List<PictureCollectionPO> pictureCollectionPOList=new ArrayList<> ();
		//PictureCollectionPO pictureCollectionPO=new PictureCollectionPO (1,"1","1",bufferedImageTem,null);
		//pictureCollectionPOList.add (pictureCollectionPO);
		String path = FolderPathMap.folderPath ("图片总路径");
		List<PictureCollectionPO> pictureCollectionPOList =
				ReadFileUtils.readPictureCollectionPOList (path,home_TS, "SIFT");
		//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY =ImagesOpenCVORBUtils.FindAllImgDataOpenCvAll (Window,
		                                                                                    pictureCollectionPOList, 0.7,
		                                                               4, true);
		assert mouseXY != null;
		log.info (mouseXY.toString ());
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}