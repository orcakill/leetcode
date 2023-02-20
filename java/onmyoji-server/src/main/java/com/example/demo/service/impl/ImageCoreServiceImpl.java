package com.example.demo.service.impl;

import com.example.demo.model.entity.PictureCollectionPO;
import com.example.demo.model.map.FolderPathMap;
import com.example.demo.model.param.MultipleImageParam;
import com.example.demo.model.param.MultipleImagesParam;
import com.example.demo.utils.ImagesBackRecUtils;
import com.example.demo.utils.ImagesOpenCVSIFTUtils;
import com.example.demo.utils.ImagesOpenCVUtils;
import com.example.demo.utils.ReadFileUtils;
import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utils.RandomUtils.getRandom;

/**
 * @Classname ImageCoreServiceImpl
 * @Description 图像识别核心实现类
 * @Date 2023/1/26 22:33
 * @Created by orcakill
 */
@Log4j2
public class ImageCoreServiceImpl {
	/***
	 * @description: 识别并点击成功或识别成功返回true、识别失败返回false
	 * @param identificationAlgorithmType  图像识别算法类型（目前3种）
	 * @param folder  文件夹
	 * @param process 进程名
	 * @param re_num  识别次数
	 * @param i1      识别开始间隔 如 1
	 * @param i2      识别结束间隔 如 2
	 * @param b       是否显示日志 如 true 或false
	 * @param isClick       是否点击如 true 或false
	 * @param coefficient      相似系数  （1，2 算法专用）
	 * @param characteristicPoint 特征点 （2 算法专用）
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/1/26 22:26
	 */
	public static boolean imagesBackClick (String folder, String identificationAlgorithmType, String process, int re_num,
	                                       int i1, int i2, boolean b, boolean isClick, Double coefficient,
	                                       int characteristicPoint)
			throws AWTException, IOException, InterruptedException {
		int num_time;
		boolean result;
		String path = FolderPathMap.folderPath ("图片总路径");
		List<PictureCollectionPO> pictureCollectionPOList =
				ReadFileUtils.readPictureCollectionPOList (path, folder, identificationAlgorithmType);
		for (int i = 0; i < re_num; i++) {
			num_time = getRandom (i1, i2);
			//每20次重新初始化数据集
			if (i % 20 == 0 && i > 0) {
				pictureCollectionPOList =
						ReadFileUtils.readPictureCollectionPOList (path, folder, identificationAlgorithmType);
			}
			Thread.sleep (num_time * 1000L);
			result = isResult (identificationAlgorithmType, process, isClick, coefficient, characteristicPoint,
			                   pictureCollectionPOList);
			if (result) {
				log.info ("图片识别成功");
				Thread.sleep (num_time * 1000L);
				return true;
			}
			else {
				if (b) {
					log.info ("在间隔{}秒的检测中，第{}次检查未发现 {} 的图片", num_time, (i + 1), folder);
				}
			}
		}
		return false;
	}
	
	/***
	 * @description: 识别并点击成功或识别成功返回true、识别失败返回false
	 * @param multipleImagesParam  多组图片不同算法识别
	 * @return: boolean
	 * @author: orcakill
	 * @date: 2023/1/26 22:26
	 */
	public static String imagesBackClick (MultipleImagesParam multipleImagesParam)
			throws AWTException, IOException, InterruptedException {
		int num_time;
		boolean result;
		String path = FolderPathMap.folderPath ("图片总路径");
		List<PictureCollectionPO> pictureCollectionPOList = new ArrayList<> ();
		for (int i = 0; i < multipleImagesParam.getRe_num (); i++) {
			num_time = getRandom (multipleImagesParam.getStart_time (), multipleImagesParam.getEnd_time ());
			for (MultipleImageParam multipleImageParam : multipleImagesParam.getMultipleImageParamList ()) {
				//每20次重新初始化数据集
				if (i % 20 == 0) {
					pictureCollectionPOList =
							ReadFileUtils.readPictureCollectionPOList (path, multipleImageParam.getFolder (),
							                                           multipleImageParam.getImageRecParam ()
							                                                             .getIdentificationAlgorithmType ());
				}
				Thread.sleep (num_time * 1000L);
				result = isResult (multipleImageParam.getImageRecParam ().getIdentificationAlgorithmType (),
				                   multipleImagesParam.getProcess (),
				                   multipleImagesParam.isClick (),
				                   multipleImageParam.getImageRecParam ().getCoefficient (),
				                   multipleImageParam.getImageRecParam ().getCharacteristicPoint (),
				                   pictureCollectionPOList);
				if (result) {
					log.info ("图片识别成功");
					Thread.sleep (num_time * 1000L);
					return multipleImageParam.getFolder ();
				}
				else {
					if (multipleImagesParam.getBoole ()) {
						log.info ("在间隔{}秒的检测中，第{}次检查未发现 {} 的图片", num_time, (i + 1),
						          multipleImageParam.getFolder ());
					}
				}
			}
			
		}
		return null;
	}
	
	private static boolean isResult (String identificationAlgorithmType, String process, boolean isClick,
	                                 Double coefficient, int characteristicPoint,
	                                 List<PictureCollectionPO> pictureCollectionPOList)
			throws AWTException, IOException {
		boolean result = false;
		if (identificationAlgorithmType.equals ("RGB")) {
			result = ImagesBackRecUtils.imagesRecognition (pictureCollectionPOList, process, isClick);
		}
		if (identificationAlgorithmType.equals ("TM_SQDIFF_NORMED")) {
			
			result = ImagesOpenCVUtils.imagesRecognitionOpenCv (pictureCollectionPOList, process, isClick,
			                                                    coefficient,identificationAlgorithmType);
		}
		if (identificationAlgorithmType.equals ("SIFT")) {
			result =
					ImagesOpenCVSIFTUtils.imagesRecognition (pictureCollectionPOList, process, isClick,
					                                         coefficient,
					                                         characteristicPoint);
		}
		return result;
	}
}

