package other.scenario.util;

import lombok.SneakyThrows;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import other.scenario.dao.OnmyojiInfoMapper;
import other.scenario.dao.TaskInfoMapper;
import other.scenario.dao.TaskListMapper;
import other.scenario.entity.OnmyojiInfoPO;
import other.scenario.entity.TaskInfoPO;
import other.scenario.entity.TaskListPO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

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
	 * 初始化计划时间、初始化任务、初始化账号
	 * */
	private static void initPlanTimes () throws ParseException, SQLException {
		planTimes = new ArrayList<> ();
		Date date = new Date ();
		Calendar calendar = Calendar.getInstance ();
		calendar.setTime (date);
		int hour = calendar.get (Calendar.HOUR_OF_DAY);
		int minute = calendar.get (Calendar.MINUTE) + 2;
		planTimes.add (hour + ":" + minute);
	}
	
	/*
	 * 执行
	 * */
	@SneakyThrows
	@Override
	public void run () {
		Date date = new Date ();
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
			stringBuilder.append ("    在计划时间点内,执行开始");
			List<OnmyojiInfoPO> onmyojiInfoPOList= OnmyojiInfoMapper.findAll ();
			List<TaskInfoPO> taskInfoPOList= TaskInfoMapper.findAll ();
			TaskListMapper.deleteAll ();
			for (OnmyojiInfoPO onmyojiInfoPO : onmyojiInfoPOList) {
				
				for (TaskInfoPO taskInfoPO : taskInfoPOList) {
					TaskListPO taskListPO = new TaskListPO ();
					taskListPO.setTaskListDate (date);
					taskListPO.setTaskNum (taskInfoPO.getTaskNum ());
					taskListPO.setUserName (onmyojiInfoPO.getUserName ());
					taskListPO.setTaskState (0);
					TaskListMapper.save (taskListPO);
				}
			}
			int minute1 = RandomUtil.randomMinute (60);
			planTimes.remove (0);
			planTimes.add ("0:" + minute1);
		}
		else {
			stringBuilder.append ("   不在计划时间点内");
		}
		logger.info (stringBuilder);
	}
}
