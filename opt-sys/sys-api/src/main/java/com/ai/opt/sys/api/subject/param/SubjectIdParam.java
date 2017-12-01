package com.ai.opt.sys.api.subject.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 科目查询请求参数<br>
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class SubjectIdParam extends BaseInfo {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 科目ID
     */
    private long subjectId;

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

}
