package com.ai.baas.abm.api.invoice.params;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 发票信息详情实体类
 * @author wangluyang
 *
 */
public class InvoiceInfoDetailVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//账单表字断---start----//
	private Long invoiceSeq;

    private String acctId;

    private Long subsId;

    private String serviceId;

    private Long totalAmount;
    
    private Timestamp lastPayDate;
    
    private Long custId;

    private Long custType;
    
    private String custName;

    private String tenantId;
    
    private String extCustId;
    //账单表字断---end----//
    
    //开具记录表--start--//
    private Integer amount;

    private Integer status;

    private String invoiceNo;

    private String expressNo;

    private Timestamp sendTime;
    
    private String billMonth;
    //开具记录表--end--//
    
    //发票明细表--start--//
    private String invoiceInfoId;

    private String title;

    private Integer issueType;

    private Integer invoiceType;

    private String linkName;

    private String address;

    private String postCode;

    private String mobileNo;

    private String phoneNo;

    private String email;

    private String taxRegNo;

    private String bankName;

    private String bankAcctNo;

    private String regAddress;

    private String regPhone;

    private String licenseAttachId;

    private String licenseAttachType;

    private String taxRegAttachId;

    private String taxRegAttachType;

    private String taxpayerAttachId;

    private String taxpayerAttachType;
    //发票明细表--end--//

	public Long getInvoiceSeq() {
		return invoiceSeq;
	}

	public void setInvoiceSeq(Long invoiceSeq) {
		this.invoiceSeq = invoiceSeq;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public Long getSubsId() {
		return subsId;
	}

	public void setSubsId(Long subsId) {
		this.subsId = subsId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Timestamp getLastPayDate() {
		return lastPayDate;
	}

	public void setLastPayDate(Timestamp lastPayDate) {
		this.lastPayDate = lastPayDate;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getCustType() {
		return custType;
	}

	public void setCustType(Long custType) {
		this.custType = custType;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public String getInvoiceInfoId() {
		return invoiceInfoId;
	}

	public void setInvoiceInfoId(String invoiceInfoId) {
		this.invoiceInfoId = invoiceInfoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIssueType() {
		return issueType;
	}

	public void setIssueType(Integer issueType) {
		this.issueType = issueType;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTaxRegNo() {
		return taxRegNo;
	}

	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAcctNo() {
		return bankAcctNo;
	}

	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getRegPhone() {
		return regPhone;
	}

	public void setRegPhone(String regPhone) {
		this.regPhone = regPhone;
	}

	public String getLicenseAttachId() {
		return licenseAttachId;
	}

	public void setLicenseAttachId(String licenseAttachId) {
		this.licenseAttachId = licenseAttachId;
	}

	public String getLicenseAttachType() {
		return licenseAttachType;
	}

	public void setLicenseAttachType(String licenseAttachType) {
		this.licenseAttachType = licenseAttachType;
	}

	public String getTaxRegAttachId() {
		return taxRegAttachId;
	}

	public void setTaxRegAttachId(String taxRegAttachId) {
		this.taxRegAttachId = taxRegAttachId;
	}

	public String getTaxRegAttachType() {
		return taxRegAttachType;
	}

	public void setTaxRegAttachType(String taxRegAttachType) {
		this.taxRegAttachType = taxRegAttachType;
	}

	public String getTaxpayerAttachId() {
		return taxpayerAttachId;
	}

	public void setTaxpayerAttachId(String taxpayerAttachId) {
		this.taxpayerAttachId = taxpayerAttachId;
	}

	public String getTaxpayerAttachType() {
		return taxpayerAttachType;
	}

	public void setTaxpayerAttachType(String taxpayerAttachType) {
		this.taxpayerAttachType = taxpayerAttachType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getExtCustId() {
		return extCustId;
	}

	public void setExtCustId(String extCustId) {
		this.extCustId = extCustId;
	}
}
