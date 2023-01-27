package com.example.demo.service.impl;

import com.example.demo.service.ImageCoreService;

import java.awt.*;
import java.io.IOException;

/**
 * @Classname ImageServiceImpl
 * @Description 图像识别接口实现类
 * @Date 2023/1/26 22:53
 * @Created by orcakill
 */
public class ImageServiceImpl {
	//定义常量，用于通用赋值
	private static final int identificationAlgorithmType0 = 0; /*模板匹配，RGB图片匹配法*/
	private static final int identificationAlgorithmType1 = 1; /*模板匹配，openCV平方差法*/
	private static final int identificationAlgorithmType2 = 2; /*特征匹配，openCV sift特征匹配*/
	private static final String process0 = "夜神模拟器"; /*默认进程名*/
	private static final int re_num0 = 60;/*默认识别次数*/
	private static final int start_time0 = 1;     //识别开始间隔 如 1
	private static final int end_time0 = 2;     //识别结束间隔 如 2
	private static final boolean boole0 = true;    //是否显示日志 如 true 或false
	private static final boolean isClick0 = true;     //是否点击如 true 或false
	private static final double coefficient0 = 1;    //  相似系数  （默认）
	private static final double coefficient1 = 2E-11;    //  相似系数  （1 算法专用）
	private static final double coefficient2 = 0.7;    //  相似系数  （2 算法专用）
	private static final int characteristicPoint0 = 4;// 特征点 （2 算法专用）
	
	public static boolean imagesClickBack (String folder) throws IOException, InterruptedException, AWTException {
		return ImageCoreService.imagesBackClick (folder,identificationAlgorithmType0,process0,re_num0,start_time0,
		                                          end_time0,
		                                  boole0,isClick0,coefficient0,characteristicPoint0);
	}
	
	public static boolean imagesClickBack (String folder, Integer identificationAlgorithmType)
			throws IOException, InterruptedException, AWTException {
		return ImageCoreService.imagesBackClick (folder,identificationAlgorithmType,process0,re_num0,start_time0,
		                                         end_time0,
		                                         boole0,isClick0,coefficient0,characteristicPoint0);
	}
	
	public static boolean imagesClickBack (String folder, Integer identificationAlgorithmType, Integer reNum,
	                                       boolean boole) throws IOException, InterruptedException, AWTException {
		return ImageCoreService.imagesBackClick (folder,identificationAlgorithmType,process0,reNum,start_time0,
		                                         end_time0,
		                                         boole,isClick0,coefficient0,characteristicPoint0);
	}
	
	public static boolean imagesClickBackIsEmpty (String folder, Integer identificationAlgorithmType, Integer reNum,
	                                        boolean boole, boolean isClick)
			throws IOException, InterruptedException, AWTException {
		return ImageCoreService.imagesBackClick (folder,identificationAlgorithmType,process0,reNum,start_time0,
		                                         end_time0,
		                                         boole,isClick,coefficient0,characteristicPoint0);
	}
}
