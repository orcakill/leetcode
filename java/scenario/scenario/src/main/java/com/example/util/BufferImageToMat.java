package com.example.util;

import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * @Classname BufferImageToMat
 * @Description TODO
 * @Date 2022/11/6 3:10
 * @Created by orcakill
 */
public class BufferImageToMat {
	/**
	 * BufferedImage转换成Mat
	 *
	 * @param original 要转换的BufferedImage
	 * @param imgType  bufferedImage的类型 如 BufferedImage.TYPE_3BYTE_BGR
	 * @param matType  转换成mat的type 如 CvType.CV_8UC3
	 */
	public static Mat bufImg2Mat(BufferedImage original, int imgType, int matType) {
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		if (original.getType() != imgType) {
			BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), imgType);
			Graphics2D g = image.createGraphics();
			try {
				g.setComposite(AlphaComposite.Src);
				g.drawImage(original, 0, 0, null);
				original = image;
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				g.dispose();
			}
		}
		byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
		try {
			Mat mat = Mat.eye(original.getHeight(), original.getWidth(), matType);
			mat.put(0, 0, pixels);
			return mat;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

