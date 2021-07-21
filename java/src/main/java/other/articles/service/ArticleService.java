package other.articles.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import other.articles.dao.ArticleMapper;
import other.articles.model.entity.ArticlePO;

import java.util.Objects;

@Service
public class ArticleService {
    private final ArticleMapper articleMapper;

    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }


}
