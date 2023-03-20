package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.map.FolderPathMap;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.example.demo.model.var.CommVar.explore_DTXGZD;
import static com.example.demo.utils.ImagesOpenCVSIFTUtils.getMat;
import static com.example.demo.utils.ImagesOpenCVSIFTUtils.goodMatchList;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageFastUtilsTest.java
 * @Description 极速识别点击测试
 * @createTime 2023年03月02日 09:40:00
 */
@SpringBootTest
@Log4j2
public class ImageFastUtilsTest {
	
	
	/***
	 * @description: 测试算法 多次执行速度
	 * @return: void
	 * @author: orcakill
	 * @date: 2023/2/23 15:25
	 */
	@Test
	void findAllImgDataOpenCvAllS () throws IOException, AWTException {
		long startTime = System.currentTimeMillis ();
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		log.info ("测试开始");
		int num = 10;
		for (int i = 1; i < num; i++) {
			test1 ();
		}
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
		log.info ("平均用时{}毫秒", (System.currentTimeMillis () - startTime) / 10);
	}
	
	@Test
	void test1 () throws IOException, AWTException {
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		//图片集
		String path = FolderPathMap.folderPath ("图片总路径");
		List<PictureCollectionPO> pictureCollectionPOList =
				ReadFileUtils.readPictureCollectionPOList (path, explore_DTXGZD, "SIFT");
		//声明 坐标列表
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
		//声明 识别坐标
		PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
		//特征匹配
		Mat resT = new Mat ();
		Mat resO = new Mat ();
		int matchesPointCount;
		//即当detector 又当Detector
		//SIFT sift = SIFT.create ();
		// C++: static Ptr_ORB cv::ORB::create(int nfeatures = 500, float scaleFactor = 1.2f, int nlevels = 8,
		// int edgeThreshold = 31, int firstLevel = 0, int WTA_K = 2, ORB_ScoreType scoreType = ORB::HARRIS_SCORE,
		// int patchSize = 31, int fastThreshold = 20)
		ORB sift = ORB.create (800, 1.2f, 8, 31, 0, 2, ORB.FAST_SCORE, 3, 10);
		//ORB sift = ORB.create ();
		Mat templateImage;
		MatOfKeyPoint templateKeyPoints = new MatOfKeyPoint ();
		MatOfKeyPoint originalKeyPoints = new MatOfKeyPoint ();
		WinDef.HWND hwnd = User32.INSTANCE.FindWindow (null, "谷歌浏览器");
		Double bl = ComputerScalingUtils.getScale ();
		StringBuilder X;
		StringBuilder Y;
		log.info ("开始计时");
		long startTime = System.currentTimeMillis ();
		//来源图片
		//BufferedImage Window = ScreenshotUtils.screenshotBack ("夜神模拟器");
		String str_tem = "D:/a.jpg";
		File file_tem = new File (str_tem);
		BufferedImage Window = ImageIO.read (file_tem);
		log.info (System.currentTimeMillis () - startTime);
		log.info ("图像识别");
		Mat originalImage = getMat (Window);
		//Imgcodecs.imwrite ("D:\\a.jpg", originalImage);
		log.info (System.currentTimeMillis () - startTime);
		sift.detect (originalImage, originalKeyPoints);
		sift.compute (originalImage, originalKeyPoints, resO);
		Mat mat1 = new Mat ();
		Features2d.drawKeypoints (originalImage, originalKeyPoints, mat1, new Scalar (0, 0, 255),
		                          Features2d.DrawMatchesFlags_DRAW_RICH_KEYPOINTS);
		Imgcodecs.imwrite ("D:\\mat1.jpg", mat1);
		log.info (System.currentTimeMillis () - startTime);
		for (PictureCollectionPO imagesDatum : pictureCollectionPOList) {
			List<MatOfDMatch> matches = new LinkedList<> ();
			LinkedList<DMatch> goodMatchesList = new LinkedList<> ();
			templateImage = getMat (imagesDatum.getImage ());
			//获取模板图的特征点
			sift.detect (templateImage, templateKeyPoints);
			sift.compute (templateImage, templateKeyPoints, resT);
			DescriptorMatcher descriptorMatcher = DescriptorMatcher.create (DescriptorMatcher.FLANNBASED);
			resO.convertTo (resO, CvType.CV_32F, 1 / 255.0);
			resT.convertTo (resT, CvType.CV_32F, 1 / 255.0);
			////knnMatch方法的作用就是在给定特征描述集合中寻找最佳匹配
			////使用KNN-matching算法，令K=2，则每个match得到两个最接近的descriptor，然后计算最接近距离和次接近距离之间的比值，当比值大于既定值时，才作为最终match。
			descriptorMatcher.knnMatch (resT, resO, matches, 2);
			//System.out.println ("计算匹配结果");
			goodMatchList (matches, goodMatchesList, 0.7);
			matchesPointCount = goodMatchesList.size ();
			//当匹配后的特征点大于等于 4 个，则认为模板图在原图中，该值可以自行调整
			log.info ("{} {},特征点:{},预计特征点{}", imagesDatum.getImageNumber (),
			          imagesDatum.getImageName (),
			          matchesPointCount, 4);
			MatOfDMatch goodMatches = new MatOfDMatch ();
			goodMatches.fromList (goodMatchesList);
			Mat matchOutput =
					new Mat (originalImage.rows () * 2, originalImage.cols () * 2, Imgcodecs.IMREAD_COLOR);
			Features2d.drawMatches (templateImage, templateKeyPoints, originalImage, originalKeyPoints,
			                        goodMatches, matchOutput, new Scalar (0, 255, 0),
			                        new Scalar (255, 0, 0), new MatOfByte (), 2);
			Imgcodecs.imwrite ("D:\\Output.jpg", matchOutput);
			if (matchesPointCount >= 4) {
				List<KeyPoint> templateKeyPointList = templateKeyPoints.toList ();
				List<KeyPoint> originalKeyPointList = originalKeyPoints.toList ();
				LinkedList<org.opencv.core.Point> objectPoints = new LinkedList<> ();
				LinkedList<org.opencv.core.Point> scenePoints = new LinkedList<> ();
				goodMatchesList.forEach (goodMatch -> {
					objectPoints.addLast (templateKeyPointList.get (goodMatch.queryIdx).pt);
					scenePoints.addLast (originalKeyPointList.get (goodMatch.trainIdx).pt);
				});
				MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f ();
				objMatOfPoint2f.fromList (objectPoints);
				MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f ();
				scnMatOfPoint2f.fromList (scenePoints);
				//使用 findHomography 寻找匹配上的关键点的变换
				Mat homography = Calib3d.findHomography (objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC,
				                                         2);
				//透视变换(Perspective Transformation)是将图片投影到一个新的视平面(Viewing Plane)，也称作投影映射(Projective Mapping)。
				Mat templateCorners = new Mat (4, 1, CvType.CV_32FC2);
				Mat templateTransformResult = new Mat (4, 1, CvType.CV_32FC2);
				templateCorners.put (0, 0, 0, 0);
				templateCorners.put (1, 0, templateImage.cols (), 0);
				templateCorners.put (2, 0, templateImage.cols (), templateImage.rows ());
				templateCorners.put (3, 0, 0, templateImage.rows ());
				//使用 perspectiveTransform 将模板图进行透视变以矫正图象得到标准图片
				Core.perspectiveTransform (templateCorners, templateTransformResult, homography);
				//矩形四个顶点  匹配的图片经过旋转之后就这个矩形的四个点的位置就不是正常的 a、b、c、d了
				double[] pointA = templateTransformResult.get (0, 0);
				double[] pointB = templateTransformResult.get (1, 0);
				double[] pointC = templateTransformResult.get (2, 0);
				double[] pointD = templateTransformResult.get (3, 0);
				pictureIdentifyWorkPO.setX (
						(int) ((pointA[0])));
				pictureIdentifyWorkPO.setY (
						(int) ((pointA[1])));
				if (pictureIdentifyWorkPO.getX () > 0 && pictureIdentifyWorkPO.getY () > 0) {
					if (true) {
						Imgproc.rectangle (originalImage,
						                   new Point (pictureIdentifyWorkPO.getX () - (templateImage.cols () / 2.0),
						                              pictureIdentifyWorkPO.getY () - (templateImage.rows () / 2.0)),
						                   new Point (pictureIdentifyWorkPO.getX () + (templateImage.cols () / 2.0),
						                              pictureIdentifyWorkPO.getY () + (templateImage.rows () / 2.0)),
						                   new Scalar (255, 0, 0));
						Imgcodecs.imwrite ("D:\\match.jpg", originalImage);
					}
					log.info ("目标坐标为:({}，{})", pictureIdentifyWorkPO.getX (),
					          pictureIdentifyWorkPO.getY ());
					log.info ("已识别的序号{},图片:{}", imagesDatum.getImageNumber (),
					          imagesDatum.getImageName ());
					mouseMessages.add (pictureIdentifyWorkPO);
					break;
				}
			}
		}
		log.info (System.currentTimeMillis () - startTime);
		log.info ("鼠标点击");
		//int mousePressTime = (int) (Math.random () * 500 + 100);
		for (PictureIdentifyWorkPO mouseMessage : mouseMessages) {
			// 解析鼠标坐标参数,低位为X轴,高位为Y轴坐标
			X = new StringBuilder (Integer.toHexString ((int) (mouseMessage.getX () / bl)));
			Y = new StringBuilder (Integer.toHexString ((int) (mouseMessage.getY () / bl)));
			while (X.length () < 4) {
				X.insert (0, "0");
			}
			while (Y.length () < 4) {
				Y.insert (0, "0");
			}
			Integer in = Integer.valueOf (Y + X.toString (), 16);
			WinDef.LPARAM lPARAM = new WinDef.LPARAM (in);
			// 模拟计算鼠标按下的间隔并且按下鼠标
			ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 513, new WinDef.WPARAM (513), lPARAM);
			ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 514, new WinDef.WPARAM (514), lPARAM);
		}
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void test2 () throws IOException, AWTException {
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		//图片集
		String path = FolderPathMap.folderPath ("图片总路径");
		List<PictureCollectionPO> pictureCollectionPOList =
				ReadFileUtils.readPictureCollectionPOList (path, explore_DTXGZD, "SIFT");
		//声明 坐标列表
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
		//声明 识别坐标
		PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
		WinDef.HWND hwnd = User32.INSTANCE.FindWindow (null, "谷歌浏览器");
		Double bl = ComputerScalingUtils.getScale ();
		StringBuilder X;
		StringBuilder Y;
		log.info ("开始计时");
		long startTime = System.currentTimeMillis ();
		//来源图片
		//BufferedImage Window = ScreenshotUtils.screenshotBack ("夜神模拟器");
		String str_tem = "D:/a.jpg";
		File file_tem = new File (str_tem);
		BufferedImage Window = ImageIO.read (file_tem);
		log.info (System.currentTimeMillis () - startTime);
		log.info ("图像识别");
		//Imgcodecs.imwrite ("D:\\a.jpg", originalImage);
		log.info (System.currentTimeMillis () - startTime);
		List<Double> doubleList = new ArrayList<> ();
		//获取来源图片.将来源图片转为Mat格式
		Mat g_src = BufferImageToMatUtils.bufImg2Mat (Window, BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3);
		//处理目标图片
		for (PictureCollectionPO pictureCollectionPO : pictureCollectionPOList) {
			//将目标图片转换为Mat
			ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream ();
			ImageIO.write (pictureCollectionPO.getImage (), "jpg", byteArrayOutputStream2);
			byteArrayOutputStream2.flush ();
			Mat g_tem = BufferImageToMatUtils.bufImg2Mat (pictureCollectionPO.getImage (),
			                                              BufferedImage.TYPE_3BYTE_BGR,
			                                              CvType.CV_8UC3);
			assert g_src != null;
			assert g_tem != null;
			int result_rows = g_src.rows () - g_tem.rows () + 1;
			int result_cols = g_src.cols () - g_tem.cols () + 1;
			Mat g_result = new Mat (result_rows, result_cols, CvType.CV_32FC1);
			Imgproc.matchTemplate (g_src, g_tem, g_result, Imgproc.TM_SQDIFF_NORMED); // 归一化相关系数匹配法
			Core.normalize (g_result, g_result, 0, 1, Core.NORM_MINMAX, -1, new Mat ());
			Core.MinMaxLocResult core_result = Core.minMaxLoc (g_result);
			Point matchLocation = core_result.minLoc; // 此处使用maxLoc还是minLoc取决于使用的匹配算法
			log.info ("匹配系数{}", core_result.minVal);
			//将匹配系数填入结果集
			if (core_result.minVal > 0) {
				doubleList.add (core_result.minVal);
			}
			//判断匹配系数大于预期，则返回坐标
			if (core_result.minVal <= 2E-11) {
				if (true) {
					log.info ("打印图片");
					Imgproc.rectangle (g_src, matchLocation,
					                   new Point (matchLocation.x + g_tem.cols (), matchLocation.y + g_tem.rows ()),
					                   new Scalar (0, 0, 0, 0));
					Imgcodecs.imwrite ("D:\\match.jpg", g_src);
				}
				pictureIdentifyWorkPO.setX ((int) (matchLocation.x + g_tem.cols () / 2));
				pictureIdentifyWorkPO.setY ((int) (matchLocation.y + g_tem.rows () / 2));
				log.info ("找到坐标了,({},{})", pictureIdentifyWorkPO.getX (), pictureIdentifyWorkPO.getY ());
				log.info ("已识别的匹配系数:{}", core_result.minVal);
				//坐标添加到返回参数中
				mouseMessages.add (pictureIdentifyWorkPO);
			}
			//识别出3个坐标后跳出
			if (mouseMessages.size () >= 1) {
				break;
			}
		}
		if (doubleList.size () > 0) {
			log.info ("最小匹配系数为{}", Collections.min (doubleList));
		}
		log.info (System.currentTimeMillis () - startTime);
		log.info ("鼠标点击");
		//int mousePressTime = (int) (Math.random () * 500 + 100);
		for (PictureIdentifyWorkPO mouseMessage : mouseMessages) {
			// 解析鼠标坐标参数,低位为X轴,高位为Y轴坐标
			X = new StringBuilder (Integer.toHexString ((int) (mouseMessage.getX () / bl)));
			Y = new StringBuilder (Integer.toHexString ((int) (mouseMessage.getY () / bl)));
			while (X.length () < 4) {
				X.insert (0, "0");
			}
			while (Y.length () < 4) {
				Y.insert (0, "0");
			}
			Integer in = Integer.valueOf (Y + X.toString (), 16);
			WinDef.LPARAM lPARAM = new WinDef.LPARAM (in);
			// 模拟计算鼠标按下的间隔并且按下鼠标
			ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 513, new WinDef.WPARAM (513), lPARAM);
			ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 514, new WinDef.WPARAM (514), lPARAM);
		}
		log.info ("测试结束");
		log.info ("用时{}毫秒", System.currentTimeMillis () - startTime);
	}
	
	@Test
	void test3 () throws IOException, AWTException {
		log.info ("测试开始");
		System.setProperty ("java.awt.headless", "false");
		System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
		findORBFeatures ("D:/a.jpg", "D:/b.png");
	}
	
	public static void findORBFeatures (String image1Path, String image2Path) {
		// 加载图像
		Mat img1 = Imgcodecs.imread(image1Path, Imgcodecs.IMREAD_GRAYSCALE);
		Mat img2 = Imgcodecs.imread(image2Path, Imgcodecs.IMREAD_GRAYSCALE);
		
		// 初始化ORB检测器
		ORB detector = ORB.create();
		
		// 检测图像1和2的ORB特征并计算描述符
		MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
		Mat descriptors1 = new Mat();
		detector.detectAndCompute(img1, new Mat(), keypoints1, descriptors1);
		
		MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
		Mat descriptors2 = new Mat();
		detector.detectAndCompute(img2, new Mat(), keypoints2, descriptors2);
		
		// 在图像1和2之间匹配描述符
		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
		List<MatOfDMatch> matches = new ArrayList<>();
		matcher.knnMatch(descriptors1, descriptors2, matches, 2);
		
		// 通过比值测试筛选匹配项
		float ratioThresh = 0.7f;
		List<DMatch> goodMatchesList = new ArrayList<>();
		for (int i = 0; i < matches.size(); i++) {
			MatOfDMatch matofDMatch = matches.get(i);
			DMatch[] dMatchArray = matofDMatch.toArray();
			DMatch m1 = dMatchArray[0];
			DMatch m2 = dMatchArray[1];
			if (m1.distance < ratioThresh * m2.distance) {
				goodMatchesList.add(m1);
			}
		}
		MatOfDMatch goodMatches = new MatOfDMatch();
		goodMatches.fromList(goodMatchesList);
		
		// 找到最佳匹配的特征点对应的区域
		double maxDist = 0.0;
		int maxIdx = -1;
		for (int i = 0; i < goodMatches.rows(); i++) {
			double dist = goodMatches.toArray()[i].distance;
			if (dist > maxDist) {
				maxDist = dist;
				maxIdx = i;
			}
		}
		Point pt1 = keypoints1.toArray()[goodMatches.toArray()[maxIdx].queryIdx].pt;
		Point pt2 = keypoints2.toArray()[goodMatches.toArray()[maxIdx].trainIdx].pt;
		
		// 计算匹配区域
		int x = (int) Math.max(pt1.x - img1.cols() / 2, 0);
		int y = (int) Math.max(pt1.y - img1.rows() / 2, 0);
		int w = (int) Math.min(img1.cols(), img2.cols() - x);
		int h = (int) Math.min(img1.rows(), img2.rows() - y);
		
		// 绘制匹配区域
		Mat imgMatches = new Mat();
		Imgproc.cvtColor(img1, imgMatches, Imgproc.COLOR_GRAY2BGR);
		Imgproc.rectangle(imgMatches, new Point(x, y), new Point(x + w, y + h), new Scalar(0, 255, 0), 2);
		Imgcodecs.imwrite ("D:\\match.jpg", imgMatches);
		
	}
	
}
