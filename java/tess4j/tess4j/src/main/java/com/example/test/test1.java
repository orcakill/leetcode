package com.example.test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName test1.java
 * @Description 测试tess4j
 * @createTime 2022年11月04日 09:33:00
 */
public class test1 {
	public static void main (String[] args) throws IOException {
		// 创建实例
		ITesseract instance = new Tesseract ();
		
		// 设置识别语言
		
		instance.setLanguage ("chi_sim");
		
		// 设置识别引擎
		
		instance.setOcrEngineMode (4);
		
		// 读取文件
		File file=new File ("D:/a.jpg");
		BufferedImage image = ImageIO.read (file);
		image=img_gray(image);
		try {
			
			// 识别
			
			String result = instance.doOCR (image);
			System.out.println (result);
		} catch (TesseractException e) {
			System.err.println (e.getMessage ());
		}
		
	}
	
	public static BufferedImage img_gray(BufferedImage imgsrc) {
		try {
			//创建一个灰度模式的图片
			BufferedImage back=new BufferedImage(imgsrc.getWidth(), imgsrc.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
			int width = imgsrc.getWidth();
			int height = imgsrc.getHeight();
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					back.setRGB(i,j,imgsrc.getRGB(i, j));
				}
			}
			return back;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
