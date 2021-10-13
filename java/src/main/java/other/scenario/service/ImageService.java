package other.scenario.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.util.ImageRecognition;
import other.scenario.util.ImagesRecognition;

import java.awt.*;
import java.io.File;

public class ImageService {
	private static final Logger logger = LogManager.getLogger (ImageService.class);
	
	/*单张图片识别*/
	public static  void  imageClick (File file) throws AWTException, InterruptedException {
		if(file.exists ()){
            for(int i=0;i<20;i++){
	            Thread.sleep (5000);
	           if(ImageRecognition.imageRecognitionIsEmpty (file)){
		           logger.info ("图片匹配成功");
		           Thread.sleep (2000);
				   ImageRecognition.imageRecognition (file);
		           Thread.sleep (2000);
		           logger.info ("操作成功");
				   break;
	           }
			   else{
				   logger.error ("在每5秒的检测中，第"+(i+1)+"次检查未发现该图片");
			   }
            }

		}
		else{
			logger.info ("图标路径不存在");
		}
		
	}
	
	/*多张图片识别*/
	public static  void  imagesClick (String folder) throws AWTException, InterruptedException {
		File file=new File (
				System.getProperty("user.dir") + "/java/src/main/resources/image/"+ folder);
		if(file.exists ()){
			for(int i=0;i<20;i++){
				Thread.sleep (5000);
				if(ImagesRecognition.imagesRecognitionIsEmpty (folder)){
					logger.info ("图片匹配成功");
					Thread.sleep (2000);
					ImagesRecognition.imagesRecognition (folder);
					Thread.sleep (2000);
					logger.info ("操作成功");
					break;
				}
				else{
					logger.error ("在每5秒的检测中，第"+(i+1)+"次检查未发现该图片");
				}
			}
			
		}
		else{
			logger.info ("图标路径不存在");
		}
		
	}
	
}
