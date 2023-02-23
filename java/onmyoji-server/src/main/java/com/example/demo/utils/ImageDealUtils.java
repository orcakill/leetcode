package com.example.demo.utils;

import java.awt.image.BufferedImage;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageDealUtils.java
 * @Description 图像处理
 * @createTime 2023年02月23日 14:46:00
 */
public class ImageDealUtils {
	public static BufferedImage grayImage(BufferedImage bufferedImage) {
		
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		BufferedImage grayBufferedImage = new BufferedImage (width, height, bufferedImage.getType ());
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				final int color = bufferedImage.getRGB(i, j);
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;
				int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayBufferedImage.setRGB(i, j, newPixel);
			}
		}
		return grayBufferedImage;
	}
	public  static int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;
		
		return newPixel;
		
	}
}
