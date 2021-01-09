package other.fund.util;

import java.util.Map;
import java.util.TreeMap;

public class GetCode {
    //获取基金的基金代码
    public static String getCode(String s) throws Exception {
        Map<String,String> map=new TreeMap<>();
        String address="http://fund.eastmoney.com/js/fundcode_search.js";
        String dayLine = new DealJS().getHttpJson(address,1);
        dayLine=dayLine.substring(11,dayLine.length());
        dayLine=dayLine.substring(0,dayLine.length()-3);
        dayLine=dayLine.replaceAll("\\\"", "");
        dayLine=dayLine.replaceAll("[\\[\\]]","");
        String substring = dayLine.substring(0, dayLine.length() - 2);
        String[] result = substring.split(",");
        String  s0="";
        for(int i=0;i<result.length;i++){
            if(result[i].equals(s)){
                s0=result[i-2];
            }
        }

        return  s0;
    }
}
