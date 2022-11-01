package other.scenario.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.service.impl.FightAutoServiceImpl;
import other.scenario.util.ComputerScaling;
import other.scenario.util.ImagesBackRec;
import other.scenario.util.MouseClick;
import other.scenario.util.Screenshot;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImageServiceTest {
	public static final Logger logger = LogManager.getLogger (ImageServiceTest.class);
	
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
	public void test(){
			UUID uuid = UUID.randomUUID ();
			System.out.println (uuid.toString ().replaceAll ("-","").toUpperCase ());
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
	public void   imagesClick4() throws InterruptedException, AWTException {
		for(int i=0;i<800;i++){
			String folderName="scenario/测试图片";
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
	
	
	//获取鼠标位置和图片的坐标系数
	@Test
	public void   imagesClick5() throws InterruptedException, AWTException {
		Double bl = ComputerScaling.getScale ();
		Thread.sleep (5000);
		for(int i=0;i<10;i++){
			String folderName="scenario/首页/首页勾玉";
			File file= new File (
					System.getProperty ("user.dir") + "/src/main/resources/image/" + folderName);
			if(file.exists ()){
				PictureIdentifyWorkPO pictureIdentifyWorkPO1 = ImagesBackRec.imagesRecognitionMouse (folderName);
				logger.info ("初始图片x坐标："+pictureIdentifyWorkPO1.getX ()+"    初始y坐标"+pictureIdentifyWorkPO1.getY ());
				Point point=MouseInfo.getPointerInfo().getLocation();
				logger.info ("当前x坐标："+point.getX ()*bl+"    当前y坐标"+point.getY ()*bl);
				double x=point.getX ()*bl/pictureIdentifyWorkPO1.getX ();
				double y=point.getY ()*bl/pictureIdentifyWorkPO1.getY ();
				logger.info ("当前x系数："+x+"    当前y系数"+y);
			}
			else{
				System.out.println ("图片文件路径不存在");
			}
		}
	}
	//截全屏图
	@Test
	public void   screenshotBack() throws InterruptedException, AWTException {
		Screenshot.screenshotBack ();
	}
}