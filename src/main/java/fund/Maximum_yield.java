package fund;




import org.json.JSONObject;
import util.GetJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Maximum_yield {
    //在每周一定投100元的情况下，计算周期内中的最大收益率
    //爬取网站：天天财富网
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date ksrq= dateFormat1.parse("2020-01-01");
        Date jsrq= dateFormat1.parse("2020-12-31");


        String s1="天弘沪深300ETF联接C";
        System.out.println(count(s1,ksrq,jsrq));

    }
    public static String count(String s,Date ksrq,Date jsrq) throws Exception {
        List<Double> list=new ArrayList<>();
        Map<String,String> map=get_week(ksrq,jsrq);
        String code=getCode(s);
        return s;
    }

    //获取一年中所有日期对应的星期
    public static Map<String,String> get_week(Date ksrq,Date jsrq) throws ParseException {
        Map<String,String> map=new TreeMap<>();
        long maxDay=(long) (jsrq.getTime()-ksrq.getTime())/(24*60*60*1000)+1;
        for(int i=0;i<maxDay;i++){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String  beginDay=formatter.format(ksrq);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(ksrq);
            String dow=String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
            map.put(beginDay,dow);
            calendar.add(Calendar.DATE,1);
            ksrq=calendar.getTime();
            }
        return  map;
    }

    //获取基金的基金代码
    public static String getCode(String s) throws Exception {
        Map<String,String> map=new TreeMap<>();
        String address="http://fund.eastmoney.com/js/fundcode_search.js";
        String dayLine = new GetJson().getHttpJson(address,1);
        dayLine=dayLine.substring(11,dayLine.length());
        dayLine=dayLine.substring(0,dayLine.length()-3);
        dayLine=dayLine.replaceAll("\\\"", "");
        dayLine=dayLine.replaceAll("[\\[\\]]","");
        String substring = dayLine.substring(0, dayLine.length() - 1);
        String[] result = substring.split(",");
        String  s0="";
        for(int i=0;i<result.length;i++){
            if(result[i].equals(s)){
                s0=result[i-1];
            }
        }

        return  s0;
    }


}