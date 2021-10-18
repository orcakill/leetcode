package other.scenario.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import other.scenario.util.ImageRecognition;
import other.scenario.util.ImagesRecognition;

import java.awt.*;
import java.io.File;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageServiceImpl.java
 * @Description TODO
 * @createTime 2021年10月15日 10:36:00
 */
@Service
public class ImageServiceImpl  {
	public  static  final Logger logger = LogManager.getLogger (ImageServiceImpl.class);
	private  static final int num=20;
	

	public static void imageClick (File file) throws InterruptedException, AWTException {
		if (file.exists ()) {
			for (int i = 0; i <num; i++) {
				Thread.sleep (5000);
				if (ImageRecognition.imageRecognitionIsEmpty (file)) {
					logger.info ("图片匹配成功");
					Thread.sleep (2000);
					ImageRecognition.imageRecognition (file);
					Thread.sleep (2000);
					logger.info ("操作成功");
					break;
				}
				else {
					logger.error ("在每5秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==num-1){
					logger.info (file + "图片未找到");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
		
	}
	
	public static boolean imageClickIsEmpty (File file) throws InterruptedException, AWTException {
		boolean b = false;
		if (file.exists ()) {
			for (int i = 0; i < num; i++) {
				Thread.sleep (5000);
				if (ImageRecognition.imageRecognitionIsEmpty (file)) {
					logger.info ("图片匹配成功,该图片在当前页面存在");
					b = true;
					break;
				}
				else {
					logger.error ("在每5秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==num-1){
					logger.info (file + "图片未找到");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
		return b;
	}
	

	public static void imagesClick (String folder) throws InterruptedException, AWTException {
		File file = new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder);
		if (file.exists ()) {
			for (int i = 0; i < num; i++) {
				Thread.sleep (5000);
				if (ImagesRecognition.imagesRecognitionIsEmpty (folder)) {
					logger.info ("图片匹配成功");
					Thread.sleep (2000);
					ImagesRecognition.imagesRecognition (folder);
					Thread.sleep (2000);
					logger.info ("操作成功");
					break;
				}
				else {
					logger.error ("在每5秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==num-1){
					logger.info (folder + "路径下，图片未找到");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
		
	}
	

	public static boolean imagesClickIsEmpty (String folder) throws AWTException, InterruptedException {
		boolean b = false;
		File file = new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder);
		if(!file.exists ()){
			file = new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folder);
		}
		if (file.exists ()) {
			for (int i = 0; i <num; i++) {
				Thread.sleep (5000);
				if (ImagesRecognition.imagesRecognitionIsEmpty (folder)) {
					logger.info ("图片匹配成功,该图片在当前页面存在");
					b = true;
					break;
				}
				else {
					logger.error ("在每5秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==num-1){
					logger.info (folder + "路径下，图片未找到");
				}
			}

			
		}
		else {
			logger.info ("图标路径不存在");
		}
		return b;
	}
	
}

