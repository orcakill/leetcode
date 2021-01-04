package fund;




import fund.entity.FixedInvestment;
import fund.entity.FundData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static fund.util.GetFundData.getFundData;
import static fund.util.GetFundDataList.getFundDataList;
import static fund.util.GetStageList.getStageList;
import static fund.util.GetWeek.getWeek;


public class MaximumYield {
    //在每周一定投100元的情况下，计算周期内中的最大收益率
    //爬取网站：天天财富网
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date ksrq= dateFormat1.parse("2020-07-06");   //开始日期
        Date jsrq= dateFormat1.parse("2020-12-31");   //结束日期
        String s1="天弘沪深300ETF联接C";                       //基金名称
        double  d1=100.0;                                    //定投金额
        String  sWeek="周一";                                 //定投时间
        List<String> list=new ArrayList<>();
        list.add("2020-10-19");
        list.add("2020-10-26");
        System.out.println(getRate(s1,ksrq,jsrq,d1,sWeek,list));

    }

    public  static List<FixedInvestment>  getRate(String s,Date startDate,Date endDate,Double d,String sWeek,List list) throws Exception {
        //存储定投信息
        List<FixedInvestment> fixedInvestments=new ArrayList<>();
        //存储每日基金信息
        List<FundData> fundDataList=getFundDataList(s,startDate,endDate);
        //获取到阶段内的每一天
        List<String>   dateList=getStageList(startDate,endDate);
        //失败天数
        //开始处理每日数据
        Double   turn=0.0;
        for(int i=0;i<dateList.size();i++){
            FixedInvestment fixedInvestment=new FixedInvestment();
            String date=dateList.get(i);
            //如果当前日期在失败日期中，跳过
            if(list.contains(date)){
                continue;
            }

            fixedInvestment.setFixedDate(date);
            if(i==0){
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
                //当日净值
                Double fundNetValue=fundData.getFundNetValue();
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
                fixedInvestment.setFixedEarningRate(fixedEarningRate);
            }
            fixedInvestments.add(fixedInvestment);

        }




        return   fixedInvestments;

    }











}
