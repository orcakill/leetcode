package other.fund.index;


import other.fund.util.Result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MaximumYield {

    //爬取网站：天天财富网
    public static void getMaximumYield(){
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        //开始日期
        Date ksrq= null;
        try {
            ksrq = dateFormat1.parse("2020-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //结束日期
        Date jsrq= null;
        try {
            jsrq = dateFormat1.parse("2021-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> fundList=new ArrayList<>();
        //基金名称
        fundList.add("天弘沪深300ETF联接C");
        fundList.add("中欧医疗健康混合C");
        fundList.add("中欧医疗健康混合A");
        fundList.add("招商中证白酒指数(LOF)A");
        fundList.add("景顺长城新兴成长混合");
        fundList.add("广发纳斯达克100指数A");
        fundList.add("博时黄金ETF");
        fundList.add("广发中证全指金融地产联接A");
        //定投金额
        double  d1=100.0;
        //定投周期
        String  sWeek="周一";
        //失败日期
        List<String> list=new ArrayList<>();
        //list.add("2020-10-19");
        // list.add("2020-10-26");
        // 在每周一定投100元的情况下，计算周期内中的最大收益率
        try {
         //   Result.resultGetMaxRate(fundList,ksrq,jsrq,d1,sWeek,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //止盈收益率
        double  d2=10.0;
        try {
            Result.resultGetMaxRate1(d2,fundList,ksrq,jsrq,d1,sWeek,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }












}
