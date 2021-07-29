package other.fund.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import other.fund.entity.FundData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static other.fund.util.GetCode.getCode;
import static other.fund.util.GetNetValue.getNetValue;
import static other.fund.util.GetStageList.getStageList;
import static other.fund.util.GetWeekMap.getWeekMap;

public class GetFundDataList {
    //获得一个基金一段时间内的净值数据
    public static List<FundData>  getFundDataList(String s, Date ksrq, Date jsrq) {
        List<FundData> fundDataList =new ArrayList<>();
        List<String> stage=getStageList(ksrq,jsrq);
        Map<String,String> map=getWeekMap(ksrq,jsrq);
        String code=getCode(s);
        JSONArray jsonArray=getNetValue(code,ksrq,jsrq);
        for (String date : stage) {

            String week = map.get(date);
            double netValue = 0.0;
            for (int j = jsonArray.size() - 1; j >= 0; j--) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                String date1 = jsonObject.getString("FSRQ");
                if (date1.equals(date)) {
                    String val1 = jsonObject.getString("DWJZ");
                    netValue = Double.parseDouble(val1);
                    jsonArray.remove(j);

                    break;
                }
            }
            FundData fundData = new FundData();
            fundData.setFundCode(code);
            fundData.setFundDay(date);
            fundData.setFundWeek(week);
            if (netValue != 0.0) {
                fundData.setFundNetValue(netValue);
            }
            fundDataList.add(fundData);
        }
        return fundDataList;
    }
}