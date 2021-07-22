package other.articles.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import other.articles.dao.ArticleMapper;
import other.articles.model.entity.ArticlePO;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public interface ArticleService {
    ArticlePO findById(String id) throws SQLException;

    List<ArticlePO> findAll() throws SQLException;

    void insert(ArticlePO articlePO) throws SQLException;

    //csdn网站爬取
    void  crawlAllArticle();
}
