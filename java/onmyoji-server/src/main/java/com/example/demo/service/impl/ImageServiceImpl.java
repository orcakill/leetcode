package com.example.demo.service.impl;

import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.example.demo.model.param.ImageRecParam;
import com.example.demo.model.param.MultipleImagesParam;
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

	
	public static boolean imagesBack (String folder, ImageRecParam imageRecParam) throws IOException, InterruptedException,
	                                                                        AWTException {
		return ImageCoreService.imagesBackClick (folder,imageRecParam);
	}
	
	
	public static PictureIdentifyWorkPO imagesBackGetCoordinate (String folder, ImageRecParam imageRecParam)
			throws IOException, InterruptedException, AWTException {
		return ImageCoreService.imagesBackGetCoordinate (folder,imageRecParam);
	}
	public static String imagesBackList (MultipleImagesParam multipleImageParams)
			throws IOException, InterruptedException, AWTException {
		return ImageCoreService.imagesBackClickList (multipleImageParams);
		
	}
	

}
