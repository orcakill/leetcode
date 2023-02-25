package com.example.demo.utils;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageDealUtils.java
 * @Description 图像处理
 * @createTime 2023年02月23日 14:46:00
 */
public class ImageDealUtils {
	public static Mat dealMat (Mat mat) {
		//图像灰度化
		Imgproc.cvtColor (mat,mat, Imgproc.COLOR_BGR2GRAY);
		//图像二值化
		Imgproc.adaptiveThreshold(mat,mat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,Imgproc.THRESH_BINARY_INV, 25, 10);
		return  mat;
	}
}
