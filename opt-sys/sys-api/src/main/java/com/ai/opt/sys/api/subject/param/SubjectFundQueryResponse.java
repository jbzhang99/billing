package com.ai.opt.sys.api.subject.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 资金科目查询返回报文.<br>
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class SubjectFundQueryResponse extends BaseResponse {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 资金科目结果
     */
    private SubjectFund subjectFund;

    public SubjectFund getSubjectFund() {
        return subjectFund;
    }

    public void setSubjectFund(SubjectFund subjectFund) {
        this.subjectFund = subjectFund;
    }
    
}
