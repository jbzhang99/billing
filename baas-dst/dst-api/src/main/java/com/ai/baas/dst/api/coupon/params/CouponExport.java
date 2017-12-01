package com.ai.baas.dst.api.coupon.params;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class CouponExport implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 优惠券Id
	 */
	private String couponId;
	
	/**
	 * 优惠券名称
	 */
	private String couponName;
	/**
	 * 优惠券类型
	 */
	private String couponType;
	/**
	 * 优惠券数量
	 */
	private String couponAmount;
	/**
	 * 优惠券面值
	 */
	private String couponValue;
	/**
	 * 建券时间
	 */
	private Timestamp createTime;
	/**
	 * 生效时间
	 */
	private Timestamp activeTime;
	/**
	 * 失效时间
	 */
	private Timestamp inactiveTime;
	
	/**
	 * 优惠券使用条件
	 */
	private String couponConditon;
	/**
	 * 优惠券编码相关列表
	 */
	private List<CouponCodeList> codeList;
	/**
	 * 优惠方式
	 */
	private String conditionValue;
	/**
	 * 优惠券编码状态
	 */
	private String status;
	/**
	 * 优惠券的有效时间
	 */
	private String lastTime;
	
	private String topMoney;
	
	private String productName;
	
	
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTopMoney() {
		return topMoney;
	}

	public void setTopMoney(String topMoney) {
		this.topMoney = topMoney;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Timestamp activeTime) {
		this.activeTime = activeTime;
	}

	public Timestamp getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(Timestamp inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public String getCouponConditon() {
		return couponConditon;
	}

	public void setCouponConditon(String couponConditon) {
		this.couponConditon = couponConditon;
	}

	public List<CouponCodeList> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<CouponCodeList> codeList) {
		this.codeList = codeList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	
}
