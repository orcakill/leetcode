package other.articles;

import other.articles.controller.FindCsdn;

import java.io.UnsupportedEncodingException;

public class APP {
    public static void main(String[] args) throws UnsupportedEncodingException {
        /*爬取CSD本人的博客文章*/
        FindCsdn.findCsdnArticles();
    }
}
