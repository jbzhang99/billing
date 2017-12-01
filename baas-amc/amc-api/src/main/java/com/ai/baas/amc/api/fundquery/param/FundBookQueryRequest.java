package com.ai.baas.amc.api.fundquery.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 余额查询请求.<br>
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class FundBookQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID，必填
     */
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
