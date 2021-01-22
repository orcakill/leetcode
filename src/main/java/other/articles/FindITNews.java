package other.articles;

import other.articles.entity.ITHomeNews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static other.articles.map.getWebAddress.getWebAddress;
import static other.articles.util.getPutFile.getPutFile;

/**
 * @author orcakill
 * @date 2021/1/22  11:05
 **/
public class FindITNews {
    //爬取IT之家AI数据
    public  static  void  main(String[] args) throws  Exception{
        //爬取IT之家最近一年数据
        String web="IT之家AI";
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=format.parse("2020-01-01");
        Date endDate=new Date();
        dealData(web,startDate,endDate);


    }


    public  static  void  dealData(String web,Date startDate,Date endDate){
        List<ITHomeNews> itHomeNews=new ArrayList<>();
        String url = getWebAddress(web);
        String downloadDir = "D:\\test\\ITNews\\" + web + ".html";
        try {
            getPutFile(url, downloadDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
