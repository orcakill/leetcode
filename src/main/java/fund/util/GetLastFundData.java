package fund.util;

import fund.entity.FundData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author orcakill
 * @date 2021/1/5  8:04
 **/
public class GetLastFundData {
    //从基金历史数据中获取上一交易日的基金数据
    public static FundData getLastFundData(List<FundData> list, String sDate) throws ParseException {
        FundData fundData=new FundData();
        if(list.size()>0){
            for(int i=1;i<list.size();i++){
                String date=list.get(i).getFundDay();
                if(sDate.equals(date)){
                    fundData=list.get(i-1);
                }
                SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
                Date day=format.parse(sDate);
                Date day1=format.parse(date);
                if(day.getTime()<day1.getTime()){
                    break;
                }
            }
        }
        return  fundData;
    }
}
