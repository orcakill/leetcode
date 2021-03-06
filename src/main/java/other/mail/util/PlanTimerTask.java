package other.mail.util;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static other.mail.util.SendMail.sendOneMail;
import static util.getPassWord.get163mail;

public class PlanTimerTask extends TimerTask {
    //计划时间
    private static List<String> planTimes;
    /*
     * 静态初始化
     * */
    static {
        try {
            initPlanTimes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*
     * 初始化计划时间
     * */
    private static void initPlanTimes() throws ParseException {
        planTimes = new ArrayList<String>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=format.parse("2021-01-14 21:00:00");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for(int i=0;i<60;i++){

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            calendar.add(Calendar.MINUTE,1);
            planTimes.add(String.valueOf(hour)+":"+String.valueOf(minute));

        }
    }

    /*
     * 执行
     * */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String dateTime=String.valueOf(hour)+":"+String.valueOf(minute);
        if(planTimes.contains(dateTime))
        {
            String mail="orcakill@dingtalk.com";
            String title=dateTime+" java测试";
            String content="这是一封java邮件";
            String passWord=get163mail();
            try {
                System.out.println(sendOneMail(mail,title,content,passWord));
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            System.out.println("邮件已发送");
        }
        else{
            System.out.println("不在计划时间点内");
        }
    }
}
