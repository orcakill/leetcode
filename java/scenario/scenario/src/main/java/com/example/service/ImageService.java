package com.example.service;



import com.example.service.impl.ImageServiceImpl;

import java.awt.*;
import java.io.File;

public interface ImageService {
	
	
	/*多张图片后台识别并点击,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息*/
	static void imagesClickBack (String folder) throws InterruptedException, AWTException {
		imagesClickBack (folder,60,1,2,true);
	}
	
	/*多张图片后台识别,手动设置参数*/
	static void imagesClickBack (String folder,Integer re_num,Integer start_time,
	                             Integer end_time,Boolean boole) throws InterruptedException,
	                                                                                 AWTException {
		ImageServiceImpl.imagesClickBack (folder,re_num,start_time,end_time,boole);
	}
	
}
