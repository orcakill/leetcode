package com.example.util;

import com.example.model.entity.PictureIdentifyWorkPO;

import java.util.List;

/**
 * @Classname ListNoNull
 * @Description 集合不为空，且无空值
 * @Date 2023/1/14 19:59
 * @Created by orcakill
 */
public class ListNoNull {
	public static boolean pictureIdentifyWorkPOSEmpty (List<PictureIdentifyWorkPO> pictureIdentifyWorkPOS) {
		if (pictureIdentifyWorkPOS != null && !pictureIdentifyWorkPOS.isEmpty ()) {
			for (PictureIdentifyWorkPO pictureIdentifyWorkPO : pictureIdentifyWorkPOS) {
				if (pictureIdentifyWorkPO.getX () > 0 && pictureIdentifyWorkPO.getY () > 0) {
					return true;
				}
			}
		}
		return false;
		
	}
}
