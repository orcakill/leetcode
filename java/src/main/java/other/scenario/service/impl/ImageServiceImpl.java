package other.scenario.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import other.scenario.util.ImageRecognition;
import other.scenario.util.ImagesBackRec;
import other.scenario.util.ImagesRecognition;

import java.awt.*;
import java.io.File;

import static other.scenario.util.RandomUtil.getRandom;

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

	

	public static void imageClick (File file) throws InterruptedException, AWTException {
		if (file.exists ()) {
			for (int i = 0; i <60; i++) {
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
				if(i==60-1){
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
			for (int i = 0; i < 10; i++) {
				Thread.sleep (3000);
				if (ImageRecognition.imageRecognitionIsEmpty (file)) {
					logger.info ("图片匹配成功,该图片在当前页面存在");
					b = true;
					break;
				}
				else {
					logger.error ("在每5秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==10-1){
					logger.info (file + "图片未找到");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
		return b;
	}
	

	public static void imagesClick (String folder ) throws InterruptedException, AWTException {
		File file = new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder);
		if (file.exists ()) {
			for (int i = 0; i <60; i++) {
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
				if(i==60-1){
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
			for (int i = 0; i <10; i++) {
				Thread.sleep (5000);
				if (ImagesRecognition.imagesRecognitionIsEmpty (folder)) {
					logger.info ("图片匹配成功,该图片在当前页面存在");
					b = true;
					break;
				}
				else {
					logger.error ("在每5秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==10-1){
					logger.info (folder + "路径下，图片未找到");
				}
			}

			
		}
		else {
			logger.info ("图标路径不存在");
		}
		return b;
	}
	
	public static void imagesClickNumber (String folder,Integer number) throws InterruptedException, AWTException {
		File file= new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder);
		if (file.exists ()) {
			for (int i = 0; i < number; i++) {
				Thread.sleep (2000);
				if (ImagesRecognition.imagesRecognitionIsEmpty (folder)) {
					logger.info ("图片匹配成功");
					Thread.sleep (1000);
					ImagesRecognition.imagesRecognition (folder);
					Thread.sleep (1000);
					logger.info ("操作成功");
					break;
				}
				else {
					logger.error ("在每5秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==number-1){
					logger.info (folder + "路径下，图片未找到");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
		
	}
	
	public static void imagesClickBack (String folder) throws InterruptedException, AWTException {
		File file = new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder);
		if(!file.exists ()){
			file=new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folder);
		}
		if (file.exists ()) {
			for (int i = 0; i < 60; i++) {
				Thread.sleep (2000);
				if (ImagesBackRec.imagesRecognition (folder)) {
					logger.info ("图片匹配成功");
					logger.info ("点击成功");
					break;
				}
				else {
					logger.error ("在每1-2秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
				if(i==60-1){
					logger.info (folder + "路径下，图片未找到");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
	}
	
	public static boolean imagesClickBackIsEmpty (String folder,Integer nums) throws InterruptedException {
		boolean b = false;
		File file = new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder);
		if(!file.exists ()){
			file = new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folder);
		}
		if (file.exists ()) {
			for (int i = 0; i <nums; i++) {
				Thread.sleep (2000);
				if (ImagesBackRec.imagesRecognitionIsEmpty (folder)) {
					logger.info ("图片匹配成功,该图片在当前页面存在");
					b = true;
					break;
				}
				else {
					logger.error ("在每2秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
		return b;
	}
	
	public static void imagesClickBackNumber (String folder, Integer number,boolean b) throws InterruptedException,
	                                                                                 AWTException {
		File file= new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder);
		if (file.exists ()) {
			for (int i = 0; i < number; i++) {
				Thread.sleep (2000);
				if (ImagesBackRec.imagesRecognition (folder)) {
					logger.info ("图片匹配成功");
					logger.info ("点击操作成功");
					break;
				}
				else {
					if(b){
					logger.error ("在每2秒的检测中，第" + (i + 1) + "次检查未发现该图片");
					}
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
	}
	
	public static void imagesClickBackNumberOrder (String folder1, String folder2, Integer number) throws
	                                                                                               InterruptedException,
	                                                                                               AWTException {
		File file1= new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder1);
		File file2= new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder2);
		if (file1.exists ()&&file2.exists ()) {
			for (int i = 0; i < number; i++) {
				Thread.sleep (2000);
				boolean a=ImagesBackRec.imagesRecognitionIsEmpty (folder1);
				boolean b=ImagesBackRec.imagesRecognitionIsEmpty (folder2);
				if (a) {
					logger.info ("图片匹配成功");
					ImagesBackRec.imagesRecognition (folder1);
					Thread.sleep (getRandom (1,2) * 1000L);
					logger.info ("点击操作成功");
					imagesClickBackNumber (folder2,30,true);
					break;
				}
				else if(b){
					logger.info ("图片匹配成功");
					ImagesBackRec.imagesRecognition (folder2);
					Thread.sleep (getRandom (1,2) * 1000L);
					logger.info ("点击操作成功");
					break;
				}
				else {
					logger.error ("在每2秒的检测中，第" + (i + 1) + "次检查未发现该图片");
				}
			}
		}
		else {
			logger.info ("图标路径不存在");
		}
	}
	
	public static boolean imagesClickBackNumberOrderNumbers (String folder1, String folder2, String folder3,
	                                                         String folder4,
	                                                   Integer number) throws
	                                                                                                                    InterruptedException,
	                                                                                                                    AWTException {
			File file1= new File (
					System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder1);
			File file2= new File (
					System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder2);
		    File file3= new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folder3);
			boolean a;
			boolean b;
			boolean c;
		    boolean d = false;
			if (file1.exists ()&&file2.exists ()&&file3.exists ()) {
				for (int i = 0; i < number; i++) {
					Thread.sleep (2000);
					a=ImagesBackRec.imagesRecognitionIsEmpty (folder1);
					b=ImagesBackRec.imagesRecognitionIsEmpty (folder2);
					c=ImagesBackRec.imagesRecognitionIsEmpty (folder3);
					if(i>=number/2&&folder4!=null){
						d=ImagesBackRec.imagesRecognitionIsEmpty (folder4);
					}
					if (a) {
						logger.info ("图片匹配成功");
						Thread.sleep (getRandom (1,2) * 1000L);
						ImagesBackRec.imagesRecognition (folder1);
						Thread.sleep (getRandom (2,3) * 1000L);
						logger.info ("点击操作成功");
						imagesClickBackNumber (folder2,30,true);
						break;
					}
					else if(b){
						logger.info ("图片匹配成功");
						Thread.sleep (getRandom (1,2) * 1000L);
						ImagesBackRec.imagesRecognition (folder2);
						Thread.sleep (getRandom (1,2) * 1000L);
						logger.info ("点击操作成功");
						break;
					}
					else if(c){
						logger.info ("图片匹配成功");
						Thread.sleep (getRandom (1,2) * 1000L);
						ImagesBackRec.imagesRecognition (folder3);
						Thread.sleep (getRandom (1,2) * 1000L);
						logger.info ("点击操作成功");
						return  false;
					}
					else if(d){
						logger.info ("图片匹配成功");
						Thread.sleep (getRandom (1,2) * 1000L);
						ImagesBackRec.imagesRecognition (folder4);
						Thread.sleep (getRandom (1,2) * 1000L);
						logger.info ("点击操作成功");
						break;
					}
					else{
						logger.error ("在每2秒的检测中，第" + (i + 1) + "次检查未发现该图片");
					}
				}
			}
			else {
				logger.info ("图标路径不存在");
			}
			return  true;
	}
}

