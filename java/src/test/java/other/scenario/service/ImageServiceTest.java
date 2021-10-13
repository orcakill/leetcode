package other.scenario.service;

import org.junit.Test;
import other.scenario.util.ImageRecognition;
import other.scenario.util.ImagesRecognition;

import java.awt.*;
import java.io.File;

import static org.junit.Assert.*;

public class ImageServiceTest {
	
	@Test
	public void imageClick () throws InterruptedException, AWTException {
		File file=new File (System.getProperty("user.dir") + "/src/main/resources/image/"+"scenario/适龄提示.png");
		for(int i=0;i<10;i++) {
			Thread.sleep (5000);
			System.out.println ("第"+(i+1)+"轮测试");
			ImageRecognition.imageRecognitionIsEmpty (file);
		}
	}
	
	@Test
	public void imagesClick () throws InterruptedException, AWTException {
		String folderName="scenario/首页";
		for(int i=0;i<10;i++){
			Thread.sleep (5000);
			System.out.println ("第"+(i+1)+"轮测试");
			boolean b=ImagesRecognition.imagesRecognitionIsEmpty (folderName);
			System.out.println (b);
		}

	}
}