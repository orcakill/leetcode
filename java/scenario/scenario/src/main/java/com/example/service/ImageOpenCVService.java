package com.example.service;

import com.example.service.impl.ImageOpenCVServiceImpl;

import java.awt.*;
import java.io.IOException;

/**
 * @Classname ImageOpenCVService
 * @Description TODO
 * @Date 2022/11/6 0:29
 * @Created by orcakill
 */
public interface ImageOpenCVService {
	
	/*多张图片后台识别,参数默认，识别次数1，识别起始间隔1，识别结束间隔2，是否返回日志信息是,点击*/
	static void imagesOpenCV (String folder) throws InterruptedException, AWTException, IOException {
		imagesOpenCV(folder, "夜神模拟器", 60, 1, 2, true, true,1.0);
	}
	
	/*多张图片后台识别,参数默认，识别次数1，识别起始间隔1，识别结束间隔2，是否返回日志信息是,点击*/
	static boolean imagesOpenCVIsEmpty (String folder,boolean isClick) throws InterruptedException, AWTException, IOException {
		return  imagesOpenCV(folder, "夜神模拟器", 60, 1, 2, true, isClick,1.0);
	}
	
	static  boolean imagesOpenCVIsEmpty (String folder,Integer re_num) throws InterruptedException, AWTException, IOException {
		return imagesOpenCV(folder,"夜神模拟器",re_num,1,2,true,false,1.0);
	}
	
	/*多张图片后台识别,参数默认，识别次数，识别起始间隔，识别结束间隔，是否返回日志信息,点击*/
	static void imagesOpenCV (String folder,Double coefficient) throws InterruptedException, AWTException, IOException {
		imagesOpenCV(folder, "夜神模拟器", 60, 1, 2, true, true,coefficient);
	}
	
	/*多张图片后台识别,手动设置参数*/
	static boolean imagesOpenCV (String folder,String process,Integer re_num,Integer start_time,
	                                Integer end_time,Boolean boole,boolean isClick,Double coefficient) throws InterruptedException,
	                                                                                                          AWTException, IOException {
		return  ImageOpenCVServiceImpl.imagesClickBack (folder,process,re_num,start_time,end_time,boole,isClick,coefficient);
	}
}
