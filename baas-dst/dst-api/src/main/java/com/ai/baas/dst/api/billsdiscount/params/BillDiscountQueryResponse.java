package com.ai.baas.dst.api.billsdiscount.params;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单优惠活动产品信息查询返回结果
 * @author wangluyang
 *
 */
public class BillDiscountQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 账单优惠产品信息
     */
    private BillDiscountInfo info;

    public BillDiscountInfo getInfo() {
        return info;
    }

    public void setInfo(BillDiscountInfo info) {
        this.info = info;
    }
}
