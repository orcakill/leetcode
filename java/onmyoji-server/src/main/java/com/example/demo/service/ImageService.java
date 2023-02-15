package com.example.demo.service;

import com.example.demo.service.impl.ImageServiceImpl;
import java.awt.*;
import java.util.List;
import java.io.IOException;

/**
 * @Classname ImageService
 * @Description 图像识别服务接口
 * @Date 2023/1/26 22:42
 * @Created by orcakill
 */
public interface ImageService {
	//图片后台识别,文件夹下图片识别,默认RGB识别
	static boolean imagesBack0 (String folder) throws IOException, InterruptedException, AWTException {
		return ImageServiceImpl.imagesClickBack (folder);
	}
	
	//图片后台识别,文件夹下图片识别,可切换算法类型
	static boolean imagesBack (String folder, Integer identificationAlgorithmType)
			throws IOException, InterruptedException, AWTException {
		return ImageServiceImpl.imagesClickBack (folder, identificationAlgorithmType);
	}
	
	//图片后台识别,文件夹下图片识别,可切换算法类型、识别次数
	static boolean imagesBack (String folder, Integer identificationAlgorithmType,Integer re_num)
			throws IOException, InterruptedException, AWTException {
		return ImageServiceImpl.imagesClickBack (folder, identificationAlgorithmType,re_num, true);
	}
	
	//图片后台识别,文件夹下图片识别,可切换算法类型、识别次数
	static boolean imagesBackIsEmpty (String folder, Integer identificationAlgorithmType,Integer re_num)
			throws IOException, InterruptedException, AWTException {
		return  ImageServiceImpl.imagesClickBackIsEmpty (folder, identificationAlgorithmType, re_num, true, false);
	}
	//图片后台识别,文件夹下图片识别,可切换算法类型，指定识别次数，指定日志识别显示
	static boolean imagesBackSingleHide (String folder, Integer identificationAlgorithmType, Integer re_num,
	                                   boolean boole)
			throws IOException,
			       InterruptedException, AWTException {
		return ImageServiceImpl.imagesClickBack (folder, identificationAlgorithmType, re_num, boole);
	}
	 
	//图片后台识别,文件夹下图片识别,可切换算法类型，指定识别次数，指定日志识别显示,但不点击
	static boolean imagesBackSingleHideIsEmpty (String folder, Integer identificationAlgorithmType, Integer re_num,
	                                       boolean boole) throws IOException,
	                                                             InterruptedException, AWTException {
		return ImageServiceImpl.imagesClickBackIsEmpty (folder, identificationAlgorithmType, re_num, boole, false);
	}
	
	//图片后台识别,多组图片识别,可切换算法类型，指定识别次数，指定日志识别显示,但不点击
	static String imagesBackListIsEmpty (List<String> folderList, Integer identificationAlgorithmType)
			throws IOException,
			       AWTException, InterruptedException {
		return ImageServiceImpl.imagesBackListIsEmpty(folderList, identificationAlgorithmType,false);
	}
}
