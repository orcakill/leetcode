package com.example.demo.model.entity;

import lombok.Data;

/**
 * @Classname ScreenshotPointPO
 * @Description TODO
 * @Date 2023/1/7 22:37
 * @Created by orcakill
 */
@Data
public class ScreenshotPointPO {
	private String name; //键
	
	private int width;
	
	private int height;
	
	private int x;
	private int y;
	private int w;
	private int h;
	
	public ScreenshotPointPO (String name, int width, int height, int x, int y, int w, int h) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public ScreenshotPointPO () {
	
	}
}
