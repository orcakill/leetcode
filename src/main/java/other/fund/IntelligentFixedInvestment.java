package other.fund;

import other.fund.entity.FixedInvestment;
import other.fund.entity.FundData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static other.fund.util.GetFundDataList.getFundDataList;
import static other.fund.util.Round.round;

/**
 * @author orcakill
 * @date 2021/1/13  8:22
 **/
public class IntelligentFixedInvestment {
    //在每周一智能定投50元的情况下，计算周期内中的最大收益率
    //爬取网站：天天财富网
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date ksrq= dateFormat1.parse("2015-01-01");   //开始日期
        Date jsrq= dateFormat1.parse("2021-01-01");   //结束日期
        List<String> fundList=new ArrayList<>();
//        fundList.add("天弘沪深300ETF联接C");                       //基金名称
        fundList.add("中欧医疗健康混合C");
        fundList.add("中欧医疗健康混合A");
//        fundList.add("招商中证白酒指数");
//        fundList.add("景顺长城新兴成长混合");
//        fundList.add("广发纳斯达克100指数A");
//        fundList.add("广发中证全指金融地产联接A");
//        fundList.add("博时黄金ETF");
        double  d1=100.0;                                    //定投金额
        String  sWeek="周一";                                 //定投时间
        List<String> list=new ArrayList<>();
//        list.add("2020-10-19");
//        list.add("2020-10-26");
        for(int i=0;i<fundList.size();i++){
            String  s1=fundList.get(i);
            FixedInvestment fixedInvestment=getIntelRate(s1,ksrq,jsrq,d1,sWeek,list);
            System.out.println(s1+" "
                    +" 基金代码:"+fixedInvestment.getFixedCode()
                    +" 最后日期:"+fixedInvestment.getFixedDate()
                    +" 当前持有份额:"+ round(fixedInvestment.getFixedShare())
                    +" 当前定投金额:"+ round(fixedInvestment.getFixedMoney())
                    +" 当前收益金额:"+round(fixedInvestment.getFixedHoldProfit())
                    +" 当前收益率:"+round(fixedInvestment.getFixedEarningRate()*100)
                    +" 最低收益率:"+round(fixedInvestment.getFixedMinEarningRate()*100)
                    +" 最高收益率:"+round(fixedInvestment.getFixedMaxEarningRate()*100)
            );
        }


    }

    public  static FixedInvestment getIntelRate(String s,Date startDate,Date endDate,Double d,String sWeek,List list) throws Exception {
        //存储最终定投信息
        FixedInvestment fixedInvestment=new FixedInvestment();
        //存储定投信息
        List<FixedInvestment> fixedInvestments=new ArrayList<>();
        //存储每日基金信息
        List<FundData> fundDataList=getFundDataList(s,startDate,endDate);


        return  fixedInvestment;

    }

}
