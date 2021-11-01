package other.document.find.video_1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import other.mail.util.GetJson;
import other.mail.util.HttpClient;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author orcakill
 * @date 2021/4/15  16:48
 **/
public class video_1 {
       /*爬取b站的1个视频,使用手动获取url*/
       public static void main(String[] args) throws Exception {
           System.out.println("开始");
           long start = System.currentTimeMillis();
           /**
            *  从json中获取到的 url
            *  请获取后手动填写
            */
           String url="https://api.bilibili.com/x/player/playurl?cid=111804822&otype=json&avid=58906853&fnver=0&fnval" +
                      "=2&player=1&qn=112\n";
           String  str= GetJson.getHttpJson (url);
           JSONObject jsonObject=JSONObject.parseObject (str);
           JSONArray  jsonArray=jsonObject.getJSONObject ("data").getJSONArray ("durl");
           String lastUrl =jsonArray.getJSONObject (0).get ("url").toString ();
          //自定义文件名称
          
           String fileName = "a.flv";
           downloadMovie(lastUrl, fileName);

           long end = System.currentTimeMillis();
           System.out.println("完成 ");
           System.err.println("总共耗时：" + (end - start) / 1000 + "s");
       }

    public static void downloadMovie(String BLUrl, String fileName) {
        InputStream inputStream = null;
        try {
            URL url = new URL(BLUrl);
            URLConnection urlConnection = url.openConnection();
            // 填需要爬取的av号
            urlConnection.setRequestProperty("Referer", "https://www.bilibili.com/video/av58906853");
            urlConnection.setRequestProperty("Sec-Fetch-Mode", "no-cors");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            urlConnection.setConnectTimeout(10 * 1000);
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //定义路径
        String path = "D:\\test\\video_find\\" + fileName;
        File file = new File(path);
        int i = 1;
        try {
            assert inputStream != null;
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bys = new byte[1024];
            int len;
            while ((len = bis.read(bys)) != -1) {
                bos.write(bys, 0, len);
                System.out.println("写入第 " + i++ + "次");
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
