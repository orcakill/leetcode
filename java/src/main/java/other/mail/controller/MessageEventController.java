package other.mail.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import other.mail.model.dto.CommitDTO;
import other.mail.model.entity.MessageEventPO;
import other.mail.util.GetJson;

import java.util.*;

public class MessageEventController {
	public static List<MessageEventPO> dealMessage () {
		List<MessageEventPO> messageEventPOList = new ArrayList<> ();
		Calendar calendar = Calendar.getInstance ();
		Date  date=new Date ();
		calendar.setTime(date);
		int week = calendar.get (Calendar.DAY_OF_WEEK);
		calendar.add(calendar.DATE,-6);
		Date  date1=calendar.getTime ();
		
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
				Date commitDate=jsonObject2.getDate ("date");
				if(commitDate.getTime ()<date1.getTime ()){
					break;
				}
				String content=jsonObject1.getString ("message");
				CommitDTO commitDTO=new CommitDTO ();
				commitDTO.setCommitDate (commitDate);
				commitDTO.setCommitContent (content);
				commitDTOS.add (commitDTO);
			}
			num++;
			
		}
		int commitNum=0;
		for (CommitDTO commitDTO : commitDTOS) {
			String commitMessage = commitDTO.getCommitContent ();
			int x = commitMessage.indexOf ("leet");
			if (x != -1) {
				commitNum = 1;
				break;
			}
		}
		if(commitNum==0){
			messageEventPO.setMessageDate (date);
			messageEventPO.setMessageTitle ("leetcode题目本周未提交");
			messageEventPO.setMessageContent ("leetcode题目本周未提交");
			messageEventPO.setMessageType (0);
			messageEventPOList.add (messageEventPO);
		}

		
		return messageEventPOList;
	}
}
