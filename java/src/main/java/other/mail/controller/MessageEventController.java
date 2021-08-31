package other.mail.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import other.mail.model.dto.CommitDTO;
import other.mail.model.entity.MessageEventPO;
import other.mail.service.MessageService;
import other.mail.util.GetJson;

import java.io.IOException;
import java.util.*;

public class MessageEventController {
	public static List<MessageEventPO> dealMessage () throws Exception {
		List<MessageEventPO> messageEventPOList=new ArrayList<> ();
        /*github  1、leetcode题库算法题目一周至少提交一次 2、leetcode项目每两天至少提交一次*/
        List<MessageEventPO> messageEventPOList1= MessageService.commitMessage ();
        /*        1、考试报名提醒  2、*/
		List<MessageEventPO> messageEventPOList2= MessageService.officeMessage ();
		messageEventPOList.addAll (messageEventPOList1);
		messageEventPOList.addAll (messageEventPOList2);
		return messageEventPOList;
	}
}
