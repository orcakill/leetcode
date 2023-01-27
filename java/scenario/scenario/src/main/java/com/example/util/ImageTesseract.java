package com.example.util;

import net.sourceforge.tess4j.Tesseract;

import java.awt.image.BufferedImage;

/**
 * @Classname ImageTesseract
 * @Description Tesseract 图像文字识别
 * @Date 2023/1/7 21:32
 * @Created by orcakill
 */
public class ImageTesseract {
	public static String findOCR (BufferedImage textImage, boolean ZH_CN) {
		try {
			Tesseract instance = new Tesseract ();
			instance.setDatapath ("D:\\software\\tesseract-ocr\\tessdata");//设置训练库
			instance.setTessVariable ("user_defined_dpi", "300");
			if (ZH_CN) {
				instance.setLanguage ("chi_sim");//中文识别
			}
			String result;
			result = instance.doOCR (textImage);
			return result;
		} catch (Exception e) {
			e.printStackTrace ();
			return "发生未知错误";
		}
	}
}
