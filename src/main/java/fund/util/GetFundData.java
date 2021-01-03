package fund.util;

import fund.entity.FundData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetFundData {
    //从基金历史数据中获取某日的基金数据
    public static FundData getFundData(List<FundData> list,String sDate) throws ParseException {
        FundData fundData=new FundData();
        for(int i=0;i<list.size();i++){
            String date=list.get(i).getFundDay();
            if(sDate.equals(date)){
                fundData=list.get(i);
            }
            SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
            Date day=format.parse(sDate);
            Date day1=format.parse(date);
            if(day.getTime()<day1.getTime()){
                break;
            }
        }
        return  fundData;
    }
}
