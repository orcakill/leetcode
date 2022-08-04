package com.example.service;



import com.example.service.impl.ImageServiceImpl;

import java.awt.*;


public interface ImageService {
	
	
	/*多张图片后台识别,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息,点击*/
	static boolean imagesClickBack (String folder) throws InterruptedException, AWTException {
		return  imagesClickBack (folder,"夜神模拟器",60,1,2,true,true);
	}
	
	static boolean imagesClickBack (String folder,Integer re_num) throws InterruptedException, AWTException {
		return  imagesClickBack (folder,"夜神模拟器",re_num,1,2,true,true);
	}
	
	/*多张图片后台识别,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息,不点击*/
	static  boolean imagesClickBackIsEmpty (String folder) throws InterruptedException, AWTException {
		return imagesClickBack (folder,"夜神模拟器",60,1,2,true,false);
	}
	
	static  boolean imagesClickBackIsEmpty (String folder,Integer re_num) throws InterruptedException, AWTException {
		return imagesClickBack (folder,"夜神模拟器",re_num,1,2,true,false);
	}
	
	/*多张图片后台识别,手动设置参数*/
	static boolean imagesClickBack (String folder,String process,Integer re_num,Integer start_time,
	                             Integer end_time,Boolean boole,boolean isClick) throws InterruptedException,
	                                                                                 AWTException {
		return  ImageServiceImpl.imagesClickBack (folder,process,re_num,start_time,end_time,boole,isClick);
	}
	
	//根据静态照片计算动态照片的坐标并点击
	static boolean imagesClickBackCount(String file1,String file2,String name,double x,double y) throws
	                                                                                             InterruptedException,
	                                                                                             AWTException {
		return  imagesClickBackCount(file1,file2,name,x,y,"夜神模拟器");
	}
	
	//根据静态照片计算动态照片的坐标并点击
	static boolean imagesClickBackCount(String file1,String file2,String name,double x,double y,String process) throws
	                                                                                                            InterruptedException,
	                                                                                                            AWTException {
		return  ImageServiceImpl.imagesClickBackCount(file1,file2,name,x,y,process);
	}
}
