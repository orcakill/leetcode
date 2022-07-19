package other.dao;



import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.text.SimpleDateFormat;

public class Sql {
	public static void deleteSQL (String sql, Logger logger) throws SQLException {
		Connection connection = JDBC.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
		int num = preparedStatement.executeUpdate ();
		if (num > 0) {
			logger.info ("删除成功");
		}
		else {
			logger.error ("删除失败");
		}
		JDBC.release (null, preparedStatement, connection);
	}
	
	public static Boolean BooleanSQL (String sql) throws SQLException {
		Connection connection= JDBC.getConnection ();
		Statement statement=connection.createStatement ();
		ResultSet resultSet =statement.executeQuery (sql);
		int num=0;
		while(resultSet.next()){
			num=resultSet.getInt("num");
		}
		JDBC.release(null, statement, connection);
		return num!=0;
	}
	
	public  static  String dateToString(java.util.Date date){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		return simpleDateFormat.format (date);
	}
}
