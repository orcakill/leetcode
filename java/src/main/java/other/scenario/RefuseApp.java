package other.scenario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.service.ImageService;

import java.awt.*;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName RefuseApp.java
 * @Description TODO
 * @createTime 2021年10月28日 12:40:00
 */
public class RefuseApp {
	//	记录日志
	public static final Logger logger = LogManager.getLogger (RefuseApp.class);
	
	public static void main (String[] args) throws Exception {
		refuse();
	}
	
	public static   void  refuse() throws InterruptedException, AWTException {
		for(int i=0;i<=120;i++){
			String file1 = "scenario/temp/御魂/拒接协战";
			ImageService.imagesClickBackNumber (file1,30);
			
		}
	}
}
