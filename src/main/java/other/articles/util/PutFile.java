package other.articles.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author orcakill
 * @date 2021/1/19  13:34
 **/
public class PutFile {
    public static void getPutFile(String urlPath,String downloadDir) {
        //确定爬取的网页地址
        //建立url爬取核心对象
        try {
            URL url=new URL(urlPath);
            //通过url建立与网页的连接
            URLConnection conn=url.openConnection();
            //通过链接取得网页返回的数据

            InputStream is=conn.getInputStream();
            String    s=conn.getContentType();
            String  charset=s.substring(s.indexOf("charset=")+8);
          System.out.println(conn.getContentEncoding());
            //一般按行读取网页数据，并进行内容分析
            //因此用BufferedReader和InputStreamReader把字节流转化为字符流的缓冲流
            //进行转换时，需要处理编码格式问题
            BufferedReader br=new BufferedReader(new InputStreamReader(is,charset));
            //按行读取并打印
            File file = new File(downloadDir);
            //创建本地文件操作对象
            File fileParent = file.getParentFile();
            //获取目录
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            if(file.exists()) {
                file.delete();
            }
            //文件不存在
                try {
                    //如果目标文件不存在则自动创建
                    file.createNewFile();
                    System.out.println("已自动创建文件！");
                } catch (IOException e) {
                    System.out.println("自动创建文件失败！");
                }

            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
                //创建文件输出流将读取到的网页源代码写入文件（文件流）
//                                         FileOutputStream fileOutputStream = new FileOutputStream(file,true);
//                                         fileOutputStream.write(line.getBytes(charset));
//                                         fileOutputStream.close();
                //字节流
                OutputStream out = new FileOutputStream(file,true);
                out.write(line.getBytes(charset)); //向文件中写入数据
                out.write('\r'); // \r\n表示换行
                out.write('\n');
                out.close();
            }

            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
