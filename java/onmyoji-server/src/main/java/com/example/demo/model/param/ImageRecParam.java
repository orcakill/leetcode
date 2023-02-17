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
	private Integer identificationAlgorithmType=0;
	// 进程名
	private String process="夜神模拟器";
	// 识别次数
	private Integer re_num=60;
	// 识别开始间隔  1s
	private Integer start_time=1;
	// 识别结束间隔  2s
	private Integer end_time=2;
	//  是否显示不识别的日志
	private Boolean boole=true;
	//  是否点击图片
	private boolean isClick=true;
	//  相似系数  平方差法系数 0为完全匹配
	private Double coefficient=2E-11;
	//  特征点  默认4个特征点
	private int characteristicPoint=4;
	
	public ImageRecParam () {
	}
	//监控参数常量
	public static final ImageRecParam paramMonitoring=paramMonitoring ();
	public  final static ImageRecParam paramRGB=paramRGB ();
	public  final static ImageRecParam paramSIFT=paramSIFT ();
	
	/***
	 * @description: 监控参数  算法为0 每分钟识别一次 不显示未识别日志
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	protected static ImageRecParam paramMonitoring (){
		ImageRecParam imageRecParam=new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType (0);
		imageRecParam.setRe_num (1);
		imageRecParam.setBoole (false);
		return  imageRecParam;
	}
	
	/***
	 * @description: RGB识别参数  算法为0  识别10次
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	protected static ImageRecParam paramRGB (){
		ImageRecParam imageRecParam=new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType (0);
		imageRecParam.setRe_num (10);
		return  imageRecParam;
	}
	
	
	/***
	 * @description:  openCV sift 特征匹配 识别参数  算法为2  识别10次
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	protected static ImageRecParam paramSIFT (){
		ImageRecParam imageRecParam=new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType (2);
		imageRecParam.setRe_num (10);
		return  imageRecParam;
	}
	/***
	 * @description:  openCV sift 特征匹配 识别参数  算法为2  识别10次
	 * @return: com.example.demo.model.param.ImageRecParam
	 * @author: orcakill
	 * @date: 2023/2/17 1:43
	 */
	public static ImageRecParam paramSIFT (int characteristicPoint){
		ImageRecParam imageRecParam=new ImageRecParam ();
		imageRecParam.setIdentificationAlgorithmType (2);
		imageRecParam.setCharacteristicPoint (characteristicPoint);
		imageRecParam.setRe_num (10);
		return  imageRecParam;
	}
	
	

}
