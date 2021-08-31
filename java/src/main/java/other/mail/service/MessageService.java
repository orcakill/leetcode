package other.mail.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import other.mail.model.dto.CommitDTO;
import other.mail.model.entity.MessageEventPO;
import other.mail.util.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static other.articles.util.HttpClient.getITHttpClient;

public class MessageService {
	public static List<MessageEventPO>  commitMessage(){
		List<MessageEventPO> messageEventPOList = new ArrayList<> ();
		Calendar calendar = Calendar.getInstance ();
		Date date=new Date ();
		calendar.setTime(date);
		int week = calendar.get (Calendar.DAY_OF_WEEK);
		calendar.add (Calendar.DATE,-6);
		Date  date1=calendar.getTime ();
		
		/*leetcode需要一周一次提交一次leetcode题库的代码*/
		MessageEventPO messageEventPO = new MessageEventPO ();
		String url = "https://api.github.com/repos/orcakill/leetcode/commits?per_page=100&page=";
		List<CommitDTO> commitDTOS=new ArrayList<> ();
		
		int num=1;
		while(true){
			String  str= GetJson.getHttpJson (url + num);
			JSONArray jsonArray=JSONArray.parseArray (str);
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
		calendar.add(calendar.DATE,4);
		Date  lastTwoDay=calendar.getTime ();
		if(commitDTOS.get (0).getCommitDate ().getTime ()<lastTwoDay.getTime ()){
			MessageEventPO messageEventPO1=new MessageEventPO ();
			messageEventPO1.setMessageDate (date);
			messageEventPO1.setMessageTitle ("leetcode项目最近两天未提交");
			messageEventPO1.setMessageContent ("leetcode项目最近两天未提交");
			messageEventPO1.setMessageType (0);
			messageEventPOList.add (messageEventPO1);
		}
		
		
		return  messageEventPOList;
	}
	
	public static List<MessageEventPO>  officeMessage() throws Exception {
		Calendar calendar = Calendar.getInstance ();
		Date date=new Date ();
		calendar.setTime(date);
		calendar.add (Calendar.DATE,-2);
		Date  date1=calendar.getTime ();
		List<MessageEventPO> messageEventPOList=new ArrayList<> ();
		String   url="https://www.dyrsks.cn/gwysydwks.html";
		Document document = Jsoup.parse (HttpClient.getITHttpClient (url));
		Elements elements = document.getElementsByClass ("list-item");
		String  str=elements.get(0).select ("a").text ();
		String  strDate=elements.get (0).getElementsByClass ("list-item-date").text ();
		SimpleDateFormat dateFormat=new SimpleDateFormat ("yyyy-mm-dd");
		Date date2=dateFormat.parse (strDate);
		if(str.indexOf ("事业单位")!=-1){
		   if(date1.getTime ()<date2.getTime ()){
			   MessageEventPO messageEventPO1=new MessageEventPO ();
			   messageEventPO1.setMessageDate (date);
			   messageEventPO1.setMessageTitle ("东营市人事考试信息网");
			   messageEventPO1.setMessageContent ("东营市人事考试信息网近两天有事业单位考试信息,链接地址："+url);
			   messageEventPO1.setMessageType (0);
			   messageEventPOList.add (messageEventPO1);
		   }
		}
		return  messageEventPOList;
	}
}
