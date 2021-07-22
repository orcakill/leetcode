package other.articles.service.impl;

import other.articles.dao.ArticleMapper;
import other.articles.model.entity.ArticlePO;
import other.articles.model.entity.ITHomeNews;
import other.articles.service.ArticleService;

import java.sql.SQLException;
import java.util.List;

import static other.articles.map.webAddress.getWebAddress;
import static other.articles.util.HttpClient.getITHttpClient;
import static other.articles.util.index.getArticleIndex;
import static other.articles.util.index.getIndex;
import static other.articles.util.screen.screenITNews;

public class ArticleServiceImpl implements ArticleService {

    @Override
    public ArticlePO findById(String id) throws SQLException {
        return ArticleMapper.findByIdArticle(id);
    }

    @Override
    public List<ArticlePO> findAll() throws SQLException {
        return ArticleMapper.findAllArticle();
    }

    @Override
    public void insert(ArticlePO articlePO) throws SQLException {
       ArticleMapper.insertArticle(articlePO);
    }

    @Override
    public void crawlAllArticle() {
        String web="CSDN";
        dealCsdnData(web);
    }

    private void dealCsdnData(String web) {
        String url = getWebAddress(web);
        List<ArticlePO> articlePOS=getArticleIndex((getITHttpClient(url)));
    }
}
