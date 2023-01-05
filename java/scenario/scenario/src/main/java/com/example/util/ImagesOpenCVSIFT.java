package com.example.util;

import com.example.model.entity.BufferedImagePO;
import com.example.model.entity.PictureIdentifyWorkPO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @Classname imagesOpenCVSIFT
 * @Description openCV sift方法 特征匹配
 * @Date 2023/1/4 21:26
 * @Created by orcakill
 */
public class ImagesOpenCVSIFT {
	private static final Logger logger = LogManager.getLogger ("imagesOpenCVSIFT");
	
	//识别图片存在并点击或只识别不点击
	public static boolean imagesRecognition (String FolderName, String process, boolean isClick, Double coefficient) throws AWTException,
	                                                                                                                        IOException {
		//		屏幕截图
		BufferedImage Window = Screenshot.screenshotBack (process);
		//		图片集获取
		List<BufferedImagePO> ImagesData = readFiles (FolderName);
		
		if (Objects.requireNonNull (ImagesData)
		           .size () > 0) {
			List<PictureIdentifyWorkPO> mouseXY = FindAllImgDataOpenCv (Window, ImagesData, coefficient);
			//		识别+鼠标点击或仅识别
			return MouseClick.mouseClickBack (mouseXY, process, isClick);
		}
		else {
			return false;
		}
	}
	
	/**
	 * 本方法根据文件夹名从当前项目中找文件夹并且返回文件夹所有照片文件
	 * @param FolderName - 指定的文件夹名字
	 * @return - 返回指定文件夹内的所有照片文件
	 */
	public static List<BufferedImagePO> readFiles (String FolderName) {
		try {
			// 判断当前目录下的指定文件夹是否存在
			File Folder = new File ("D:/project/leetcode/java/scenario/scenario/src/main/resources/image/" + FolderName);
			if (!Folder.exists ()) {
				Folder = new File ("D:/study/Project/leetcode/java/scenario/scenario/src/main/resources/image/" + FolderName);
			}
			if (Folder.isDirectory ()) {
				List<BufferedImagePO> files = new ArrayList<> ();
				String[] fileList = Folder.list ();
				// 将所有照片存储并且返回
				for (int i = 0; i < Objects.requireNonNull (fileList).length; i++) {
					String s = fileList[i];
					File file = new File (Folder + File.separator + s);
					// 判断是否为照片文件
					String[] strArray = file.getName ()
					                        .split ("\\.");
					int suffixIndex = strArray.length - 1;
					// 存储照片文件
					if (!file.isDirectory () && (strArray[suffixIndex].equals ("png") || strArray[suffixIndex].equals ("jpg"))) {
						BufferedImage img = ImageIO.read (file);
						BufferedImagePO bufferedImagePO = new BufferedImagePO (i, s, img);
						files.add (bufferedImagePO);
					}
				}
				return files;
			}
		} catch (Exception e) {
			e.getStackTrace ();
			
		}
		return null;
	}
	
	public static List<PictureIdentifyWorkPO> FindAllImgDataOpenCv (BufferedImage Window, List<BufferedImagePO> ImagesData,
	                                                                Double coefficient) throws IOException {
		//声明 坐标列表
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
		if (coefficient == null) {
			coefficient = 0.7;
		}
		for (int i = 0; i < ImagesData.size (); i++) {
			PictureIdentifyWorkPO pictureIdentifyWorkPO;
			pictureIdentifyWorkPO = ImagesOpenCVSIFT.matchImage (Window, ImagesData.get (i)
			                                                                       .getImage (), coefficient, false);
			mouseMessages.add (pictureIdentifyWorkPO);
			if (mouseMessages.size () > 1) {
				logger.info ("当前识别的图片信息为{}", ImagesData.get (i)
				                                                 .toString ());
				return mouseMessages;
			}
		}
		return mouseMessages;
	}
	
