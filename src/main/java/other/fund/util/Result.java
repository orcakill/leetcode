package other.fund.util;

import other.fund.entity.FixedInvestment;

import java.util.Date;
import java.util.List;

import static other.fund.util.MaxRate.getMaxRate;
import static other.fund.util.MaxRate.getMaxRate1;
import static other.fund.util.Round.round;

/**
 * @author orcakill
 * @date 2021/1/26  9:05
 **/
public class Result {
    public static  void resultGetMaxRate(List<String> fundList, Date ksrq,Date jsrq,Double d1,String sWeek,List<String> list) throws Exception {
        for(int i=0;i<fundList.size();i++){
            String  s1=fundList.get(i);
            FixedInvestment fixedInvestment=getMaxRate(s1,ksrq,jsrq,d1,sWeek,list);
            System.out.println(s1+" "
                    +" 基金代码:"+fixedInvestment.getFixedCode()
                    +" 最后日期:"+fixedInvestment.getFixedDate()
                    +" 当前持有份额:"+ round(fixedInvestment.getFixedShare())
                    +" 当前定投金额:"+ round(fixedInvestment.getFixedMoney())
                    +" 当前收益金额:"+round(fixedInvestment.getFixedHoldProfit())
                    +" 当前收益率:"+round(fixedInvestment.getFixedEarningRate()*100)
                    +" 最低收益率:"+round(fixedInvestment.getFixedMinEarningRate()*100)
                    +" 最高收益率:"+round(fixedInvestment.getFixedMaxEarningRate()*100)
                    +" 最终收益:"+round(fixedInvestment.getFixedHoldProfit()-fixedInvestment.getFixedMoney())
            );
        }

    }

    public static  void resultGetMaxRate1(Double d2,List<String> fundList, Date ksrq,Date jsrq,Double d1,String sWeek,List<String> list) throws Exception {
        for(int i=0;i<fundList.size();i++){
            String  s1=fundList.get(i);
            FixedInvestment fixedInvestment=getMaxRate1(d2,s1,ksrq,jsrq,d1,sWeek,list);
            System.out.println(s1+" "
                    +" 基金代码:"+fixedInvestment.getFixedCode()
                    +" 最后日期:"+fixedInvestment.getFixedDate()
                    +" 当前持有份额:"+ round(fixedInvestment.getFixedShare())
                    +" 当前定投金额:"+ round(fixedInvestment.getFixedMoney())
                    +" 当前收益金额:"+round(fixedInvestment.getFixedHoldProfit())
                    +" 当前收益率:"+round(fixedInvestment.getFixedEarningRate()*100)
                    +" 最低收益率:"+round(fixedInvestment.getFixedMinEarningRate()*100)
                    +" 最高收益率:"+round(fixedInvestment.getFixedMaxEarningRate()*100)
                    +" 最终收益:"+round(fixedInvestment.getFixedCumulativeRevenue())
            );
        }

    }
}
