package other.scenario.dao;

import org.junit.Test;
import other.auto.util.CommonUtils;
import other.scenario.entity.TaskListPO;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskListMapperTest {
	@Test
	public void findById() throws SQLException {
		TaskListPO taskListPO=TaskListMapper.findById("2021-09-27",1);
		System.out.println(taskListPO.getTaskListDate());
		System.out.println(taskListPO.getTaskListNum());
		System.out.println(taskListPO.getTaskNum());
		System.out.println(taskListPO.getUserName());
		System.out.println(taskListPO.getTaskState());
	}
	
	@Test
	public void findAll() throws SQLException {
		List<TaskListPO> taskListPOList=new ArrayList();
		for(int i=0;i<taskListPOList.size();i++){
			System.out.println (taskListPOList.get(i).getTaskListDate());
			System.out.println (taskListPOList.get(i).getTaskListNum());
			System.out.println (taskListPOList.get(i).getTaskNum());
			System.out.println (taskListPOList.get(i).getUserName());
			System.out.println (taskListPOList.get(i).getTaskState());
		}
	}
	
	@Test
	public void check() throws SQLException {
		Boolean boolean1=TaskListMapper.check("2021-09-27",1);
		System.out.println (boolean1);
	}
	
	@Test
	public void save() throws SQLException {
		TaskListPO taskListPO=new TaskListPO();
		taskListPO.setTaskListDate(CommonUtils.getDate());
		taskListPO.setTaskListNum(1);
		taskListPO.setTaskNum(1);
		taskListPO.setUserName("测试");
		taskListPO.setTaskState(1);
		TaskListMapper.save(taskListPO);
	}
	
	@Test
	public void deleteAll() throws SQLException {
		TaskListMapper.deleteAll();
	}
	
	@Test
	public void deleteById() throws SQLException {
		TaskListMapper.deleteById("2021-09-27",1);
	}
}