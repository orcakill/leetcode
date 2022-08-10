package other.test;


import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;
import other.scenario.util.Screenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.bytedeco.leptonica.global.lept.pixDestroy;
import static org.bytedeco.leptonica.global.lept.pixRead;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ImageOcr.java
 * @Description TODO
 * @createTime 2021年10月12日 16:42:00
 */
public class ImageOcr {
		public static void main(String[] args) throws Exception{
			System.out.println("开始识别");
			long starttime = System.currentTimeMillis();
			//		屏幕截图
			//BufferedImage Window= Screenshot.screenshotBack ();
			//ImageIO.write (Window, "jpg", new File ("D://123.jpg"));
			//获取当前程序执行绝对路径。
			//String langpath="D:\\software\\tesseract-ocr\\tessdata";
			//String imgeurl="D:\\123.jpg";
			//String text = Ocr("chi_sim",langpath, imgeurl);
			
			//获取当前程序执行绝对路径。
			String currentfile=System.getProperty("user.dir");
			String text = Ocr("chi_sim", currentfile, "D:\\123.jpg");
			System.out.println("discriminate interval:" + (System.currentTimeMillis() - starttime) + ",ocr text:" + text);
		}
		
		public static String Ocr(String lang, String langpath, String imageurl) throws UnsupportedEncodingException {
			//创建字节容器。
			BytePointer outtext;
			//启动识别器
			TessBaseAPI ocrapi = new TessBaseAPI ();
			//初始化识别器的语言包
			if (ocrapi.Init(langpath, lang) != 0) {
				System.out.println("Could not initialize tesseract");
				return null;
			}
			//读取源图片
			PIX image = pixRead (imageurl);
			//识别器装入图片
			ocrapi.SetImage(image);
			//识别器识别进行段。
			outtext = ocrapi.GetUTF8Text();
			try {
				return outtext.getString("utf-8");
			} finally {
				//最后释放资源
				ocrapi.End();
				outtext.deallocate();
				pixDestroy(image);
			}
		}
	
}
