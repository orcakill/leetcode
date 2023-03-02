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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
		ORB sift = ORB.create (1000, 1.2f, 8, 31, 0, 2, ORB.HARRIS_SCORE, 3, 8);
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
		String str_tem = "D:/b.jpg";
		File file_tem = new File (str_tem);
		BufferedImage Window = ImageIO.read (file_tem);
		log.info (System.currentTimeMillis () - startTime);
		log.info ("图像识别");
		Mat originalImage = getMat (Window);
		//Imgcodecs.imwrite ("D:\\b.jpg",originalImage );
		log.info (System.currentTimeMillis () - startTime);
		sift.detect (originalImage, originalKeyPoints);
		sift.compute (originalImage, originalKeyPoints, resO);
		Mat mat1 =new Mat ();
		Features2d.drawKeypoints (originalImage, originalKeyPoints, mat1, new Scalar (0, 0, 255),
		                          Features2d.DrawMatchesFlags_DRAW_RICH_KEYPOINTS);
		
		File file_resO = new File ("D:\\resO.jpg");
		if (file_resO.exists ()) {
			boolean deleteNot = file_resO.delete ();
			if (deleteNot) {
				log.info ("先删除");
			}
		}
		Imgcodecs.imwrite ("D:\\rea0.jpg", mat1);
		log.info (System.currentTimeMillis () - startTime);
		for (PictureCollectionPO imagesDatum : pictureCollectionPOList) {
			List<MatOfDMatch> matches = new LinkedList<> ();
			LinkedList<DMatch> goodMatchesList = new LinkedList<> ();
			templateImage = getMat (imagesDatum.getImage ());
			//获取模板图的特征点
			sift.detect (templateImage, templateKeyPoints);
			sift.compute (templateImage, templateKeyPoints, resT);
			DescriptorMatcher descriptorMatcher = DescriptorMatcher.create (
					DescriptorMatcher.FLANNBASED);
			resO.convertTo (resO,CvType.CV_32F,1/255.0);
			resT.convertTo (resT,CvType.CV_32F, 1/255.0);
			//knnMatch方法的作用就是在给定特征描述集合中寻找最佳匹配
			//使用KNN-matching算法，令K=2，则每个match得到两个最接近的descriptor，然后计算最接近距离和次接近距离之间的比值，当比值大于既定值时，才作为最终match。
			descriptorMatcher.knnMatch (resT, resO, matches, 2);
			//System.out.println ("计算匹配结果");
			goodMatchList (matches, goodMatchesList, 0.7);
			matchesPointCount = goodMatchesList.size ();
			//当匹配后的特征点大于等于 4 个，则认为模板图在原图中，该值可以自行调整
			log.info ("{} {},特征点:{},预计特征点{}", imagesDatum.getImageNumber (),
			          imagesDatum.getImageName (),
			          matchesPointCount, 4);
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
				                                         3);
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
						                   new Scalar (0, 255, 0));
						MatOfDMatch goodMatches = new MatOfDMatch ();
						goodMatches.fromList (goodMatchesList);
						Mat matchOutput =
								new Mat (originalImage.rows () * 2, originalImage.cols () * 2, Imgcodecs.IMREAD_COLOR);
						Features2d.drawMatches (templateImage, templateKeyPoints, originalImage, originalKeyPoints,
						                        goodMatches, matchOutput, new Scalar (0, 255, 0),
						                        new Scalar (255, 0, 0), new MatOfByte (), 2);
						File file_Match = new File ("D:\\match.jpg");
						if (file_Match.exists ()) {
							boolean deleteNot = file_Match.delete ();
							if (deleteNot) {
								log.info ("先删除");
							}
						}
						Imgcodecs.imwrite ("D:\\match.jpg", originalImage);
						
						File file_Output = new File ("D:\\Output.jpg");
						if (file_Output.exists ()) {
							boolean deleteNot = file_Output.delete ();
							if (deleteNot) {
								log.info ("先删除");
							}
						}
						Imgcodecs.imwrite ("D:\\Output.jpg", matchOutput);
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
	
}
