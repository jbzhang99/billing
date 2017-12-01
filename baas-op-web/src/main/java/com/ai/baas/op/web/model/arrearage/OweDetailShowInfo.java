package com.ai.baas.op.web.model.arrearage;

import java.io.Serializable;
import java.util.List;

public class OweDetailShowInfo implements Serializable{

	private static final long serialVersionUID = 1L;
    private String tenantId;
    private String custName;
    private String custGrade;
    private String unsettledMonth;
    private double balance;
    private List<OweGDetailShowInfo> oweDetailShowInfoList;
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
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
	public String getUnsettledMonth() {
		return unsettledMonth;
	}
	public void setUnsettledMonth(String unsettledMonth) {
		this.unsettledMonth = unsettledMonth;
	}
	public List<OweGDetailShowInfo> getOweDetailShowInfoList() {
		return oweDetailShowInfoList;
	}
	public void setOweDetailShowInfoList(List<OweGDetailShowInfo> oweDetailShowInfoList) {
		this.oweDetailShowInfoList = oweDetailShowInfoList;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
