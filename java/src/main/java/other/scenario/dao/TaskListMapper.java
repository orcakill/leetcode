package other.scenario.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.dao.Jdbc;
import other.dao.Sql;
import other.scenario.entity.TaskListPO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static other.dao.Sql.dateToString;

public class TaskListMapper {
	private static final Logger logger = LogManager.getLogger (TaskListMapper.class);
	
	public static TaskListPO findById (String taskListDate, Integer taskListNum) throws SQLException {
		TaskListPO taskListPO = new TaskListPO ();
		String sql = "select * from task_list where task_list_date=? and task_list_num=?";
		Connection connection = Jdbc.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
		preparedStatement.setObject (1, taskListDate);
		preparedStatement.setObject (2, taskListNum);
		ResultSet resultSet = preparedStatement.executeQuery ();
		while (resultSet.next ()) {
			taskListPO.setTaskListDate (resultSet.getDate (1));
			taskListPO.setTaskListNum (resultSet.getInt (2));
			taskListPO.setTaskNum (resultSet.getInt (3));
			taskListPO.setUserName (resultSet.getString (4));
			taskListPO.setTaskState (resultSet.getInt (5));
		}
		Jdbc.release (null, preparedStatement, connection);
		return taskListPO;
	}
	/*根据主键判断数据已存在*/
	public static Boolean check (String taskListDate, Integer taskListNum) throws SQLException {
		String sql = "select count(*) num from task_list where task_list_date=? and task_list_num=?";
		Connection connection = Jdbc.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
		preparedStatement.setObject (1, taskListDate);
		preparedStatement.setObject (2, taskListNum);
		ResultSet resultSet = preparedStatement.executeQuery ();
		int num = 0;
		while (resultSet.next ()) {
			num = resultSet.getInt ("num");
		}
		Jdbc.release (null, preparedStatement, connection);
		return num != 0;
	}
	
	public static void save (TaskListPO taskListPO) throws SQLException {
		Boolean check = check (dateToString (taskListPO.getTaskListDate ()), taskListPO.getTaskListNum ());
		if (check) {
			String sql =
					"update task_list set task_num=?,user_name=?,task_state=? where task_list_date=? and " +
					"task_list_num=?";
			Connection connection = Jdbc.getConnection ();
			PreparedStatement preparedStatement = connection.prepareStatement (sql);
			preparedStatement.setInt (1, taskListPO.getTaskNum ());
			preparedStatement.setString (2, taskListPO.getUserName ());
			preparedStatement.setInt (3, taskListPO.getTaskState ());
			preparedStatement.setDate (4, new java.sql.Date (taskListPO.getTaskListDate ()
			                                                           .getTime ()));
			preparedStatement.setInt (5, taskListPO.getTaskListNum ());
			int num = preparedStatement.executeUpdate ();
			if (num > 0) {
				logger.info ("更新成功");
			}
			else {
				logger.error ("更新失败");
			}
			Jdbc.release (null, preparedStatement, connection);
		}
		else {
			String sql =
					"insert into task_list(task_list_date,task_list_num,task_num,user_name,task_state)values(?,?,?,?," +
					"?)";
			Connection connection = Jdbc.getConnection ();
			PreparedStatement preparedStatement = connection.prepareStatement (sql);
			preparedStatement.setDate (1, new java.sql.Date (taskListPO.getTaskListDate ()
			                                                           .getTime ()));
			preparedStatement.setInt (2, taskListPO.getTaskListNum ());
			preparedStatement.setInt (3, taskListPO.getTaskNum ());
			preparedStatement.setString (4, taskListPO.getUserName ());
			preparedStatement.setInt (5, taskListPO.getTaskState ());
			int num = preparedStatement.executeUpdate ();
			if (num > 0) {
				logger.info ("插入成功");
			}
			else {
				logger.error ("插入失败");
			}
			Jdbc.release (null, preparedStatement, connection);
		}
	}
	
	public static void deleteAll () throws SQLException {
		String sql = "delete  from task_list";
		Sql.deleteSQL (sql, (org.apache.logging.log4j.core.Logger) logger);
	}
	
	public static void deleteById(String taskListDate, Integer taskListNum) throws SQLException {
		String sql = "delete  from task_list where task_list_date=? and task_list_num=?";
		Connection connection = Jdbc.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
		preparedStatement.setObject (1, taskListDate);
		preparedStatement.setObject (2, taskListNum);
		int num = preparedStatement.executeUpdate ();
		if (num > 0) {
			logger.info ("删除成功");
		}
		else {
			logger.error ("删除失败");
		}
		Jdbc.release (null, preparedStatement, connection);
	}
}
