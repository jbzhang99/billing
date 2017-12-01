package com.ai.opt.sys.api.citicrestcommon.param;

import java.io.Serializable;

/**
 * 中信VPC服务实例实体
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class VPC implements Serializable{
    /**
     * VPC服务实例的唯一标识。
     */
    private String vpcId;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }
}
