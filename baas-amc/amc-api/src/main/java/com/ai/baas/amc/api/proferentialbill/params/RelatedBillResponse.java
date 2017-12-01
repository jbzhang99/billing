package com.ai.baas.amc.api.proferentialbill.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;
/**
 * 查询优惠账务关联账单科目出参
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class RelatedBillResponse extends BaseResponse {

	
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
	 *账单类产品Id
	 */
	private String productId;
	
	/**
	 * 账单类产品名称
	 */
	private String productName;
	
	/**
	 * 关联费用科目
	 */
	private String CostNameList;
	/**
	 * 账单科目描述
	 */
	private String comments;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCostNameList() {
		return CostNameList;
	}
	public void setCostNameList(String costNameList) {
		CostNameList = costNameList;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

}
