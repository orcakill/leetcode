package other.scenario.service;

import other.scenario.map.ExeAddress;
import other.scenario.util.StartUpExeUtils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.Random;

public class LoginService {
	public static void loginService () throws Exception {
//	    启动程序
		StartUpExeUtils.startUpExe ("CMD /C " + ExeAddress.exeAddress (), "onmyoji.exe");
//      鼠标单击桌面最中央一次
		Point point = MouseInfo.getPointerInfo().getLocation();
		System.out.println("初始位置：x=" + point.getX() + ",y="+ point.getY());
		Robot robot = new Robot ();
		robot.delay (20000);
		robot.mouseMove (850, 500);
		Point point1 = MouseInfo.getPointerInfo().getLocation();
		System.out.println("移动后位置：x=" + point1.getX() + ",y="+ point1.getY());
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay (500);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		System.out.println ("点击成功");
	}
}
