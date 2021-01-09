package other.fund.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author orcakill
 * @date 2021/1/7  11:15
 **/
public class Round {
    public  static  double round(Double num){
        BigDecimal bd = new BigDecimal(num);
        num= bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return  num;
    }

}
