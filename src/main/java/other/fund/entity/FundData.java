package other.fund.entity;

/**
 * @author orcakill
 * @date 2020/12/30  10:54
 **/
public class FundData {
    String fundName;       //基金名称
    String fundCode;       //基金代码
    String fundDay;        //日期
    String fundWeek;       //星期几
    Double fundNetValue;   //基金净值


    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundDay() {
        return fundDay;
    }

    public void setFundDay(String fundDay) {
        this.fundDay = fundDay;
    }

    public Double getFundNetValue() {
        return fundNetValue;
    }

    public void setFundNetValue(Double fundNetValue) {
        this.fundNetValue = fundNetValue;
    }


    public String getFundWeek() {
        return fundWeek;
    }

    public void setFundWeek(String fundWeek) {
        this.fundWeek = fundWeek;
    }
}
