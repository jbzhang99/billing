package com.ai.baas.amc.api.bill.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单查询响应.<br>
 *
 * Date: 2016年7月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class BillSearchResponse extends BaseResponse{
    private String tenantId;
    private String tenantName;
    private PageInfo<BillChargeVo> billPageVo;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public PageInfo<BillChargeVo> getBillPageVo() {
        return billPageVo;
    }

    public void setBillPageVo(PageInfo<BillChargeVo> billPageVo) {
        this.billPageVo = billPageVo;
    }
}
