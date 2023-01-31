package com.example.demo.service;

import com.example.demo.model.entity.BufferedImagePO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.demo.model.enums.GameEnum.*;

/**
 * @Classname ImageServiceTest
 * @Description ImageServiceTest
 * @Date 2023/1/28 15:59
 * @Created by orcakill
 */
@Log4j2
@SpringBootTest
class ImageServiceTest {
	//默认图像识别算法，RGB模板匹配
	@Test
	void imagesBack0 () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		log.info ("测试开始");
		boolean b = ImageService.imagesBack0 (home_TS.getValue ());
		log.info (b);
	}
	
	@Test
	void imagesBack () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBack (home_TS.getValue (), 2);
		log.info (b);
	}
	
	@Test
	void imagesBackSingleHide () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBackSingleHide (home_TS.getValue (), 2, 1, true);
		log.info (b);
	}
	
	@Test
	void imagesBackSingleHideIsEmpty () throws IOException, InterruptedException, AWTException {
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		boolean b = ImageService.imagesBackSingleHideIsEmpty (home_TS.getValue (), 2, 1, true);
		log.info (b);
	}
	
	@Test
	//初始化图片
	void  bufferedImageTest() throws IOException {
		long a=System.currentTimeMillis ();
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		//Map<String,List<BufferedImagePO>> map=new HashMap<String,List<BufferedImagePO>>();
		test ("D:\\project\\leetcode\\java\\onmyoji-server\\src\\main" +
		                                            "\\resources\\static\\scenario");
		log.info ("测试结束");
		long b=System.currentTimeMillis ();
		log.info ("用时{}毫秒",b-a);
	}
	
	private static void test(String path) throws IOException {
		try (Stream<Path> paths = Files.walk (Paths.get (path))){
			List<File> files =
					paths.filter (Files::isRegularFile).map(l -> new File (l.toString ())).collect (Collectors.toList ());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}