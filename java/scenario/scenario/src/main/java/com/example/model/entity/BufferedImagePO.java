package com.example.model.entity;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @Classname BufferedImagePO
 * @Description BufferedImage图片类
 * @Date 2023/1/4 21:33
 * @Created by orcakill
 */
@Data
public class BufferedImagePO {
	
	private String ImageNumber; /*图片序号*/
	private String ImageName; /*图片名称*/
	private BufferedImage Image; /*图片*/
	
	public BufferedImagePO (int i, String s, BufferedImage img) {
	}
}
