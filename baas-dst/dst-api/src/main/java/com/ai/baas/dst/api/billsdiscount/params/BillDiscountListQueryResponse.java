package com.ai.baas.dst.api.billsdiscount.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单优惠活动产品列表查询返回结果
 * @author wangluyang
 *
 */
public class BillDiscountListQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    /**
     * 账单优惠活动产品列表信息
     */
    private PageInfo<BillDiscountInfo> pageInfo;

    public PageInfo<BillDiscountInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<BillDiscountInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }

}
