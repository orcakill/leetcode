package other.mail.dao;

import org.junit.Test;
import other.mail.model.entity.MessageEventPO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

public class MessageEventMapperTest {
	@Test
	public void findById() throws SQLException {
		MessageEventPO messageEventPO=MessageEventMapper.findById (BigDecimal.valueOf (1));
		System.out.println(messageEventPO.getMessageId());
		System.out.println(messageEventPO.getMessageDate());
		System.out.println(messageEventPO.getMessageTitle());
		System.out.println(messageEventPO.getMessageContent());
		System.out.println(messageEventPO.getMessageType());
		System.out.println(messageEventPO.getGmtCreate());
		System.out.println(messageEventPO.getGmtUpdate());
		System.out.println(messageEventPO.getIsDelete());
	}
	@Test
	public void insert() throws SQLException {
		Date date=new Date();
		MessageEventPO messageEventPO=new MessageEventPO();
		messageEventPO.setMessageDate(date);
		messageEventPO.setMessageTitle("测试");
		messageEventPO.setMessageContent("测试");
		messageEventPO.setMessageType(0);
		messageEventPO.setGmtCreate(date);
		messageEventPO.setGmtUpdate(date);
		messageEventPO.setIsDelete(0);
		
		MessageEventMapper.insert(messageEventPO);
	}
	@Test
	public void deleteAll() throws SQLException {
		MessageEventMapper.deleteAll();
	}
}