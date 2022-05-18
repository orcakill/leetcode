package other.articles;

import other.articles.controller.FindCsdn;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;

public class APP {
    public static void main(String[] args) throws UnsupportedEncodingException, SQLException, ParseException {
        /*爬取CSD本人的博客文章*/
        FindCsdn.findCsdnArticles();
    }
}
