package com.ai.baas.amc.api.virtualdeduct.param;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 模拟销账后返回结果 <br>
 *
 * Date: 2016年03月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class VdRealTimeBalance implements Serializable {
	private static final long serialVersionUID = 690621398847099331L;
	/**
	 * 查询条件对象,即信控对象
	 */
	private BalanceQueryRequest owner;
	/**
	 *业务类型
	 */
	private String serviceType;
	/**
	 *返回码
	 */
	private String returnCode;
	/**
	 *抵扣后预存
	 */
	private BigDecimal realBalance;
	/**
	 *当前实时欠费
	 */
	private BigDecimal realBill;
	/**
	 *当前总可销账余额
	 */
	private BigDecimal balance;
	/**
	 *当前历史欠费
	 */
	private BigDecimal unSettleBill;
	/**
	 *外部费用
	 */
	private BigDecimal unIntoBill;
	/**
	 *最早欠费月份
	 */
	private String fstUnsettLemon;
	/**
	 *未交缴费月数
	 */
	private int unsettLemons;
	/**
	 *信贷额度
	 */
	private BigDecimal creditLine;
	/**
	 *当前账期
	 */
	private String acctMonth;
	/**
	 *扩展信息
	 */
	private String  expandInfo;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public BalanceQueryRequest getOwner() {
		return owner;
	}

	public void setOwner(BalanceQueryRequest owner) {
		this.owner = owner;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public BigDecimal getRealBalance() {
		return realBalance;
	}

	public void setRealBalance(BigDecimal realBalance) {
		this.realBalance = realBalance;
	}

	public BigDecimal getRealBill() {
		return realBill;
	}

	public void setRealBill(BigDecimal realBill) {
		this.realBill = realBill;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getUnSettleBill() {
		return unSettleBill;
	}

	public void setUnSettleBill(BigDecimal unSettleBill) {
		this.unSettleBill = unSettleBill;
	}

	public BigDecimal getUnIntoBill() {
		return unIntoBill;
	}

	public void setUnIntoBill(BigDecimal unIntoBill) {
		this.unIntoBill = unIntoBill;
	}

	public String getFstUnsettLemon() {
		return fstUnsettLemon;
	}

	public void setFstUnsettLemon(String fstUnsettLemon) {
		this.fstUnsettLemon = fstUnsettLemon;
	}

	public int getUnsettLemons() {
		return unsettLemons;
	}

	public void setUnsettLemons(int unsettLemons) {
		this.unsettLemons = unsettLemons;
	}

	public BigDecimal getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(BigDecimal creditLine) {
		this.creditLine = creditLine;
	}

	public String getAcctMonth() {
		return acctMonth;
	}

	public void setAcctMonth(String acctMonth) {
		this.acctMonth = acctMonth;
	}

	public String getExpandInfo() {
		return expandInfo;
	}

	public void setExpandInfo(String expandInfo) {
		this.expandInfo = expandInfo;
	}

	@Override
	public String toString() {
		return "RealTimeBalance [owner=" + owner + ", serviceType="
				+ serviceType + ", returnCode=" + returnCode + ", realBalance="
				+ realBalance + ", realBill=" + realBill + ", balance="
				+ balance + ", unSettleBill=" + unSettleBill + ", unIntoBill="
				+ unIntoBill + ", fstUnsettLemon=" + fstUnsettLemon
				+ ", unsettLemons=" + unsettLemons + ", creditLine="
				+ creditLine + ", acctMonth=" + acctMonth + ", expandInfo="
				+ expandInfo + "]";
	}
	
	
	


}