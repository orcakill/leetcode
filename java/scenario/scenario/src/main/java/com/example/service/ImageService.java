package com.example.service;



import com.example.service.impl.ImageServiceImpl;

import java.awt.*;


public interface ImageService {
	
	
	/*多张图片后台识别,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息,点击*/
	static void imagesClickBack (String folder) throws InterruptedException, AWTException {
		imagesClickBack (folder,"夜神模拟器",60,1,2,true,true);
	}
	
	/*多张图片后台识别,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息,不点击*/
	static void imagesClickBackIsEmpty (String folder) throws InterruptedException, AWTException {
		imagesClickBack (folder,"夜神模拟器",60,1,2,true,false);
	}
	
	/*多张图片后台识别,手动设置参数*/
	static void imagesClickBack (String folder,String process,Integer re_num,Integer start_time,
	                             Integer end_time,Boolean boole,boolean isClick) throws InterruptedException,
	                                                                                 AWTException {
		ImageServiceImpl.imagesClickBack (folder,process,re_num,start_time,end_time,boole,isClick);
	}
	
}
