package other.document.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName GetVideoTime.java
 * @Description TODO
 * @createTime 2021年11月01日 12:33:00
 */
public class GetVideoTime {
	public static void main(String[] args) {
		//ffmepg工具地址
		String ffmpegPath = "D:/study/utils/ffmpeg/bin/ffmpeg.exe";
		//视频文件地址
		String videoPath = "F:\\test\\video_high\\b.mp4";
//        拼接cmd命令语句
		StringBuffer buffer = new StringBuffer();
		buffer.append(ffmpegPath);
		//注意要保留单词之间有空格
		buffer.append(" -i ");
		buffer.append(videoPath);
//        执行命令语句并返回执行结果
		try {
			Process process = Runtime.getRuntime().exec(buffer.toString());
			InputStream in = process.getErrorStream ();
			BufferedReader br = new BufferedReader(new InputStreamReader (in));
			String line ;
			while((line=br.readLine())!=null) {
				if(line.trim().startsWith("Duration:")){
					//根据字符匹配进行切割
					System.out.println("视频时间 = " + line.trim().substring(0,line.trim().indexOf(",")));
				}
				//一般包含fps的行就包含分辨率
				if(line.contains("fps")){
					//根据
					String definition = line.split(",")[2];
					definition = definition.trim().split(" ")[0];
					System.out.println("分辨率 = " + definition);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
