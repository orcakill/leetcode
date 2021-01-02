package fund;




import com.alibaba.fastjson.JSONArray;
import fund.entity.FundProfit;
import fund.util.DealJS;
import fund.util.GetJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static fund.util.GetCode.getCode;
import static fund.util.GetFundProfitList.getFundProfitList;
import static fund.util.GetNetValue.getNetValue;
import static fund.util.GetWeek.getWeek;


public class MaximumYield {
    //在每周一定投100元的情况下，计算周期内中的最大收益率
    //爬取网站：天天财富网
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date ksrq= dateFormat1.parse("2020-01-01");
        Date jsrq= dateFormat1.parse("2020-12-31");


        String s1="天弘沪深300ETF联接C";
        System.out.println(getFundProfitList(s1,ksrq,jsrq));

    }











}
