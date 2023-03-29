package com.example.demo.model.entity;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @Classname BufferedImagePO
 * @Description BufferedImage图片类
 * @Date 2023/1/4 21:33
 * @Created by orcakill
 */
@Data
public class PictureCollectionPO {
	
	private int ImageNumber; /*图片序号*/
	private String ImageHome; /*图片目录*/
	private String ImageName; /*图片名称*/
	
	private BufferedImage Image; /*图片BufferedImage*/
	private  int[][]  twoArray;/*图片二维数组*/
	
	public PictureCollectionPO (int imageNumber, String imageHome, String imageName, BufferedImage image,
	                            int[][] twoArray) {
		ImageNumber = imageNumber;
		ImageHome = imageHome;
		ImageName = imageName;
		Image = image;
		this.twoArray = twoArray;
	}
	
	public PictureCollectionPO () {
	
	}
}
