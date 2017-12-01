package com.ai.baas.dst.api.billsdiscount.params;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单优惠活动产品列表查询请求
 * @author wangluyang
 *
 */
public class BillDiscountListQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;
        
    /**
     * 账单优惠产品ID
     */
    private String discountId;

    /**
     * 账单优惠类型<br>
     * mz:满赠<br>
     * mj:满减<br>
     * dz:限时折扣<br>
     * bd:保底<br>
     * fd:封顶<br>
     * 
     */
    private String discountType;

    /**
     * 优惠活动名称
     */
    private String discountName;

    /**
     * 生效日期
     */
    private String effectDate;

    /**
     * 失效日期
     */
    private String expireDate;
    
    /**
     * 是否所有产品参与优惠
     * 0:否
     * 1:是
     */
    private String allPrdDiscount;

    /**
     * 分页信息
     */
    private PageInfo<BillDiscountInfo> pageInfo;

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

	public PageInfo<BillDiscountInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<BillDiscountInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }

	public String getAllPrdDiscount() {
		return allPrdDiscount;
	}

	public void setAllPrdDiscount(String allPrdDiscount) {
		this.allPrdDiscount = allPrdDiscount;
	}
}
