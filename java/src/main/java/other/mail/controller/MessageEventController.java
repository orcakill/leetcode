package other.mail.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import other.mail.model.dto.CommitDTO;
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
		
		/*leetcode需要一周一次提交一次leetcode题库的代码*/
		MessageEventPO messageEventPO = new MessageEventPO ();
		String url = "https://api.github.com/repos/orcakill/leetcode/commits?per_page=100&page=";
		List<CommitDTO> commitDTOS=new ArrayList<> ();
		int num=1;
		while(true){
			String  str= GetJson.getHttpJson (url+num);
			JSONArray  jsonArray=JSONArray.parseArray (str);
			if(jsonArray.size ()==0){
				break;
			}
			for(int i=0;i<jsonArray.size ();i++){
				JSONObject jsonObject=jsonArray.getJSONObject (i);
				JSONObject jsonObject1=jsonObject.getJSONObject ("commit");
				JSONObject jsonObject2=jsonObject1.getJSONObject ("committer");
				Date date=jsonObject2.getDate ("date");
				String content=jsonObject1.getString ("message");
				CommitDTO commitDTO=new CommitDTO ();
				commitDTO.setCommitDate (date);
				commitDTO.setCommitContent (content);
				commitDTOS.add (commitDTO);
			}
			num++;
			
		}
		messageEventPO.setMessageTitle ("leetcode题目本周未提交");
		messageEventPO.setMessageContent ("");
		
		return messageEventPOList;
	}
}
