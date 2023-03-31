package com.example.demo.model.param;

import lombok.Data;

/**
 * @Classname ImageRecParam
 * @Description 图片识别类
 * @Date 2023/2/17 1:11
 * @Created by orcakill
 */
@Data
public class ImageRecParam {
	//图像识别算法
	//0  模板匹配	 RGB识别法
	//1  模板匹配 openCV
	//2  特征匹配 openCV sift
	private String identificationAlgorithmType = "RGB";
	// 进程名
	private String process = "夜神模拟器";
	// 识别次数
	private Integer re_num = 60;
	// 识别开始间隔  1s
	private Integer start_time = 1;
	// 识别结束间隔  2s
	private Integer end_time = 2;
	//  是否显示不识别的日志
	private Boolean boole = true;
	//  是否点击图片
	private boolean isClick = true;
	//  相似系数  平方差法系数 0为完全匹配
	private Double coefficient = 2E-11;
	//  特征点  默认4个特征点
	private int characteristicPoint = 4;
	
	public ImageRecParam () {
	}
	
	/***
	 * @description: 监控参数  算法为0 每分钟识别一次 不显示未识别日志
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramMonitoring (String process) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("RGB");
		imageRecParam.setProcess(process);
		imageRecParam.setRe_num (1);
		imageRecParam.setBoole (false);
		return imageRecParam;
	}
	
	/***
	 * @description: openCV  模板匹配   归一化平方差匹配  值越小越好
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramTM_SQDIFF_NORMED (String process,Double coefficient) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("TM_SQDIFF_NORMED");
		imageRecParam.setProcess(process);
		imageRecParam.setRe_num (20);
		imageRecParam.setCoefficient (coefficient);
		return imageRecParam;
	}
	
	/***
	 * @description: RGB识别参数  算法为0  识别20次 不点击
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramRGBNotClick (String process,int re_num) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("RGB");
		imageRecParam.setProcess(process);
		imageRecParam.setRe_num (re_num);
		imageRecParam.setClick (false);
		return imageRecParam;
	}
	
	/***
	 * @description: 识别参数  算法为0  识别20次
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramRGB (String process) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("RGB");
		imageRecParam.setProcess(process);
		imageRecParam.setRe_num (20);
		return imageRecParam;
	}
	
	/***
	 * @description: RGB识别  识别次数
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramRGB (String process,int re_num) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("RGB");
		imageRecParam.setProcess(process);
		imageRecParam.setRe_num (re_num);
		return imageRecParam;
	}
	
	/***
	 * @description: openCV sift 特征匹配 识别参数  算法为2  识别20次
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFTNotClick (String process) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("SIFT");
		imageRecParam.setProcess(process);
		imageRecParam.setRe_num (20);
		imageRecParam.setClick (false);
		imageRecParam.setCoefficient (0.7);
		return imageRecParam;
	}
	
	/***
	 * @description: openCV sift 特征匹配  识别20次  点击
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFT (String process) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("SIFT");
		imageRecParam.setProcess(process);
		imageRecParam.setRe_num (20);
		imageRecParam.setCoefficient (0.7);
		return imageRecParam;
	}
	
	/***
	 * @description: openCV sift 特征匹配 特征点  默认识别 20次
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFT (String process,int characteristicPoint) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("SIFT");
		imageRecParam.setCharacteristicPoint (characteristicPoint);
		imageRecParam.setProcess(process);
		imageRecParam.setRe_num (20);
		imageRecParam.setCoefficient (0.7);
		return imageRecParam;
	}
	
	/***
	 * @description: openCV sift 特征匹配 相似度+特征点  默认识别 20次
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFT (String process,double coefficient, int characteristicPoint) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("SIFT");
		imageRecParam.setProcess(process);
		imageRecParam.setCharacteristicPoint (characteristicPoint);
		imageRecParam.setRe_num (20);
		imageRecParam.setCoefficient (coefficient);
		return imageRecParam;
	}
	
	/***
	 * @description: openCV sift 特征匹配 识别次数+特征点
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFT (String process,int re_num, int characteristicPoint) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("SIFT");
		imageRecParam.setProcess(process);
		imageRecParam.setCharacteristicPoint (characteristicPoint);
		imageRecParam.setRe_num (re_num);
		imageRecParam.setCoefficient (0.7);
		return imageRecParam;
	}
	/***
	 * @description: openCV sift 特征匹配 识别次数+特征点
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFT (String process,int start_time,int end_time,int characteristicPoint) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("SIFT");
		imageRecParam.setProcess(process);
		imageRecParam.setCharacteristicPoint (characteristicPoint);
		imageRecParam.setCoefficient (0.7);
		imageRecParam.setStart_time (start_time);
		imageRecParam.setEnd_time (end_time);
		return imageRecParam;
	}
	
	/***
	 * @description: openCV sift 特征匹配  识别特征点
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFTNotClick (String process,int characteristicPoint) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("SIFT");
		imageRecParam.setProcess(process);
		imageRecParam.setCharacteristicPoint (characteristicPoint);
		imageRecParam.setRe_num (20);
		imageRecParam.setClick (false);
		imageRecParam.setCoefficient (0.7);
		return imageRecParam;
	}
	
	
	
	/***
	 * @description: openCV sift 特征匹配    识别次数+识别特征点  不点击
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFTNotClick (String process,int re_num, int characteristicPoint) {
		ImageRecParam imageRecParam = new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType ("SIFT");
		imageRecParam.setProcess(process);
		imageRecParam.setCharacteristicPoint (characteristicPoint);
		imageRecParam.setRe_num (re_num);
		imageRecParam.setClick (false);
		imageRecParam.setCoefficient (0.7);
		return imageRecParam;
	}
	

	
}
