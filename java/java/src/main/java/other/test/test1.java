package other.test;

import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName test1.java
 * @Description TODO
 * @createTime 2021年10月12日 09:10:00
 */
public class test1 {
	
	public static void main (String[] args) throws Exception {
		String imgPath = "D:\\project\\leetcode\\java\\src\\main\\resources\\image\\scenario\\捕获.png";
		String text = FindOCR (imgPath, true);
		System.out.println (text);
	}
	
	public static String FindOCR (String srImage, boolean ZH_CN) {
		try {
			System.out.println ("开始识别");
			double start = System.currentTimeMillis ();
			File imageFile = new File (srImage);
			if (!imageFile.exists ()) {
				return "图片不存在";
			}
			BufferedImage textImage = ImageIO.read (imageFile);
			Tesseract instance = new Tesseract ();
			instance.setDatapath ("D:\\software\\tesseract-ocr\\tessdata");//设置训练库
			instance.setTessVariable("user_defined_dpi", "300");
			if (ZH_CN) {
				instance.setLanguage ("chi_sim");//中文识别
			}
			String result = null;
			result = instance.doOCR (textImage);
			double end = System.currentTimeMillis ();
			System.out.println ("耗时" + (end - start) / 1000 + " s");
			return result;
		} catch (Exception e) {
			e.printStackTrace ();
			return "发生未知错误";
		}
	}
	
}

