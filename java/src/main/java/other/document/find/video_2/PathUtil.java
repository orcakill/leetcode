package other.document.find.video_2;

import java.io.File;


/**
 * @author orcakill
 * @date 2021/5/6  18:21
 **/
public class PathUtil {
    // 创建视频路径
    public static String createMoviePath(String title) {
        System.out.println("开始创建视频路径");
        //图片名称
        title= title.replaceAll("[/\\\\:*?|]", ".");
        title =title.replaceAll("[\"<>]", "'");

        String movieName = title + ".mp4";
        //创建路径
        String path = "F://test//video_find";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = dir + File.separator + movieName;
        System.out.println("视频路径：" + fileName);

        return fileName;
    }

}
