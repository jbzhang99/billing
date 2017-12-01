package com.ai.baas.op.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.ai.baas.bmc.api.marktableproduct.params.ProductInfo;



public class SingleProduct implements Serializable{
	
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
	 * 产品Id
	 */
	private Long productId;
	
	/**
	 * 优惠产品类型，必填
	 */
	private String productType;
	
	
	/**
	 * 优惠活动名称
	 */
	private String programName;
	
	/**
	 * 规则金额
	 */
	private double ruleAmount;
	/**
	 * 规则单位
	 */
	private String ruleUnit;
	/**
	 * 满减金额
	 */
	private double reduceAmount;
	
	/**
	 * 生效日期，必填
	 */
	private Timestamp activeDate;
	
	/**
	 * 失效时间，必填
	 */
	private Timestamp invalidDate;
	/**
	 * 备注
	 */
	private String comments;
	/**
	 * 已选择的产品列表,必填
	 */
	private List<ProductInfo> proList;
	
	/**
	 * 操作员Id
	 */
	private String operatorId;
	
	/**
	 * 满赠数据
	 */
	private List<FullVO> preList;

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public double getRuleAmount() {
		return ruleAmount;
	}

	public void setRuleAmount(double ruleAmount) {
		this.ruleAmount = ruleAmount;
	}

	public String getRuleUnit() {
		return ruleUnit;
	}

	public void setRuleUnit(String ruleUnit) {
		this.ruleUnit = ruleUnit;
	}

	public double getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(double reduceAmount) {
		this.reduceAmount = reduceAmount;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<ProductInfo> getProList() {
		return proList;
	}

	public void setProList(List<ProductInfo> proList) {
		this.proList = proList;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public List<FullVO> getPreList() {
		return preList;
	}

	public void setPreList(List<FullVO> preList) {
		this.preList = preList;
	}

	
	

}
