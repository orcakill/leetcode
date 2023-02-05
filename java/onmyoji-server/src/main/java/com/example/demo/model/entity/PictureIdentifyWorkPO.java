package com.example.demo.model.entity;

import lombok.Data;

@Data
public class PictureIdentifyWorkPO {
	private  Integer X;
	private  Integer Y;
	
	public PictureIdentifyWorkPO () {
	}
	
	public PictureIdentifyWorkPO (Integer x, Integer y) {
		X = x;
		Y = y;
	}
}
