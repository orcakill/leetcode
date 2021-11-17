package other.scenario.util;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import other.scenario.dao.OnmyojiInfoMapper;
import other.scenario.dao.TaskInfoMapper;
import other.scenario.dao.TaskListMapper;
import other.scenario.entity.OnmyojiInfoPO;
import other.scenario.entity.TaskInfoPO;
import other.scenario.entity.TaskListPO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static other.scenario.controller.TaskListController.dealTaskList;

public class PlanTimerTask extends TimerTask {
	private static final Logger logger = LogManager.getLogger (PlanTimerTask.class);
	
	//计划时间
	private static List<String> planTimes;
	/*
	 * 静态初始化
	 * */
	static {
		try {
			initPlanTimes ();
		} catch (ParseException | SQLException e) {
			e.printStackTrace ();
		}
	}
	
	/*
	 * 初始化计划时间，只执行一次
	 * */
	private static void initPlanTimes () throws ParseException, SQLException {
		planTimes = new ArrayList<> ();
		Date date = new Date ();
		Calendar calendar = Calendar.getInstance ();
		calendar.setTime (date);
		int hour = calendar.get (Calendar.HOUR_OF_DAY);
		int minute = calendar.get (Calendar.MINUTE);
		planTimes.add (hour + ":" + minute);
	}
	
	/*
	 * 初始化当天任务,每天执行
	 * */
	private static void initTaskListPOList () throws SQLException {
//		获取当前日期
		Date date=new Date ();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		String dateStr=simpleDateFormat.format (date);
//        获取当天任务列表
		List<TaskListPO> taskListPOList=TaskListMapper.findByListDate (dateStr);
		if(taskListPOList.size ()==0){
//			获取账号
            List<OnmyojiInfoPO> onmyojiInfoPOList=OnmyojiInfoMapper.findAll ();
			int num=1;
			for (OnmyojiInfoPO onmyojiInfoPO : onmyojiInfoPOList) {
//				获取任务列表
				List<TaskInfoPO> taskInfoPOList = TaskInfoMapper.findAll ();
				for (TaskInfoPO taskInfoPO : taskInfoPOList) {
					TaskListPO taskListPO = new TaskListPO ();
					taskListPO.setListDate (date);
					taskListPO.setListNum (num);
					taskListPO.setUserName (onmyojiInfoPO.getUserName ());
					taskListPO.setTaskId (taskInfoPO
							.getTaskId ());
					taskListPO.setTaskState (0);
					taskListPO.setTaskNumber (0);
					taskListPOList.add (taskListPO);
					TaskListMapper.save (taskListPO);
					num++;
				}
			}
			
		}
		
	}
	
	/*
	 * 执行
	 * */
	@SneakyThrows
	@Override
	public void run () {
		Calendar calendar = Calendar.getInstance ();
		
		int hour = calendar.get (Calendar.HOUR_OF_DAY);
		int minute = calendar.get (Calendar.MINUTE);
		String dateTime = hour + ":" + minute;
		StringBuilder stringBuilder = new StringBuilder ();
		stringBuilder.append ("当前时间：")
		             .append (dateTime);
		for (String planTime : planTimes) {
			stringBuilder.append (" 计划时间")
			             .append (planTime);
		}
		if (planTimes.contains (dateTime)) {
			stringBuilder.append (",在计划时间点内,执行开始。");
			logger.info (stringBuilder);
			
//			初始化化每日任务
			initTaskListPOList ();
//			执行任务
			dealTaskList ();
		}
		else {
			stringBuilder.append (",不在计划时间点内");
			logger.info (stringBuilder);
		}

	}
}
