package fund.util;

import com.alibaba.fastjson.JSONArray;
import fund.entity.FundProfit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetNetValue {
    //根据基金代码，开始日期，结束日期获取到阶段内所有的每日净值
    public  static JSONArray getNetValue(String code, Date ksrq, Date jsrq) throws Exception {
        List<FundProfit> list=new ArrayList<>();
        Integer pageIndex = 1;
        long maxDay=(long) (jsrq.getTime()-ksrq.getTime())/(24*60*60*1000)+1;
        Integer pageSize= Math.toIntExact(maxDay);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startTime=formatter.format(ksrq);
        String endTime = formatter.format(jsrq);
        String referer = "http://fundf10.eastmoney.com/f10/jjjz_" + code + ".html";
        long time = System.currentTimeMillis();
        String url = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306596328894644803_1571038362181&" +
                "fundCode=%s&pageIndex=%s&pageSize=%s&startDate=%s&endDate=%s&_=%s";
        url = String.format(url,code,pageIndex,pageSize,startTime,endTime,time);
        JSONArray jsonArray= GetJson.getHttpJson(url,referer);
        return  jsonArray;

    }
}
