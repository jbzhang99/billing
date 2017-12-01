package com.ai.opt.sys.api.subject.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 科目名称查询返回报文
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class SubjectNameQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 科目名称
     */
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
}
