package other.mail;

import other.mail.util.PlanTimerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class App {
        //给指定邮箱，定时发送邮件
        public static void main(String[] args) throws Exception {
            
            Calendar calendar= Calendar.getInstance ();
            TimerTask task = new PlanTimerTask ();
            Date firstTime = calendar.getTime ();
            //间隔：1分钟
            long period = 1000 * 60 ;
            
            Timer timer = new Timer();
            timer.schedule(task, firstTime, period);
            
        }
    
}
