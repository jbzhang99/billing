package com.ai.baas.op.web.model.arrearage;

import com.ai.opt.base.vo.BaseInfo;

public class OweInfoListQueryVo extends BaseInfo{
    private static final long serialVersionUID = 1L;
    private String custName;
    private String custGrade;
    private String startDate;
    private String endDate;
    private String bottomAmount;
    private String topAmount;
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustGrade() {
        return custGrade;
    }
    public void setCustGrade(String custGrade) {
        this.custGrade = custGrade;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getBottomAmount() {
        return bottomAmount;
    }
    public void setBottomAmount(String bottomAmount) {
        this.bottomAmount = bottomAmount;
    }
    public String getTopAmount() {
        return topAmount;
    }
    public void setTopAmount(String topAmount) {
        this.topAmount = topAmount;
    }
   
    
    
}
