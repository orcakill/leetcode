package fund.entity;

public class FixedInvestment {
    private String fixedCode;          /*基金代码*/
    private String fixedWeek;          /*基金星期*/
    private String fixedDate;          /*当前时间*/
    private Double fixedNetValue;      /*当前净值*/
    private Double fixedShare;         /*当前持有份额*/
    private Double fixedMoney;         /*当前定投金额*/
    private Double fixedHoldProfit;    /*持有收益金额*/
    private Double fixedEarningRate;   /*当前收益率*/
    private double fixedMinEarningRate;  /*最低收益率*/
    private double fixedMaxEarningRate;  /*最低收益率*/

    public String getFixedWeek() {
        return fixedWeek;
    }

    public void setFixedWeek(String fixedWeek) {
        this.fixedWeek = fixedWeek;
    }

    public String getFixedCode() {
        return fixedCode;
    }

    public void setFixedCode(String fixedCode) {
        this.fixedCode = fixedCode;
    }

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

    public double getFixedMinEarningRate() {
        return fixedMinEarningRate;
    }

    public void setFixedMinEarningRate(double fixedMinEarningRate) {
        this.fixedMinEarningRate = fixedMinEarningRate;
    }

    public double getFixedMaxEarningRate() {
        return fixedMaxEarningRate;
    }

    public void setFixedMaxEarningRate(double fixedMaxEarningRate) {
        this.fixedMaxEarningRate = fixedMaxEarningRate;
    }

    public Double getFixedMoney() {
        return fixedMoney;
    }

    public void setFixedMoney(Double fixedMoney) {
        this.fixedMoney = fixedMoney;
    }
}
