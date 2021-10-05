package other.scenario.dao;

import org.junit.Test;
import other.scenario.entity.TaskInfoPO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

public class TaskInfoMapperTest {
	@Test
	public void findById() throws SQLException {
		TaskInfoPO taskInfoPO=TaskInfoMapper.findById (1);
		System.out.println(taskInfoPO.getTaskNum());
		System.out.println(taskInfoPO.getTaskName());
		System.out.println(taskInfoPO.getTaskType());
	}
	@Test
	public void insert() throws SQLException {
		Date date=new Date();
		TaskInfoPO taskInfoPO=new TaskInfoPO();
		taskInfoPO.setTaskNum (1);
		taskInfoPO.setTaskName("测试");
		taskInfoPO.setTaskType(0);
		
		TaskInfoMapper.save(taskInfoPO);
	}
	@Test
	public void deleteAll() throws SQLException {
		TaskInfoMapper.deleteAll();
	}
}