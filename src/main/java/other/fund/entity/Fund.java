package other.fund.entity;

import java.util.Date;

/**
 * @author orcakill
 * @date 2021/1/19  8:29
 **/
public class Fund {
    private String fundCode; /*基金代码*/
    private String fundName; /*基金名称*/
    private Date startDate; /*开始日期*/
    private Date endDate; /*结束日期*/
    private Date createTime; /*创建日期*/
    private Date updateTime; /*更新日期*/
    private String activeInd; /*有效标识*/

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getActiveInd() {
        return activeInd;
    }

    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }
}
