package com.example.util;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.example.util.ScanningProcess.scanningProcess;


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
	public  static BufferedImage screenshotBack(String process){
		HWND hwnd = User32.INSTANCE.FindWindow (null, process);
		// 获取屏幕尺寸
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		BufferedImage screenshot = scanningProcess(hwnd,windows_width,windows_height);
		return  screenshot;
	}
}
