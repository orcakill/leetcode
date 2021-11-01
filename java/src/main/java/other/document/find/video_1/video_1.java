package other.document.find.video_1;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author orcakill
 * @date 2021/4/15  16:48
 **/
public class video_1 {
       /*爬取b站的1个视频,使用手动获取url*/
       public static void main(String[] args) {
           System.out.println("开始");
           long start = System.currentTimeMillis();
           /**
            *  从json中获取到的 url
            *  请获取后手动填写
            */
           String lastUrl = "https://upos-sz-mirrorcos.bilivideo.com/upgcxcode/22/48/111804822/111804822_nb2-1-64.flv?e=ig8euxZM2rNcNbN1hbdVhwdlhbRghwdVhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1635736449&gen=playurlv2&os=cosbv&oi=3661349261&trid=9f742a24a52c4f0d97209eaa8515b871u&platform=pc&upsig=db2c59d85742b581f2a1f2bddebd7c7a&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0&bvc=vod&nettype=0&orderid=0,3&agrr=0&logo=80000000";
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
        String path = "F:\\test\\video_find\\" + fileName;
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
