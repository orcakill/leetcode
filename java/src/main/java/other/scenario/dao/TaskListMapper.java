package other.scenario.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.dao.Jdbc;
import other.dao.Sql;
import other.scenario.entity.TaskListPO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static other.dao.Sql.dateToString;

public class TaskListMapper {
	private static final Logger logger = LogManager.getLogger(TaskListPO.class);
	
	/*根据主键查询一条数据*/
	public static TaskListPO findById(String taskListDate,Integer taskListNum) throws SQLException {
		TaskListPO taskListPO =new TaskListPO();
		String sql="select * from task_list where list_date=? and list_num=?";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,taskListDate);
		preparedStatement.setObject (2,taskListNum);
		ResultSet resultSet=preparedStatement.executeQuery(sql);
		while(resultSet.next()){
			taskListPO.setListDate(resultSet.getDate(1));
			taskListPO.setListNum(resultSet.getInt(2));
			taskListPO.setTaskNum(resultSet.getInt(3));
			taskListPO.setUserName(resultSet.getString(4));
			taskListPO.setTaskState(resultSet.getInt(5));
		}
		Jdbc.release(null, preparedStatement, connection);
		return taskListPO;
	}
	/*查询当日数据*/
	public static List<TaskListPO> findByListDate(String ListDate) throws SQLException {
		List<TaskListPO> taskListPOList=new ArrayList<>();
		String sql="select * from task_list where list_date=? ";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,ListDate);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()){
			TaskListPO taskListPO =new TaskListPO();
			taskListPO.setListDate(resultSet.getDate(1));
			taskListPO.setListNum(resultSet.getInt(2));
			taskListPO.setTaskNum(resultSet.getInt(3));
			taskListPO.setUserName(resultSet.getString(4));
			taskListPO.setTaskState(resultSet.getInt(5));
			taskListPOList.add(taskListPO);
		}
		Jdbc.release(null,preparedStatement, connection);
		return taskListPOList;
	}

	
	/*查询全部数据*/
	public static List<TaskListPO> findAll() throws SQLException {
		List<TaskListPO> taskListPOList=new ArrayList<>();
		String sql="select * from task_list";
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(sql);
		while(resultSet.next()){
			TaskListPO taskListPO =new TaskListPO();
			taskListPO.setListDate(resultSet.getDate(1));
			taskListPO.setListNum(resultSet.getInt(2));
			taskListPO.setTaskNum(resultSet.getInt(3));
			taskListPO.setUserName(resultSet.getString(4));
			taskListPO.setTaskState(resultSet.getInt(5));
			taskListPOList.add(taskListPO);
		}
		Jdbc.release(null, statement, connection);
		return taskListPOList;
	}
	
	/*根据主键判断数据已存在*/
	public static Boolean check(String ListDate,Integer ListNum) throws SQLException {
		String sql="select count(*) num from task_list where list_date=? and list_num=?";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,ListDate);
		preparedStatement.setObject (2,ListNum);
		ResultSet resultSet=preparedStatement.executeQuery();
		int num = 0;
		while (resultSet.next ()) {
			num = resultSet.getInt ("num");
		}
		Jdbc.release (null, preparedStatement, connection);
		return num != 0;
	}
	
	/*保存方法*/
	public static void save(TaskListPO taskListPO) throws SQLException {
		Boolean check = check (dateToString (taskListPO.getListDate()),taskListPO.getListNum());
		if (check) {
			String sql="update task_list set task_num=?,user_name=?,task_state=? where list_date=?  and list_num=? ";
			Connection connection = Jdbc.getConnection ();
			PreparedStatement preparedStatement = connection.prepareStatement (sql);
			preparedStatement.setInt(1,taskListPO.getTaskNum());
			preparedStatement.setString(2,taskListPO.getUserName());
			preparedStatement.setInt(3,taskListPO.getTaskState());
			preparedStatement.setDate(4,new java.sql.Date (taskListPO.getListDate().getTime()));
			preparedStatement.setInt(5,taskListPO.getListNum());
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
			String sql="insert into task_list(list_date,list_num,task_num,user_name,task_state)values(?,?,?,?,?)";
			Connection connection = Jdbc.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1,new java.sql.Date(taskListPO.getListDate().getTime()));
			preparedStatement.setInt(2,taskListPO.getListNum());
			preparedStatement.setInt(3,taskListPO.getTaskNum());
			preparedStatement.setString(4,taskListPO.getUserName());
			preparedStatement.setInt(5,taskListPO.getTaskState());
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
		String sql = "delete  from task_list";
		Sql.deleteSQL (sql, (org.apache.logging.log4j.core.Logger) logger);
	}
	
	/*根据主键删除数据*/
	public static void deleteById(String ListDate,Integer ListNum) throws SQLException {
		String sql = "delete  from task_list where list_date=?  and list_num=? ";
		Connection connection = Jdbc.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
		preparedStatement.setObject(1,ListDate);
		preparedStatement.setObject(2,ListNum);
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
