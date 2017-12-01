package com.ai.opt.sys.api.subject.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 科目查询请求参数<br>
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class SubjectTypeParam extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 科目类型<br>
     * 1：资源科目<br>
     * 2：消费科目（原计费的科目）<br>
     * 21:消费科目中非通信类科目<br>
     * 4：虚科目（暂无）<br>
     * 9：资金科目（原余额中心的的科目）<br>
     * 10:订单科目 （原营业的科目）<br>
     * 11：商品中心科目
     */
    private String subjectType;
    
    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
}
