package com.ai.opt.sys.api.subject.param;

import java.util.Collections;
import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 根据科目类型查询科目返回报文.<br>
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class SubjectQueryByTypeResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 科目结果列表
     */
    private List<Subject> subjectList;

    public List<Subject> getSubjectList() {
        return Collections.unmodifiableList(subjectList);
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}
