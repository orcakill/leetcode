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

        File  file1=new File("/raw/mysql.txt");
        File  file2=new File("/raw/user.txt");
        File  file3=new File("/raw/password.txt");
        String mysql=file1.toString();
        String user=file2.toString();
        String password=file3.toString();
        try {
            conn= DriverManager.getConnection(mysql,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
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
