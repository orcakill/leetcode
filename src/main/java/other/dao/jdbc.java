package other.dao;

import java.sql.*;

import static util.getPassWord.getMysqlPassword;

/**
 * @author orcakill
 * @date 2021/1/19  8:51
 **/
public class jdbc {
    public static void main(String[] args) throws Exception{
        //加载数据库驱动程序
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }
        String dburl = "jdbc:mysql://123.57.14.117:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String sql = "SELECT fund_name rq FROM fund";
        try(Connection conn = DriverManager.getConnection(dburl,"test",getMysqlPassword());
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
