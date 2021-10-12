package other.scenario.service;

import org.junit.Test;

import java.awt.*;
import java.io.File;

import static org.junit.Assert.*;

public class ImageServiceTest {
	
	@Test
	public void imageClick () throws InterruptedException, AWTException {
		File file=new File ("");
		for(int i=0;i<10;i++) {
			System.out.println ("第"+i+1+"轮测试");
			ImageService.imageClick (file);
		}
	}
	
	@Test
	public void imagesClick () throws InterruptedException, AWTException {
		String folderName="";
		for(int i=0;i<10;i++){
			System.out.println ("第"+i+1+"轮测试");
			ImageService.imagesClick (folderName);
		}

	}
}