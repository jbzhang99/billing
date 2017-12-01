package com.ai.opt.sys.api.subject.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 科目查询返回报文
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class SubjectQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 科目结果
     */
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
