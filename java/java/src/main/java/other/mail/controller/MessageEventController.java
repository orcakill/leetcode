package other.mail.controller;

import other.mail.model.entity.*;
import other.mail.service.*;

import java.util.*;

public class MessageEventController {
	public static List<MessageEventPO> dealMessage () throws Exception {
		List<MessageEventPO> messageEventPOList=new ArrayList<> ();
        /*github  1、leetcode题库算法题目一周至少提交一次 2、leetcode项目每两天至少提交一次*/
        List<MessageEventPO> messageEventPOList1= MessageService.commitMessage ();
        /*东营市人事考试信息网        1、考试报名提醒  2、*/
		List<MessageEventPO> messageEventPOList2= MessageService.officeMessage ();
		/*微博        1、百词斩  2、粉笔-事业单位做题 */
		List<MessageEventPO> messageEventPOList3= MessageService.weiboMessage();
		//QQ音乐会员提醒       生日时提醒续费会员
		List<MessageEventPO> messageEventPOList4= MessageService.birthdayMessage () ;
		messageEventPOList.addAll (messageEventPOList1);
		messageEventPOList.addAll (messageEventPOList2);
		messageEventPOList.addAll (messageEventPOList3);
		messageEventPOList.addAll (messageEventPOList4);
		return messageEventPOList;
	}
}
