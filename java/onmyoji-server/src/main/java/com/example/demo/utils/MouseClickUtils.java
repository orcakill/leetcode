package com.example.demo.utils;

import com.example.demo.model.entity.PictureIdentifyWorkPO;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MouseClickUtils {
	
	public static final Logger logger = LogManager.getLogger ("MouseClick");
	
	//后台鼠标点击，坐标集，进程名，是否点击
	public static boolean mouseClickBack (List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList,
	                                      String process, boolean isClick) throws AWTException {
		List<PictureIdentifyWorkPO> mouseXY1 = new ArrayList<> ();
		//鼠标点击
		if (pictureIdentifyWorkPOList.size () > 0) {
			if (isClick) {
				int num = RandomUtils.randomMinute (pictureIdentifyWorkPOList.size ());
				mouseXY1.add (pictureIdentifyWorkPOList.get (num));
				HWND hwnd = User32.INSTANCE.FindWindow (null, process);
				mouseClickBackground (hwnd, mouseXY1);
				logger.info ("点击完成");
			}
			return true;
		}
		return false;
		
	}
	
	//后台鼠标拖拽，坐标1，坐标2，进程名
	public static void mouseClickBackDrag (PictureIdentifyWorkPO pictureIdentifyWorkPO1,
	                                       PictureIdentifyWorkPO pictureIdentifyWorkPO2,
	                                       String process) throws AWTException {
		HWND hwnd = User32.INSTANCE.FindWindow (null, process);
		mouseClickBackgroundDrag (hwnd, pictureIdentifyWorkPO1, pictureIdentifyWorkPO2);
	}
	
	/**
	 * 本方法可以向后台进程窗口发送鼠标事件从而实现后台操作游戏
	 */
	public static void mouseClickBackground (HWND hwnd, List<PictureIdentifyWorkPO> mouseMessages)
			throws AWTException {
		Double bl = ComputerScalingUtils.getScale ();
		StringBuilder X;
		StringBuilder Y;
		int moveTime = (int) (Math.random () * 400 + 300);
		//int mousePressTime = (int) (Math.random () * 500 + 100);
		for (PictureIdentifyWorkPO mouseMessage : mouseMessages) {
			// 解析鼠标坐标参数,低位为X轴,高位为Y轴坐标
			X = new StringBuilder (Integer.toHexString ((int) (mouseMessage.getX () / bl)));
			Y = new StringBuilder (Integer.toHexString ((int) (mouseMessage.getY () / bl)));
			while (X.length () < 4) {
				X.insert (0, "0");
			}
			while (Y.length () < 4) {
				Y.insert (0, "0");
			}
			Integer in = Integer.valueOf (Y + X.toString (), 16);
			WinDef.LPARAM lPARAM = new WinDef.LPARAM (in);
			try {
				// 模拟计算鼠标按下的间隔并且按下鼠标
				Thread.sleep (moveTime);
				ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 513, new WinDef.WPARAM (513), lPARAM);
				ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 514, new WinDef.WPARAM (514), lPARAM);
				Thread.sleep (moveTime);
			} catch (InterruptedException e) {
				
				e.printStackTrace ();
				
			}
			
		}
		
	}
	
	public static void mouseClickBackgroundDrag (HWND hwnd, PictureIdentifyWorkPO mouseMessages1,
	                                             PictureIdentifyWorkPO mouseMessages2)
			throws AWTException {
		Double bl = ComputerScalingUtils.getScale ();
		StringBuilder X1;
		StringBuilder Y1;
		StringBuilder X2;
		StringBuilder Y2;
		int moveTime = (int) (Math.random () * 400 + 300);
		//int mousePressTime = (int) (Math.random () * 500 + 100);
		// 解析鼠标坐标参数,低位为X轴,高位为Y轴坐标
		X1 = new StringBuilder (Integer.toHexString ((int) (mouseMessages1.getX () / bl)));
		Y1 = new StringBuilder (Integer.toHexString ((int) (mouseMessages1.getY () / bl)));
		
		X2 = new StringBuilder (Integer.toHexString ((int) (mouseMessages2.getX () / bl)));
		Y2 = new StringBuilder (Integer.toHexString ((int) (mouseMessages2.getY () / bl)));
		
		while (X1.length () < 4) {
			X1.insert (0, "0");
		}
		while (Y1.length () < 4) {
			Y1.insert (0, "0");
		}
		
		while (X2.length () < 4) {
			X2.insert (0, "0");
		}
		while (Y2.length () < 4) {
			Y2.insert (0, "0");
		}
		
		Integer in1 = Integer.valueOf (Y1 + X1.toString (), 16);
		Integer in2 = Integer.valueOf (Y2 + X2.toString (), 16);
		
		WinDef.LPARAM lPARAM1 = new WinDef.LPARAM (in1);
		WinDef.LPARAM lPARAM2 = new WinDef.LPARAM (in2);
		try {
			// 模拟计算鼠标按下的间隔并且按下鼠标
			Thread.sleep (moveTime);
			ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 513, new WinDef.WPARAM (513), lPARAM1);
			Thread.sleep (1000);
			ScanningProcessUtils.User32.INSTANCE.PostMessage (hwnd, 514, new WinDef.WPARAM (514), lPARAM2);
			Thread.sleep (moveTime);
		} catch (InterruptedException e) {
			
			e.printStackTrace ();
			
		}
		
	}
	
}
