package com.ai.baas.amc.vo;

public class AccountPreferentialVo {
    private String acctId;
    private String tenantId;
    private String fee1;
    private String subject1;
    private String fee2;
    private String subject2;
    private String fee3;
    private String subject3;

    private String billMonth;
    private String custId;
    private String subsId;
    private String serviceId;
    
    //wangjing19 add 2017/04/12 Start
    private String drTotalAmount;
    //wangjing19 add 2017/04/12 End
    
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getSubsId() {
        return subsId;
    }
    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }
    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getBillMonth() {
        return billMonth;
    }
    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }
    public String getAcctId() {
        return acctId;
    }
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }
    public String getTenantId() {
        return tenantId;
    }
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    public String getFee1() {
        return fee1;
    }
    public void setFee1(String fee1) {
        this.fee1 = fee1;
    }
    public String getSubject1() {
        return subject1;
    }
    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }
    public String getFee2() {
        return fee2;
    }
    public void setFee2(String fee2) {
        this.fee2 = fee2;
    }
    public String getSubject2() {
        return subject2;
    }
    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }
    public String getFee3() {
        return fee3;
    }
    public void setFee3(String fee3) {
        this.fee3 = fee3;
    }
    public String getSubject3() {
        return subject3;
    }
    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }
    //wangjing19 add 2017/04/12 Start
	public String getDrTotalAmount() {
		return drTotalAmount;
	}
	public void setDrTotalAmount(String drTotalAmount) {
		this.drTotalAmount = drTotalAmount;
	}
	 //wangjing19 add 2017/04/12 End
}
