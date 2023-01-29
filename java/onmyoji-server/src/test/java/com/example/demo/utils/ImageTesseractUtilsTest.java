package com.example.demo.utils;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * @Classname ImageTesseractTest
 * @Description ImageTesseractTest
 * @Date 2023/1/28 2:37
 * @Created by orcakill
 */

@Log4j2
class ImageTesseractUtilsTest {

	@Test
	void findOCR () throws IOException {
		File file=new File ("D:/a.jpg");
		BufferedImage bufferedImage= ImageIO.read (file);
		String s= ImageTesseractUtils.findOCR (bufferedImage, true);
		log.info (s);
	}
}