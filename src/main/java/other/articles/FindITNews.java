package other.articles;

import other.articles.entity.ITHomeNews;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static other.articles.map.getWebAddress.getWebAddress;
import static other.articles.util.HttpClient.getITHttpClient;
import static other.articles.util.getIndex.getIndex;
import static other.articles.util.screen.screenITNews;

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


    public  static  void  dealData(String web,Date startDate,Date endDate) throws SQLException {
        String url = getWebAddress(web);
        //获取当前页IT之家AI新闻目录
        //检查数据库是否已有，将已有的剔除，不执行插入
        List<ITHomeNews> itHomeNews=screenITNews(getIndex(getITHttpClient(url)));
        //获取新闻内容
        for(int i=0;i<itHomeNews.size();i++){
            ITHomeNews itHomeNews1=itHomeNews.get(i);

        }


    }
}
