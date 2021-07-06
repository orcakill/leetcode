package other.fund.util;

import other.fund.entity.FixedInvestment;
import other.fund.entity.FundData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static other.fund.util.GetCode.getCode;
import static other.fund.util.GetFundData.getFundData;
import static other.fund.util.GetFundDataList.getFundDataList;
import static other.fund.util.GetStageList.getStageList;
import static other.fund.util.GetWeek.getWeek;

/**
 * @author orcakill
 * @date 2021/1/26  9:03
 **/
@SuppressWarnings("rawtypes")
public class MaxRate {
    public  static FixedInvestment getMaxRate(String s, Date startDate, Date endDate, Double d, String sWeek, List list) throws Exception {
        //存储定投信息
        List<FixedInvestment> fixedInvestments=new ArrayList<>();
        //存储每日基金信息
        List<FundData> fundDataList=getFundDataList(s,startDate,endDate);

        List<String> stageList=getStageList(startDate,endDate);
        //开始处理每日数据
        Double   turn=0.0;
        //最低收益率
        double min=0.0;
        //最高收益率
        double max=0.0;
        //确认交易
        String code=getCode(s);
        boolean trade=false;
        for(int i=0;i<stageList.size();i++){
            FixedInvestment fixedInvestment=new FixedInvestment();
            String date=stageList.get(i);

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
            if(week.equals(sWeek)){
                trade=true;
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
                FixedInvestment fixedInvestment1=fixedInvestments.get(fixedInvestments.size()-1);

                //上个交易日期份额
                Double     lastFixedShare=fixedInvestment1.getFixedShare();
                Double     lastTurn=fixedInvestment1.getFixedMoney();
                //当日数据
                FundData  fundData=getFundData(fundDataList,date);
                //当日净值
                Double fundNetValue=fundData.getFundNetValue();
                //如果当前非交易日
                if(fundNetValue==null){
                    continue;
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

        }

        return fixedInvestments.get(fixedInvestments.size()-1);

    }

    @SuppressWarnings("rawtypes")
    public  static FixedInvestment getMaxRate1(Double rate,String s, Date startDate, Date endDate, Double d, String sWeek, List list) throws Exception {
        //存储定投信息
        List<FixedInvestment> fixedInvestments=new ArrayList<>();
        //存储每日基金信息
        List<FundData> fundDataList=getFundDataList(s,startDate,endDate);

        List<String> stageList=getStageList(startDate,endDate);
        //开始处理每日数据
        Double   turn=0.0;
        //最低收益率
        double min=0.0;
        //最高收益率
        double max=0.0;
        //确认交易
        String code=getCode(s);
        //累计收益金额
        double money=0.0;
        boolean trade=false;
        for(int i=0;i<stageList.size();i++){
            FixedInvestment fixedInvestment=new FixedInvestment();
            String date=stageList.get(i);

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
            if(week.equals(sWeek)){
                trade=true;
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
                FixedInvestment fixedInvestment1=fixedInvestments.get(fixedInvestments.size()-1);

                //上个交易日期份额
                Double     lastFixedShare=fixedInvestment1.getFixedShare();
                Double     lastTurn=fixedInvestment1.getFixedMoney();
                Double     lastMoney=fixedInvestment1.getFixedCumulativeRevenue();
                fixedInvestment.setFixedCumulativeRevenue(lastMoney);
                //当日数据
                FundData  fundData=getFundData(fundDataList,date);
                //当日净值
                Double fundNetValue=fundData.getFundNetValue();
                //如果当前非交易日
                if(fundNetValue==null){
                    continue;
                }
                //累计投资金额
                double fixedEarningRate=0.0;
                if(turn>0.0){
                    fixedEarningRate=((lastFixedShare*fundNetValue)-turn)/turn;
                    if(fixedEarningRate>=rate/100){
                        fixedInvestment.setFixedCumulativeRevenue(money+lastFixedShare*fundNetValue);
                        turn=0.0;
                        lastFixedShare=0.0;

                    }
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

        }

        return fixedInvestments.get(fixedInvestments.size()-1);

    }


}