	public static PictureIdentifyWorkPO matchImage (BufferedImage originalImageB,BufferedImage templateImageB, Double coefficient,
	                                                Boolean printOrNot) {
		PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
		Mat resT = new Mat ();
		Mat resO = new Mat ();
		
		//即当detector 又当Detector
		SIFT sift = SIFT.create ();
		
		Mat templateImage = getMatify (templateImageB);
		Mat originalImage = getMatify (originalImageB);
		
		MatOfKeyPoint templateKeyPoints = new MatOfKeyPoint ();
		MatOfKeyPoint originalKeyPoints = new MatOfKeyPoint ();
		
		//获取模板图的特征点
		sift.detect (templateImage, templateKeyPoints);
		sift.detect (originalImage, originalKeyPoints);
		
		sift.compute (templateImage, templateKeyPoints, resT);
		sift.compute (originalImage, originalKeyPoints, resO);
		
		List<MatOfDMatch> matches = new LinkedList ();
		DescriptorMatcher descriptorMatcher = DescriptorMatcher.create (DescriptorMatcher.FLANNBASED);
		//System.out.println ("寻找最佳匹配");
		
		/**
		 * knnMatch方法的作用就是在给定特征描述集合中寻找最佳匹配
		 * 使用KNN-matching算法，令K=2，则每个match得到两个最接近的descriptor，然后计算最接近距离和次接近距离之间的比值，当比值大于既定值时，才作为最终match。
		 */
		descriptorMatcher.knnMatch (resT, resO, matches, 2);
		//System.out.println ("计算匹配结果");
		LinkedList<DMatch> goodMatchesList = new LinkedList ();
		//对匹配结果进行筛选，依据distance进行筛选
		matches.forEach (match -> {
			DMatch[] dmatcharray = match.toArray ();
			DMatch m1 = dmatcharray[0];
			DMatch m2 = dmatcharray[1];
			
			if (m1.distance <= m2.distance * coefficient) {
				goodMatchesList.addLast (m1);
			}
		});
		
		int matchesPointCount = goodMatchesList.size ();
		//当匹配后的特征点大于等于 4 个，则认为模板图在原图中，该值可以自行调整
		if (matchesPointCount >= 4) {
			//System.out.println ("模板图在原图匹配成功！");
			
			List<KeyPoint> templateKeyPointList = templateKeyPoints.toList ();
			List<KeyPoint> originalKeyPointList = originalKeyPoints.toList ();
			LinkedList<org.opencv.core.Point> objectPoints = new LinkedList ();
			LinkedList<org.opencv.core.Point> scenePoints = new LinkedList ();
			goodMatchesList.forEach (goodMatch -> {
				objectPoints.addLast (templateKeyPointList.get (goodMatch.queryIdx).pt);
				scenePoints.addLast (originalKeyPointList.get (goodMatch.trainIdx).pt);
			});
			MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f ();
			objMatOfPoint2f.fromList (objectPoints);
			MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f ();
			scnMatOfPoint2f.fromList (scenePoints);
			//使用 findHomography 寻找匹配上的关键点的变换
			Mat homography = Calib3d.findHomography (objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);
			/*
			  透视变换(Perspective Transformation)是将图片投影到一个新的视平面(Viewing Plane)，也称作投影映射(Projective Mapping)。
			 */
			Mat templateCorners = new Mat (4, 1, CvType.CV_32FC2);
			Mat templateTransformResult = new Mat (4, 1, CvType.CV_32FC2);
			templateCorners.put (0, 0, new double[]{0, 0});
			templateCorners.put (1, 0, new double[]{templateImage.cols (), 0});
			templateCorners.put (2, 0, new double[]{templateImage.cols (), templateImage.rows ()});
			templateCorners.put (3, 0, new double[]{0, templateImage.rows ()});
			//使用 perspectiveTransform 将模板图进行透视变以矫正图象得到标准图片
			Core.perspectiveTransform (templateCorners, templateTransformResult, homography);
			
			//矩形四个顶点  匹配的图片经过旋转之后就这个矩形的四个点的位置就不是正常的abcd了
			double[] pointA = templateTransformResult.get (0, 0);
			double[] pointB = templateTransformResult.get (1, 0);
			double[] pointC = templateTransformResult.get (2, 0);
			double[] pointD = templateTransformResult.get (3, 0);
			//是否打印
			if (printOrNot) {
				//将匹配的图像用用四条线框出来
				Imgproc.rectangle (originalImage, new org.opencv.core.Point (pointA), new Point (pointC), new Scalar (0, 255, 0));
				MatOfDMatch goodMatches = new MatOfDMatch ();
				goodMatches.fromList (goodMatchesList);
				Mat matchOutput = new Mat (originalImage.rows () * 2, originalImage.cols () * 2, Imgcodecs.IMREAD_COLOR);
				Features2d.drawMatches (templateImage, templateKeyPoints, originalImage, originalKeyPoints, goodMatches, matchOutput,
				                        new Scalar (0, 255, 0), new Scalar (255, 0, 0), new MatOfByte (), 2);
				Features2d.drawMatches (templateImage, templateKeyPoints, originalImage, originalKeyPoints, goodMatches, matchOutput,
				                        new Scalar (0, 255, 0), new Scalar (255, 0, 0), new MatOfByte (), 2);
				File file_Match = new File ("D:\\match.jpg");
				if (file_Match.exists ()) {
					file_Match.delete ();
				}
				Imgcodecs.imwrite ("D:\\match.jpg", originalImage);
			}
			pictureIdentifyWorkPO.setX ((int) ((pointA[0]+pointB[0]+pointC[0]+pointD[0])/4)+RandomUtil.getRandom (1,5));
			pictureIdentifyWorkPO.setY ((int) ((pointA[1]+pointB[1]+pointC[1]+pointD[1])/4)+RandomUtil.getRandom (1,5));
			logger.info ("目标坐标为（{}，{}）",pictureIdentifyWorkPO.getX (),pictureIdentifyWorkPO.getY ());
			return pictureIdentifyWorkPO;
		}
		else {
			logger.info ("模板图不在原图中！");
		}
		return pictureIdentifyWorkPO;
	}
	
	public static void printPic (String name, Mat pre) {
		Imgcodecs.imwrite (name + ".jpg", pre);
	}
	
	/**
	 * 尝试把BufferedImage转换为Mat
	 * @param im
	 * @return
	 */
	public static Mat getMatify (BufferedImage im) {
		BufferedImage bufferedImage = toBufferedImageOfType (im, BufferedImage.TYPE_3BYTE_BGR);
		//将bufferedimage转换为字节数组
		byte[] pixels = (
				(DataBufferByte) bufferedImage.getRaster ()
				                              .getDataBuffer ()).getData ();
		//        byte[] pixels = ((DataBufferByte) im.getRaster().getDataBuffer()).getData();
		Mat image = new Mat (bufferedImage.getHeight (), bufferedImage.getWidth (), CvType.CV_8UC3);
		
		image.put (0, 0, pixels);
		
		return image;
	}
	
	/**
	 * 转换图片类型
	 * @param original
	 * @param type
	 * @return
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
