package other.test;



import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName test3.java
 * @Description TODO
 * @createTime 2022年11月04日 08:20:00
 */
public class test3 {
	
	public static void main (String[] args) throws IOException {
		// 创建实例
		ITesseract instance = new Tesseract ();
		
		// 设置识别语言
		
		instance.setLanguage ("chi_sim");
		
		// 设置识别引擎
		
		instance.setOcrEngineMode (1);
		
		// 读取文件
		File file=new File ("D:/a.jpg");
		BufferedImage image = ImageIO.read (file);
		try {
			
			// 识别
			
			String result = instance.doOCR (image);
			System.out.println (result);
		} catch (TesseractException e) {
			System.err.println (e.getMessage ());
		}
		
	}
	
}
