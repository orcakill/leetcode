package com.example.demo.model.entity;

import java.awt.image.BufferedImage;
import javax.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @Classname BufferedImagePO
 * @Description BufferedImage图片类
 * @Date 2023/1/4 21:33
 * @Created by orcakill
 */
@Data
public class PictureCollectionPO {
	
	private int ImageNumber; /*图片序号*/
	private String ImageName; /*图片名称*/
	
	@NotBlank(message = "图片不能为空")
	private BufferedImage Image; /*图片BufferedImage*/
	
	private  int[][]  twoArray;/*图片二维数组*/
	
	public PictureCollectionPO (int imageNumber, String imageName, BufferedImage image,
	                            int[][] twoArray) {
		ImageNumber = imageNumber;
		ImageName = imageName;
		Image = image;
		this.twoArray = twoArray;
	}
}
