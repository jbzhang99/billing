package com.ai.opt.sys.api.citicrestcommon.param;

import java.io.Serializable;

/**
 * 中信用户服务信息实体
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class Service implements Serializable {
    /**
     * 服务的唯一标识。
     */
    private String serviceId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
