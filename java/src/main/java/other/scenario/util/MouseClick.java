package other.scenario.util;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.entity.PictureIdentifyWorkPO;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;

import com.sun.jna.platform.win32.WinDef.HWND;

import javax.resource.spi.work.Work;

public class MouseClick {
	private static final Logger logger = LogManager.getLogger (MouseClick.class);
	
	//点击坐标
	public static void mouseClicks (List<PictureIdentifyWorkPO> findAllImgData) throws AWTException {
		Double bl = ComputerScaling.getScale ();
		for (PictureIdentifyWorkPO findAllImgDatum : findAllImgData) {
			Robot robot1 = new Robot ();
			logger.debug ("目标坐标" + (int) (findAllImgDatum.getX () / bl) + "---" + (int) (findAllImgDatum.getY () / bl));
			// 修复JDK8的移动不正确的BUG(亲测在JDK11,该BUG已经修复)
			for (int j = 0; j < 6; j++) {
				robot1.mouseMove ((int) (findAllImgDatum.getX () / bl), (int) (findAllImgDatum.getY () / bl));
			}
			Point p = MouseInfo.getPointerInfo ()
			                   .getLocation ();
			logger.debug ("当前坐标：" + p.getX () + "---" + p.getY ());
			robot1.mousePress (InputEvent.BUTTON1_MASK);
			robot1.mouseRelease (InputEvent.BUTTON1_MASK);
		}
		
	}
	
	//根据当前坐标点击,传0就是原地点击，x1、y1不为0会产生移动
	public static void mouseClickNow (double x1, double y1) throws AWTException {
		Robot robot1 = new Robot ();
		Point p = MouseInfo.getPointerInfo ()
		                   .getLocation ();
		logger.debug ("当前坐标：" + p.getX () + "---" + p.getY ());
		double x = p.getX ();
		double y = p.getY ();
		// 修复JDK8的移动不正确的BUG(亲测在JDK11,该BUG已经修复)
		for (int j = 0; j < 6; j++) {
			robot1.mouseMove ((int) (x + x1), (int) (y + y1));
		}
		
		robot1.mousePress (InputEvent.BUTTON1_MASK);
		robot1.mouseRelease (InputEvent.BUTTON1_MASK);
		
	}
	
	//移动到坐标后，滚动一定刻度后单击
	public static void mouseRoll (List<PictureIdentifyWorkPO> findAllImgData, Integer num, Integer wheelAmt) throws
	                                                                                                         AWTException,
	                                                                                                         InterruptedException {
		Robot robot1 = new Robot ();
		for (int i = 0; i < num; i++) {
			Double bl = ComputerScaling.getScale ();
			for (PictureIdentifyWorkPO findAllImgDatum : findAllImgData) {
				// 修复JDK8的移动不正确的BUG(亲测在JDK11,该BUG已经修复)
				for (int j = 0; j < 6; j++) {
					robot1.mouseMove ((int) (findAllImgDatum.getX () / bl), (int) (findAllImgDatum.getY () / bl));
				}
				Thread.sleep (2000);
				robot1.mouseWheel (wheelAmt);
			}
			
		}
		Thread.sleep (2000);
		robot1.mousePress (InputEvent.BUTTON1_MASK);
		robot1.mouseRelease (InputEvent.BUTTON1_MASK);
		
	}
	
//	向后台进程发送鼠标点击事件
	public  static  void  mouseClickBack(List<PictureIdentifyWorkPO> pictureIdentifyWorkPOList) throws AWTException {
		HWND hwnd = User32.INSTANCE.FindWindow (null, "夜神模拟器");
		mouseClickBackground (hwnd,pictureIdentifyWorkPOList);
	}
	
	
	
	/**
	 * 本方法可以向后台进程窗口发送鼠标事件从而实现后台操作游戏
	 */
	public static void mouseClickBackground (HWND hwnd,List<PictureIdentifyWorkPO> mouseMessages) throws AWTException {
		Double bl = ComputerScaling.getScale ();
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
			Integer in = Integer.valueOf (Y.toString () + X, 16);
			WinDef.LPARAM lPARAM = new WinDef.LPARAM (in);
			try {
				// 模拟计算鼠标按下的间隔并且按下鼠标
				Thread.sleep (moveTime);
				ScanningProcess.User32.INSTANCE.PostMessage (hwnd, 513, new WinDef.WPARAM (513), lPARAM);
				ScanningProcess.User32.INSTANCE.PostMessage (hwnd, 514, new WinDef.WPARAM (514), lPARAM);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace ();
				
			}
			
		}
		
	}
	
}
