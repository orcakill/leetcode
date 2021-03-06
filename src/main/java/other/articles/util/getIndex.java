package other.articles.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import other.articles.entity.ITHomeNews;

import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill @date 2021/1/23  14:25
 */
public class getIndex {
    public static List<ITHomeNews> getIndex(String html) {
        List<ITHomeNews> itHomeNews = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Element list = document.getElementById("list");
        Elements elements = list.getElementsByClass("c");
        for (Element element : elements) {
            ITHomeNews itHomeNews1 = new ITHomeNews();
            String title = element.select(".title").text();
            String m = element.select(".m").text();
            String tags = element.select(".tags").text();
            tags=tags.replaceAll("Tags：","").replaceAll(" ","");
            String href = element.select(".title").attr("href");
            itHomeNews1.setNewsName(title);
            itHomeNews1.setNewsDescribe(m);
            itHomeNews1.setNewsTags(tags);
            itHomeNews1.setNewsHref(href);
            itHomeNews.add(itHomeNews1);
        }

        return  itHomeNews;
    }
}
