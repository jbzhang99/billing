package com.ai.baas.bmc.srv.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class ResBook implements Serializable {
	private static final long serialVersionUID = 405120043774726371L;
	private Long bookId;
	private String tenantId;
	private Long ownerId;
	private Integer ownerType;
	private String subjectId;
	private String goodsType;
	private String resourceType;
	private Timestamp createTime;
	private Timestamp effectTime;
	private Timestamp expireTime;
	private Double totalAmount;
	private Double deductAmount;
	private Double presentAmount;
	private Double exchangeAmount;
	private String bookStatus;
	private Integer allowPresent;
	private Integer allowConvert;
	private Integer allowClear;
	private Integer sourceType;
	private String sourceId;
	private Double balance;
	private String acctMonth;
	private Integer useVersion;
	private Timestamp lastUpdateTime;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Timestamp effectTime) {
		this.effectTime = effectTime;
	}

	public Timestamp getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getDeductAmount() {
		return deductAmount;
	}

	public void setDeductAmount(Double deductAmount) {
		this.deductAmount = deductAmount;
	}

	public Double getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(Double presentAmount) {
		this.presentAmount = presentAmount;
	}

	public Double getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(Double exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Integer getAllowPresent() {
		return allowPresent;
	}

	public void setAllowPresent(Integer allowPresent) {
		this.allowPresent = allowPresent;
	}

	public Integer getAllowConvert() {
		return allowConvert;
	}

	public void setAllowConvert(Integer allowConvert) {
		this.allowConvert = allowConvert;
	}

	public Integer getAllowClear() {
		return allowClear;
	}

	public void setAllowClear(Integer allowClear) {
		this.allowClear = allowClear;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAcctMonth() {
		return acctMonth;
	}

	public void setAcctMonth(String acctMonth) {
		this.acctMonth = acctMonth;
	}

	public Integer getUseVersion() {
		return useVersion;
	}

	public void setUseVersion(Integer useVersion) {
		this.useVersion = useVersion;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
