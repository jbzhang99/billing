package com.ai.baas.amc.api.invoice.params;

import java.io.Serializable;

/**
 * 发票信息实体类
 * @author wangluyang
 *
 */
public class InvoiceInfoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String invoiceInfoId;
	
	/**
	 * 租户Id
	 */
	private String tenantId;

	 /**
     * 账户ID<br>
     */
	private String acctId;
	/**
	 * 客户id
	 */
	private String custId;
	/**
	 * 发票抬头
	 */
	private String title;
	/**
	 * 开具类型
	 */
	private int issueType;
	/**
	 * 发票类型
	 * 0:增值税普通发票	1:增值税专用发票
	 */
	private int invoiceType;
	/**
	 * 纳税人识别号
	 */
	private String taxRegNo;
	/**
	 * 开户银行名称
	 */
	private String bankName;
	/**
	 * 开户账号
	 */
	private String bankAcctNo;
	/**
	 * 公司注册地址
	 */
	private String regAddress;
	/**
	 * 注册固定电话
	 */
	private String regPhone;
	
	/**
	 * 联系人姓名
	 */
	private String linkName;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String postCode;
	/**
	 * 联系电话1
	 */
	private String mobileNo;
	/**
	 * 联系电话2
	 */
	private String phoneNo;
	/**
	 * 邮箱地址
	 */
	private String email;
	
	private String licenseAttachId;

    private String licenseAttachType;

    private String taxRegAttachId;

    private String taxRegAttachType;

    private String taxpayerAttachId;

    private String taxpayerAttachType;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInvoiceInfoId() {
		return invoiceInfoId;
	}
	public void setInvoiceInfoId(String invoiceInfoId) {
		this.invoiceInfoId = invoiceInfoId;
	}
	public int getIssueType() {
		return issueType;
	}
	public void setIssueType(int issueType) {
		this.issueType = issueType;
	}
	public int getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(int invoiceType) {
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
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
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
