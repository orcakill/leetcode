package other.articles.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import other.articles.model.entity.ArticlePO;

import java.sql.SQLException;
import java.util.List;

import static other.articles.dao.ArticleMapper.insertArticle;

public class ArticleMapperTest {

    /*测试查询单个*/
    @Test
    public void findById() throws SQLException {
        ArticlePO articlePO = ArticleMapper.findByIdArticle("1");
        System.out.println(articlePO.getId());
        System.out.println(articlePO.getAuthor());
        System.out.println(articlePO.getCategory());
        System.out.println(articlePO.getTabloid());
        System.out.println(articlePO.getContent());
        System.out.println(articlePO.getTags());
        System.out.println(articlePO.getTitle());
        System.out.println(articlePO.getType());
        System.out.println(articlePO.getViews());
        System.out.println(articlePO.getGmtCreate());
        System.out.println(articlePO.getGmtUpdate());
    }

    /*测试查询全部*/
    @Test
    public void findAll() throws SQLException {
        List<ArticlePO> articlePOs = ArticleMapper.findAllArticle();
        for (int i = 0; i < articlePOs.size(); i++) {
            ArticlePO articlePO = articlePOs.get(i);
            System.out.println(
                    articlePO.getId() +"\t"+
                            articlePO.getAuthor()+"\t" +
                            articlePO.getCategory()+"\t" +
                            articlePO.getTabloid()+"\t" +
                            articlePO.getContent()+"\t" +
                            articlePO.getTags()+"\t" +
                            articlePO.getTitle()+"\t" +
                            articlePO.getType()+"\t" +
                            articlePO.getViews() +"\t"+
                            articlePO.getGmtCreate()+"\t" +
                            articlePO.getGmtUpdate()
            );

        }
    }

    /*插入单挑数据*/
    @Test
    public void insert() throws SQLException {
       ArticlePO articlePO=new ArticlePO();
        articlePO.setId("3");
        articlePO.setAuthor("3");
        articlePO.setCategory("3");
        articlePO.setTabloid("3");
        articlePO.setContent("3");
        articlePO.setTags("3");
        articlePO.setTitle("3");
        articlePO.setType(3);
        articlePO.setViews(3);
        articlePO.setGmtCreate(Long.valueOf(3));
        articlePO.setGmtUpdate(Long.valueOf(3));
        insertArticle(articlePO);
    }

    @Test
    public void deleteAll() throws SQLException {
        ArticleMapper.deleteAll();
    }
}