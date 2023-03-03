package com.example.demo.model.map;

import com.example.demo.model.entity.ScreenshotPointPO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Classname ScreenshotPointEnums
 * @Description 等级提升 截图位置
 * @Date 2023/1/7 22:18
 * @Created by orcakill
 */
public class ScreenshotPointMap {
	public static ScreenshotPointPO getScreenshotPoint (String name) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width = gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode ().getHeight ();
		List<ScreenshotPointPO> screenshotPointPOS = new ArrayList<> ();
		screenshotPointPOS.add (new ScreenshotPointPO ("御魂等级强化第一部分", 2560, 1600, 1354, 727, 294, 462));
		screenshotPointPOS.add (new ScreenshotPointPO ("御魂等级强化第二部分", 2560, 1600, 1627, 727, 200, 462));
		screenshotPointPOS.add (new ScreenshotPointPO ("御魂等级强化第三部分", 2560, 1600, 1945, 727, 200, 462));
		List<ScreenshotPointPO> screenshotPointPOS1 = screenshotPointPOS.stream ().filter (
				s -> s.getName ().equals (name) && s.getWidth () == windows_width && s.getHeight () == windows_height).collect (toList ());
		return screenshotPointPOS1.get (0);
	}
}
