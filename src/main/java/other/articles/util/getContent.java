package other.articles.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import other.articles.entity.ITHomeNews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author orcakill
 * @date 2021/1/23  14:26
 **/
public class getContent {
    public  static ITHomeNews getITContent(String html) throws ParseException {
        ITHomeNews itHomeNews=new ITHomeNews();
        Document document = Jsoup.parse(html);
        String title=document.select("h1").text();
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date= document.getElementById("pubtime_baidu").text();
        Date date1=format.parse(date);
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date=format1.format(date1);
        String  source=document.getElementById("source_baidu").select("a").text();
        Element list =document.getElementById("paragraph");
        Elements elements= list.select("p");
        StringBuilder content= new StringBuilder();
        for(Element element:elements){
            content.append("    ").append(element.select("p").text()).append("\r\n");
        }
        itHomeNews.setNewsName(title);
        itHomeNews.setNewsDate(date);
        itHomeNews.setNewsSource(source);
        itHomeNews.setNewsContent(content.toString());
        return itHomeNews;
    }
}
