package com.example.demo.model.map;


import com.example.demo.model.entity.PictureIdentifyWorkPO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CoordinateAddress
 * @Description 坐标获取
 * @Date 2021/12/2 21:53
 * @Created by orcakill
 */
public class CoordinateAddress {
	
	public static List<PictureIdentifyWorkPO> getCoordinate (String name){
		List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList=new ArrayList<> ();
		PictureIdentifyWorkPO pictureIdentifyWorkPO=new PictureIdentifyWorkPO ();
		//判断当前系统的分辨率
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		if(windows_width==2560){
			if(name.equals ("御魂加成")){
				pictureIdentifyWorkPO.setX (1567);
				pictureIdentifyWorkPO.setY (487);
			}
			if(name.equals ("地面点击")){
				pictureIdentifyWorkPO.setX (1464);
				pictureIdentifyWorkPO.setY (1425);
			}
		}
		else if(windows_width==1920){
			if(name.equals ("御魂加成")){
				pictureIdentifyWorkPO.setX (1169);
				pictureIdentifyWorkPO.setY (312);
			}
		}
		pictureIdentifyWorkPOList.add(pictureIdentifyWorkPO);
		//根据分辨率添加坐标
		return  pictureIdentifyWorkPOList;
	}
}
