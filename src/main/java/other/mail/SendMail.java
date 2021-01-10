package other.mail;


import other.mail.util.PlanTimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.*;

public class SendMail {
    //给指定邮箱，定时发送邮件
    public static void main(String[] args) throws Exception {



        Calendar  calendar= Calendar.getInstance();
        TimerTask task = new PlanTimerTask();
        Date firstTime = calendar.getTime();
        //间隔：1分钟
        long period = 1000 * 60 ;


        Timer timer = new Timer();
        timer.schedule(task, firstTime, period);



    }


}
