package other.scenario.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.entity.PictureIdentifyWorkPO;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

public class MouseClick {
	private static final Logger logger = LogManager.getLogger (MouseClick.class);
	
	public  static  void  mouseClicks(List<PictureIdentifyWorkPO> findAllImgData) throws AWTException {
		Double bl=ComputerScaling.getScale ();
		for (PictureIdentifyWorkPO findAllImgDatum : findAllImgData) {
			Robot robot1 = new Robot ();
			

			logger.debug ("目标坐标"+(int) (findAllImgDatum.getX () / bl)+ "---" + (int) (findAllImgDatum.getY () / bl));
			// 修复JDK8的移动不正确的BUG(亲测在JDK11,该BUG已经修复)
			for (int j = 0; j < 6; j++) {
				robot1.mouseMove ((int) (findAllImgDatum.getX () / bl), (int) (findAllImgDatum.getY () / bl));
			}
			Point p = MouseInfo.getPointerInfo ()
			                   .getLocation ();
			logger.debug ("当前坐标："+p.getX () + "---" + p.getY ());
			robot1.mousePress (InputEvent.BUTTON1_MASK);
			robot1.mouseRelease (InputEvent.BUTTON1_MASK);
		}
		
	}
	

}
