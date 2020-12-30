package entity;

/**
 * @author orcakill
 * @date 2020/12/30  10:54
 **/
public class FundProfit {
    String fundName;
    String fundCode;
    String fundDay;
    Double fundNetValue;

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
}
