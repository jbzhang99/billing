package com.ai.baas.amc.api.paymentquery.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

/**
 * 充值缴费列表查询返回结果
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class PaymentLogQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 充值缴费查询分页结果
     */
    private PageInfo<PaymentLog> pageInfo;

    public PageInfo<PaymentLog> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<PaymentLog> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
