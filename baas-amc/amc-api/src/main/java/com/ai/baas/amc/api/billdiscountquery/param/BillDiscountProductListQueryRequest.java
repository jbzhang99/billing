package com.ai.baas.amc.api.billdiscountquery.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单优惠活动产品列表查询请求
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class BillDiscountProductListQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;
        
    /**
     * 账单优惠产品ID
     */
    private String productId;

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
    private String productName;

    /**
     * 生效日期
     */
    private Timestamp effectDate;

    /**
     * 失效日期
     */
    private Timestamp expireDate;

    /**
     * 分页信息
     */
    private PageInfo<BillDiscountProductInfo> pageInfo;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Timestamp getEffectDate() {
        return new Timestamp(effectDate.getTime());
    }

    public void setEffectDate(Timestamp effectDate) {
        this.effectDate = new Timestamp(effectDate.getTime());
    }

    public Timestamp getExpireDate() {
        return new Timestamp(expireDate.getTime());
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = new Timestamp(expireDate.getTime());
    }

    public PageInfo<BillDiscountProductInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<BillDiscountProductInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }
    
}
