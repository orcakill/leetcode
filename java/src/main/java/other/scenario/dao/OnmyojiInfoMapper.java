package other.scenario.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.dao.Jdbc;
import other.dao.Sql;
import other.scenario.entity.OnmyojiInfoPO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnmyojiInfoMapper {
	private static final Logger logger = LogManager.getLogger(OnmyojiInfoMapper.class);
	
	/*根据主键查询一条数据*/
	public static OnmyojiInfoPO findById(String userName) throws SQLException {
		OnmyojiInfoPO onmyojiInfoPO =new OnmyojiInfoPO();
		String sql="select * from onmyoji_info where user_name=?";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,userName);
		System.out.println (preparedStatement);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()){
			onmyojiInfoPO.setUserName(resultSet.getString(1));
			onmyojiInfoPO.setUserAccount(resultSet.getString(2));
			onmyojiInfoPO.setUserType(resultSet.getString(3));
			onmyojiInfoPO.setUserAddress(resultSet.getString(4));
			onmyojiInfoPO.setUserNum(resultSet.getInt(5));
		}
		Jdbc.release(null, preparedStatement, connection);
		return onmyojiInfoPO;
	}
	
	/*查询全部数据*/
	public static List<OnmyojiInfoPO> findAll() throws SQLException {
		List<OnmyojiInfoPO> onmyojiInfoPOList=new ArrayList<>();
		String sql="select * from onmyoji_info order  by user_num";
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(sql);
		while(resultSet.next()){
			OnmyojiInfoPO onmyojiInfoPO =new OnmyojiInfoPO();
			onmyojiInfoPO.setUserName(resultSet.getString(1));
			onmyojiInfoPO.setUserAccount(resultSet.getString(2));
			onmyojiInfoPO.setUserType(resultSet.getString(3));
			onmyojiInfoPO.setUserAddress(resultSet.getString(4));
			onmyojiInfoPO.setUserNum(resultSet.getInt(5));
			onmyojiInfoPOList.add(onmyojiInfoPO);
		}
		Jdbc.release(null, statement, connection);
		return onmyojiInfoPOList;
	}
	
	/*根据主键判断数据已存在*/
	public static Boolean check(String userName) throws SQLException {
		String sql="select count(*) num from onmyoji_info where user_name=?";
		Connection connection= Jdbc.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject (1,userName);
		ResultSet resultSet=preparedStatement.executeQuery();
		int num = 0;
		while (resultSet.next ()) {
			num = resultSet.getInt ("num");
		}
		Jdbc.release (null, preparedStatement, connection);
		return num != 0;
	}
	
	/*保存方法*/
	public static void save(OnmyojiInfoPO onmyojiInfoPO) throws SQLException {
		Boolean check = check (onmyojiInfoPO.getUserName());
		if (check) {
			String sql="update onmyoji_info set user_account=?,user_type=?,user_address=?,user_num=? where user_name=? ";
			Connection connection = Jdbc.getConnection ();
			PreparedStatement preparedStatement = connection.prepareStatement (sql);
			preparedStatement.setString(1,onmyojiInfoPO.getUserAccount());
			preparedStatement.setString(2,onmyojiInfoPO.getUserType());
			preparedStatement.setString(3,onmyojiInfoPO.getUserAddress());
			preparedStatement.setInt(4,onmyojiInfoPO.getUserNum());
			preparedStatement.setString(5,onmyojiInfoPO.getUserName());
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
			String sql="insert into onmyoji_info(user_name,user_account,user_type,user_address,user_num)values(?,?,?,?,?)";
			Connection connection = Jdbc.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,onmyojiInfoPO.getUserName());
			preparedStatement.setString(2,onmyojiInfoPO.getUserAccount());
			preparedStatement.setString(3,onmyojiInfoPO.getUserType());
			preparedStatement.setString(4,onmyojiInfoPO.getUserAddress());
			preparedStatement.setInt(5,onmyojiInfoPO.getUserNum());
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
		String sql = "delete  from onmyoji_info";
		Sql.deleteSQL (sql, logger);
	}
	
	/*根据主键删除数据*/
	public static void deleteById(String userName) throws SQLException {
		String sql = "delete  from onmyoji_info where user_name=? ";
		Connection connection = Jdbc.getConnection ();
		PreparedStatement preparedStatement = connection.prepareStatement (sql);
		preparedStatement.setObject(1,userName);
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
