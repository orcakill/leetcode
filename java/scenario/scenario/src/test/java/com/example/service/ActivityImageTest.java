package com.example.service;

import com.example.model.entity.PictureIdentifyWorkPO;
import com.example.util.ImagesBackRec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @Classname ActivityImageTest
 * @Description TODO
 * @Date 2022/11/1 21:25
 * @Created by orcakill
 */
public class ActivityImageTest {
	public static final Logger logger = LogManager.getLogger ("ActivityImageTest");
	
	
    //java RGB匹配法
	@Test
	public void testImagesClick1 () throws IOException {
		File file=new File (System.getProperty ("user.dir") + "/src/main/resources/image/"+"scenario/测试图片/动态图片测试/探索/source/a.jpg");
		String folderName="scenario/测试图片/动态图片测试/探索/search";
		//		屏幕截图
		BufferedImage window= ImageIO.read (file);
		//		图片
		List<int[][]> ImagesData = ImagesBackRec.imageToDate (folderName);
		//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY = ImagesBackRec.FindAllImgData (window, ImagesData);
		String test= String.valueOf (1);
	}
	//opencv测试是否可正常使用
	@Test
	public void openCVTest () throws IOException {
		URL url = ClassLoader.getSystemResource ("lib/opencv_java460.dll");
		System.load(url.getPath());
		//        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		Mat mat = Mat.eye (4,4, CvType.CV_8UC1);
		System.out.println(mat.dump());
	}
	
	//opencv,特征匹配
	@Test
	public void openCVImageTest () throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String file_src=System.getProperty ("user.dir") + "/src/main/resources/image/"+"scenario/english/exp/source/a.jpg";
		String file_tem=System.getProperty ("user.dir") + "/src/main/resources/image/"+"scenario/english/exp/search/b.png";
		Mat g_src= Imgcodecs.imread (file_src);
		Mat g_tem= Imgcodecs.imread (file_tem);
		int result_rows = g_src.rows() - g_tem.rows() + 1;
		int result_cols = g_src.cols() - g_tem.cols() + 1;
		Mat g_result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
		Imgproc.matchTemplate (g_src, g_tem, g_result, Imgproc.TM_CCORR_NORMED); // 归一化平方差匹配法
		// Imgproc.matchTemplate(g_src, g_tem, g_result,
		// Imgproc.TM_CCOEFF_NORMED); // 归一化相关系数匹配法
		
		// Imgproc.matchTemplate(g_src, g_tem, g_result, Imgproc.TM_CCOEFF);
		// //
		// 相关系数匹配法：1表示完美的匹配；-1表示最差的匹配。
		
		// Imgproc.matchTemplate(g_src, g_tem, g_result, Imgproc.TM_CCORR); //
		// 相关匹配法
		
		// Imgproc.matchTemplate(g_src, g_tem, g_result,Imgproc.TM_SQDIFF); //
		// 平方差匹配法：该方法采用平方差来进行匹配；最好的匹配值为0；匹配越差，匹配值越大。
		
		// Imgproc.matchTemplate(g_src, g_tem,g_result,Imgproc.TM_CCORR_NORMED);
		// // 归一化相关匹配法
		Core.normalize(g_result, g_result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
		Point matchLocation = new Point();
		Core.MinMaxLocResult mmlr = Core.minMaxLoc (g_result);
		
		matchLocation = mmlr.maxLoc; // 此处使用maxLoc还是minLoc取决于使用的匹配算法
		Imgproc.rectangle (g_src, matchLocation,
				new Point(matchLocation.x + g_tem.cols(), matchLocation.y + g_tem.rows()),
				new Scalar (0, 0, 0, 0));
		logger.info ("x："+g_tem.cols()+"y:"+g_tem.rows());
		logger.info ("x："+matchLocation.x+"y:"+matchLocation.y);
		logger.info ("x："+(matchLocation.x+g_tem.cols()/2)+"y:"+(matchLocation.y+g_tem.rows()/2) );
		//Imgcodecs.imwrite("D:\\match.jpg", g_src);
	}
	
}
