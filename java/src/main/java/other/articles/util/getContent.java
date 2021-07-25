package other.articles.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import other.articles.model.dto.ArticleDTO;
import other.articles.model.entity.ArticlePO;
import other.articles.model.entity.ITHomeNews;

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
        long longDate=date1.getTime();
        String  source=document.getElementById("source_baidu").select("a").text();
        Element list =document.getElementById("paragraph");
        Elements elements= list.select("p");
        StringBuilder content= new StringBuilder();
        for(Element element:elements){
            content.append("    ").append(element.select("p code").text()).append("\r\n");
        }
        itHomeNews.setNewsName(title);
        itHomeNews.setNewsDate(longDate);
        itHomeNews.setNewsSource(source);
        itHomeNews.setNewsContent(content.toString());
        return itHomeNews;
    }

    public  static ArticlePO getCsdnContent(String html) throws ParseException {
        ArticlePO  articlePO=new ArticlePO();
        Document documents = Jsoup.parse(html);
        String temp = documents.html().replace("<br>", "</p><p>");
        Document document = Jsoup.parse(temp);
        articlePO.setAuthor(document.getElementsByClass("follow-nickName ").text());
        articlePO.setCategory(document.getElementsByClass("tag-link").text());
        Element list =document.getElementById("content_views");
        assert list != null;
        Elements elements= list.children();
        StringBuilder content= new StringBuilder();
        for(Element element:elements){
            if(!element.select("img").isEmpty()){
                String imgName="20210713143344961.png";
                String imgAddress=element.select("img").attr("src");
                String  img="!["+imgName+"]("+imgAddress+")";
                content.append(img).append("\r\n");
            }
            if(!element.select("p").isEmpty()){
                content.append("    ").append(element.select("p").text()).append("\r\n");
            }
            if(!element.select("code").isEmpty()){
                content.append("    ").append(element.select("code").text()).append("\r\n");
            }



        }
        String  content1= String.valueOf(content);
        String  content2=content1.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");
        articlePO.setContent(content2);
        articlePO.setTags(document.getElementsByClass("tag-link").text());
        String time=document.getElementsByClass("time").get(0).text();
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
        Date date = sdf.parse(time);
        Long numDate=date.getTime();
        articlePO.setGmtCreate(numDate);
        articlePO.setGmtUpdate(numDate);
        return articlePO;
    }
}
