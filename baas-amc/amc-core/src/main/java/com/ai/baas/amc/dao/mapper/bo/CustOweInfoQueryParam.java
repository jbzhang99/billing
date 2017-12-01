package com.ai.baas.amc.dao.mapper.bo;

public class CustOweInfoQueryParam {
    
    private String tenantId;

    private String custName;
    
    private String custGrade;
    
    private String startDate;
    
    private String endDate;
    
    private Long bottomBalance; 
    
    private Long topBalance; 
    
    private Integer limitStart;

    private Integer limitEnd;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCustGrade() {
        return custGrade;
    }

    public void setCustGrade(String custGrade) {
        this.custGrade = custGrade == null ? null : custGrade.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public Long getBottomBalance() {
        return bottomBalance;
    }

    public void setBottomBalance(Long bottomBalance) {
        this.bottomBalance = bottomBalance;
    }

    public Long getTopBalance() {
        return topBalance;
    }

    public void setTopBalance(Long topBalance) {
        this.topBalance = topBalance;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd = limitEnd;
    }
}
