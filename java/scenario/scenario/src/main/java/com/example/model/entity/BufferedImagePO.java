package com.example.model.entity;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.awt.image.BufferedImage;

/**
 * @Classname BufferedImagePO
 * @Description BufferedImage图片类
 * @Date 2023/1/4 21:33
 * @Created by orcakill
 */
@Data
public class BufferedImagePO {
	
	private int ImageNumber; /*图片序号*/
	private String ImageName; /*图片名称*/
	@NotBlank (message = "图片不能为空")
	private BufferedImage Image; /*图片*/
	
	public BufferedImagePO (int ImageNumber, String ImageName, BufferedImage Image) {
		this.ImageNumber = ImageNumber;
		this.ImageName = ImageName;
		this.Image = Image;
	}
}
