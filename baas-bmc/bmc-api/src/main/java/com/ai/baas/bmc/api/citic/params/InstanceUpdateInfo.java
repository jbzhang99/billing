package com.ai.baas.bmc.api.citic.params;

import com.ai.opt.base.vo.BaseInfo;

public class InstanceUpdateInfo extends BaseInfo{

    private static final long serialVersionUID = -6666115297878537537L;
    private String trade_seq;
    private String tenant_id;
    private String instance_id;
    private String change_code;
    public String getTrade_seq() {
        return trade_seq;
    }
    public void setTrade_seq(String trade_seq) {
        this.trade_seq = trade_seq;
    }
    public String getTenant_id() {
        return tenant_id;
    }
    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }
    public String getInstance_id() {
        return instance_id;
    }
    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }
    public String getChange_code() {
        return change_code;
    }
    public void setChange_code(String change_code) {
        this.change_code = change_code;
    }
    
    
}
