package other.document.transformation;

import static other.document.utils.ConvertVideo.process;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName transformation.java
 * @Description 进行格式转换，将非MP4类文件转换为mp4
 * @createTime 2021年11月01日 09:24:00
 */
public class transformation {
	public static void main(String[] args) {
		String inputPath="D:\\test\\video_find\\a.flv";
		String outputPath="D:\\test\\video_target\\a.mp4";
		String outputPath1="D:\\test\\video_middle";
		process(inputPath,outputPath,outputPath1);
	}
}
