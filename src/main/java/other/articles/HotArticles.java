package other.articles;


import other.articles.entity.HotSpot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static other.articles.map.getWebAddress.getWebAddress;
import static other.articles.util.getPutFile.getPutFile;
import static util.getPassWord.getMysqlPassword;

/**
 * @author orcakill
 * @date 2021/1/13  15:55
 **/
public class HotArticles {
     /*实现爬取当日热点及数据*/
    /*目标爬取：百度热点   进度：0  %
    * */
    public static void main(String[] args) throws Exception{
       String  web="百度实时热点";
        List<HotSpot> hotSpots=getHotSpot(web);

       System.out.println(getHotSpot(web));

    }

    public static List<HotSpot> getHotSpot(String web) throws Exception {
        List<HotSpot> hotSpots=new ArrayList<>();
        String  url=getWebAddress(web);
        getPutFile(url,"D:\\test\\hotSpot\\"+web+".html");

        return hotSpots;
    }
}

