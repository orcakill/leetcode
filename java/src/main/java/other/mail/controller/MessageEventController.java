package other.mail.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import other.mail.model.entity.MessageEventPO;
import other.mail.util.GetJson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MessageEventController {
	public static List<MessageEventPO> dealMessage () {
		List<MessageEventPO> messageEventPOList = new ArrayList<> ();
		Calendar calendar = Calendar.getInstance ();
		int week = calendar.get (Calendar.DAY_OF_WEEK);
		if (week != 1) {
			/*leetcode需要一周一次提交一次leetcode题库的代码*/
			MessageEventPO messageEventPO=new MessageEventPO ();
			String  url="https://api.github.com/repos/orcakill/leetcode/commits";
			JSONObject jsonObject= GetJson.getHttpJson (url);
			messageEventPO.setMessageTitle ("leetcode题目本周未提交");
			messageEventPO.setMessageContent ("");
		}
		return messageEventPOList;
	}
}
