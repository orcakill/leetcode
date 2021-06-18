package other.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import other.articles.entity.ITHomeNews;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import  java.sql.PreparedStatement;


/**
 * @author orcakill
 * @date 2021/1/23  14:41
 **/
public class ITNews {
    private static Logger logger = LogManager.getLogger(ITNews.class);

    public static  boolean isEmpty(String name) throws SQLException {

        String sql="select count(*) num from it_news where news_name='"+name+"'";
        Connection connection= Jdbc.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        int num=0;
        while(resultSet.next()){
            num=resultSet.getInt("num");
        }
        Jdbc.release(null, statement, connection);
        return num!=0;
    }

    public static void insert(ITHomeNews itHomeNews) throws SQLException {
        String sql = "insert into it_news" +
                "(news_name,news_date,news_tags,news_describe,news_source,news_index,news_content,news_href) " +
                "values (?,?,?,?,?,?,?,?)";
        Connection connection = Jdbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, itHomeNews.getNewsName());
        pstmt.setLong(2, itHomeNews.getNewsDate());
        pstmt.setString(3, itHomeNews.getNewsTags());
        pstmt.setString(4, itHomeNews.getNewsDescribe());
        pstmt.setString(5, itHomeNews.getNewsSource());
        pstmt.setString(6, itHomeNews.getNewsIndex());
        pstmt.setString(7, itHomeNews.getNewsContent());
        pstmt.setString(8, itHomeNews.getNewsHref());

        int num =pstmt.executeUpdate();

        if(num>0){
            logger.info("插入成功");
        }
        else{
            logger.error("插入失败");
        }

        Jdbc.release(null, pstmt, connection);
    }
}
