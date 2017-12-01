package com.ai.baas.abm.api.account.params;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.ai.baas.abm.api.account.interfaces.IAccountRecordSV;

/**
 * 入账入參实体类
 * @author wangluyang
 *
 */
public class AmcResBookVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "systemId不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
	private String systemId;
	
    private String externalId;
    
    @NotNull(message = "ownerType不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private int ownerType;
    
    @NotNull(message = "ownerId不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private long ownerId;
    
    @NotNull(message = "resourceType不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private int resourceType;
    
    /**
     * 单位：kb
     */
    @NotNull(message = "totalAmount不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private long totalAmount;
    
    @NotNull(message = "effectDate不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private String effectDate;
    
    @NotNull(message = "expireDate不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private String expireDate;
    
    private int allowPresent;
    private int allowConvert;
    private int allowClear;
    
    @NotNull(message = "sourceType不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private int sourceType;
    
    @NotNull(message = "sourceId不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private long sourceId;
    
    //useVersion  使用版本
    private String useFlag;
    
    //商品类型编码   goodsType
    @NotNull(message = "productType不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private String productType;
    
    //入账月份  acctMonth
    @NotNull(message = "accountPeriod不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private String accountPeriod;
    
    //租户id
    @NotNull(message = "tenantId不能为空", groups = { IAccountRecordSV.saveAccountRecord.class })
    private String tenantId;
    
    //  资源科目    默认 000000
    private String subjectId;
  
//  商品类型编码   productType
//  private String goodsType;

    //    抵扣额  默认 0
  	private BigDecimal deductAmount;
  
  	//  已转赠额  默认 0
  	private BigDecimal presentAmount;

  	//	  已转兑额 默认 0
  	private BigDecimal exchangeAmount;
  
  	//  默认1：有效
  	private String bookStatus;

//  剩余资源  totalAmount
//  private BigDecimal balance;

  //  默认为0  useFlag
//private Long useVersion;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public int getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(int ownerType) {
		this.ownerType = ownerType;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public int getAllowPresent() {
		return allowPresent;
	}

	public void setAllowPresent(int allowPresent) {
		this.allowPresent = allowPresent;
	}

	public int getAllowConvert() {
		return allowConvert;
	}

	public void setAllowConvert(int allowConvert) {
		this.allowConvert = allowConvert;
	}

	public int getAllowClear() {
		return allowClear;
	}

	public void setAllowClear(int allowClear) {
		this.allowClear = allowClear;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public long getSourceId() {
		return sourceId;
	}

	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public BigDecimal getDeductAmount() {
		return deductAmount;
	}

	public void setDeductAmount(BigDecimal deductAmount) {
		this.deductAmount = deductAmount;
	}

	public BigDecimal getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(BigDecimal presentAmount) {
		this.presentAmount = presentAmount;
	}

	public BigDecimal getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(BigDecimal exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
}
