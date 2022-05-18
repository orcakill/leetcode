package other.fund.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class GetWeek {
    public static String getWeek(String date) throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date  beginDay=formatter.parse(date);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(beginDay);
            String dow=String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
            if(dow.equals("1")){
                dow="周日";
            }
            if(dow.equals("2")){
                dow="周一";
            }
            if(dow.equals("3")){
                dow="周二";
            }
            if(dow.equals("4")){
                dow="周三";
            }
            if(dow.equals("5")){
                dow="周四";
            }
            if(dow.equals("6")){
                dow="周五";
            }
            if(dow.equals("7")){
                dow="周六";
            }

        return  dow;
    }
}
