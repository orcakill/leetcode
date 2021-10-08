package other.scenario.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.util.ImageRecognition;

import java.awt.*;
import java.io.File;

public class ImageService {
	private static final Logger logger = LogManager.getLogger (ImageService.class);
	
	public static  void  imageClick (File file) throws AWTException, InterruptedException {
		if(file.exists ()){
            for(int i=0;i<20;i++){
	            Thread.sleep (5000);
	           if(ImageRecognition.imageRecognitionIsEmpty (file)){
		           logger.info ("图片匹配成功");
				   ImageRecognition.imageRecognition (file);
				   break;
	           }
			   if(i==20){
				   logger.error ("在每5秒的检测中，检查20次,共100秒未检测到该图片");
			   }
            }

		}
		else{
			logger.info ("图标路径不存在");
		}
		
	}
	
}
