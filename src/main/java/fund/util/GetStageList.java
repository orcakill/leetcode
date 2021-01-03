package fund.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetStageList {
    //获取一个阶段内的每一天
    public static List<String> getStageList(Date ksrq, Date jsrq) throws ParseException {
        List<String> list=new ArrayList<>();
        long maxDay=(long) (jsrq.getTime()-ksrq.getTime())/(24*60*60*1000)+1;
        for(int i=0;i<maxDay;i++){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String  beginDay=formatter.format(ksrq);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(ksrq);
            String dow=String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
            list.add(beginDay);
            calendar.add(Calendar.DATE,1);
            ksrq=calendar.getTime();
        }
        return  list                                                                     ;
    }
}
