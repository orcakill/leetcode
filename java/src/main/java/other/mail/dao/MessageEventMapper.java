package other.mail.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import other.dao.Jdbc;
import other.mail.model.entity.MessageEventPO;

import java.math.BigDecimal;
import java.sql.*;

public class MessageEventMapper {
	private static final Logger logger = LogManager.getLogger(MessageEventPO.class);
	public static MessageEventPO findById(BigDecimal id) throws SQLException {
		MessageEventPO messageEventPO =new MessageEventPO();
		String sql="select * from message_event where message_id="+id;
		Connection connection= Jdbc.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(sql);
		while(resultSet.next()){
			messageEventPO.setMessageId(resultSet.getBigDecimal(1));
			messageEventPO.setMessageDate(resultSet.getDate(2));
			messageEventPO.setMessageTitle(resultSet.getString(3));
			messageEventPO.setMessageContent(resultSet.getString(4));
			messageEventPO.setMessageType(resultSet.getInt(5));
			messageEventPO.setGmtCreate(resultSet.getDate(6));
			messageEventPO.setGmtUpdate(resultSet.getDate(7));
			messageEventPO.setIsDelete(resultSet.getInt(8));
			
		}
		Jdbc.release(null, statement, connection);
		return messageEventPO;
	}
	public static void insert(MessageEventPO messageEventPO) throws SQLException {
		String sql="insert into message_event(message_id,message_date,message_title,message_content,message_type,gmt_create,gmt_update,is_delete)values(?,?,?,?,?,?,?,?)";
		Connection connection = Jdbc.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setBigDecimal(1,messageEventPO.getMessageId());
		preparedStatement.setDate(2,new java.sql.Date(messageEventPO.getMessageDate().getTime()));
		preparedStatement.setString(3,messageEventPO.getMessageTitle());
		preparedStatement.setString(4,messageEventPO.getMessageContent());
		preparedStatement.setInt(5,messageEventPO.getMessageType());
		preparedStatement.setDate(6,new java.sql.Date(messageEventPO.getGmtCreate().getTime()));
		preparedStatement.setDate(7,new java.sql.Date(messageEventPO.getGmtUpdate().getTime()));
		preparedStatement.setInt(8,messageEventPO.getIsDelete());
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
		String sql = "delete   from message_event";
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
