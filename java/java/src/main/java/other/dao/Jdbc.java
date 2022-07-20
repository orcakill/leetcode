package other.dao;

import java.sql.*;

import static util.getPassWord.getTestMysqlPassword;

/**
 * @author orcakill
 * @date 2021/1/19  8:51
 **/
public class Jdbc {
	
	//准备数据库的四大参数：
	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String url ="123";
	private static final String username ="123";
	private static final String password =getTestMysqlPassword();
	
	
	/*
	 * 驱动： 只需要注册一次就OK
	 *
	 */
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 1：创建工具类： 直接获得一个连接对象：
	 */
	public static Connection getConnection (){
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 定义一个方法： 释放资源： 直接将rs stmt conn 全部释放：
	 */
	public static void release(ResultSet rs ,Statement stmt , Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
}
