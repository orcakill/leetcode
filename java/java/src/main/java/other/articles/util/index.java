package other.articles.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import other.articles.model.dto.ArticleDTO;
import other.articles.model.entity.ITHomeNews;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author orcakill @date 2021/1/23  14:25
 */
public class index {
    public static List<ITHomeNews> getIndex(String html) {
        List<ITHomeNews> itHomeNews = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Element list = document.getElementById("list");
        assert list != null;
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


    public static List<ArticleDTO> getArticleIndex(String html) {
        List<ArticleDTO> articleDTOS= new ArrayList<>();
        Document document = Jsoup.parse(html);
        //Element list = document.getElementById("navList-box");
        Elements elements = document.getElementsByClass("blog-list-box");
        for (Element element : elements) {
//            ITHomeNews itHomeNews1 = new ITHomeNews();
//            String title = element.select(".title").text();
//            String m = element.select(".m").text();
//            String tags = element.select(".tags").text();
//            tags=tags.replaceAll("Tags：","").replaceAll(" ","");
//            String href = element.select(".title").attr("href");

            ArticleDTO articleDTO=new ArticleDTO();
            articleDTO.setId(UUID.randomUUID().toString());
            articleDTO.setAuthor("");
            articleDTO.setCategory("");
            articleDTO.setTabloid(element.getElementsByClass("blog-list-content").text());
            articleDTO.setContent("");
            articleDTO.setTags("");
            articleDTO.setTitle(element.getElementsByClass("blog-list-box-top").text());
            String  type=element.getElementsByClass("article-type article-type-yc").text();
            if(type.equals("原创")) type = "1";
            else type = "0";
            articleDTO.setType(Integer.valueOf(type));
            articleDTO.setViews(Integer.valueOf(element.getElementsByClass("view-num").text().replaceAll("阅读","")));
            articleDTO.setGmtCreate(1L);
            articleDTO.setGmtUpdate(1L);
            articleDTO.setWebAddress(element.select("a").attr("href"));

            articleDTOS.add(articleDTO);
        }

        return  articleDTOS;
    }
}
