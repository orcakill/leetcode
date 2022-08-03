package com.example.util;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import java.awt.*;
import java.awt.image.BufferedImage;

import static other.scenario.util.ScanningProcess.scanningProcess;

public class Screenshot {
// 	前台截图，截全屏
	public  static BufferedImage screenshot() throws AWTException {
		Robot robot=new Robot ();
//      获取屏幕尺寸截屏
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		Rectangle rectangle=new Rectangle(windows_width, windows_height);
		return robot.createScreenCapture (rectangle);
	}
//	后台截图
	public  static BufferedImage screenshotBack(){
		HWND hwnd = User32.INSTANCE.FindWindow (null, "夜神模拟器");
//		if (hwnd == null)
//		{
//			System.out.println("没有找到");
//		}
//		else
//		{
//			System.out.println("找到了窗口句柄了"+hwnd);
//		}
		
		// 获取屏幕尺寸
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		BufferedImage screenshot = scanningProcess(hwnd,windows_width,windows_height);
		// 写入文件
//		try {
//			ImageIO.write(screenshot, "jpg", new File("D://123.jpg"));
//			System.out.println("截屏成功！");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return  screenshot;
	}
	
	
	
}
