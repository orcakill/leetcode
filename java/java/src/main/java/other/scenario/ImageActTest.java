package other.scenario;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.util.MouseClick;
import other.scenario.util.RandomUtil;
import other.scenario.util.Screenshot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static other.scenario.util.ImagesBackRec.FindAllImgData;
import static other.scenario.util.ImagesBackRec.imageToDate;
import static other.scenario.util.ScanningProcess.scanningProcess;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageActTest.java
 * @Description TODO
 * @createTime 2022年10月20日 14:00:00
 */
public class ImageActTest {
	
	public static void main (String[] args) throws AWTException {
		imagesActTest("scenario/测试图片/动态图片测试1");
		imagesActTest("scenario/测试图片/动态图片测试2");
	}
	
	public static boolean imagesActTest (String FolderName) throws AWTException {
		//		屏幕截图
		BufferedImage Window = screenshotBack ();
		//		图片
		java.util.List<int[][]> ImagesData = imageToDate (FolderName);
		//		屏幕截图和图片对比
		java.util.List<PictureIdentifyWorkPO> mouseXY = FindAllImgData (Window, ImagesData);
		//		鼠标点击
		List<PictureIdentifyWorkPO> mouseXY1 = new ArrayList<> ();
		//		鼠标点击
		if (mouseXY.size () > 0) {
			int num = RandomUtil.randomMinute (mouseXY.size ());
			mouseXY1.add (mouseXY.get (num));
			MouseClick.mouseClickBack (mouseXY1);
			return true;
		}
		return false;
	}
	
	public  static BufferedImage screenshotBack(){
		WinDef.HWND hwnd = User32.INSTANCE.FindWindow (null, "夜神模拟器");
		// 获取屏幕尺寸
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		int windows_width =gd.getDisplayMode ().getWidth ();
		int windows_height = gd.getDisplayMode().getHeight();
		BufferedImage screenshot = scanningProcess(hwnd,windows_width,windows_height);
		return  screenshot;
	}
	
	
	
}
