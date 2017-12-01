package com.ai.baas.abm.api.invoice.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 发票开具记录新增入參
 * @author wangluyang
 *
 */
public class InvoiceRecordInsertParam extends BaseInfo{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 账户id
	 */
	private String acctId;
	
	/**
	 * 客户id
	 */
	private String custId;
	
	/**
	 * 帐期
	 */
	private String billMonth;
	
	/**
	 * 快递单号
	 */
	private String expressNo;
	/**
	 * 寄出时间
	 */
	private Timestamp sendTime;
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
}
