package other.articles.controller;

import other.articles.model.entity.ITHomeNews;
import other.dao.ITNews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static other.articles.map.webAddress.getWebAddress;
import static other.articles.util.HttpClient.getITHttpClient;
import static other.articles.util.getContent.getITContent;
import static other.articles.util.index.getIndex;
import static other.articles.util.screen.screenITNews;

/**
 * @author orcakill
 * @date 2021/1/22  11:05
 **/
public class FindITNews {

    //爬取IT之家AI数据
    public  static  void  main(String[] args) throws  Exception{

        //爬取IT之家从2020-01-01至今的数据
        String web="IT之家AI";
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=format.parse("2020-01-01");
        Date endDate=new Date();
        dealData(web,startDate,endDate);


    }


    public  static  void  dealData(String web,Date startDate,Date endDate) throws Exception {
        String url = getWebAddress(web);
        //获取当前页IT之家AI新闻目录
        //检查数据库是否已有，将已有的剔除，不执行插入
        List<ITHomeNews> itHomeNews=screenITNews(getIndex(getITHttpClient(url)));
        //获取新闻内容
        if(itHomeNews.size()>0){

        for(int i=0;i<itHomeNews.size();i++){
            ITHomeNews itHomeNews1=itHomeNews.get(i);
            String href=itHomeNews1.getNewsHref();
            ITHomeNews itHomeNews2=getITContent(getITHttpClient(href));
            itHomeNews2.setNewsName(itHomeNews1.getNewsName());
            itHomeNews2.setNewsTags(itHomeNews1.getNewsTags());
            itHomeNews2.setNewsDescribe(itHomeNews1.getNewsDescribe());
            itHomeNews2.setNewsIndex(web);
            itHomeNews2.setNewsHref(href);
            itHomeNews.set(i,itHomeNews2);
            ITNews.insert(itHomeNews2);

        }

        }
    }
}
