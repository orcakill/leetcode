package fund.entity;

public class FixedInvestment {
    private String fixedDate;          /*当前时间*/
    private Double fixedNetValue;      /*当前净值*/
    private Double fixedShare;         /*当前持有份额*/
    private Double fixedHoldProfit;    /*持有收益金额*/
    private Double fixedEarningRate;   /*当前收益率*/

    public String getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(String fixedDate) {
        this.fixedDate = fixedDate;
    }

    public Double getFixedHoldProfit() {
        return fixedHoldProfit;
    }

    public void setFixedHoldProfit(Double fixedHoldProfit) {
        this.fixedHoldProfit = fixedHoldProfit;
    }

    public Double getFixedEarningRate() {
        return fixedEarningRate;
    }

    public void setFixedEarningRate(Double fixedEarningRate) {
        this.fixedEarningRate = fixedEarningRate;
    }

    public Double getFixedShare() {
        return fixedShare;
    }

    public void setFixedShare(Double fixedShare) {
        this.fixedShare = fixedShare;
    }

    public Double getFixedNetValue() {
        return fixedNetValue;
    }

    public void setFixedNetValue(Double fixedNetValue) {
        this.fixedNetValue = fixedNetValue;
    }
}
