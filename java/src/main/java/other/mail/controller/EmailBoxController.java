package other.mail.controller;

import other.mail.model.entity.EmailBoxPO;
import other.mail.model.entity.MessageEventPO;

import java.util.ArrayList;
import java.util.List;

public class EmailBoxController {
	public  static List<EmailBoxPO>  messageToEmail(List<MessageEventPO> messageEventPOList){
		List<EmailBoxPO> emailBoxPOList=new ArrayList<> ();
		for (MessageEventPO messageEventPO : messageEventPOList) {
			EmailBoxPO emailBoxPO = new EmailBoxPO ();
			emailBoxPO.setSender ("orcakill@163.com");
			emailBoxPO.setReceiver ("orcakill@dingtalk.com");
			emailBoxPO.setTitle (messageEventPO.getMessageTitle ());
			emailBoxPO.setContent (messageEventPO.getMessageContent ());
			emailBoxPOList.add (emailBoxPO);
		}
		return  emailBoxPOList;
	}
}
