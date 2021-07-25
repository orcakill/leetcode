package other.articles.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import other.articles.dao.ArticleMapper;
import other.articles.model.entity.ArticlePO;
import other.articles.service.impl.ArticleServiceImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public interface ArticleService {
    ArticlePO findById(String id) throws SQLException;

    List<ArticlePO> findAll() throws SQLException;

    void insert(ArticlePO articlePO) throws SQLException;

    //csdn网站爬取
    static void crawlAllArticle() throws SQLException, ParseException {
        String web="CSDN";
        ArticleServiceImpl.dealCsdnData(web);
    }
}
