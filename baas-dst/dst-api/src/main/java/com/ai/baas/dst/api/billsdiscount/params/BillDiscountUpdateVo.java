package com.ai.baas.dst.api.billsdiscount.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 账单优惠产品变更入参
 * @author wangluyang
 *
 */
public class BillDiscountUpdateVo extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 账单优惠产品ID
     */
    private String discountId;

    /**
     * 优惠活动名称
     */
    private String discountName;

    /**
     * 产品生效日期
     */
    private String effectDate;

    /**
     * 产品失效日期
     */
    private String expireDate;
    
    /**
     * 状态: <br>
     * 0:失效<br>
     * 1:生效<br>
     * 2:待生效<br>
     */
    private String status;

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
     * 满赠（满减）到达金额，单位为元<br>
     * 优惠类型为满赠、满减时使用<br>
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
     * 扣减金额，单位为元<br>
     * 优惠类型为满减优惠时使用<br>
     */
    private String discountAmount;
    
    /**
     * 扣减单位<br>
     * 优惠类型为满减优惠时必填<br>
     */
    private String discountUnitId;

    /**
     * 折扣比例，优惠类型为限时折扣时使用.<br>
     */
    private Double discountPercent;

    /**
     * 保底价，单位为元<br>
     * 优惠类型为保底优惠时使用<br>
     */
    private Double bottomAmount;

    /**
     * 封顶价，单位为元<br>
     * 优惠类型为封顶优惠时使用<br>
     */
    private Double topAmount;

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

    public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }
    
    public String getAllPrdDiscount() {
		return allPrdDiscount;
	}

	public void setAllPrdDiscount(String allPrdDiscount) {
		this.allPrdDiscount = allPrdDiscount;
	}

	public String getFullCostUnitId() {
		return fullCostUnitId;
	}

	public void setFullCostUnitId(String fullCostUnitId) {
		this.fullCostUnitId = fullCostUnitId;
	}

	public String getDiscountRullType() {
		return discountRullType;
	}

	public void setDiscountRullType(String discountRullType) {
		this.discountRullType = discountRullType;
	}

	public String getDiscountUnitId() {
		return discountUnitId;
	}

	public void setDiscountUnitId(String discountUnitId) {
		this.discountUnitId = discountUnitId;
	}

	public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getBottomAmount() {
        return bottomAmount;
    }

    public void setBottomAmount(Double bottomAmount) {
        this.bottomAmount = bottomAmount;
    }

    public Double getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(Double topAmount) {
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
