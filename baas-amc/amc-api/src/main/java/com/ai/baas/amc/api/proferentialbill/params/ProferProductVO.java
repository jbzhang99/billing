package com.ai.baas.amc.api.proferentialbill.params;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.ai.baas.amc.api.proferentialbill.interfaces.IProferProductManageSV;
import com.ai.opt.base.vo.BaseInfo;

/**
 * 优惠账务信息添加入参
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class ProferProductVO extends BaseInfo{

	
	private static final long serialVersionUID = 1L;
	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR(32)
     */
	@NotNull(message="消息流水号不能为空")
	private String tradeSeq;
	/**
	 * 优惠产品Id
	 */
	@NotNull(message="优惠产品Id不能为空",groups={IProferProductManageSV.UpdateProduct.class})
	private String productId;
	/**
	 * 账单优惠类型
	 */
	@NotNull(message="优惠类型不能为空")
	private String proferType;
	
	/**
	 * 优惠活动名称
	 */
	@NotNull(message="优惠活动名称不能为空")
	private String activityName;
	/**
	 * 满赠（满减）达到金额
	 */
	@NotNull(message="满赠（满减）达到金额不能为空",groups={IProferProductManageSV.AddFullGiftBill.class,IProferProductManageSV.AddFullReduceBill.class})
	private double  AmountOfFullCost;
	/**.
	 * 满减优惠的金额
	 */
	@NotNull(message="满减优惠金额不能为空",groups={IProferProductManageSV.AddFullReduceBill.class})
	private double discountAmount;
	/**
	 * "限时折扣"优惠规则
	 */
	@NotNull(message="限时折扣优惠规则不能为空",groups={IProferProductManageSV.AddDiscountBill.class})
	private List<Map<String,List<ExtendInfo>>> discountRuleList;
	/**
	 * "限时折扣"折扣数目
	 */
	@NotNull(message="限时折扣折扣数不能为空",groups={IProferProductManageSV.AddDiscountBill.class})
	private double discount;
	/**
	 * 保底价
	 */
	@NotNull(message="保底优惠保底价不能为空",groups={IProferProductManageSV.AddBasePriceBill.class})
	private double basePrice;
	/**
	 * 封顶价
	 */
	@NotNull(message="封顶优惠封顶价不能为空",groups={IProferProductManageSV.AddTopPriceBill.class})
	private double topPrice;
	/**
	 * 账单生效日期
	 */
	@NotNull(message="账单生效日期不能为空")
	private Timestamp activeDate;
	/**
	 * 账单失效日期
	 */
	@NotNull(message="账单失效日期不能为空")
	private Timestamp invalidDate;
	/**
	 * 备注
	 */
	private String comments;
	/**
	 * 参加活动的账单列表
	 */
	List<BillInfo> billList;
	/**
	 * 满赠活动赠品
	 */
	List<String> giftList;
	public String getProferType() {
		return proferType;
	}
	public void setProferType(String proferType) {
		this.proferType = proferType;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public double getAmountOfFullCost() {
		return AmountOfFullCost;
	}
	public void setAmountOfFullCost(double amountOfFullCost) {
		AmountOfFullCost = amountOfFullCost;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getTopPrice() {
		return topPrice;
	}
	public void setTopPrice(double topPrice) {
		this.topPrice = topPrice;
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
	public List<BillInfo> getBillList() {
		return billList;
	}
	public void setBillList(List<BillInfo> billList) {
		this.billList = billList;
	}
	public List<String> getGiftList() {
		return giftList;
	}
	public void setGiftList(List<String> giftList) {
		this.giftList = giftList;
	}
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
	public List<Map<String, List<ExtendInfo>>> getDiscountRuleList() {
		return discountRuleList;
	}
	public void setDiscountRuleList(List<Map<String, List<ExtendInfo>>> discountRuleList) {
		this.discountRuleList = discountRuleList;
	}
	
	
	
}
