package other.articles;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import other.articles.entity.HotSpot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static other.articles.map.webAddress.getWebAddress;
import static other.articles.util.PutFile.getPutFile;
import static other.articles.util.replace.replaceA;


/**
 * @author orcakill
 * @date 2021/1/13  15:55
 **/
public class HotArticles {
    /*实现爬取当日热点及数据*/
    /*目标爬取：百度热点   进度：100  %
     * */
    public static void main(String[] args) throws Exception {
        String web = "百度实时热点";
        List<HotSpot> hotSpots = getHotSpot(web);

        for (HotSpot hotSpot : hotSpots) {
            System.out.println("热点网站名称:" + hotSpot.getHotSpotWeb()
                    + " 热点排行:" + hotSpot.getHotSpotRank()
                    + " 热点搜索指数:" + hotSpot.getHotSpotDegree()
                    + " 热点名称：" + hotSpot.getHotSpotName()
            );
        }


    }

    public static List<HotSpot> getHotSpot(String web) throws Exception {
        List<HotSpot> hotSpots = new ArrayList<>();
        String url = getWebAddress(web);
        String downloadDir = "D:\\test\\hotSpot\\" + web + ".html";
        getPutFile(url, downloadDir);
        Document document = Jsoup.parse(new File(downloadDir), "gb2312");
        Elements s = document.select(".list-title");
        Elements s1 = document.select("td").select(".last").select("span");
        Elements s2= document.select("td").select(".first").select("span");
        for(int i=0;i<s.size();i++){
            HotSpot hotSpot=new HotSpot();
            hotSpot.setHotSpotName(replaceA(s.get(i).text()));
            hotSpot.setHotSpotWeb(web);
            hotSpot.setHotSpotRank(Integer.parseInt(s2.get(i).text()));
            hotSpot.setHotSpotDegree(Integer.parseInt(s1.get(i).text()));
            hotSpots.add(hotSpot);
        }
        return hotSpots;
    }


}





