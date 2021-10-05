package other.scenario.dao;

import org.junit.Test;
import other.scenario.entity.TaskInfoPO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TaskInfoMapperTest {
	@Test
	public void findById() throws SQLException {
		TaskInfoPO taskInfoPO=TaskInfoMapper.findById(1);
		System.out.println(taskInfoPO.getTaskNum());
		System.out.println(taskInfoPO.getTaskName());
		System.out.println(taskInfoPO.getTaskType());
	}
	
	@Test
	public void findAll() throws SQLException {
		List<TaskInfoPO> taskInfoPOList=TaskInfoMapper.findAll ();
		for (TaskInfoPO taskInfoPO : taskInfoPOList) {
			System.out.println (taskInfoPO.getTaskNum ());
			System.out.println (taskInfoPO.getTaskName ());
			System.out.println (taskInfoPO.getTaskType ());
		}
	}
	
	@Test
	public void check() throws SQLException {
		Boolean boolean1=TaskInfoMapper.check(1);
		System.out.println (boolean1);
	}
	
	@Test
	public void save() throws SQLException {
		TaskInfoPO taskInfoPO=new TaskInfoPO();
		taskInfoPO.setTaskNum(1);
		taskInfoPO.setTaskName("测试");
		taskInfoPO.setTaskType(1);
		TaskInfoMapper.save(taskInfoPO);
	}
	
	@Test
	public void deleteAll() throws SQLException {
		TaskInfoMapper.deleteAll();
	}
	
	@Test
	public void deleteById() throws SQLException {
		TaskInfoMapper.deleteById(1);
	}
}