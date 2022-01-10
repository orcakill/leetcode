package other.exercise.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.dao.Jdbc;
import other.dao.Sql;
import other.exercise.entity.DailyExercisePO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName DailyExerciseMapper.java
 * @Description TODO
 * @createTime 2022年01月10日 14:06:00
 */
public class DailyExerciseMapper {
	private static final Logger logger = LogManager.getLogger(DailyExerciseMapper.class);
	
	/*根据主键查询一条数据*/
	public static DailyExercisePO findById(BigDecimal exerciseId) throws SQLException {
		DailyExercisePO dailyExercisePO =new DailyExercisePO();
		String sql="select * from daily_exercise where exercise_id=?";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,exerciseId);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()){
			dailyExercisePO.setExerciseId(resultSet.getBigDecimal(1));
			dailyExercisePO.setExerciseDay(resultSet.getTimestamp(2));
			dailyExercisePO.setExerciseType(resultSet.getString(3));
			dailyExercisePO.setExerciseTime(resultSet.getString(4));
		}
		Jdbc.release(null, preparedStatement, connection);
		return dailyExercisePO;
	}
	
	/*查询全部数据*/
	public static List<DailyExercisePO> findAll() throws SQLException {
		List<DailyExercisePO> dailyExercisePOList=new ArrayList<>();
		String sql="select * from daily_exercise";
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(sql);
		while(resultSet.next()){
			DailyExercisePO dailyExercisePO =new DailyExercisePO();
			dailyExercisePO.setExerciseId(resultSet.getBigDecimal(1));
			dailyExercisePO.setExerciseDay(resultSet.getTimestamp(2));
			dailyExercisePO.setExerciseType(resultSet.getString(3));
			dailyExercisePO.setExerciseTime(resultSet.getString(4));
			dailyExercisePOList.add(dailyExercisePO);
		}
		Jdbc.release(null, statement, connection);
		return dailyExercisePOList;
	}
	
	/*根据主键判断数据已存在*/
	public static Boolean check(BigDecimal exerciseId) throws SQLException {
		String sql="select count(*) num from daily_exercise where exercise_id=?";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,exerciseId);
		ResultSet resultSet=preparedStatement.executeQuery();
		int num = 0;
		while (resultSet.next ()) {
			num = resultSet.getInt ("num");
		}
		Jdbc.release (null, preparedStatement, connection);
		return num != 0;
	}
	
	/*保存方法*/
	public static void save(DailyExercisePO dailyExercisePO) throws SQLException {
		Boolean check = check (dailyExercisePO.getExerciseId());
		if (check) {
			String sql="update daily_exercise set exercise_day=?,exercise_type=?,exercise_time=? where exercise_id=? ";
			Connection connection = Jdbc.getConnection ();
			PreparedStatement preparedStatement = connection.prepareStatement (sql);
			preparedStatement.setTimestamp(1,dailyExercisePO.getExerciseDay());
			preparedStatement.setString(2,dailyExercisePO.getExerciseType());
			preparedStatement.setString(3,dailyExercisePO.getExerciseTime());
			preparedStatement.setBigDecimal(4,dailyExercisePO.getExerciseId());
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
			String sql="insert into daily_exercise(exercise_id,exercise_day,exercise_type,exercise_time)values(?,?,?,?)";
			Connection connection = Jdbc.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1,dailyExercisePO.getExerciseId());
			preparedStatement.setTimestamp(2,dailyExercisePO.getExerciseDay());
			preparedStatement.setString(3,dailyExercisePO.getExerciseType());
			preparedStatement.setString(4,dailyExercisePO.getExerciseTime());
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
		String sql = "delete  from daily_exercise";
		Sql.deleteSQL (sql,logger);
	}
	
	/*根据主键删除数据*/
	public static void deleteById (BigDecimal exerciseId) throws SQLException {
		String sql = "delete  from daily_exercise where exercise_id=? ";
		Connection connection = Jdbc.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
		preparedStatement.setObject(1,exerciseId);
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
