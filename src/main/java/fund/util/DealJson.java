package fund.util;

/**
 * @author orcakill
 * @date 2021/1/8  17:08
 **/
public class DealJson {
    public  static  String dealFundStr(String str){
        Integer  a=str.indexOf("Data");
        Integer  b=str.indexOf("ErrCode");
        str=str.substring(a+6,b-2);


        return  str;
    }
}
