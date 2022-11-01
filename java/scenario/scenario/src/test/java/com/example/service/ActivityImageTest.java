package com.example.service;

import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.util.ImagesBackRec;
import com.example.util.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Classname ActivityImageTest
 * @Description TODO
 * @Date 2022/11/1 21:25
 * @Created by orcakill
 */
public class ActivityImageTest {
	public static final Logger logger = LogManager.getLogger ("ActivityImageTest");
	
	@Test
	public void testImagesClick1 () throws IOException {
		File file=new File (System.getProperty ("user.dir") + "/src/main/resources/image/"+"scenario/测试图片/动态图片测试/探索/source/a.jpg");
		String folderName="scenario/测试图片/动态图片测试/探索/search";
		//		屏幕截图
		BufferedImage window= ImageIO.read (file);
		//		图片
		List<int[][]> ImagesData = ImagesBackRec.imageToDate (folderName);
		//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY = ImagesBackRec.FindAllImgData (window, ImagesData);
		String test= String.valueOf (1);
	}
	
}
