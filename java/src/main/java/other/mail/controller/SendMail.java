package other.mail.controller;


import other.mail.util.PlanTimerTask;

import java.math.BigInteger;
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
