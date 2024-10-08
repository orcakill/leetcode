package other.scenario.service;

import other.scenario.service.impl.ImageServiceImpl;

import java.awt.*;
import java.io.File;

public interface ImageService {
	
	/*前台识别，单张图片识别*/
	static void imageClick (File file) throws InterruptedException, AWTException {
		ImageServiceImpl.imageClick (file);
	}
	
	/*前台识别，单张图片识别，判断图片在当前页面是否存在*/
	static boolean imageClickIsEmpty (File file) throws InterruptedException, AWTException {
		return ImageServiceImpl.imageClickIsEmpty (file);
	}
	
	/*前台识别，多张图片识别*/
	static void imagesClick (String folder) throws InterruptedException, AWTException {
		ImageServiceImpl.imagesClick (folder);
	}
	
	/*前台识别，多张图片识别,判断图片在当前页面是否存在*/
	static boolean imagesClickIsEmpty (String folder) throws AWTException, InterruptedException {
		return ImageServiceImpl.imagesClickIsEmpty (folder);
	}
	
	/*前台识别，多张图片识别*/
	static void imagesClickNumber (String folder, Integer number) throws InterruptedException, AWTException {
		ImageServiceImpl.imagesClickNumber (folder, number);
	}
	
	/*多张图片后台识别*/
	static void imagesClickBack (String folder) throws InterruptedException, AWTException {
		ImageServiceImpl.imagesClickBack (folder);
	}
	
	/*多张图片后台识别,判断图片在当前页面是否存在*/
	static boolean imagesClickBackIsEmpty (String folder,Integer nums,boolean b) throws Exception {
		return ImageServiceImpl.imagesClickBackIsEmpty (folder,nums,b);
	}
	
	/*多张图片后台识别，可设定识别次数，是否返回日志信息*/
	static boolean imagesClickBackNumber (String folder, Integer number, boolean b) throws InterruptedException,
	                                                                                       AWTException {
		return ImageServiceImpl.imagesClickBackNumber (folder, number,b);
	}
	
	/*多张图片后台识别,判断第一张图片识别后点击第二张图片或直接点击第二张图片*/
	static boolean imagesClickBackNumberOrder (String folder1,String folder2, Integer number) throws InterruptedException,
	                                                                                     AWTException {
	 return 	ImageServiceImpl.imagesClickBackNumberOrder (folder1,folder2, number);
	}
	
	/*多张图片后台识别,判断第一张图片识别后点击第二张图片或直接点击第二张图片或者第三张图片*/
	static  boolean imagesClickBackNumberOrderNumbers (String folder1,String folder2,String folder3,
	                                                String folder4, Integer number) throws InterruptedException,
	                                                                                              AWTException {
	   return 	ImageServiceImpl.imagesClickBackNumberOrderNumbers(folder1,folder2,folder3,folder4,number);
	}
	
}
