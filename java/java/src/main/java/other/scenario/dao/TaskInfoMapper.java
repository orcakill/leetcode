package other.scenario.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.dao.Jdbc;
import other.dao.Sql;
import other.scenario.entity.TaskInfoPO;
import other.scenario.entity.TaskListPO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskInfoMapper {
	private static final Logger logger = LogManager.getLogger(TaskInfoMapper.class);
	
	/*根据主键查询一条数据*/
	public static TaskInfoPO findById(Integer taskId) throws SQLException {
		TaskInfoPO taskInfoPO =new TaskInfoPO();
		String sql="select * from task_info where task_id=?";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,taskId);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()){
			taskInfoPO.setTaskId(resultSet.getInt(1));
			taskInfoPO.setTaskName(resultSet.getString(2));
			taskInfoPO.setTaskType(resultSet.getInt(3));
			taskInfoPO.setTaskNumber(resultSet.getInt(4));
		}
		Jdbc.release(null, preparedStatement, connection);
		return taskInfoPO;
	}
	
	/*查询全部数据*/
	public static List<TaskInfoPO> findAll() throws SQLException {
		List<TaskInfoPO> taskInfoPOList=new ArrayList<>();
		String sql="select * from task_info";
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(sql);
		while(resultSet.next()){
			TaskInfoPO taskInfoPO =new TaskInfoPO();
			taskInfoPO.setTaskId(resultSet.getInt(1));
			taskInfoPO.setTaskName(resultSet.getString(2));
			taskInfoPO.setTaskType(resultSet.getInt(3));
			taskInfoPO.setTaskNumber(resultSet.getInt(4));
			taskInfoPOList.add(taskInfoPO);
		}
		Jdbc.release(null, statement, connection);
		return taskInfoPOList;
	}
	
	/*根据主键判断数据已存在*/
	public static Boolean check(Integer taskId) throws SQLException {
		String sql="select count(*) num from task_info where task_id=?";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,taskId);
		ResultSet resultSet=preparedStatement.executeQuery();
		int num = 0;
		while (resultSet.next ()) {
			num = resultSet.getInt ("num");
		}
		Jdbc.release (null, preparedStatement, connection);
		return num != 0;
	}
	
	/*保存方法*/
	public static void save(TaskInfoPO taskInfoPO) throws SQLException {
		Boolean check = check (taskInfoPO.getTaskId());
		if (check) {
			String sql="update task_info set task_name=?,task_type=?,task_number=? where task_id=? ";
			Connection connection = Jdbc.getConnection ();
			PreparedStatement preparedStatement = connection.prepareStatement (sql);
			preparedStatement.setString(1,taskInfoPO.getTaskName());
			preparedStatement.setInt(2,taskInfoPO.getTaskType());
			preparedStatement.setInt(3,taskInfoPO.getTaskNumber());
			preparedStatement.setInt(4,taskInfoPO.getTaskId());
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
			String sql="insert into task_info(task_id,task_name,task_type,task_number)values(?,?,?,?)";
			Connection connection = Jdbc.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,taskInfoPO.getTaskId());
			preparedStatement.setString(2,taskInfoPO.getTaskName());
			preparedStatement.setInt(3,taskInfoPO.getTaskType());
			preparedStatement.setInt(4,taskInfoPO.getTaskNumber());
			int num =preparedStatement.executeUpdate();
			if(num>0){
				logger.info("插入成功");
			}
			else{
				logger.error("插入失败");
			}
			Jdbc.release(null, preparedStatement, connection);
		}
	}
	
	/*删除全部数据*/
	public static void deleteAll () throws SQLException {
		String sql = "delete  from task_info";
		Sql.deleteSQL (sql, logger);
	}
	
	/*根据主键删除数据*/
	public static void deleteById(Integer taskId) throws SQLException {
		String sql = "delete  from task_info where task_id=? ";
		Connection connection = Jdbc.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
		preparedStatement.setObject(1,taskId);
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
