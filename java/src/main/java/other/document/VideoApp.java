package other.document;

import other.document.service.FindService;
import other.document.service.impl.FindServiceImpl;
import other.document.utils.FileParseUtil;

import java.util.Date;
import java.util.Map;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName VideoApp.java
 * @Description TODO
 * @createTime 2021年11月01日 18:01:00
 */
public class VideoApp {
	public static void main(String[] args) throws Exception{
		Date date=new Date ();
//	   通过url获取视频
       FindService.findVideo ();
//	   将视频转换为avi,h264格式
//	   视频文件压缩
//	   视频文件解压缩
//	   获取视频的格式信息，分别是初始文件，转换后文件，解压后文件
	    Map map= FileParseUtil.getEncodingFormat ("D:\\test\\video_find\\a.flv");
		System.out.println ((System.currentTimeMillis ()-date.getTime ())/1000+"秒");
	}

}
