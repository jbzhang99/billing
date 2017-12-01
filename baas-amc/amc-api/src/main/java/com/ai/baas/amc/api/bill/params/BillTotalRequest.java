package com.ai.baas.amc.api.bill.params;

import com.ai.opt.base.vo.BaseInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 总账查询请求.<br>
 *
 * Date: 2016年7月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author LiangMeng
 */
public class BillTotalRequest extends BaseInfo{

    private static final long serialVersionUID = -3864557795746895877L;
    
    private String acctId ;
    
    private String tenantId;
    private String billMonth;

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }
    
}
