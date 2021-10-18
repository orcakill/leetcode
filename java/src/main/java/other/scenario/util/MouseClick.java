package other.scenario.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.entity.PictureIdentifyWorkPO;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;

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
		Double bl = ComputerScaling.getScale ();
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
				Point p = MouseInfo.getPointerInfo ()
				                   .getLocation ();
				Thread.sleep (2000);
				robot1.mouseWheel (wheelAmt);
			}
			
		}
		Thread.sleep (2000);
		robot1.mousePress (InputEvent.BUTTON1_MASK);
		robot1.mouseRelease (InputEvent.BUTTON1_MASK);
		
	}
	
}
