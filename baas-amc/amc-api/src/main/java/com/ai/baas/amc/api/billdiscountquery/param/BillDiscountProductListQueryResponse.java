package com.ai.baas.amc.api.billdiscountquery.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单优惠活动产品列表查询返回结果
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class BillDiscountProductListQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    /**
     * 账单优惠活动产品列表信息
     */
    private PageInfo<BillDiscountProductInfo> pageInfo;

    public PageInfo<BillDiscountProductInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<BillDiscountProductInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }

}
