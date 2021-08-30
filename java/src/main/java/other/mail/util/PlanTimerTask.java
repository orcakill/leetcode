package other.mail.util;

import other.mail.controller.MessageEventController;
import other.mail.model.entity.EmailBoxPO;
import other.mail.model.entity.MessageEventPO;

import javax.mail.MessagingException;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static other.mail.util.SendMail.sendOneMail;
import static other.mail.util.SendMail.sendTextMail;
import static util.getPassWord.get163mail;

public class PlanTimerTask extends TimerTask {
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
	 * 初始化计划时间
	 * */
	private static void initPlanTimes () throws ParseException {
		planTimes = new ArrayList<> ();
		Date date = new Date ();
		Calendar calendar = Calendar.getInstance ();
		calendar.setTime (date);
		int hour = calendar.get (Calendar.HOUR_OF_DAY);
		int minute = calendar.get (Calendar.MINUTE)+1;
		planTimes.add (hour + ":" + minute);
		planTimes.add ("20:0");
		
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
		System.out.println (stringBuilder);
		if (planTimes.contains (dateTime)) {
			try {
				List<MessageEventPO> messageEventPOList= MessageEventController.dealMessage ();
			} catch (IOException e) {
				e.printStackTrace ();
			}
			List<EmailBoxPO> emailBoxPOList=new ArrayList<> ();
			for(int i=0;i<+emailBoxPOList.size ();i++){
				sendTextMail (emailBoxPOList.get (i));
			}
		}
		else {
			System.out.println ("不在计划时间点内");
		}
	}
}
