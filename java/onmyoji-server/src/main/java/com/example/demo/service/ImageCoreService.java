package com.example.demo.service;

import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.param.ImageRecParam;
import com.example.demo.model.param.MultipleImagesParam;
import com.example.demo.service.impl.ImageCoreServiceImpl;

import java.awt.*;
import java.io.IOException;

/**
 * @Classname ImageCoreService
 * @Description 图片识别核心服务接口
 * @Date 2023/1/26 22:31
 * @Created by orcakill
 */
public interface ImageCoreService {
	/***
	 * @description: 后台图像识别及点击，支持RGB、openCV模板匹配、openCV sift特征匹配
	 * @param imageRecParam  图像识别参数
	 * @return: boolean
	 * {@code @author:} orcakill
	 * @date: 2023/1/26 22:26
	 */
	static boolean imagesBackClick (String folder,ImageRecParam imageRecParam)
			throws AWTException, IOException, InterruptedException {
		return ImageCoreServiceImpl.imagesBackClick (folder,imageRecParam);
	}
	
	/***
	 * @description: 后台图像识别获取坐标，支持RGB、openCV模板匹配、openCV sift特征匹配
	 * @param imageRecParam  图像识别参数
	 * @return: boolean
	 * {@code @author:} orcakill
	 * @date: 2023/1/26 22:26
	 */
	static PictureIdentifyWorkPO imagesBackGetCoordinate (String folder, ImageRecParam imageRecParam)
			throws AWTException, IOException, InterruptedException {
		return ImageCoreServiceImpl.imagesBackGetCoordinate(folder,imageRecParam);
	}
	
	
	
	/***
	 * @description: 多组后台图像识别及点击，支持RGB、openCV模板匹配、openCV sift特征匹配
	 * @param multipleImageParams  多组图片不同算法
	 * @return: boolean
	 * {@code @author:} orcakill
	 * @date: 2023/1/26 22:26
	 */
	static String imagesBackClickList (MultipleImagesParam multipleImageParams)
			throws AWTException, IOException, InterruptedException {
		return ImageCoreServiceImpl.imagesBackClick (multipleImageParams);
	}
}
