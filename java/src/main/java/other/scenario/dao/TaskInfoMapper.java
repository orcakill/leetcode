package other.scenario.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.dao.Jdbc;
import other.dao.Sql;
import other.scenario.entity.TaskInfoPO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskInfoMapper {
	
	private static final Logger logger = LogManager.getLogger (TaskInfoPO.class);
	
	public static TaskInfoPO findById (int id) throws SQLException {
		TaskInfoPO taskInfoPO =new TaskInfoPO();
		String sql="select * from task_info where task_num="+id;
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement ();
		ResultSet resultSet=statement.executeQuery (sql);
		while(resultSet.next()){
			taskInfoPO.setTaskNum(resultSet.getInt(1));
			taskInfoPO.setTaskName(resultSet.getString(2));
			taskInfoPO.setTaskType(resultSet.getInt(3));
		}
		Jdbc.release(null, statement, connection);
		return taskInfoPO;
	}
	
	public static List<TaskInfoPO> findAll () throws SQLException {
		List<TaskInfoPO> taskInfoPOList=new ArrayList<> ();
		
		String sql="select * from task_info";
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement ();
		ResultSet resultSet=statement.executeQuery (sql);
		while(resultSet.next()){
			TaskInfoPO taskInfoPO =new TaskInfoPO();
			taskInfoPO.setTaskNum(resultSet.getInt(1));
			taskInfoPO.setTaskName(resultSet.getString(2));
			taskInfoPO.setTaskType(resultSet.getInt(3));
			taskInfoPOList.add (taskInfoPO);
		}
		Jdbc.release(null, statement, connection);
		return taskInfoPOList;
	}
	
	public static void insert(TaskInfoPO taskInfoPO) throws SQLException {
		String sql="insert into task_info(task_num,task_name,task_type)values(?,?,?)";
		Connection connection = Jdbc.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,taskInfoPO.getTaskNum());
		preparedStatement.setString(2,taskInfoPO.getTaskName());
		preparedStatement.setInt(3,taskInfoPO.getTaskType());
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
		String sql = "delete   from task_info";
		Sql.deleteSQL (sql, (org.apache.logging.log4j.core.Logger) logger);
	}
}
