package other.mail.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import other.mail.model.dto.CommitDTO;
import other.mail.model.entity.*;
import other.mail.util.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class MessageService {
	public static List<MessageEventPO>  commitMessage (){
		List<MessageEventPO> messageEventPOList = new ArrayList<> ();
		Calendar calendar = Calendar.getInstance ();
		Date date=new Date ();
		calendar.setTime(date);
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
		calendar.add (Calendar.DATE,4);
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
		SimpleDateFormat dateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		Date date2=dateFormat.parse (strDate);
		if(str.contains ("事业单位")){
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
	/*** 
	 * @description: 检查是否超2天微博上没有分享百词斩的记录
	 * @param  
	 * @return: java.util.List<other.mail.model.entity.MessageEventPO>
	 * @author: orcakill
	 * @date: 2021/11/16 15:54
	 */
	
	public static List<MessageEventPO>  weiboMessage() throws Exception {
		int days=30;
		List<MessageEventPO> messageEventPOList = new ArrayList<> ();
		Calendar calendar = Calendar.getInstance ();
		Date date=new Date ();
		calendar.setTime(date);
		calendar.add (Calendar.DATE,-days);
		Date  date1=calendar.getTime ();
		
		String   url="https://api.weibo.com/2/statuses/home_timeline.json?access_token=2.0067wTZGS4qCCC364c0530c10qYHUM&page=";
		List<CommitDTO> commitDTOS=new ArrayList<> ();
		int num=1;
		while(true){
			String  str= GetJson.getHttpJson (url+num);
			JSONArray jsonArray=JSONObject.parseObject (str).getJSONArray ("statuses");
			if(jsonArray.size ()==0){
				break;
			}
			for(int i=0;i<jsonArray.size ();i++){
				JSONObject jsonObject1=jsonArray.getJSONObject (i);
				String content=jsonObject1.getString ("text");
				String  strDate=jsonObject1.getString ("created_at");
				JSONObject jsonObject2=jsonObject1.getJSONObject ("user");
				String   name=jsonObject2.getString ("screen_name");
				
				SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy", Locale.US);
				Date commitDate=sdf.parse(strDate);
                SimpleDateFormat  simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                String s1=simpleDateFormat.format (commitDate);
				if(commitDate.getTime ()<date1.getTime ()){
					break;
				}
                if(name.equals ("逆戟之刃")){
	                CommitDTO commitDTO=new CommitDTO ();
	                commitDTO.setCommitDate (commitDate);
	                commitDTO.setCommitContent (content);
	                commitDTOS.add (commitDTO);
                }

			}
			num++;
			
		}
//		判断xx天内有无微博记录
		if(commitDTOS.size ()==0){
			MessageEventPO messageEventPO=new MessageEventPO ();
			messageEventPO.setMessageDate (date);
			messageEventPO.setMessageTitle ("微博");
			messageEventPO.setMessageContent ("近"+days+"没有微博记录");
			messageEventPO.setMessageType (0);
			messageEventPOList.add (messageEventPO);
		}
		else{
//			查找最近的百词斩分享的记录日期
			Date  dateEnglish=null;
			for(int i=0;i<commitDTOS.size ();i++){
				CommitDTO commitDTO=commitDTOS.get (i);
				String  commitMessage=commitDTO.getCommitContent ();
				int x = commitMessage.indexOf ("百词斩");
				if (x != -1) {
					dateEnglish=commitDTO.getCommitDate ();
					break;
				}
			}
//			判断百词斩日期是否为空
			if(dateEnglish==null){
				MessageEventPO messageEventPO=new MessageEventPO ();
				messageEventPO.setMessageDate (date);
				messageEventPO.setMessageTitle ("微博");
				messageEventPO.setMessageContent ("微博上近"+days+"天没有百词斩背单词记录");
				messageEventPO.setMessageType (0);
				messageEventPOList.add (messageEventPO);
			}
			else{
				int DayEnglish=(int) ((date.getTime() - dateEnglish.getTime()) / (1000*3600*24));
				if(DayEnglish>=2){
					MessageEventPO messageEventPO=new MessageEventPO ();
					messageEventPO.setMessageDate (date);
					messageEventPO.setMessageTitle ("微博");
					messageEventPO.setMessageContent ("微博上近"+DayEnglish+"天没有百词斩背单词记录");
					messageEventPO.setMessageType (0);
					messageEventPOList.add (messageEventPO);
				}
			}
		}
		return  messageEventPOList;
	}
}
