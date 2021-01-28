package other.fund.util;

import other.fund.entity.FundData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetStartDate {
    public  static Date getStartDate(List<FundData> fundDataList,Date date) throws ParseException {
        String  date1=fundDataList.get(0).getFundDay();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date  day=date;
        Date  day1=formatter.parse(date1);
        if(day.getTime()<day1.getTime()){
            day=day1;
        }
        return  day;
    }
}
