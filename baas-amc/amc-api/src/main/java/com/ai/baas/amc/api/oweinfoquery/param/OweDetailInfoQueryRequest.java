package com.ai.baas.amc.api.oweinfoquery.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 欠费明细查询请求.<br>
 *
 * Date: 2016年4月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class OweDetailInfoQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID,必填项
     */
    private String custId;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }    
}
