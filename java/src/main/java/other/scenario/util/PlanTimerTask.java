package other.scenario.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


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
		} catch (ParseException e) {
			e.printStackTrace ();
		}
	}
	
	/*
	 * 初始化计划时间、初始化任务、初始化账号
	 * */
	private static void initPlanTimes () throws ParseException {
		planTimes = new ArrayList<> ();
		Date date = new Date ();
		Calendar calendar = Calendar.getInstance ();
		calendar.setTime (date);
		int hour = calendar.get (Calendar.HOUR_OF_DAY);
		int minute = calendar.get (Calendar.MINUTE)+1;
		planTimes.add (hour + ":" + minute);

		
	}
	
	/*
	 * 执行
	 * */
	@Override
	public void run () {
		
		Calendar calendar = Calendar.getInstance ();
		
		int hour = calendar.get (Calendar.HOUR_OF_DAY);
		int minute = calendar.get (Calendar.MINUTE);
		String dateTime = hour + ":" + minute;
		StringBuilder stringBuilder=new StringBuilder ();
		stringBuilder.append ("当前时间：").append (dateTime);
		for (String planTime : planTimes) {
			stringBuilder.append (" 计划时间")
			             .append (planTime);
		}
		if (planTimes.contains (dateTime)) {
			stringBuilder.append ("    在计划时间点内,执行开始");
			int minute1=RandomUtil.randomMinute (60);
			planTimes.remove (0);
			planTimes.add ("0:"+minute1);
		}
		else {
			stringBuilder.append ("   不在计划时间点内");
		}
		logger.info (stringBuilder);
	}
}
