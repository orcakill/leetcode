package other.fund.util;

import other.fund.entity.FundData;

import java.text.ParseException;
import java.util.List;

public class GetFundData {
    //从基金历史数据中获取某日的基金数据
    public static FundData getFundData(List<FundData> list,String sDate) throws ParseException {
        FundData fundData=new FundData();
        for (FundData data : list) {
            String date = data.getFundDay();
            if (sDate.equals(date)) {
                fundData = data;
            }

        }
        return  fundData;
    }
}
