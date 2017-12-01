package com.ai.baas.amc.api.bill.params;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账单实体.<br>
 *
 * Date: 2016年7月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class BillChargeVo implements Serializable{

    private String acctId;
    private Long custId;
    private String extCustId;
    private String custName;
    private String billMonth;
    private BigDecimal adjustFee;
    private BigDecimal balanceFee;
    private Long subjectId;
    private String subjectName;
    private BigDecimal apportionment;
    private BigDecimal proceeds;

    public BigDecimal getProceeds() {
        return proceeds;
    }

    public void setProceeds(BigDecimal proceeds) {
        this.proceeds = proceeds;
    }

    public BigDecimal getBalanceFee() {
        return balanceFee;
    }

    public void setBalanceFee(BigDecimal balanceFee) {
        this.balanceFee = balanceFee;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getExtCustId() {
		return extCustId;
	}

	public void setExtCustId(String extCustId) {
		this.extCustId = extCustId;
	}

	public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public BigDecimal getAdjustFee() {
        return adjustFee;
    }

    public void setAdjustFee(BigDecimal adjustFee) {
        this.adjustFee = adjustFee;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public BigDecimal getApportionment() {
        return apportionment;
    }

    public void setApportionment(BigDecimal apportionment) {
        this.apportionment = apportionment;
    }
}
