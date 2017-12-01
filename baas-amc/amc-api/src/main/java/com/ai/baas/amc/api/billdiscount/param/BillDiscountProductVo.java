package com.ai.baas.amc.api.billdiscount.param;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 账单优惠产品新增入参
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class BillDiscountProductVo extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     */
    private String tradeSeq;

    /**
     * 优惠活动名称,必填项
     */
    private String productName;
    
    /**
     * 账单优惠类型,必填项<br>
     * mz:满赠<br>
     * mj:满减<br>
     * dz:限时折扣<br>
     * bd:保底<br>
     * fd:封顶<br>
     */
    private String discountType;
    
    /**
     * 产品生效日期,必填项
     */
    private String effectDate;
    
    /**
     * 产品失效日期,必填项
     */
    private String expireDate;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 满赠（满减）到达金额，单位为元<br>
     * 优惠类型为满赠、满减时必填<br>
     * 
     */
    private double fullCostAmount;
    
    /**
     * 扣减金额，单位为元<br>
     * 优惠类型为满减优惠时必填<br>
     */
    private double discountAmount;
    
    /**
     * 折扣比例，优惠类型为限时折扣时必填.<br>
     */
    private double discountPercent;
    
    /**
     * 保底价，单位为元<br>
     * 优惠类型为保底优惠时必填<br>
     */
    private double bottomAmount;
    
    /**
     * 封顶价，单位为元<br>
     * 优惠类型为封顶优惠时必填<br>
     */
    private double topAmount;    
    
    /**
     * 参加活动的账单科目列表，必填项
     */
    private List<String> billSubjectList;
    
    /**
     * 关联账单科目
     */
    private List<String> relatedSubjectList;
    
    /**
     * 满赠活动的赠品
     */
    private GiftProduct giftProduct;

    public String getTradeSeq() {
        return tradeSeq;
    }

    public void setTradeSeq(String tradeSeq) {
        this.tradeSeq = tradeSeq;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getFullCostAmount() {
        return fullCostAmount;
    }

    public void setFullCostAmount(double fullCostAmount) {
        this.fullCostAmount = fullCostAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getBottomAmount() {
        return bottomAmount;
    }

    public void setBottomAmount(double bottomAmount) {
        this.bottomAmount = bottomAmount;
    }

    public double getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(double topAmount) {
        this.topAmount = topAmount;
    }

    public List<String> getBillSubjectList() {
        return billSubjectList;
    }

    public void setBillSubjectList(List<String> billSubjectList) {
        this.billSubjectList = billSubjectList;
    }

    public List<String> getRelatedSubjectList() {
        return relatedSubjectList;
    }

    public void setRelatedSubjectList(List<String> relatedSubjectList) {
        this.relatedSubjectList = relatedSubjectList;
    }

    public GiftProduct getGiftProduct() {
        return giftProduct;
    }

    public void setGiftProduct(GiftProduct giftProduct) {
        this.giftProduct = giftProduct;
    }
    
}
