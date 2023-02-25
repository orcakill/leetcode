package com.example.demo.utils;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageDealUtilsTest.java
 * @Description TODO
 * @createTime 2023年02月25日 11:25:00
 */
@Log4j2
class ImageDealUtilsTest {
	
	@Test
	void dealMat () {
		Mat mat=new Mat();
		Mat mat1;
		mat1=ImageDealUtils.dealMat (mat);
		log.info(mat1);
	}
}