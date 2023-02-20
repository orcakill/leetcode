package com.example.demo.utils;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.entity.PictureIdentifyWorkPO;
import lombok.extern.log4j.Log4j2;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImagesOpenCV.java
 * @Description openCV模板图像识别
 * @createTime 2023年1月26日 22:13:00
 */
@Log4j2
public class ImagesOpenCVUtils {
	
	//识别图片存在并点击或只识别不点击
	public static boolean imagesRecognitionOpenCv (List<PictureCollectionPO> pictureCollectionPOList, String process,
	                                               boolean isClick, Double coefficient,String method)
			throws AWTException, IOException {
		//		屏幕截图
		BufferedImage Window = ScreenshotUtils.screenshotBack (process);
		//		屏幕截图和图片对比
		List<PictureIdentifyWorkPO> mouseXY =
				FindAllImgDataOpenCv (Window, pictureCollectionPOList, coefficient, false,method);
		//		识别+鼠标点击或仅识别
		return MouseClickUtils.mouseClickBack (mouseXY, process, isClick);
	}
	
	/**
	 * 本方法会根据图片数据从屏幕里找寻相同的图片信息,找到后会返回其对应的坐标集合-使用了openCV的归一性相关系数匹配法
	 * @param Window                  - 屏幕图像
	 * @param pictureCollectionPOList - 指定图片数据集合
	 * @param coefficient             -识别系数
	 * @param coefficient             -识别算法
	 * @return - 返回图片在屏幕的坐标集合
	 */
	public static List<PictureIdentifyWorkPO> FindAllImgDataOpenCv (BufferedImage Window,
	                                                                List<PictureCollectionPO> pictureCollectionPOList,
	                                                                Double coefficient, boolean printOrNot,
	                                                                String method)
			throws IOException {
		if (coefficient == null) {
			coefficient = 2E-11;
		}
		List<Double> doubleList = new ArrayList<> ();
		//声明 坐标列表
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
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
			if(method.equals ("TM_SQDIFF_NORMED")){
				Imgproc.matchTemplate (g_src, g_tem, g_result, Imgproc.TM_SQDIFF_NORMED); // 归一化相关系数匹配法
			}
			Core.normalize (g_result, g_result, 0, 1, Core.NORM_MINMAX, -1, new Mat ());
			Core.MinMaxLocResult core_result = Core.minMaxLoc (g_result);
			Point matchLocation = core_result.minLoc; // 此处使用maxLoc还是minLoc取决于使用的匹配算法
			//目标坐标
			PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
			//将匹配系数填入结果集
			if (core_result.minVal > 0) {
				doubleList.add (core_result.minVal);
			}
			//判断匹配系数大于预期，则返回坐标
			if (core_result.minVal <= coefficient && core_result.minVal >= 0) {
				if (printOrNot) {
					log.info ("打印图片");
					Imgproc.rectangle (g_src, matchLocation,
					                   new Point (matchLocation.x + g_tem.cols (), matchLocation.y + g_tem.rows ()),
					                   new Scalar (0, 0, 0, 0));
					File file_Match = new File ("D:\\match.jpg");
					if (file_Match.exists ()) {
						boolean deleteOrNot=file_Match.delete ();
						if(deleteOrNot){
							log.info ("已删除");
						}
					}
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
		// 返回所有图片对应窗口坐标
		return mouseMessages;
		
	}
	
}
