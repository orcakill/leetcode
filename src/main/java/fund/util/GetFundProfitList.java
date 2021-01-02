package fund.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fund.entity.FundProfit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import static fund.util.GetCode.getCode;
import static fund.util.GetNetValue.getNetValue;
import static fund.util.GetStageList.getStageList;
import static fund.util.GetWeek.getWeek;

public class GetFundProfitList {
    //获得一个基金一段时间内的净值数据
    public static List<FundProfit> getFundProfitList(String s, Date ksrq, Date jsrq) throws Exception {
        List<FundProfit> fundProfitList=new ArrayList<>();
        List<String> stage=getStageList(ksrq,jsrq);
        Map<String,String> map=getWeek(ksrq,jsrq);
        String code=getCode(s);
        JSONArray jsonArray=getNetValue(code,ksrq,jsrq);
        for(int i=0;i<stage.size();i++){
            String date=stage.get(i);
            String week=map.get(date);
            Double netValue=0.0;
            for(int j=jsonArray.size()-1;j>=0;j--){
                JSONObject jsonObject  =  jsonArray.getJSONObject(j) ;
                String date1=jsonObject.getString("FSRQ");
                SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
                Date day=format.parse(date);
                Date day1=format.parse(date1);
                if(day.getTime()<day1.getTime()){
                    break;
                }

                if(date1.equals(date)){
                    String   val1=jsonObject.getString("DWJZ");
                    netValue=Double.valueOf(val1);
                    break;
                }

            }
            FundProfit fundProfit=new FundProfit();
            fundProfit.setFundName(s);
            fundProfit.setFundCode(code);
            fundProfit.setFundDay(date);
            fundProfit.setFundWeek(week);
            if(netValue!=0.0){
                fundProfit.setFundNetValue(netValue);
            }
            fundProfitList.add(fundProfit);

        }
        return fundProfitList;
    }
}
