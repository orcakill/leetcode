package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import lombok.extern.log4j.Log4j2;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname imagesOpenCVSIFT
 * @Description openCV sift方法 特征匹配
 * @Date 2023/1/26 22:12
 * @Created by orcakill
 */
@Log4j2
public class ImagesOpenCVSIFTUtils {
	
	//识别图片存在并点击或只识别不点击
	public static boolean imagesRecognition (List<PictureCollectionPO> pictureCollectionPOList, String process,
	                                         boolean isClick,
	                                         Double coefficient,
	                                         int characteristicPoint) throws AWTException {
		//		屏幕截图
		BufferedImage Window = ScreenshotUtils.screenshotBack (process);
		
		List<PictureIdentifyWorkPO> mouseXY = FindAllImgDataOpenCvAll (Window, pictureCollectionPOList, coefficient,
		                                                               characteristicPoint, false);
		//		识别+鼠标点击或仅识别
		if (mouseXY != null && !mouseXY.isEmpty ()) {
			return MouseClickUtils.mouseClickBack (mouseXY, process, isClick);
		}
		else {
			return false;
		}
	}
	
	public static List<PictureIdentifyWorkPO> FindAllImgDataOpenCvAll (BufferedImage originalImageB,
	                                                                   List<PictureCollectionPO> templateImageB,
	                                                                   Double coefficient, int characteristicPoint,
	                                                                   boolean printOrNot) {
		//声明 坐标列表
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
		//声明 识别坐标
		PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
		if (coefficient == null) {
			coefficient = 0.7;
		}
		//特征匹配
		Mat resT = new Mat ();
		Mat resO = new Mat ();
		
		int matchesPointCount;
		//即当detector 又当Detector
		SIFT sift = SIFT.create ();
		Mat templateImage;
		Mat originalImage = getMat (originalImageB);
		;
		MatOfKeyPoint templateKeyPoints = new MatOfKeyPoint ();
		MatOfKeyPoint originalKeyPoints = new MatOfKeyPoint ();
		sift.detect (originalImage, originalKeyPoints);
		sift.compute (originalImage, originalKeyPoints, resO);
		try {
			for (PictureCollectionPO imagesDatum : templateImageB) {
				try {
					List<MatOfDMatch> matches = new LinkedList<> ();
					LinkedList<DMatch> goodMatchesList = new LinkedList<> ();
					templateImage = getMat (imagesDatum.getImage ());
					//获取模板图的特征点
					sift.detect (templateImage, templateKeyPoints);
					sift.compute (templateImage, templateKeyPoints, resT);
					DescriptorMatcher descriptorMatcher = DescriptorMatcher.create (
							DescriptorMatcher.FLANNBASED);
					//knnMatch方法的作用就是在给定特征描述集合中寻找最佳匹配
					//使用KNN-matching算法，令K=2，则每个match得到两个最接近的descriptor，然后计算最接近距离和次接近距离之间的比值，当比值大于既定值时，才作为最终match。
					descriptorMatcher.knnMatch (resT, resO, matches, 2);
					//System.out.println ("计算匹配结果");
					goodMatchList (matches, goodMatchesList, coefficient);
					matchesPointCount = goodMatchesList.size ();
					//当匹配后的特征点大于等于 4 个，则认为模板图在原图中，该值可以自行调整
					log.info ("{} {},特征点:{},预计特征点{}", imagesDatum.getImageNumber (),
					          imagesDatum.getImageName (),
					          matchesPointCount, characteristicPoint);
					if (matchesPointCount >= characteristicPoint) {
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
						if (pointA[0] < 0 || pointA[1] < 0 || pointB[0] < 0 || pointB[1] < 0 || pointC[0] < 0
						    || pointC[1] < 0 ||
						    pointD[0] < 0 || pointD[1] < 0) {
							return null;
						}
						pictureIdentifyWorkPO.setX (
								(int) ((pointA[0] + pointB[0] + pointC[0] + pointD[0]) / 4) + RandomUtils.getRandom (1,
								                                                                                     5));
						pictureIdentifyWorkPO.setY (
								(int) ((pointA[1] + pointB[1] + pointC[1] + pointD[1]) / 4) + RandomUtils.getRandom (1,
								                                                                                     5));
						if (pictureIdentifyWorkPO.getX () > 0 && pictureIdentifyWorkPO.getY () > 0) {
							if (printOrNot) {
								Imgproc.rectangle (originalImage, new org.opencv.core.Point (pointA),
								                   new Point (pointC), new Scalar (0, 255, 0));
								MatOfDMatch goodMatches = new MatOfDMatch ();
								goodMatches.fromList (goodMatchesList);
								Mat matchOutput = new Mat (originalImage.rows () * 2, originalImage.cols () * 2,
								                           Imgcodecs.IMREAD_COLOR);
								Features2d.drawMatches (templateImage, templateKeyPoints, originalImage,
								                        originalKeyPoints, goodMatches, matchOutput,
								                        new Scalar (0, 255, 0), new Scalar (255, 0, 0),
								                        new MatOfByte (), 2);
								Features2d.drawMatches (templateImage, templateKeyPoints, originalImage,
								                        originalKeyPoints, goodMatches, matchOutput,
								                        new Scalar (0, 255, 0), new Scalar (255, 0, 0),
								                        new MatOfByte (), 2);
								File file_Match = new File ("D:\\match.jpg");
								if (file_Match.exists ()) {
									boolean deleteNot = file_Match.delete ();
									if (deleteNot) {
										log.info ("先删除");
									}
								}
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
				} catch (Exception e) {
					log.info (e);
				}
			}
		} catch (Exception e) {
			log.info (e);
		}
		return mouseMessages;
	}
	
	private static void goodMatchList (List<MatOfDMatch> matches, LinkedList<DMatch> goodMatchesList,
	                                   Double finalCoefficient) {
		matches.forEach (match -> {
			DMatch[] dasharray = match.toArray ();
			DMatch m1 = dasharray[0];
			DMatch m2 = dasharray[1];
			if (m1.distance <= m2.distance * finalCoefficient) {
				goodMatchesList.addLast (m1);
			}
		});
	}
	
	/**
	 * 尝试把BufferedImage转换为Mat
	 * @param im 图像
	 * @return image
	 */
	public static Mat getMat (BufferedImage im) {
		BufferedImage bufferedImage = toBufferedImageOfType (im, BufferedImage.TYPE_3BYTE_BGR);
		//将bufferedImage转换为字节数组
		byte[] pixels = (
				(DataBufferByte) bufferedImage.getRaster ().getDataBuffer ()).getData ();
		//        byte[] pixels = ((DataBufferByte) im.getRaster().getDataBuffer()).getData();
		Mat image = new Mat (bufferedImage.getHeight (), bufferedImage.getWidth (), CvType.CV_8UC3);
		
		image.put (0, 0, pixels);
		
		return image;
	}
	
	/**
	 * 转换图片类型
	 * @param original 图片
	 * @param type     类型
	 * @return BufferedImage
	 */
	public static BufferedImage toBufferedImageOfType (BufferedImage original, int type) {
		if (original == null) {
			throw new IllegalArgumentException ("original == null");
		}
		// Don't convert if it already has correct type
		if (original.getType () == type) {
			return original;
		}
		// Create a buffered image
		BufferedImage image = new BufferedImage (original.getWidth (), original.getHeight (), type);
		
		// Draw the image onto the new buffer
		Graphics2D g = image.createGraphics ();
		try {
			g.setComposite (AlphaComposite.Src);
			g.drawImage (original, 0, 0, null);
		} finally {
			g.dispose ();
		}
		
		return image;
	}
}
