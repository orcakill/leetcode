package other.document.compress;

import other.document.utils.VideoUtil;

import static other.document.compress.FileZip.ZipCompress;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName compress.java
 * @Description TODO
 * @createTime 2021年11月01日 13:04:00
 */
public class compress {
	public static void main(String[] args) throws Exception {
		String s1="D:\\test\\video_high\\a.mp4";
		String s2="D:\\test\\video_compress\\a.zip";
		//ZipCompress(s1,s2);
		String s3="D:\\test\\video_high";
		String s4="a.mp4";
		String s5="d.mp4";
		VideoUtil.compressionVideo (s3,s4,s5);
		String s6="D:\\test\\video_high\\b.mp4";
		String s7="D:\\test\\video_compress\\b.7z";
		//File7z.Compress7z (s6,s7);
	}
}
