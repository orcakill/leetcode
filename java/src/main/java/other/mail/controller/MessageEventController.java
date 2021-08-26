package other.mail.controller;

import other.mail.model.entity.MessageEventPO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MessageEventController {
	
	public  static List<MessageEventPO> dealMessage (){
		List<MessageEventPO> messageEventPOList=new ArrayList<> ();
	
		Calendar calendar=Calendar.getInstance ();
		int week=calendar.get(Calendar.DAY_OF_WEEK);
		if(week==1){
			/*leetcode一周一次提交一次代码*/
		}
		return  messageEventPOList;
	}
}
