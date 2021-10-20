package other.scenario.service;



import other.scenario.service.impl.ImageServiceImpl;

import java.awt.*;
import java.io.File;


public interface ImageService {
	
	/*单张图片识别*/
	static  void  imageClick (File file) throws InterruptedException, AWTException{
		ImageServiceImpl.imageClick (file);
	}
	/*单张图片识别，判断图片在当前页面是否存在*/
    static 	boolean  imageClickIsEmpty (File file) throws InterruptedException, AWTException{
		return  ImageServiceImpl.imageClickIsEmpty (file);
    }
	
	/*多张图片识别*/
   static 	void  imagesClick (String folder) throws InterruptedException, AWTException{
	   ImageServiceImpl.imagesClick (folder);
   }
	
	/*多张图片识别,判断图片在当前页面是否存在*/
    static boolean imagesClickIsEmpty (String folder) throws AWTException, InterruptedException{
		 return  ImageServiceImpl.imagesClickIsEmpty (folder);
    }
	
	
}
