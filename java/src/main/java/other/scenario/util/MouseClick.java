package other.scenario.util;

import net.coobird.thumbnailator.Thumbnails;
import other.scenario.entity.PictureIdentifyWorkPO;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class MouseClick {
	public  static  void  mouseClicks(List<PictureIdentifyWorkPO> findAllImgData) throws AWTException {
		Robot robot=new Robot ();
		//      获取屏幕尺寸截屏
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		Rectangle rectangle=new Rectangle(windows_width, windows_height);
		BufferedImage winImage = robot.createScreenCapture(rectangle);
		//      不获取屏幕尺寸截屏
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		BufferedImage image = robot.createScreenCapture (screenRectangle);
		
		double x=winImage.getWidth ();
		double y=image.getWidth ();
		double bl=x/y;
		
		for (PictureIdentifyWorkPO findAllImgDatum : findAllImgData) {
			Robot robot1 = new Robot ();
			
			Point p = MouseInfo.getPointerInfo ()
			                   .getLocation ();
			System.out.println (p.getX () + "---" + p.getY ());
			// 修复JDK8的移动不正确的BUG(亲测在JDK11,该BUG已经修复)
			for (int j = 0; j < 6; j++) {
				robot1.mouseMove ((int) (findAllImgDatum.getX () / bl), (int) (findAllImgDatum.getY () / bl));
				System.out.println ((int) (findAllImgDatum.getX () / bl) + "---" + (int) (findAllImgDatum.getY () / bl));
			}
			robot1.mousePress (InputEvent.BUTTON1_MASK);
			robot1.mouseRelease (InputEvent.BUTTON1_MASK);
		}
		
	}
}
