package com.example.util;

import com.example.model.entity.PictureIdentifyWorkPO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.util.ImagesBackRec.readFiles;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImagesOpenCV.java
 * @Description TODO
 * @createTime 2022年11月05日 15:04:00
 */
public class ImagesOpenCV {
	private static final Logger logger = LogManager.getLogger ("ImagesOpenCV");
	
	
	//识别图片存在并点击或只识别不点击
	public static boolean imagesRecognitionOpenCv (String FolderName, String process, boolean isClick,Double coefficient) throws AWTException, IOException {
		//		屏幕截图
		BufferedImage Window = Screenshot.screenshotBack (process);
		//		图片集获取
		List<BufferedImage> ImagesData = readFiles(FolderName);
		//		屏幕截图和图片对比
		assert ImagesData != null;
		List<PictureIdentifyWorkPO> mouseXY = FindAllImgDataOpenCv (Window, ImagesData,coefficient);
		//		识别+鼠标点击或仅识别
		return MouseClick.mouseClickBack (mouseXY,process,isClick);
	}
	
	//识别图片存在并点返回坐标
	public static PictureIdentifyWorkPO imagesRecognitionMouseOpenCV (String FolderName, String process,Double coefficient) throws IOException {
		//		屏幕截图
		BufferedImage Window = Screenshot.screenshotBack (process);
		//		图片集获取
		List<BufferedImage> ImagesData = readFiles(FolderName);
		//		屏幕截图和图片对比
		assert ImagesData != null;
		List<PictureIdentifyWorkPO> mouseXY = FindAllImgDataOpenCv (Window, ImagesData,coefficient);
		//		返回坐标
		PictureIdentifyWorkPO pictureIdentifyWorkPO = new PictureIdentifyWorkPO ();
		if (mouseXY.size () > 0) {
			pictureIdentifyWorkPO = mouseXY.get (0);
		}
		return pictureIdentifyWorkPO;
	}
	
	
	/**
	 * 本方法会根据图片数据从屏幕里找寻相同的图片信息,找到后会返回其对应的坐标集合-使用了openCV的归一性相关系数匹配法
	 * @param Window     - 屏幕图像
	 * @param ImagesData - 指定图片数据集合
	 * @param coefficient -识别系数
	 * @return - 返回图片在屏幕的坐标集合
	 */
	public static List<PictureIdentifyWorkPO> FindAllImgDataOpenCv (BufferedImage Window, List<BufferedImage> ImagesData,Double coefficient) throws IOException {
		//声明 坐标列表
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
		//获取来源图片.将来源图片转为Mat格式
		Mat g_src=BufferImageToMat.bufImg2Mat(Window,BufferedImage.TYPE_3BYTE_BGR,CvType.CV_8UC3);
		//处理目标图片
		for(BufferedImage imagesData:ImagesData){
			//将目标图片转换为Mat
			ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
			ImageIO.write(imagesData, "jpg", byteArrayOutputStream2);
			byteArrayOutputStream2.flush();
			Mat g_tem=BufferImageToMat.bufImg2Mat(imagesData,BufferedImage.TYPE_3BYTE_BGR,CvType.CV_8UC3);
			assert g_src != null;
			assert g_tem != null;
			int result_rows = g_src.rows () - g_tem.rows () + 1;
			int result_cols = g_src.cols() - g_tem.cols() + 1;
			Mat g_result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
			Imgproc.matchTemplate (g_src, g_tem, g_result, Imgproc.TM_CCORR_NORMED); // 归一化相关系数匹配法
			Core.normalize (g_result, g_result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
			Core.MinMaxLocResult core_result = Core.minMaxLoc (g_result);
			Point matchLocation = core_result.maxLoc; // 此处使用maxLoc还是minLoc取决于使用的匹配算法
			//目标坐标
			PictureIdentifyWorkPO pictureIdentifyWorkPO=new PictureIdentifyWorkPO ();
			//判断匹配系数大于预期，则返回坐标
			if(core_result.maxVal>=coefficient) {
				pictureIdentifyWorkPO.setX ((int) (matchLocation.x + g_tem.cols () / 2));
				pictureIdentifyWorkPO.setY ((int) (matchLocation.y + g_tem.cols () / 2));
				logger.info ("找到坐标了，（"+(pictureIdentifyWorkPO.getX ())+"，"+(pictureIdentifyWorkPO.getY ())+")");
				logger.info ("匹配系数:"+core_result.maxVal);
				//坐标添加到返回参数中
				mouseMessages.add (pictureIdentifyWorkPO);
			}
			//识别出3个坐标后跳出
			if(mouseMessages.size ()>=3){
				break;
			}
		}
		// 返回所有图片对应窗口坐标
		return mouseMessages;
		
	}
}
