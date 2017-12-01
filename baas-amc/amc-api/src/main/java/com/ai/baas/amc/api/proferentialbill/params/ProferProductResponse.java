package com.ai.baas.amc.api.proferentialbill.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseResponse;

public class ProferProductResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     */
	private String tradeSeq;
	/**
	 * 租户Id
	 */
	private String tenantId;
	/**
	 * 优惠产品Id
	 */
	private String productId;
	/**
	 * 优惠产品类型
	 */
	private String productType;
	/**
	 * 优惠产品（包）名称
	 */
	private String productName;
	/**
	 * 优惠规则
	 */
	private String rule;
	/**
	 * 生效日期
	 */
	private Timestamp activeDate;
	/**
	 * 失效日期
	 */
	private Timestamp invalidDate;
	/**
	 * 状态
	 */
	private String status;
	
	public String getTradeSeq() {
		return tradeSeq;
	}
	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Timestamp getActiveDate() {
		return new Timestamp(activeDate.getTime());
	}
	public void setActiveDate(Timestamp activeDate) {
		this.activeDate = new Timestamp(activeDate.getTime());
	}
	public Timestamp getInvalidDate() {
		return new Timestamp(invalidDate.getTime());
	}
	public void setInvalidDate(Timestamp invalidDate) {
		this.invalidDate = new Timestamp(invalidDate.getTime());
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
