package other.fund.util;

/**
 * @author orcakill
 * @date 2021/1/8  17:08
 **/
public class DealJson {
    public  static  String dealFundStr(String str){
        int a=str.indexOf("Data");
        int b=str.indexOf("ErrCode");
        str=str.substring(a+6,b-2);


        return  str;
    }
}
