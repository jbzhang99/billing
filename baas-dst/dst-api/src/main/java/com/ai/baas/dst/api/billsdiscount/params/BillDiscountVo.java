package com.ai.baas.dst.api.billsdiscount.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 账单优惠产品新增入参
 * @author wangluyang
 *
 */
public class BillDiscountVo extends BaseInfo {

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
    private String discountName;
    
    /**
     * 账单优惠类型,必填项<br>
     * mz:满赠<br>
     * mj:满减<br>
     * dz:限时折扣<br>
     * bd:保底<br>
     * fd:封顶<br>
     * tc:套餐<br>
     */
    private String discountType;
    
    /**
     * 活动生效日期,必填项
     */
    private String effectDate;
    
    /**
     * 活动失效日期,必填项
     */
    private String expireDate;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 是否所有产品参与优惠
     * 0:否
     * 1:是
     */
    private String allPrdDiscount;
    
    /**
     * 满赠（满减）到达金额/数量／时间，<br>
     * 优惠类型为满赠、满减时必填<br>
     * 
     */
    private String fullCostAmount;
    
    /**
     * 满赠（满减）到达金额/数量／时间的单位
     * 优惠类型为满赠、满减时必填
     */
    private String fullCostUnitId;
    
    /**
     * 扣减类型，单位减折次
     * 优惠类型为满减优惠时必填
     */
    private String discountRullType;
    
    /**
     * 扣减金额，单位为元／折<br>
     * 优惠类型为满减优惠时必填<br>
     */
    private String discountAmount;
    
    /**
     * 扣减单位<br>
     * 优惠类型为满减优惠时必填<br>
     */
    private String discountUnitId;
    
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
     * 参与优惠活动的产品列表，必填项
     */
    private List<String> discountProductList;
    
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

    public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public String getFullCostUnitId() {
		return fullCostUnitId;
	}

	public void setFullCostUnitId(String fullCostUnitId) {
		this.fullCostUnitId = fullCostUnitId;
	}

	public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
    
    public String getAllPrdDiscount() {
		return allPrdDiscount;
	}

	public void setAllPrdDiscount(String allPrdDiscount) {
		this.allPrdDiscount = allPrdDiscount;
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

    public String getFullCostAmount() {
		return fullCostAmount;
	}

	public void setFullCostAmount(String fullCostAmount) {
		this.fullCostAmount = fullCostAmount;
	}

	public String getDiscountRullType() {
		return discountRullType;
	}

	public void setDiscountRullType(String discountRullType) {
		this.discountRullType = discountRullType;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDiscountUnitId() {
		return discountUnitId;
	}

	public void setDiscountUnitId(String discountUnitId) {
		this.discountUnitId = discountUnitId;
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

    public List<String> getDiscountProductList() {
		return discountProductList;
	}

	public void setDiscountProductList(List<String> discountProductList) {
		this.discountProductList = discountProductList;
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
