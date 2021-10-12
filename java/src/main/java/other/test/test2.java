package other.test;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.leptonica.global.lept;
import org.bytedeco.tesseract.TessBaseAPI;


/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName test2.java
 * @Description TODO
 * @createTime 2021年10月12日 13:44:00
 */
public class test2 {
	public static String OCR(String lng,String dataPath,String imagePath) {
		TessBaseAPI api=new TessBaseAPI ();
		if (api.Init(dataPath, lng)!=0){
			System.out.println("error");
		}
		PIX image= lept.pixRead (imagePath);
		if (image==null){
			return "";
		}
		api.SetImage(image);
		BytePointer outText=api.GetUTF8Text ();
		String result=outText.getString();
		api.End();
		outText.deallocate();
		lept.pixDestroy(image);
		return result;
	}
	
	public static void main(String[] args) {
		String a="D:\\software\\tesseract-ocr\\tessdata";
		String b="D:\\project\\leetcode\\java\\src\\main\\resources\\image\\scenario\\捕获.png";
		String text= OCR("chi_sim", a, b);
		System.out.println(text);
	}
	
}
