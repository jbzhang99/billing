package com.ai.baas.op.web.model;

import java.util.List;

/**
 * 账单优惠产品新增Vo
 */
public class BillDiscountSaveVo{

	private String discountId;       //优惠活动ID
    private String discountName;     //优惠活动名称
    private String discountType;    //账单优惠类型（mz:满赠 mj:满减 dz:限时折扣 bd:保底 fd:封顶）
    private String effectDate;      //产品生效日期
    private String expireDate;      //产品失效日期
    private String remark;          //备注
    /**
     * 是否所有产品参与优惠
     * 0:否
     * 1:是
     */
    private String allPrdDiscount;
    
    private String fullCostAmount;  //满赠（满减）到达金额，单位为元
    private String fullCostUnitId;
    private String discountRullType;
    private String discountAmount;  //扣减金额，单位为元
    private String discountUnitId;
    private double discountPercent; //折扣比例，优惠类型为限时折扣时必填
    private double bottomAmount;    //保底价，单位为元
    private double topAmount;       //封顶价，单位为元
    
    private List<String> relatedSubjectList;//关联账单科目（满减、保底、封顶时账单科目）
    private List<String> discountProductList;   //参加活动的账单科目列表
    
    /**
     * 赠品业务类型
     */
    private String giftType;
    private List<String> giftProductIdList; //赠品：赠送业务列表，必填
    private String giftActiveMode;          //赠品：生效方式，必填（ 0:立即生效 1:下月生效 2:指定日期到账）
    private String giftActivePeriod;        //赠品：赠送业务周期，必填（0:一个月 1:三个月 2:指定时间）
    private String giftEffectDate;          //赠品：生效时间
    private String cashAmount;				//赠送现金金额
    private String virtualCoinsNum;			//赠送虚拟币个数

    private String startTime;   //限时折扣：优惠开始时间
    private String endTime;     //限时折扣：优惠结束时间
    
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
    
    public String getAllPrdDiscount() {
		return allPrdDiscount;
	}

	public void setAllPrdDiscount(String allPrdDiscount) {
		this.allPrdDiscount = allPrdDiscount;
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

    public String getGiftType() {
		return giftType;
	}

	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}

	public List<String> getGiftProductIdList() {
        return giftProductIdList;
    }

    public void setGiftProductIdList(List<String> giftProductIdList) {
        this.giftProductIdList = giftProductIdList;
    }

    public String getGiftActiveMode() {
        return giftActiveMode;
    }

    public void setGiftActiveMode(String giftActiveMode) {
        this.giftActiveMode = giftActiveMode;
    }

    public String getGiftActivePeriod() {
        return giftActivePeriod;
    }

    public void setGiftActivePeriod(String giftActivePeriod) {
        this.giftActivePeriod = giftActivePeriod;
    }

    public String getGiftEffectDate() {
        return giftEffectDate;
    }

    public void setGiftEffectDate(String giftEffectDate) {
        this.giftEffectDate = giftEffectDate;
    }

    public String getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(String cashAmount) {
		this.cashAmount = cashAmount;
	}

	public String getVirtualCoinsNum() {
		return virtualCoinsNum;
	}

	public void setVirtualCoinsNum(String virtualCoinsNum) {
		this.virtualCoinsNum = virtualCoinsNum;
	}

	public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
	
}
