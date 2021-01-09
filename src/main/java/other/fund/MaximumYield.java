package other.fund;




import other.fund.entity.FixedInvestment;
import other.fund.entity.FundData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static other.fund.util.GetFundData.getFundData;
import static other.fund.util.GetFundDataList.getFundDataList;
import static other.fund.util.GetWeek.getWeek;
import static other.fund.util.Round.round;


public class MaximumYield {
    //在每周一定投100元的情况下，计算周期内中的最大收益率
    //爬取网站：天天财富网
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date ksrq= dateFormat1.parse("2018-01-01");   //开始日期
        Date jsrq= dateFormat1.parse("2021-01-01");   //结束日期
        List<String> fundList=new ArrayList<>();
        fundList.add("天弘沪深300ETF联接C");                       //基金名称
        fundList.add("中欧医疗健康混合C");
        fundList.add("中欧医疗健康混合A");
        fundList.add("招商中证白酒指数");
        fundList.add("景顺长城新兴成长混合");
        fundList.add("广发纳斯达克100指数A");
        fundList.add("广发中证全指金融地产联接A");
        double  d1=100.0;                                    //定投金额
        String  sWeek="周一";                                 //定投时间
        List<String> list=new ArrayList<>();
//        list.add("2020-10-19");
//        list.add("2020-10-26");
      for(int i=0;i<fundList.size();i++){
          String  s1=fundList.get(i);
          FixedInvestment fixedInvestment=getRate(s1,ksrq,jsrq,d1,sWeek,list);
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

    public  static FixedInvestment getRate(String s,Date startDate,Date endDate,Double d,String sWeek,List list) throws Exception {
        //存储定投信息
        List<FixedInvestment> fixedInvestments=new ArrayList<>();
        //存储每日基金信息
        List<FundData> fundDataList=getFundDataList(s,startDate,endDate);
        //失败天数
        //开始处理每日数据
        Double   turn=0.0;
        //最低收益率
        double min=0.0;
        //最高收益率
        double max=0.0;
        //确认交易
        boolean trade=false;
        for(int i=0;i<fundDataList.size();i++){
            FixedInvestment fixedInvestment=new FixedInvestment();
            String date=fundDataList.get(i).getFundDay();
            String code=fundDataList.get(i).getFundCode();
            String week=getWeek(date);
            fixedInvestment.setFixedDate(date);
            fixedInvestment.setFixedCode(code);
            fixedInvestment.setFixedWeek(week);
            //如果当前日期在失败日期中，跳过
            if(list.contains(date)){
                FixedInvestment fixedInvestment1=fixedInvestments.get(i-1);
                fixedInvestment.setFixedMoney(fixedInvestment1.getFixedMoney());
                fixedInvestment.setFixedNetValue(fixedInvestment1.getFixedNetValue());
                fixedInvestment.setFixedShare(fixedInvestment1.getFixedShare());
                fixedInvestment.setFixedEarningRate(fixedInvestment1.getFixedEarningRate());
                fixedInvestments.add(fixedInvestment);
                trade=false;
                continue;
            }


            if(i==0){
                FundData fundData=getFundData(fundDataList,date);
                fixedInvestment.setFixedNetValue(fundData.getFundNetValue());
                fixedInvestment.setFixedMoney(turn);
                fixedInvestment.setFixedShare(0.0);
                fixedInvestment.setFixedHoldProfit(0.0);
                fixedInvestment.setFixedEarningRate(0.0);


            }
            if(i>0){
                FixedInvestment fixedInvestment1=fixedInvestments.get(i-1);

                //上个交易日期份额
                Double     lastFixedShare=fixedInvestment1.getFixedShare();
                Double     lastTurn=fixedInvestment1.getFixedMoney();
                //当日数据
                FundData  fundData=getFundData(fundDataList,date);
                //如果当前非交易日
                //当日净值
                Double fundNetValue=fundData.getFundNetValue();
                if(fundNetValue==null){
                    fundNetValue=fixedInvestment1.getFixedNetValue();
                }
                //累计投资金额
                double fixedEarningRate=0.0;
                if(turn>0.0){
                    fixedEarningRate=((lastFixedShare*fundNetValue)-turn)/turn;
                }
                if(trade&&fundData.getFundNetValue()!=null){
                    //累加投资金额
                    turn+=d;
                    Double nowFixedShare=d/fundNetValue;
                    fixedInvestment.setFixedMoney(turn);
                    fixedInvestment.setFixedShare(lastFixedShare+nowFixedShare);
                    fixedInvestment.setFixedHoldProfit((lastFixedShare+nowFixedShare)*fundNetValue);
                    trade=false;
                }
                else{
                    fixedInvestment.setFixedMoney(lastTurn);
                    fixedInvestment.setFixedShare(lastFixedShare);
                    fixedInvestment.setFixedHoldProfit(lastFixedShare*fundNetValue);

                }
                fixedInvestment.setFixedNetValue(fundNetValue);

                if(fixedEarningRate<min){
                    min=fixedEarningRate;
                }
                if(fixedEarningRate>max){
                    max=fixedEarningRate;
                }
                fixedInvestment.setFixedEarningRate(fixedEarningRate);
                fixedInvestment.setFixedMinEarningRate(min);
                fixedInvestment.setFixedMaxEarningRate(max);
            }
            fixedInvestments.add(fixedInvestment);
            //如果当前日期是定投日期，则下一个交易日开启交易
            if(week.equals(sWeek)){
                trade=true;
            }
        }



        FixedInvestment  x=fixedInvestments.get(fixedInvestments.size()-1);
        return   x;

    }











}
