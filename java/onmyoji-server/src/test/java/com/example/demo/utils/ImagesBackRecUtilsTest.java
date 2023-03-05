package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.map.FolderPathMap;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.var.CommVar.house_YYLTB;
import static com.example.demo.utils.ImagesBackRecUtils.equalsRGB;

/**
 * @Classname ImagesBackRecUtilsTest
 * @Description ImagesBackRecUtilsTest
 * @Date 2023/3/5 1:33
 * @Created by orcakill
 */
@Log4j2
@SpringBootTest
class ImagesBackRecUtilsTest {
	
	@Test
	void imagesRecognition () throws AWTException {
		long startTime = System.currentTimeMillis ();
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		String path = FolderPathMap.folderPath ("图片总路径");
		List<PictureCollectionPO> pictureCollectionPOList =
				ReadFileUtils.readPictureCollectionPOList (path, house_YYLTB, "RGB");
		ImagesBackRecUtils.imagesRecognition (pictureCollectionPOList,"夜神模拟器",true);
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void imagesRecognitionOne () throws AWTException {
		
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		String path = FolderPathMap.folderPath ("图片总路径");
		String process="夜神模拟器";
		List<PictureCollectionPO> pictureCollectionPOList =
				ReadFileUtils.readPictureCollectionPOList (path, house_YYLTB, "RGB");
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
		PictureIdentifyWorkPO mouseXY = new PictureIdentifyWorkPO ();
		int[][] imagesDatum;
		WinDef.HWND hwnd = User32.INSTANCE.FindWindow (null, process);

		StringBuilder X1;
		StringBuilder Y1;
		StringBuilder X2;
		StringBuilder Y2;
		int moveTime = (int) (Math.random () * 10 + 10);
		
		long startTime = System.currentTimeMillis ();
		//		屏幕截图
		BufferedImage Window = ScreenshotUtils.screenshotBack (process);
		// 解析屏幕图片数据
		int width = Window.getWidth ();
		int height = Window.getHeight ();
		int[][] WindowData = new int[width][height];
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				WindowData[w][h] = Window.getRGB (w, h);
			}
		}

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
										break a;
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
		PictureIdentifyWorkPO mouseMessages1=mouseMessages.get (0);
		PictureIdentifyWorkPO mouseMessages2=mouseMessages.get (0);
		Double bl = ComputerScalingUtils.getScale ();
		//int mousePressTime = (int) (Math.random () * 500 + 100);
		// 解析鼠标坐标参数,低位为X轴,高位为Y轴坐标
		X1 = new StringBuilder (Integer.toHexString ((int) (mouseMessages1.getX () / bl)));
		Y1 = new StringBuilder (Integer.toHexString ((int) (mouseMessages1.getY () / bl)));
		
		X2 = new StringBuilder (Integer.toHexString ((int) (mouseMessages2.getX () / bl)));
		Y2 = new StringBuilder (Integer.toHexString ((int) (mouseMessages2.getY () / bl)));
		
		while (X1.length () < 4) {
			X1.insert (0, "0");
		}
		while (Y1.length () < 4) {
			Y1.insert (0, "0");
		}
		
		while (X2.length () < 4) {
			X2.insert (0, "0");
		}
		while (Y2.length () < 4) {
			Y2.insert (0, "0");
		}
		
		Integer in1 = Integer.valueOf (Y1 + X1.toString (), 16);
		Integer in2 = Integer.valueOf (Y2 + X2.toString (), 16);
		
		WinDef.LPARAM lPARAM1 = new WinDef.LPARAM (in1);
		WinDef.LPARAM lPARAM2 = new WinDef.LPARAM (in2);
		try {
			// 模拟计算鼠标按下的间隔并且按下鼠标
			Thread.sleep (moveTime);
			ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 513, new WinDef.WPARAM (513), lPARAM1);
			Thread.sleep (moveTime);
			ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 514, new WinDef.WPARAM (514), lPARAM2);
			Thread.sleep (moveTime);
		} catch (InterruptedException e) {
			
			e.printStackTrace ();
			
		}
		
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
}