package other.document.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.oro.text.regex.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hy
 * @date 2019/11/7 14:05
 */
public class FileParseUtil {
	
	public static final Logger LOGGER = LoggerFactory.getLogger (FileParseUtil.class);
	
	public static void main(String[] args) throws Exception{
		Date date=new Date ();
//	   获取视频的格式信息，分别是初始文件，转换后文件，解压后文件
		Map map= FileParseUtil.getEncodingFormat ("D:\\test\\video_target\\a.flv");
		Map map1= FileParseUtil.getEncodingFormat ("D:\\test\\video_target\\b.avi");
		Map map2= FileParseUtil.getEncodingFormat ("D:\\test\\video_target\\c.mp4");
		Map map3= FileParseUtil.getEncodingFormat ("D:\\test\\video_target\\d.mp4");
		System.out.println ((System.currentTimeMillis ()-date.getTime ())/1000+"秒");
	}
	
	/**
	 * 提取音频、视频编码等信息
	 *
	 * @param filePath
	 *
	 * @return
	 */
	public static Map getEncodingFormat (String filePath) {
		String processFLVResult = processFLV (filePath);
		Map retMap = new HashMap ();
		if (StringUtils.isNotBlank (processFLVResult)) {
			PatternCompiler compiler = new Perl5Compiler ();
			try {
				String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
				String regexVideo = "Video: (.*?), (.*?), (.*?)[,\\s]";
				String regexAudio = "Audio: (.*?), (.*?)[,\\s]";
				
				Pattern patternDuration = compiler.compile (regexDuration, Perl5Compiler.CASE_INSENSITIVE_MASK);
				PatternMatcher matcherDuration = new Perl5Matcher ();
				if (matcherDuration.contains (processFLVResult, patternDuration)) {
					MatchResult re =  matcherDuration.getMatch ();
					retMap.put ("提取出播放时间", re.group (1));
					retMap.put ("开始时间", re.group (2));
					retMap.put ("bitrate 码率 单位 kb", re.group (3));
					System.out.println ("提取出播放时间 ===" + re.group (1));
					System.out.println ("开始时间 =====" + re.group (2));
					System.out.println ("bitrate 码率 单位 kb==" + re.group (3));
				}
				
				Pattern patternVideo = compiler.compile (regexVideo, Perl5Compiler.CASE_INSENSITIVE_MASK);
				PatternMatcher matcherVideo = new Perl5Matcher ();
				
				if (matcherVideo.contains (processFLVResult, patternVideo)) {
					MatchResult re = matcherVideo.getMatch ();
					retMap.put ("编码格式", re.group (1));
					retMap.put ("视频格式", re.group (2));
					retMap.put ("分辨率", re.group (3));
					System.out.println ("编码格式 ===" + re.group (1));
					System.out.println ("视频格式 ===" + re.group (2));
					System.out.println (" 分辨率 == =" + re.group (3));
				}
				
				Pattern patternAudio = compiler.compile (regexAudio, Perl5Compiler.CASE_INSENSITIVE_MASK);
				PatternMatcher matcherAudio = new Perl5Matcher ();
				
				if (matcherAudio.contains (processFLVResult, patternAudio)) {
					MatchResult re =  matcherAudio.getMatch ();
					retMap.put ("音频编码", re.group (1));
					retMap.put ("音频采样频率", re.group (2));
					System.out.println ("音频编码 ===" + re.group (1));
					System.out.println ("音频采样频率 ===" + re.group (2));
				}
			} catch (MalformedPatternException e) {
				e.printStackTrace ();
			}
		}
		return retMap;
		
	}
	
	// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	private static String processFLV (String inputPath) {
		List commend = new java.util.ArrayList ();
		
		commend.add ("D:/study/utils/ffmpeg/bin/ffmpeg.exe");//可以设置环境变量从而省去这行
		commend.add ("ffmpeg");
		commend.add ("-i");
		commend.add (inputPath);
		
		try {
			
			ProcessBuilder builder = new ProcessBuilder ();
			builder.command (commend);
			builder.redirectErrorStream (true);
			Process p = builder.start ();

//1. start
			BufferedReader buf = null; // 保存ffmpeg的输出结果流
			String line = null;
//read the standard output
			
			buf = new BufferedReader (new InputStreamReader (p.getInputStream ()));
			
			StringBuffer sb = new StringBuffer ();
			while ((line = buf.readLine ()) != null) {
				//System.out.println (line);
				sb.append (line).append ("\r\n");
				continue;
			}
			int ret = p.waitFor ();//这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
//1. end
			return sb.toString ().replaceAll ("(tv, bt709/unknown/bt709, progressive)","");
		} catch (Exception e) {
			LOGGER.error ("-- processFLV error, message is {}", e);
			return null;
		}
	}
}
