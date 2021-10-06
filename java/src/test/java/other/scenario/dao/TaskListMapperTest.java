package other.scenario.dao;

import org.junit.Test;
import other.auto.util.CommonUtils;
import other.scenario.entity.TaskListPO;

import java.sql.SQLException;
import java.util.List;

public class TaskListMapperTest {
	@Test
	public void findById() throws SQLException {
		TaskListPO taskListPO=TaskListMapper.findById("2021-09-27",1);
		System.out.println(taskListPO.getListDate());
		System.out.println(taskListPO.getListNum());
		System.out.println(taskListPO.getTaskNum());
		System.out.println(taskListPO.getUserName());
		System.out.println(taskListPO.getTaskState());
	}
	
	@Test
	public void findByListDate() throws SQLException {
		List<TaskListPO> taskListPOList=TaskListMapper.findByListDate ("2021-10-06");
		for (TaskListPO taskListPO : taskListPOList) {
			System.out.println (taskListPO.getListDate ());
			System.out.println (taskListPO.getListNum ());
			System.out.println (taskListPO.getTaskNum ());
			System.out.println (taskListPO.getUserName ());
			System.out.println (taskListPO.getTaskState ());
		}
	}
	
	@Test
	public void findAll() throws SQLException {
		List<TaskListPO> taskListPOList=TaskListMapper.findAll ();
		for (TaskListPO taskListPO : taskListPOList) {
			System.out.println (taskListPO.getListDate ());
			System.out.println (taskListPO.getListNum ());
			System.out.println (taskListPO.getTaskNum ());
			System.out.println (taskListPO.getUserName ());
			System.out.println (taskListPO.getTaskState ());
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
		taskListPO.setListDate(CommonUtils.getDate());
		taskListPO.setListNum(1);
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