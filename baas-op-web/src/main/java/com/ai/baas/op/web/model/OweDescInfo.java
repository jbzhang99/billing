package com.ai.baas.op.web.model;

import com.ai.baas.amc.api.oweinfoquery.param.OweInfo;

public class OweDescInfo extends OweInfo{
    
    private static final long serialVersionUID = -4637322373266785832L;
    
    private String balanceDesc;

    public String getBalanceDesc() {
        return balanceDesc;
    }

    public void setBalanceDesc(String balanceDesc) {
        this.balanceDesc = balanceDesc;
    }

}
