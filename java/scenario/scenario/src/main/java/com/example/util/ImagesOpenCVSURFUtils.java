package com.example.util;

import lombok.extern.log4j.Log4j2;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageOpenCVSURFUtils.java
 * @Description openCV surf算法
 * @createTime 2023年02月23日 15:38:00
 */
@Log4j2
public class ImagesOpenCVSURFUtils {
	
	//public static void imageRecognitionSURF (BufferedImage originalImageB,
	//                                         BufferedImage templateImageB) {
	//	Mat imgScene = getMat (originalImageB);
	//	Mat imgObject = getMat (templateImageB);
	//	//-- 步骤1：使用SURF Detector检测关键点，计算描述符
	//	double hessianThreshold = 400;
	//	int nOctaves = 4;
	//	int nOctaveLayers = 3;
	//	boolean extended = false;
	//	boolean upright = false;
	//	SURF detector = SURF.create (hessianThreshold, nOctaves, nOctaveLayers, extended, upright);
	//	MatOfKeyPoint keypointsObject = new MatOfKeyPoint ();
	//	MatOfKeyPoint keypointsScene = new MatOfKeyPoint ();
	//	Mat descriptorsObject = new Mat ();
	//	Mat descriptorsScene = new Mat ();
	//	detector.detectAndCompute (imgObject, new Mat (), keypointsObject, descriptorsObject);
	//	detector.detectAndCompute (imgScene, new Mat (), keypointsScene, descriptorsScene);
	//
	//	//-- 步骤2：将描述符向量与基于FLANN的匹配器进行匹配
	//	// 由于SURF是浮点描述符，因此使用NORM_L2
	//	DescriptorMatcher matcher = DescriptorMatcher.create (DescriptorMatcher.FLANNBASED);
	//	List<MatOfDMatch> knnMatches = new ArrayList<> ();
	//	matcher.knnMatch(descriptorsObject, descriptorsScene, knnMatches, 2);
	//	//-- 使用Lowe比率测试过滤匹配项
	//	float ratioThresh = 0.75f;
	//	List<DMatch> listOfGoodMatches = new ArrayList<>();
	//	for (MatOfDMatch knnMatch : knnMatches) {
	//		if (knnMatch.rows () > 1) {
	//			DMatch[] matches = knnMatch.toArray ();
	//			if (matches[0].distance < ratioThresh * matches[1].distance) {
	//				listOfGoodMatches.add (matches[0]);
	//			}
	//		}
	//	}
	//	MatOfDMatch goodMatches = new MatOfDMatch();
	//	goodMatches.fromList(listOfGoodMatches);
	//	//-- 匹配
	//	Mat imgMatches = new Mat();
	//	Features2d.drawMatches (imgObject, keypointsObject, imgScene, keypointsScene, goodMatches, imgMatches, Scalar.all (-1),
	//	                        Scalar.all(-1), new MatOfByte (), Features2d.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS);
	//	//-- 定位对象
	//	List<Point> obj = new ArrayList<>();
	//	List<Point> scene = new ArrayList<>();
	//	List<KeyPoint> listOfKeypointsObject = keypointsObject.toList();
	//	List<KeyPoint> listOfKeypointsScene = keypointsScene.toList();
	//	for (DMatch listOfGoodMatch : listOfGoodMatches) {
	//		//-- 从良好的匹配中获取关键点
	//		obj.add (listOfKeypointsObject.get (listOfGoodMatch.queryIdx).pt);
	//		scene.add (listOfKeypointsScene.get (listOfGoodMatch.trainIdx).pt);
	//	}
	//	MatOfPoint2f objMat = new MatOfPoint2f();
	//	MatOfPoint2f sceneMat = new MatOfPoint2f();
	//	objMat.fromList(obj);
	//	sceneMat.fromList(scene);
	//	double ransacReprojThreshold = 3.0;
	//	Mat H = Calib3d.findHomography (objMat, sceneMat, Calib3d.RANSAC, ransacReprojThreshold);
	//	//-- 从image_1（要“检测”的对象）获取角
	//	Mat objCorners = new Mat(4, 1, CvType.CV_32FC2), sceneCorners = new Mat();
	//	float[] objCornersData = new float[(int) (objCorners.total() * objCorners.channels())];
	//	objCorners.get(0, 0, objCornersData);
	//	objCornersData[0] = 0;
	//	objCornersData[1] = 0;
	//	objCornersData[2] = imgObject.cols();
	//	objCornersData[3] = 0;
	//	objCornersData[4] = imgObject.cols();
	//	objCornersData[5] = imgObject.rows();
	//	objCornersData[6] = 0;
	//	objCornersData[7] = imgObject.rows();
	//	objCorners.put(0, 0, objCornersData);
	//	Core.perspectiveTransform(objCorners, sceneCorners, H);
	//	float[] sceneCornersData = new float[(int) (sceneCorners.total() * sceneCorners.channels())];
	//	sceneCorners.get(0, 0, sceneCornersData);
	//	//-- 在角之间绘制线（场景中的映射对象-image_2）
	//	Imgproc.line (imgMatches, new Point(sceneCornersData[0] + imgObject.cols (), sceneCornersData[1]),
	//	              new Point(sceneCornersData[2] + imgObject.cols(), sceneCornersData[3]), new Scalar(0, 255, 0), 4);
	//	Imgproc.line(imgMatches, new Point(sceneCornersData[2] + imgObject.cols(), sceneCornersData[3]),
	//	             new Point(sceneCornersData[4] + imgObject.cols(), sceneCornersData[5]), new Scalar(0, 255, 0), 4);
	//	Imgproc.line(imgMatches, new Point(sceneCornersData[4] + imgObject.cols(), sceneCornersData[5]),
	//	             new Point(sceneCornersData[6] + imgObject.cols(), sceneCornersData[7]), new Scalar(0, 255, 0), 4);
	//	Imgproc.line(imgMatches, new Point(sceneCornersData[6] + imgObject.cols(), sceneCornersData[7]),
	//	             new Point(sceneCornersData[0] + imgObject.cols(), sceneCornersData[1]), new Scalar(0, 255, 0), 4);
	//	File file_Match = new File ("D:\\match.jpg");
	//	if (file_Match.exists ()) {
	//		boolean deleteNot = file_Match.delete ();
	//		if (deleteNot) {
	//			log.info ("先删除");
	//		}
	//	}
	//	Imgcodecs.imwrite ("D:\\match.jpg",imgMatches);
	//
	//}
}


