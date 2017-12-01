package com.ai.baas.amc.api.bill.params;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 总账查询响应<br>
 *
 * Date: 2016年7月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author LiangMeng
 */
public class BillTotalResponse extends BaseResponse{
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
    public Long getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
    private static final long serialVersionUID = -2650743851723568178L;
    private String tenantId;
    private String tenantName;
    private Long totalAmount;

}
