package other.scenario.util;

import other.scenario.entity.PictureIdentifyWorkPO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageRecognition {
	public static void  imageRecognition(File file) throws AWTException {
//		屏幕截图
		BufferedImage Window=Screenshot.screenshot ();
//		图片
		List<int[][]> ImagesData=imageToDate(file);
//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY=FindAllImgData(Window,ImagesData);
//		鼠标点击
	    MouseClick.mouseClicks (mouseXY);
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
							int biggerX = 0;
							int biggerY = 0;
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
								
								System.out.println ("在屏幕上找到图片了");
								System.out.println ("坐标:( " + x + " , " + y + " )");
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

	
	
	
	
	public static List<int[][]> imageToDate(File file){
		return  getImagesGRB(readFiles (file));
	}
	
	
	public static List<BufferedImage> readFiles(File file) {
		java.util.List<BufferedImage> files = new ArrayList<>();
		String[] strArray = file.getName().split("\\.");
		int suffixIndex = strArray.length - 1;
		// 存储照片文件
		if (!file.isDirectory()
		    && (strArray[suffixIndex].equals("png") || strArray[suffixIndex].equals("jpg"))) {
			BufferedImage img = null;
			try {
				img = ImageIO.read (file);
			} catch (IOException e) {
				e.printStackTrace ();
			}
			files.add(img);
		}
		return files;
		
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
