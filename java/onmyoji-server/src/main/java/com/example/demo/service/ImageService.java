package com.example.demo.service;

import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.param.ImageRecParam;
import com.example.demo.model.param.MultipleImagesParam;
import com.example.demo.service.impl.ImageServiceImpl;

import java.awt.*;
import java.io.IOException;

/**
 * @Classname ImageService
 * @Description 图像识别服务接口
 * @Date 2023/1/26 22:42
 * @Created by orcakill
 */
public interface ImageService {
	
	/***
	 * @description: 单组图像后台识别及点击
	 * @param folder   文件夹
	 * @param imageRecParam   图像识别参数
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/2/21 12:41
	 */
	static boolean imagesBack (String folder, ImageRecParam imageRecParam)
			throws IOException, InterruptedException, AWTException {
		return ImageServiceImpl.imagesBack (folder, imageRecParam);
	}
	
	/***
	 * @description: 单组图像后台识别及获取坐标
	 * @param imageRecParam  图像识别参数
	 * @return: boolean
	 * {@code @author:} orcakill
	 * @date: 2023/1/26 22:26
	 */
	static PictureIdentifyWorkPO imagesBackGetCoordinate (String folder, ImageRecParam imageRecParam)
			throws AWTException, IOException, InterruptedException {
		return ImageServiceImpl.imagesBackGetCoordinate (folder, imageRecParam);
	}
	
	/***
	 * @description: 多组图像识别出一组并点击
	 * @param multipleImageParams   图像集识别参数
	 * @return: java.lang.String
	 * @author: orcakill
	 * @date: 2023/2/21 12:42
	 */
	static String imagesBackList (MultipleImagesParam multipleImageParams)
			throws IOException,
			       AWTException, InterruptedException {
		return ImageServiceImpl.imagesBackList (multipleImageParams);
	}
	
}
