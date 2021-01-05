package fund;




import fund.entity.FixedInvestment;
import fund.entity.FundData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static fund.util.GetFundData.getFundData;
import static fund.util.GetFundDataList.getFundDataList;
import static fund.util.GetLastFundData.getLastFundData;
import static fund.util.GetStageList.getStageList;
import static fund.util.GetWeek.getWeek;


public class MaximumYield {
    //在每周一定投100元的情况下，计算周期内中的最大收益率
    //爬取网站：天天财富网
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date ksrq= dateFormat1.parse("2020-01-01");   //开始日期
        Date jsrq= dateFormat1.parse("2021-01-05");   //结束日期
        String s1="天弘沪深300ETF联接C";                       //基金名称
      //  String s1="中欧医疗健康混合C";
     //  String s1="中欧医疗健康混合A";
    //    String s1="招商中证白酒指数";
        double  d1=100.0;                                    //定投金额
        String  sWeek="周一";                                 //定投时间
        List<String> list=new ArrayList<>();
    //    list.add("2020-10-19");
   //     list.add("2020-10-26");

        System.out.println(getRate(s1,ksrq,jsrq,d1,sWeek,list).toString());

    }

    public  static FixedInvestment getRate(String s,Date startDate,Date endDate,Double d,String sWeek,List list) throws Exception {
        //存储定投信息
        List<FixedInvestment> fixedInvestments=new ArrayList<>();
        //存储每日基金信息
        List<FundData> fundDataList=getFundDataList(s,startDate,endDate);
        //获取到阶段内的每一天
        List<String>   dateList=getStageList(startDate,endDate);
        //失败天数
        //开始处理每日数据
        Double   turn=0.0;
        double zz=0.0;
        for(int i=0;i<dateList.size();i++){
            FixedInvestment fixedInvestment=new FixedInvestment();
            String date=dateList.get(i);
            fixedInvestment.setFixedDate(date);
            //如果当前日期在失败日期中，跳过
            if(list.contains(date)){
                FixedInvestment fixedInvestment1=fixedInvestments.get(i-1);
                fixedInvestment.setFixedNetValue(fixedInvestment1.getFixedNetValue());
                fixedInvestment.setFixedShare(fixedInvestment1.getFixedShare());
                fixedInvestment.setFixedEarningRate(fixedInvestment1.getFixedEarningRate());
                fixedInvestments.add(fixedInvestment);

                continue;
            }


            if(i==0){
                FundData fundData=getFundData(fundDataList,date);
                fixedInvestment.setFixedNetValue(fundData.getFundNetValue());
                fixedInvestment.setFixedShare(0.0);
                fixedInvestment.setFixedHoldProfit(0.0);
                fixedInvestment.setFixedEarningRate(0.0);
            }
            if(i>0){
                FixedInvestment fixedInvestment1=fixedInvestments.get(i-1);
                String    lastDay=fixedInvestment1.getFixedDate();
                String     dow=getWeek(lastDay);
                //上个交易日期份额
                Double     lastFixedShare=fixedInvestment1.getFixedShare();
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
                if(dow.equals(sWeek)){
                    //累加投资金额
                    turn+=d;
                    Double nowFixedShare=d/fundNetValue;
                    fixedInvestment.setFixedShare(lastFixedShare+nowFixedShare);
                    fixedInvestment.setFixedHoldProfit((lastFixedShare+nowFixedShare)*fundNetValue);
                }
                else{
                    fixedInvestment.setFixedShare(lastFixedShare);
                    fixedInvestment.setFixedHoldProfit(lastFixedShare*fundNetValue);

                }
                fixedInvestment.setFixedNetValue(fundNetValue);

                if(fixedEarningRate<zz){
                    zz=fixedEarningRate;
                }
                fixedInvestment.setFixedEarningRate(fixedEarningRate);
            }
            fixedInvestments.add(fixedInvestment);

        }



      FixedInvestment  x=fixedInvestments.get(fixedInvestments.size()-1);
        return   x;

    }











}
