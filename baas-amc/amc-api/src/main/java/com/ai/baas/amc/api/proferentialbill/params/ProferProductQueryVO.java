package com.ai.baas.amc.api.proferentialbill.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;
/**
 * 优惠账单科目查询入参
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class ProferProductQueryVO extends BaseInfo {

	private static final long serialVersionUID = 1L;

	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR(32)
     */
	private String tradeSeq;
	/**
	 * 优惠产品Id
	 */
	private String productId;
	/**
	 * 优惠产品名称
	 */
	private String productName;
	/**
	 * 优惠产品类型
	 */
	private String productType;
	/**
	 * 生效日期
	 */
	private Timestamp activeDate;
	/**
	 * 失效日期
	 */
	private Timestamp invalidDate;
	 /**
     * 当前第几页,必填
     */
    private Integer pageNo;

    /**
     * 每页数据条数,必填
     */
    private Integer pageSize;
	public String getTradeSeq() {
		return tradeSeq;
	}
	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
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
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
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
