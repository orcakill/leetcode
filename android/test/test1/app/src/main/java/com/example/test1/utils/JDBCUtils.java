package com.example.test1.utils;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection conn=null;

        try {
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","test","test");
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return conn;
    }

    public  static  void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
