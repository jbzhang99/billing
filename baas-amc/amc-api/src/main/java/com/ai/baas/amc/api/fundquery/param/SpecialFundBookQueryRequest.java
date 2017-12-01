package com.ai.baas.amc.api.fundquery.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 按照科目和账户查询资金账本请求报文<br>
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class SpecialFundBookQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID，必填
     */
    private String accountId;

    /**
     * 科目ID，必填
     */
    private long subjectId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

}
