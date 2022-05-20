package other.scenario.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.util.MouseClick;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageServiceTest {

	
	@Test
	public void imageClickIsEmpty () throws InterruptedException, AWTException {
		File file=new File (System.getProperty("user.dir") + "/src/main/resources/image/"+"scenario/御魂.png");
		if(file.exists ()){
			boolean b=ImageService.imageClickIsEmpty (file);
			System.out.println (b);
		}
		else{
			System.out.println ("图片文件路径不存在");
		}

	}
	
	@Test
	public void   imagesClickIsEmpty () throws Exception {
		String folderName="scenario/探索/地面点击";
		File file = new File (
				System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName);
		if(file.exists ()){
			boolean b=ImageService.imagesClickBackIsEmpty (folderName,3,true);
			System.out.println (b);
		}
		else{
			System.out.println ("图片文件路径不存在");
		}
	}
	
	@Test
	public void   imagesClick () throws InterruptedException, AWTException {
		for(int i=0;i<800;i++){
			String folderName="scenario/御魂/退出挑战";
			File file= new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName);
			if(file.exists ()){
				ImageService.imagesClickBack (folderName);
			}
			else{
				System.out.println ("图片文件路径不存在");
			}
		}
	}
	
	@Test
	public void   imagesClick1 () throws InterruptedException, AWTException {
		for(int i=0;i<800;i++){
			String folderName="scenario/首页/再次召唤";
			File file= new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName);
			if(file.exists ()){
				ImageService.imagesClickBack (folderName);
			}
			else{
				System.out.println ("图片文件路径不存在");
			}
		}
	}
	
	@Test
	public void   imagesClick2 () throws InterruptedException, AWTException {
		for(int i=0;i<800;i++){
			String folderName="scenario/返回";
			File file= new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName);
			if(file.exists ()){
				ImageService.imagesClickBack (folderName);
			}
			else{
				System.out.println ("图片文件路径不存在");
			}
		}
	}
	
	@Test
	public void   imagesClick3 () throws InterruptedException, AWTException {
		for(int i=0;i<800;i++){
			String folderName="scenario/首页/结界卡合成/继续添加";
			File file= new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName);
			if(file.exists ()){
				ImageService.imagesClickBack (folderName);
			}
			else{
				System.out.println ("图片文件路径不存在");
			}
			Thread.sleep (1000);
			String folderName1="scenario/首页/结界卡合成/开始合成";
			File file1= new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName1);
			if(file1.exists ()){
				ImageService.imagesClickBack (folderName1);
			}
			else{
				System.out.println ("图片文件路径不存在");
			}
		}
	}
	
	@Test
	public void   imagesClickWheel() throws InterruptedException, AWTException {
		Thread.sleep (3*1000);
		System.out.println ("准备开始");
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList = new ArrayList<> ();
		PictureIdentifyWorkPO pictureIdentifyWorkPO= new PictureIdentifyWorkPO ();
		pictureIdentifyWorkPO.setX (2126);
		pictureIdentifyWorkPO.setY (578);
		pictureIdentifyWorkPOList.add (pictureIdentifyWorkPO);
		for(int i=0;i<30;i++){
			MouseClick.mouseRoll (pictureIdentifyWorkPOList,20,-10);
		}
		System.out.println ("结束");
	}
}