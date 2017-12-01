package com.ai.baas.op.web.model;

/**
 * 关联优惠活动的产品查询vo
 * @author wangluyang
 *
 */
public class BillProductQueryVo {
	
	private String discountId;       //优惠活动ID
	private String mainProductId;
    private String mainProductName;
    private String billingCycle;

    private String effectDate;      //产品生效日期
    private String expireDate;      //产品失效日期
    
    private Integer pageNo;
    private Integer pageSize;
    
	public String getDiscountId() {
		return discountId;
	}
	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	public String getMainProductId() {
		return mainProductId;
	}
	public void setMainProductId(String mainProductId) {
		this.mainProductId = mainProductId;
	}
	public String getMainProductName() {
		return mainProductName;
	}
	public void setMainProductName(String mainProductName) {
		this.mainProductName = mainProductName;
	}
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
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
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
