package com.ai.baas.amc.api.invoice.params;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 发票开具记录查询返回实体类
 * @author wangluyang
 *
 */
public class InvoiceRecordVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long invoiceSeq;

    private String acctId;

    private Long subsId;

    private String serviceId;

    private Long totalAmount;

    private Long adjustAfterwards;

    private Long discTotalAmount;

    private Long balance;

    private Long payStatus;

    private Timestamp lastPayDate;

    private Long printTimes;

    private Long custId;

    private Long custType;

    private String tenantId;
	
    private String billMonth;
    
    private String invoiceId;

    private Integer amount;

    private Integer status;

    private String invoiceNo;

    private String expressNo;

    private Timestamp sendTime;
    
    private String custName;
    
    private String extCustId;
    
    /** 发票记录信息 **/
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
	
	public String getExtCustId() {
		return extCustId;
	}

	public void setExtCustId(String extCustId) {
		this.extCustId = extCustId;
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

	public Long getAdjustAfterwards() {
		return adjustAfterwards;
	}

	public void setAdjustAfterwards(Long adjustAfterwards) {
		this.adjustAfterwards = adjustAfterwards;
	}

	public Long getDiscTotalAmount() {
		return discTotalAmount;
	}

	public void setDiscTotalAmount(Long discTotalAmount) {
		this.discTotalAmount = discTotalAmount;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Long getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Long payStatus) {
		this.payStatus = payStatus;
	}

	public Timestamp getLastPayDate() {
		return new Timestamp(lastPayDate.getTime());
	}

	public void setLastPayDate(Timestamp lastPayDate) {
		this.lastPayDate = new Timestamp(lastPayDate.getTime());
	}

	public Long getPrintTimes() {
		return printTimes;
	}

	public void setPrintTimes(Long printTimes) {
		this.printTimes = printTimes;
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

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
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
}
