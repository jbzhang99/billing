package com.ai.citic.billing.web.model.deposit;

import java.sql.Timestamp;
/**
 * 充值明细扩展
 * Date: 2016年7月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author luoxuan
 */
public class FundSerialInfoVo {

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 交易金额
     */
    private Long totalAmount;
    
    /**
     * 交易时间
     */
    private Timestamp payTime;
    
    /**
     * 交易账户ID1
     */
    private String acctId1;
    
    private String orgName;
	private String bankAccount;

	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Timestamp getPayTime() {
		return payTime;
	}
	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getAcctId1() {
		return acctId1;
	}
	public void setAcctId1(String acctId1) {
		this.acctId1 = acctId1;
	}
}
