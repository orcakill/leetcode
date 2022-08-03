package com.example.util;

import com.example.model.entity.PictureIdentifyWorkPO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.entity.PictureIdentifyWorkPO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImagesRecognition {
	private static final Logger logger = LogManager.getLogger (ImagesRecognition.class);
	
	public static void  imagesRecognition (String FolderName) throws AWTException {
//		屏幕截图
		BufferedImage Window=Screenshot.screenshot ();
//		图片
		List<int[][]> ImagesData=imageToDate(FolderName);
//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY=FindAllImgData (Window,ImagesData);
//		鼠标点击
		List<PictureIdentifyWorkPO> mouseXY1=new ArrayList<> ();
//		鼠标点击
		if(mouseXY.size ()>0){
			int num=RandomUtil.randomMinute (mouseXY.size ());
			mouseXY1.add (mouseXY.get (num));
			MouseClick.mouseClicks (mouseXY1);
		}
		else{
			MouseClick.mouseClicks (mouseXY);
		}
	}
	
	
	
	public static boolean imagesRecognitionIsEmpty(String FolderName) throws AWTException {
//		屏幕截图
		BufferedImage Window=Screenshot.screenshot ();
//		图片
		List<int[][]> ImagesData=imageToDate(FolderName);
//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY=FindAllImgData(Window,ImagesData);
//		鼠标点击
		return  mouseXY.size ()>0;
	}
	
	public static PictureIdentifyWorkPO imagesRecognitionMouse(String FolderName) throws AWTException {
//		屏幕截图
		BufferedImage Window=Screenshot.screenshot ();
//		图片
		List<int[][]> ImagesData=imageToDate(FolderName);
//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY=FindAllImgData(Window,ImagesData);
//		鼠标点击
		PictureIdentifyWorkPO pictureIdentifyWorkPO=new PictureIdentifyWorkPO ();
		if(mouseXY.size ()>0){
			pictureIdentifyWorkPO=mouseXY.get (0);
		}
		return pictureIdentifyWorkPO;
	}
	
	/**
	 * 本方法会根据图片数据从屏幕里找寻相同的图片信息,找到后会返回其对应的坐标集合
	 *
	 * @param Window - 屏幕图像
	 * @param ImagesData - 指定图片数据集合
	 *
	 * @return - 返回图片在屏幕的坐标集合
	 */
	public static List<PictureIdentifyWorkPO> FindAllImgData(BufferedImage Window, List<int[][]> ImagesData) {
		
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<>();
		// 解析屏幕图片数据
		int width = Window.getWidth();
		int height = Window.getHeight();
		int[][] WindowData = new int[width][height];
		
		for (int w = 0; w < width; w++)
			for (int h = 0; h < height; h++) {
				WindowData[w][h] = Window.getRGB(w, h);
			}
		
		// 将屏幕与全部目标图片对比
		for (int[][] imagesDatum : ImagesData) {
			
			// 获取图片尺寸
			int imgWidth = imagesDatum.length;
			int imgHeight = imagesDatum[0].length;

a:
			{
				for (int x = 0; x < width - imgWidth; x++) {
					for (int y = 0; y < height - imgHeight; y++) {
						// 判断图片的四个顶点的RGB是否相等
						if (equalsRGB (imagesDatum[0][0], WindowData[x][y])
						    && equalsRGB (imagesDatum[imgWidth - 1][0], WindowData[x + imgWidth - 1][y])
						    && equalsRGB (imagesDatum[imgWidth - 1][imgHeight - 1],
								WindowData[x + imgWidth - 1][y + imgHeight - 1])
						    && equalsRGB (imagesDatum[0][imgHeight - 1], WindowData[x][y + imgHeight - 1])) {
							// 如果相等,进行二次匹配确认
							int biggerX;
							int biggerY;
							boolean flag = true;
b:
							{
								for (int smallerX = 0; smallerX < imgWidth; smallerX++) {
									biggerX = x + smallerX;
									for (int smallerY = 0; smallerY < imgHeight; smallerY++) {
										biggerY = y + smallerY;
										// 如果对应点数据不同为假
										if (!equalsRGB (imagesDatum[smallerX][smallerY],
												WindowData[biggerX][biggerY])) {
											
											flag = false;
											break b;
											
										}
									}
								}
							}
							if (flag) {
								
								logger.info ("在屏幕上找到图片了,坐标:( " + x + " , " + y + " )");
								// 这是专门存储数据的类
								PictureIdentifyWorkPO mouseXY = new PictureIdentifyWorkPO ();
								x += (int) (Math.random () * imgWidth);
								y += (int) (Math.random () * imgHeight);
								mouseXY.setX (x);
								mouseXY.setY (y);
								mouseMessages.add (mouseXY);
								break a;
								
							}
							
						}
					}
				}
			}
		}
		// 返回所有图片对应窗口坐标
		return mouseMessages;
		
	}
	// 对比方法
	public static boolean equalsRGB(int RGB1, int RGB2) {
		
		int R1 = (RGB1 & 0xff0000) >> 16;
		int G1 = (RGB1 & 0xff00) >> 8;
		int B1 = (RGB1 & 0xff);
		
		int R2 = (RGB2 & 0xff0000) >> 16;
		int G2 = (RGB2 & 0xff00) >> 8;
		int B2 = (RGB2 & 0xff);
		
		return Math.abs (R1 - R2) < 5 && Math.abs (G1 - G2) < 5 && Math.abs (B1 - B2) < 5;
	}
	
	
	
	
	
	public static List<int[][]> imageToDate(String FolderName){
		return getImagesGRB (Objects.requireNonNull (readFiles (FolderName)));
	}
	
	/**
	 * 本方法根据文件夹名从当前项目中找文件夹并且返回文件夹所有照片文件
	 *
	 * @param FolderName    - 指定的文件夹名字
	 *
	 * @return - 返回指定文件夹内的所有照片文件
	 */
	public static List<BufferedImage> readFiles(String FolderName) {
		
		try {
			// 获取当前目录下的指定文件夹
			File Folder = new File(
					System.getProperty("user.dir") + "/java/src/main/resources/image/"+ FolderName);
			if(!Folder.isDirectory()){
				Folder = new File(
						System.getProperty("user.dir") + "/src/main/resources/image/"+ FolderName);
			}			// 遍历文件夹的所有文件
			if (Folder.isDirectory()) {
				List<BufferedImage> files = new ArrayList<>();
				String[] filelist = Folder.list();
				// 将所有照片存储并且返回
				assert filelist != null;
				for (String s : filelist) {
					File file = new File (Folder + File.separator + s);
					// 判断是否为照片文件
					String[] strArray = file.getName ()
					                        .split ("\\.");
					int suffixIndex = strArray.length - 1;
					// 存储照片文件
					if (!file.isDirectory ()
					    && (strArray[suffixIndex].equals ("png") || strArray[suffixIndex].equals ("jpg"))) {
						BufferedImage img = ImageIO.read (file);
						files.add (img);
					}
					
				}
				return files;
			}
		} catch (Exception e) {
			
			e.getStackTrace();
			
		}
		
		return null;
		
	}
	
	
	
	/**
	 * 本方法将图片集合转化为图片数据集合
	 *
	 * @param imgs - 指定图片集合
	 *
	 * @return - 指定图片数据集合
	 */
	private static List<int[][]> getImagesGRB (List<BufferedImage> imgs) {
		
		List<int[][]> ImagesData = new ArrayList<> ();
		
		for (BufferedImage bufferedImage : imgs) {
			
			int width = bufferedImage.getWidth ();
			int height = bufferedImage.getHeight ();
			int[][] img = new int[width][height];
			
			for (int w = 0; w < width; w++)
				for (int h = 0; h < height; h++) {
					img[w][h] = bufferedImage.getRGB (w, h);
				}
			
			ImagesData.add (img);
		}
		
		return ImagesData;
		
	}
}
