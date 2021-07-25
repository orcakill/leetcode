package other.articles.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import other.articles.model.entity.ArticlePO;
import other.dao.ITNews;
import other.dao.Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @date 2021/2/25  11:55
 **/

public class ArticleMapper {
    private static final Logger logger = LogManager.getLogger(ITNews.class);


    public static ArticlePO findByIdArticle(String id) throws SQLException {
        ArticlePO articlePO=new ArticlePO();
        String sql="select * from articles where id='"+id+"'";
        Connection connection= Jdbc.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            articlePO.setId(resultSet.getString(1));
            articlePO.setAuthor(resultSet.getString(2));
            articlePO.setCategory(resultSet.getString(3));
            articlePO.setTabloid(resultSet.getString(4));
            articlePO.setContent(resultSet.getString(5));
            articlePO.setTags(resultSet.getString(6));
            articlePO.setTitle(resultSet.getString(7));
            articlePO.setType(resultSet.getInt(8));
            articlePO.setViews(resultSet.getInt(9));
            articlePO.setGmtCreate(resultSet.getLong(10));
            articlePO.setGmtUpdate(resultSet.getLong(11));
        }
        Jdbc.release(null, statement, connection);
        return articlePO;
    }

    public static List<ArticlePO> findAllArticle() throws SQLException {
        List<ArticlePO> articlePOs=new ArrayList<>();
        String sql="select * from articles";
        Connection connection= Jdbc.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            ArticlePO articlePO=new ArticlePO();
            articlePO.setId(resultSet.getString(1));
            articlePO.setAuthor(resultSet.getString(2));
            articlePO.setCategory(resultSet.getString(3));
            articlePO.setTabloid(resultSet.getString(4));
            articlePO.setContent(resultSet.getString(5));
            articlePO.setTags(resultSet.getString(6));
            articlePO.setTitle(resultSet.getString(7));
            articlePO.setType(resultSet.getInt(8));
            articlePO.setViews(resultSet.getInt(9));
            articlePO.setGmtCreate(resultSet.getLong(10));
            articlePO.setGmtUpdate(resultSet.getLong(11));
            articlePOs.add(articlePO);
        }
        Jdbc.release(null, statement, connection);
        return articlePOs;
    }

    public static void insertArticle(ArticlePO articlePO) throws SQLException {
        String sql = "insert into articles" +
                "(id,author,category,tabloid,content,tags,title,type,views,gmt_create,gmt_update) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = Jdbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, articlePO.getId());
        pstmt.setString(2, articlePO.getAuthor());
        pstmt.setString(3, articlePO.getCategory());
        pstmt.setString(4, articlePO.getTabloid());
        pstmt.setString(5, articlePO.getContent());
        pstmt.setString(6, articlePO.getTags());
        pstmt.setString(7, articlePO.getTitle());
        pstmt.setInt(8, articlePO.getType());
        pstmt.setInt(9, articlePO.getViews());
        pstmt.setLong(10,articlePO.getGmtCreate());
        pstmt.setLong(11,articlePO.getGmtUpdate());

        int num =pstmt.executeUpdate();

        if(num>0){
            logger.info("插入成功");
        }
        else{
            logger.error("插入失败");
        }

        Jdbc.release(null, pstmt, connection);
    }

    public static void deleteAll() throws SQLException {
        String sql = "delete   from articles";
        Connection connection = Jdbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);


        int num =pstmt.executeUpdate();

        if(num>0){
            logger.info("删除成功");
        }
        else{
            logger.error("删除失败");
        }

        Jdbc.release(null, pstmt, connection);
    }
}
