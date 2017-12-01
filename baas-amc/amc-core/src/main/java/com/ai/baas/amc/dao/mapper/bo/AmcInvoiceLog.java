package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;

public class AmcInvoiceLog extends AmcInvoiceLogKey {
    private String invoiceId;

    private String custId;

    private Integer amount;

    private Integer status;

    private String invoiceNo;

    private String expressNo;

    private Timestamp sendTime;

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

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId == null ? null : invoiceId.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
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
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
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
        this.title = title == null ? null : title.trim();
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
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTaxRegNo() {
        return taxRegNo;
    }

    public void setTaxRegNo(String taxRegNo) {
        this.taxRegNo = taxRegNo == null ? null : taxRegNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAcctNo() {
        return bankAcctNo;
    }

    public void setBankAcctNo(String bankAcctNo) {
        this.bankAcctNo = bankAcctNo == null ? null : bankAcctNo.trim();
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress == null ? null : regAddress.trim();
    }

    public String getRegPhone() {
        return regPhone;
    }

    public void setRegPhone(String regPhone) {
        this.regPhone = regPhone == null ? null : regPhone.trim();
    }

    public String getLicenseAttachId() {
        return licenseAttachId;
    }

    public void setLicenseAttachId(String licenseAttachId) {
        this.licenseAttachId = licenseAttachId == null ? null : licenseAttachId.trim();
    }

    public String getLicenseAttachType() {
        return licenseAttachType;
    }

    public void setLicenseAttachType(String licenseAttachType) {
        this.licenseAttachType = licenseAttachType == null ? null : licenseAttachType.trim();
    }

    public String getTaxRegAttachId() {
        return taxRegAttachId;
    }

    public void setTaxRegAttachId(String taxRegAttachId) {
        this.taxRegAttachId = taxRegAttachId == null ? null : taxRegAttachId.trim();
    }

    public String getTaxRegAttachType() {
        return taxRegAttachType;
    }

    public void setTaxRegAttachType(String taxRegAttachType) {
        this.taxRegAttachType = taxRegAttachType == null ? null : taxRegAttachType.trim();
    }

    public String getTaxpayerAttachId() {
        return taxpayerAttachId;
    }

    public void setTaxpayerAttachId(String taxpayerAttachId) {
        this.taxpayerAttachId = taxpayerAttachId == null ? null : taxpayerAttachId.trim();
    }

    public String getTaxpayerAttachType() {
        return taxpayerAttachType;
    }

    public void setTaxpayerAttachType(String taxpayerAttachType) {
        this.taxpayerAttachType = taxpayerAttachType == null ? null : taxpayerAttachType.trim();
    }
}