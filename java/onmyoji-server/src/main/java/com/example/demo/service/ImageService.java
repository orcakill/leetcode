package com.example.demo.service;

import com.example.demo.model.param.ImageRecParam;
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
	
	//图片后台识别,文件夹下单组图片识别
	static boolean imagesBack (String folder, ImageRecParam imageRecParam)
			throws IOException, InterruptedException, AWTException {
		return ImageServiceImpl.imagesBack (folder, imageRecParam);
	}
	
	
	//图片后台识别,多文件夹多组图片识别
	static String imagesBackList(List<String> folderList, ImageRecParam imageRecParam)
			throws IOException,
			       AWTException, InterruptedException {
		return ImageServiceImpl.imagesBackList(folderList,imageRecParam);
	}
}
