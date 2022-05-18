package other.scenario.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ComputerScaling {
	public  static Double getScale() throws AWTException {
		Robot robot=new Robot ();
		//      获取屏幕尺寸截屏
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		Rectangle rectangle=new Rectangle(windows_width, windows_height);
		BufferedImage winImage = robot.createScreenCapture (rectangle);
		//      不获取屏幕尺寸截屏
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		BufferedImage image = robot.createScreenCapture (screenRectangle);
		
		double x=winImage.getWidth ();
		double y=image.getWidth ();
		
		return x / y;
	}
}
