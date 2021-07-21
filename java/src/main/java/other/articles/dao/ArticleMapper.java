package other.articles.dao;

import other.articles.model.entity.ArticlePO;
import other.dao.Jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author orcakill
 * @date 2021/2/25  11:55
 **/

public class ArticleMapper {
    public ArticlePO findById (String id) throws SQLException {
        ArticlePO articlePO=new ArticlePO();
        String sql="select * from articles where id='"+id+"'";
        Connection connection= Jdbc.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        int num=0;
        while(resultSet.next()){
            num=resultSet.getInt("num");
        }
        Jdbc.release(null, statement, connection);
        return articlePO;
    }
}
