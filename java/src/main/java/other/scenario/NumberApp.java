package other.scenario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.service.ImageService;

import java.awt.*;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName NumberApp.java
 * @Description 统计材料的次数
 * @createTime 2021年11月06日 11:32:00
 */
public class NumberApp {
	//	记录日志
	public static final Logger logger = LogManager.getLogger (NumberApp.class);
	
	public static void main (String[] args) throws Exception {
		number ();
	}
	
	public static   void  number() throws InterruptedException, AWTException {
		int n=0;
		for(int i=0;i<=150;i++){
			String file1 = "scenario/temp/御魂/活动材料";
			boolean b=ImageService.imagesClickBackIsEmpty (file1);
			if(b){
				n++;
				System.out.println (n);
			}
			
		}
	}
}
