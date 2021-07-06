package other.fund.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class GetWeekMap {
    //获取一年中所有日期对应的星期
    public static Map<String,String> getWeekMap(Date ksrq, Date jsrq) {
        Map<String,String> map=new TreeMap<>();
        long maxDay= (jsrq.getTime()-ksrq.getTime()) /(24*60*60*1000)+1;
        for(int i=0;i<maxDay;i++){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String  beginDay=formatter.format(ksrq);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(ksrq);
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
            map.put(beginDay,dow);
            calendar.add(Calendar.DATE,1);
            ksrq=calendar.getTime();
        }
        return  map;
    }
}
