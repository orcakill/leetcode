package other.articles;


import java.sql.*;

import static util.getPassWord.getMysqlPassword;

/**
 * @author orcakill
 * @date 2021/1/13  15:55
 **/
public class HotArticles {

    public static void main(String[] args) throws Exception{
        //加载数据库驱动程序
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }
        String dburl = "jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String sql = "SELECT DATE_FORMAT(rq,'%Y-%m-%d') rq FROM test";
        try(Connection conn = DriverManager.getConnection(dburl,"root",getMysqlPassword());
            Statement stmt = conn.createStatement();
            ResultSet rst  = stmt.executeQuery(sql))

        {
            while (rst.next()){
                System.out.println(rst.getString(1)
                );
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

    }
}

