package com.ai.baas.amc.api.billdiscountquery.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单优惠活动产品信息查询返回结果
 *
 * Date: 2016年4月8日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class BillDiscountProductQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 账单优惠产品信息
     */
    private BillDiscountProductInfo info;

    public BillDiscountProductInfo getInfo() {
        return info;
    }

    public void setInfo(BillDiscountProductInfo info) {
        this.info = info;
    }
}
