package other.articles.service.impl;

import other.articles.dao.ArticleMapper;
import other.articles.model.dto.ArticleDTO;
import other.articles.model.entity.ArticlePO;
import other.articles.model.entity.ITHomeNews;
import other.articles.service.ArticleService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import static other.articles.map.webAddress.getWebAddress;
import static other.articles.util.HttpClient.getITHttpClient;
import static other.articles.util.getContent.getCsdnContent;
import static other.articles.util.getContent.getITContent;
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


    public static void dealCsdnData(String web) throws ParseException, SQLException {
        String url = getWebAddress(web);
        List<ArticleDTO> articleDTOS=getArticleIndex((getITHttpClient(url)));
        ArticleMapper.deleteAll();
        for (int i=0;i<articleDTOS.size();i++) {
            ArticleDTO articleDTO=articleDTOS.get(i);
            ArticlePO articlePO=getCsdnContent(getITHttpClient(articleDTO.getWebAddress()));
            articlePO.setId(articleDTO.getId());
            articlePO.setTabloid(articleDTO.getTabloid());
            articlePO.setTitle(articleDTO.getTitle());
            articlePO.setType(articleDTO.getType());
            articlePO.setViews(articleDTO.getViews());
            ArticleMapper.insertArticle(articlePO);
        }
    }
}
