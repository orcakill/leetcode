package other.scenario.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import other.dao.Jdbc;
import other.scenario.entity.OnmyojiInfoPO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnmyojiInfoMapper {
	
	private static final Logger logger = LogManager.getLogger(OnmyojiInfoPO.class);
	
	public static OnmyojiInfoPO findById(BigDecimal id) throws SQLException {
		OnmyojiInfoPO onmyojiInfoPO =new OnmyojiInfoPO();
		String sql="select * from onmyoji_info where user_name="+id;
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(sql);
		while(resultSet.next()){
			onmyojiInfoPO.setUserName(resultSet.getString(1));
			onmyojiInfoPO.setUserAccount(resultSet.getString(2));
			onmyojiInfoPO.setUserType(resultSet.getString(3));
			onmyojiInfoPO.setUserAddress(resultSet.getString(4));
			onmyojiInfoPO.setUserNum(resultSet.getInt(5));
		}
		Jdbc.release(null, statement, connection);
		return onmyojiInfoPO;
	}
	
	public static List<OnmyojiInfoPO> findAll () throws SQLException {
		List<OnmyojiInfoPO> onmyojiInfoPOList=new ArrayList<> ();
		
		String sql="select * from onmyoji_info order by user_num";
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
			onmyojiInfoPOList.add (onmyojiInfoPO);
		}
		Jdbc.release(null, statement, connection);
		return onmyojiInfoPOList;
	}
	
	public static void insert(OnmyojiInfoPO onmyojiInfoPO) throws SQLException {
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
	
	public static void deleteAll () throws SQLException {
		String sql = "delete   from onmyoji_info";
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
