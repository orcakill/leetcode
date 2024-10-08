package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ImagesBackRec
 * @Description ImagesBackRec
 * @Date 2023/1/26 2:15
 * @Created by orcakill
 */
@Log4j2
public class ImagesBackRecUtils {
	
	//识别图片存在并点击或只识别不点击
	public static boolean imagesRecognition (List<PictureCollectionPO> pictureCollectionPOList, String process,
	                                         boolean isClick) throws AWTException {
		//窗口句柄
		WinDef.HWND hwnd = User32.INSTANCE.FindWindow (null, process);
		//显示器縮放比例
		Double bl = ComputerScalingUtils.getScale ();
		//屏幕截图
		BufferedImage Window = ScreenshotUtils.screenshotBack (process);
		//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY = FindAllImgData (Window, pictureCollectionPOList);
		//		识别+鼠标点击或仅识别
		return MouseClickUtils.mouseClickBack (mouseXY, hwnd, bl, isClick);
	}
	
	//返回坐标
	public static PictureIdentifyWorkPO imagesRecognitionMouse (List<PictureCollectionPO> pictureCollectionPOList,
	                                                            String process) {
		//		屏幕截图
		BufferedImage Window = ScreenshotUtils.screenshotBack (process);
		//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY = FindAllImgData (Window, pictureCollectionPOList);
		//		鼠标点击
		PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
		if (mouseXY.size () > 0) {
			if (mouseXY.get (0).getX () > 0 && mouseXY.get (0).getY () > 0) {
				pictureIdentifyWorkPO = mouseXY.get (0);
			}
		}
		return pictureIdentifyWorkPO;
	}
	
	/**
	 * 本方法会根据图片数据从屏幕里找寻相同的图片信息,找到后会返回其对应的坐标集合
	 * @param Window                  - 屏幕图像
	 * @param pictureCollectionPOList - 指定图片数据集合
	 * @return - 返回图片在屏幕的坐标集合
	 */
	public static List<PictureIdentifyWorkPO> FindAllImgData (BufferedImage Window,
	                                                          List<PictureCollectionPO> pictureCollectionPOList) {
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
		PictureIdentifyWorkPO mouseXY = new PictureIdentifyWorkPO ();
		// 解析屏幕图片数据
		int width = Window.getWidth ();
		int height = Window.getHeight ();
		int[][] WindowData = new int[width][height];
		
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				WindowData[w][h] = Window.getRGB (w, h);
			}
		}
		int[][] imagesDatum;
		// 将屏幕与全部目标图片对比
		try {
			for (PictureCollectionPO pictureCollectionPO : pictureCollectionPOList) {
				imagesDatum = pictureCollectionPO.getTwoArray ();
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
									
									log.info ("在屏幕上找到图片了,坐标:({},{}),{}", x, y,
									          pictureCollectionPO.getImageName ());
									// 这是专门存储数据的类
									x += (int) (Math.random () * 0.1 * imgWidth);
									y += (int) (Math.random () * 0.1 * imgHeight);
									mouseXY.setX (x);
									mouseXY.setY (y);
									mouseMessages.add (mouseXY);
									if (mouseMessages.size () > 1) {
										return mouseMessages;
									}
									break a;
								}
								
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {
			log.info (e);
		}
		// 返回所有图片对应窗口坐标
		return mouseMessages;
		
	}
	
	// 对比方法
	public static boolean equalsRGB (int RGB1, int RGB2) {
		
		int R1 = (RGB1 & 0xff0000) >> 16;
		int G1 = (RGB1 & 0xff00) >> 8;
		int B1 = (RGB1 & 0xff);
		
		int R2 = (RGB2 & 0xff0000) >> 16;
		int G2 = (RGB2 & 0xff00) >> 8;
		int B2 = (RGB2 & 0xff);
		
		return Math.abs (R1 - R2) < 5 && Math.abs (G1 - G2) < 5 && Math.abs (B1 - B2) < 5;
	}
}
