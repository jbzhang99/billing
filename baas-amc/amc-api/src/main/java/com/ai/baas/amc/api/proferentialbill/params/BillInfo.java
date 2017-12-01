package com.ai.baas.amc.api.proferentialbill.params;

import java.io.Serializable;

public class BillInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 账单科目Id
	 */
	private String billId;
	/**
	 * 账单科目名称
	 */
	private String billName;
	/**
	 * 账单科目类型
	 */
	private String billType;
	/**
	 * 账单科目描述
	 */
	private String comments;
	/**
	 * 状态
	 */
	private String status;
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
