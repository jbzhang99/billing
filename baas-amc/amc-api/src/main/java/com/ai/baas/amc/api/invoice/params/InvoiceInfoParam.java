package com.ai.baas.amc.api.invoice.params;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.baas.amc.api.invoice.interfaces.IInvoiceInfoSV;
import com.ai.opt.base.vo.BaseInfo;
import javax.validation.constraints.NotNull;

/**
 * 发票信息添加入參
 * @author wangluyang
 *
 */
public class InvoiceInfoParam extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 发票信息id<br>
     * 每条发票信息添加入參都需要一个对应且唯一的ID，<br>
     * 在新建保存后系统自动生成，为不必填项<br>
     * 修改和删除的时候，为必填项<br>
     * VARCHAR(32)
     */
	@Size(max = 32,groups = { IInvoiceInfoSV.saveInvoiceInfo.class  })
	private String invoiceInfoId;

	 /**
     * 账户ID<br>
     * 必填<br>
     * VARCHAR(32)
     */
	@NotNull(message = "账户id不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String acctId;
	/**
	 * 客户id
	 */
	@NotNull(message = "客户id不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String custId;
	/**
	 * 发票抬头
	 */
	@NotBlank(message = "发票抬头不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String title;
	/**
	 * 开具类型
	 */
	private Integer issueType;
	/**
	 * 发票类型
	 * 0:增值税普通发票	1:增值税专用发票
	 */
	@NotNull(message = "发票类型不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private Integer invoiceType;
	/**
	 * 纳税人识别号
	 * 发票类型为增值税专用发票时不能为空
	 */
	private String taxRegNo;
	/**
	 * 开户银行名称
	 * 发票类型为增值税专用发票时不能为空
	 */
	private String bankName;
	/**
	 * 开户账号
	 * 发票类型为增值税专用发票时不能为空
	 */
	private String bankAcctNo;
	/**
	 * 公司注册地址
	 * 发票类型为增值税专用发票时不能为空
	 */
	private String regAddress;
	/**
	 * 注册固定电话
	 * 发票类型为增值税专用发票时不能为空
	 */
	private String regPhone;
	
	/**
	 * 联系人姓名
	 */
	@NotBlank(message = "联系人姓名不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String linkName;
	/**
	 * 联系地址
	 */
	@NotBlank(message = "联系地址不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String address;
	/**
	 * 邮政编码
	 */
	@NotBlank(message = "邮政编码不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String postCode;
	/**
	 * 联系电话1
	 */
	@NotBlank(message = "联系电话1不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String mobileNo;
	/**
	 * 联系电话2
	 */
	@NotBlank(message = "联系电话2不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String phoneNo;
	/**
	 * 邮箱地址
	 */
	@NotBlank(message = "邮箱地址不能为空", groups = { IInvoiceInfoSV.saveInvoiceInfo.class })
	private String email;
	
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
}
