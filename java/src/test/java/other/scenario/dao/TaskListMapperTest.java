package other.scenario.dao;

import org.junit.Test;
import other.scenario.entity.TaskListPO;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class TaskListMapperTest {
	@Test
	public void findById() throws SQLException {
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		String strDate=simpleDateFormat.format (date);
		TaskListPO taskListPO=TaskListMapper.findById (strDate);
		System.out.println(taskListPO.getTaskListDate());
		System.out.println(taskListPO.getTaskNum());
		System.out.println(taskListPO.getUserName());
		System.out.println(taskListPO.getTaskState());
	}
	@Test
	public void insert() throws SQLException {
		Date date=new Date();
		TaskListPO taskListPO=new TaskListPO();
		taskListPO.setTaskListDate (date);
		taskListPO.setTaskNum(0);
		taskListPO.setUserName("测试");
		taskListPO.setTaskState(0);
		
		TaskListMapper.insert(taskListPO);
	}
	@Test
	public void deleteAll() throws SQLException {
		TaskListMapper.deleteAll();
	}
}