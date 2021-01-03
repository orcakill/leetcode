package fund.entity;

public class FixedInvestment {
    private String fixedDate; /*定投时间*/
    private Integer fixedTurn; /*定投轮次*/
    private Double fixedCycleMoney; /*每期金额*/
    private String fixedConfirmDate; /*定投确认时间*/
    private Double fixedConfirmShare; /*定投确认份额*/
    private Double fixedTotalInvestmentMoney; /*累计投资金额*/
    private Double fixedShare;     /*当前持有份额*/
    private Double fixedHoldMoney; /*当前持有金额*/
    private Double fixedHoldProfit;   /*持有收益金额*/
    private Double fixedEarningRate;  /*当前收益率*/

    public String getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(String fixedDate) {
        this.fixedDate = fixedDate;
    }

    public Integer getFixedTurn() {
        return fixedTurn;
    }

    public void setFixedTurn(Integer fixedTurn) {
        this.fixedTurn = fixedTurn;
    }

    public Double getFixedCycleMoney() {
        return fixedCycleMoney;
    }

    public void setFixedCycleMoney(Double fixedCycleMoney) {
        this.fixedCycleMoney = fixedCycleMoney;
    }

    public Double getFixedTotalInvestmentMoney() {
        return fixedTotalInvestmentMoney;
    }

    public void setFixedTotalInvestmentMoney(Double fixedTotalInvestmentMoney) {
        this.fixedTotalInvestmentMoney = fixedTotalInvestmentMoney;
    }

    public Double getFixedHoldMoney() {
        return fixedHoldMoney;
    }

    public void setFixedHoldMoney(Double fixedHoldMoney) {
        this.fixedHoldMoney = fixedHoldMoney;
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

    public String getFixedConfirmDate() {
        return fixedConfirmDate;
    }

    public void setFixedConfirmDate(String fixedConfirmDate) {
        this.fixedConfirmDate = fixedConfirmDate;
    }

    public Double getFixedConfirmShare() {
        return fixedConfirmShare;
    }

    public void setFixedConfirmShare(Double fixedConfirmShare) {
        this.fixedConfirmShare = fixedConfirmShare;
    }
}
