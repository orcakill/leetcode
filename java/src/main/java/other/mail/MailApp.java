package other.mail;

import other.mail.controller.EmailBoxController;
import other.mail.controller.MessageEventController;
import other.mail.model.entity.EmailBoxPO;
import other.mail.model.entity.MessageEventPO;

import java.util.*;

import static other.mail.util.SendMail.sendTextMail;

public class MailApp {
        //给指定邮箱，定时发送邮件
        public static void main(String[] args) throws Exception {
            
/*
            Calendar calendar= Calendar.getInstance ();
            TimerTask task = new PlanTimerTask ();
            Date firstTime = calendar.getTime ();
            //间隔：1分钟
            long period = 1000 * 60 ;
            
            Timer timer = new Timer();
           timer.schedule(task, firstTime, period);
*/
            List<MessageEventPO> messageEventPOList= MessageEventController.dealMessage ();
            List<EmailBoxPO> emailBoxPOList= EmailBoxController.messageToEmail (messageEventPOList);
//            for(int i=0;i<+emailBoxPOList.size ();i++){
//                sendTextMail (emailBoxPOList.get (i));
//            }
        }
    
}
