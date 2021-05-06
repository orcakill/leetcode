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
           String lastUrl = "https://upos-hz-mirrorakam.akamaized.net/upgcxcode/22/48/111804822/111804822_nb2-1-80.flv?e=ig8euxZM2rNcNbuM7WdVhoMa7wUVhwdEto8g5X10ugNcXBlqNxHxNEVE5XREto8KqJZHUa6m5J0SqE85tZvEuENvNo8g2ENvNo8i8o859r1qXg8xNEVE5XREto8GuFGv2U7SuxI72X6fTr859r1qXg8gNEVE5XREto8z5JZC2X2gkX5L5F1eTX1jkXlsTXHeux_f2o859IB_&uipk=5&nbs=1&deadline=1620291477&gen=playurlv2&os=akam&oi=804332179&trid=41e6ed23a5c441d096898098d08b4eaau&platform=pc&upsig=6d47a91d34af00efaf9b8ce82e3c46e1&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&hdnts=exp=1620291477~hmac=4a6298d916753170fe9e7358ced00d6fcded1922eff79e726df838a0d9a96262&mid=179559763&orderid=0,1&agrr=1&logo=80000000";
           //自定义文件名称
           String fileName = "a.mp4";
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
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bys = new byte[1024];
            int len = 0;
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
