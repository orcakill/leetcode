package other.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author orcakill
 * @date 2021/1/23  14:41
 **/
public class ITNews {
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
}
