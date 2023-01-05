package com.example.service;

import com.example.util.ImagesOpenCVSIFT;
import org.junit.Test;
import org.opencv.core.Core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName OpenCVSIFTTest.java
 * @Description OpenCVSIFTTest
 * @createTime 2023年01月05日 08:46:00
 */
public class OpenCVSIFTTest {
	@Test
	public void  sift1() throws IOException {
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		String str_src="D:/a.png";
		String str_tem="D:/b.png";
		File file_src = new File (str_src);
		File file_tem = new File (str_tem);
		BufferedImage bufferedImageSrc = ImageIO.read (file_src);
		BufferedImage bufferedImageTem = ImageIO.read (file_tem);
		ImagesOpenCVSIFT.matchImage (bufferedImageSrc,bufferedImageTem,0.7,true);
	}
	
}
