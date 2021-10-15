package other.scenario.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.File;

public class ImageServiceTest {

	
	@Test
	public void imageClickIsEmpty () throws InterruptedException, AWTException {
		File file=new File (System.getProperty("user.dir") + "/src/main/resources/image/"+"scenario/适龄提示.png");
		if(file.exists ()){
			boolean b=ImageService.imageClickIsEmpty (file);
			System.out.println (b);
		}
		else{
			System.out.println ("图片文件路径不存在");
		}

	}
	
	@Test
	public void   imagesClickIsEmpty () throws InterruptedException, AWTException {
		String folderName="scenario/网易-相伴相随";
		File file = new File (
				System.getProperty ("user.dir") + "/java/src/main/resources/image/" + folderName);
		if(file.exists ()){
			boolean b=ImageService.imagesClickIsEmpty (folderName);
			System.out.println (b);
		}
		else{
			System.out.println ("图片文件路径不存在");
		}
	}
}