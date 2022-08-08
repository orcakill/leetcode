package com.example.service;



import com.example.service.impl.ImageServiceImpl;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ImageService {
	
	
	/*多张图片后台识别,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息,点击*/
	static boolean imagesClickBack (String folder) throws InterruptedException, AWTException {
		return  imagesClickBack (folder,"夜神模拟器" ,60,1,2,true,true);
	}
	
	static boolean imagesClickBack (String folder,Integer re_num) throws InterruptedException, AWTException {
		return  imagesClickBack (folder,"夜神模拟器",re_num,1,2,true,true);
	}
	
	static boolean imagesClickBack (String folder,Integer re_num,Boolean boole) throws InterruptedException,
	                                                                                 AWTException {
		return  imagesClickBack (folder,"夜神模拟器",re_num,1,2,boole,true);
	}
	
	
	/*多张图片后台识别,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息,不点击*/
	static  boolean imagesClickBackIsEmpty (String folder) throws InterruptedException, AWTException {
		return imagesClickBack (folder,"夜神模拟器",60,1,2,true,false);
	}
	
	static  boolean imagesClickBackIsEmpty (String folder,Integer re_num) throws InterruptedException, AWTException {
		return imagesClickBack (folder,"夜神模拟器",re_num,1,2,true,false);
	}
	
	static  boolean imagesClickBackIsEmpty (String folder,Integer re_num,boolean boole) throws InterruptedException,
	                                                                                      AWTException {
		return imagesClickBack (folder,"夜神模拟器",re_num,1,2,false,false);
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
	
	//传入多个文件夹，返回识别的key
	static String imagesClickBack (Map<String,String> files) throws
	                                                    InterruptedException,
	                                                    AWTException {
		return  imagesClickBack (files,"夜神模拟器",60,1,2,true,true);
	}
	
	static String imagesClickBack (Map<String,String> files,Integer start_num,Integer end_num) throws InterruptedException, AWTException {
		return  imagesClickBack (files,"夜神模拟器",60,start_num,end_num,true,true);
	}
	
	static String imagesClickBack (Map<String,String> files,String process,Integer re_num,Integer start_time,
	                               Integer end_time,Boolean boole,boolean isClick) throws
	                                                    InterruptedException,
	                                                    AWTException {
		return  ImageServiceImpl.imagesClickBack (files,process,re_num,start_time,end_time,boole,isClick);
	}
	
	//拖动点击
	static boolean imagesClickBackDrag (String folder,double x,double y) throws InterruptedException, AWTException {
		return  imagesClickBackDrag (folder,x,y,"夜神模拟器" ,60,1,2,true);
	}
	
	static boolean imagesClickBackDrag (String folder,double x,double y,String process,Integer re_num,Integer start_time,
	                                    Integer end_time,Boolean boole) throws InterruptedException, AWTException {
		return  ImageServiceImpl.imagesClickBackDrag (folder,x,y,process,re_num,start_time,end_time,boole);
	}
}
