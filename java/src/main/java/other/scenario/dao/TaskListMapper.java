package other.scenario.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import other.dao.Jdbc;
import other.scenario.entity.TaskListPO;


import java.sql.*;

public class TaskListMapper {
	private static final Logger logger = LogManager.getLogger (TaskListMapper.class);
	
	public static TaskListPO findById (String date) throws SQLException {
		TaskListPO taskListPO =new TaskListPO();
		String sql="select * from task_list where task_list_date="+"'"+date+"'";
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement ();
		ResultSet resultSet=statement.executeQuery (sql);
		System.out.println (sql);
		while(resultSet.next()){
			taskListPO.setTaskListDate(resultSet.getDate(1));
			taskListPO.setTaskNum(resultSet.getInt(2));
			taskListPO.setUserName(resultSet.getString(3));
			taskListPO.setTaskState(resultSet.getInt(4));
		}
		Jdbc.release(null, statement, connection);
		return taskListPO;
	}
	public static void insert(TaskListPO taskListPO) throws SQLException {
		String sql="insert into task_list(task_list_date,task_num,user_name,task_state)values(?,?,?,?)";
		Connection connection = Jdbc.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDate(1,new java.sql.Date(taskListPO.getTaskListDate().getTime()));
		preparedStatement.setInt(2,taskListPO.getTaskNum());
		preparedStatement.setString(3,taskListPO.getUserName());
		preparedStatement.setInt(4,taskListPO.getTaskState());
		int num =preparedStatement.executeUpdate();
		if(num>0){
			logger.info("插入成功");
		}
		else{
			logger.error("插入失败");
		}
		Jdbc.release (null, preparedStatement, connection);
	}
	
	public static void deleteAll () throws SQLException {
		String sql = "delete  from task_list";
		Connection connection = Jdbc.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
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
