package com.example.util;

import com.example.model.entity.PictureIdentifyWorkPO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImagesOpenCV.java
 * @Description TODO
 * @createTime 2022年11月05日 15:04:00
 */
public class ImagesOpenCV {
	
	/**
	 * 本方法会根据图片数据从屏幕里找寻相同的图片信息,找到后会返回其对应的坐标集合-使用了openCV的归一性相关系数匹配法
	 * @param Window     - 屏幕图像
	 * @param ImagesData - 指定图片数据集合
	 * @param plan       - 识别方案
	 * @param coefficient -识别系数
	 * @return - 返回图片在屏幕的坐标集合
	 */
	public static List<PictureIdentifyWorkPO> FindAllImgDataOpenCv (BufferedImage Window, List<BufferedImage> ImagesData,int plan,Double coefficient) throws IOException {
		//声明 坐标列表
		List<PictureIdentifyWorkPO> mouseMessages = new ArrayList<> ();
		ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
		ImageIO.write(Window, "jpg", byteArrayOutputStream1);
		byteArrayOutputStream1.flush();
		Mat mat1= Imgcodecs.imdecode (new MatOfByte (byteArrayOutputStream1.toByteArray ()), Imgcodecs.IMREAD_UNCHANGED);
		// 返回所有图片对应窗口坐标
		return mouseMessages;
		
	}
}
