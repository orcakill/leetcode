package other.articles.controller;

import other.articles.service.ArticleService;

import java.sql.SQLException;
import java.text.ParseException;

public class FindCsdn {


    public static void  findCsdnArticles() throws SQLException, ParseException {
        ArticleService.crawlAllArticle();
    }
}
