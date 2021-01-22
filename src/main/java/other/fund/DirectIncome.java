package other.fund;

import other.fund.entity.FixedInvestment;
import other.fund.entity.FundData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static other.fund.util.GetCode.getCode;
import static other.fund.util.GetFundData.getFundData;
import static other.fund.util.GetFundDataList.getFundDataList;
import static other.fund.util.GetStageList.getStageList;
import static other.fund.util.GetWeek.getWeek;
import static other.fund.util.Round.round;

/**
 * 统计在开始日期投资1万，在结束日期的收益及在过程中最低收益率和最高收益率
 * @author orcakill
 * @date 2021/1/21  8:20
 **/
public class DirectIncome {
    /*天天基金网*/
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date ksrq= dateFormat1.parse("2020-01-01");   //开始日期
        Date jsrq= dateFormat1.parse("2021-01-01");   //结束日期
        List<String> fundList=new ArrayList<>();
        //基金名称
        fundList.add("天弘沪深300ETF联接C");
        fundList.add("天弘弘择短债C");
        fundList.add("天弘增利短债C");

        fundList.add("博时信用债券A/B");
        fundList.add("鹏华可转债债券A");
//        fundList.add("中欧医疗健康混合C");
//        fundList.add("中欧医疗健康混合A");
//        fundList.add("招商中证白酒指数(LOF)");
//        fundList.add("景顺长城新兴成长混合");
//        fundList.add("广发纳斯达克100指数A");
//        fundList.add("广发中证全指金融地产联接A");
//        fundList.add("博时黄金ETF");
        Double d=10000.0;
        List<String> list=new ArrayList<>();
        for(int i=0;i<fundList.size();i++){
            String  s1=fundList.get(i);
            FixedInvestment fixedInvestment=getDirectRate(s1,ksrq,jsrq,d,list);
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



    public  static FixedInvestment getDirectRate(String s,Date startDate,Date endDate,Double d,List list) throws Exception {
        //存储每日收益
        List<FixedInvestment> fixedInvestments=new ArrayList<>();
        //存储每日基金信息
        List<FundData> fundDataList=getFundDataList(s,startDate,endDate);
        //存储阶段内日期
        List<String> stageList=getStageList(startDate,endDate);
        //最低收益率
        double min=0.0;
        //最高收益率
        double max=0.0;
        //基金代码
        String code=getCode(s);
        for(int i=0;i<stageList.size();i++){
            FixedInvestment fixedInvestment=new FixedInvestment();
            String date=stageList.get(i);
            String week=getWeek(date);
            fixedInvestment.setFixedDate(date);
            fixedInvestment.setFixedCode(code);
            fixedInvestment.setFixedWeek(week);
            //当日基金数据
            FundData fundData=getFundData(fundDataList,date);
            //当日净值
            Double fundNetValue=fundData.getFundNetValue();
            //确定份额
            if(fundNetValue!=null&&fixedInvestment.getFixedShare()==null){
                fixedInvestment.setFixedNetValue(fundNetValue);
                fixedInvestment.setFixedShare(d/fundNetValue);

            }
            //投资金额
            fixedInvestment.setFixedMoney(d);
            if(i>0){
                FixedInvestment fixedInvestment1=fixedInvestments.get(fixedInvestments.size()-1);
                //上个交易日期份额
                Double     lastFixedShare=fixedInvestment1.getFixedShare();
                if(lastFixedShare!=null){
                    if(fundNetValue==null){
                        fundNetValue=fixedInvestment1.getFixedNetValue();
                    }
                    Double     fixedEarningRate=((lastFixedShare*fundNetValue)-d)/d;
                    fixedInvestment.setFixedMoney(d);
                    fixedInvestment.setFixedShare(lastFixedShare);
                    fixedInvestment.setFixedHoldProfit(lastFixedShare*fundNetValue);
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


            }
            fixedInvestments.add(fixedInvestment);

        }



        FixedInvestment  x=fixedInvestments.get(fixedInvestments.size()-1);
        return   x;

    }

}
