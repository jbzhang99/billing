package com.ai.baas.amc.api.custbalancequery.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

/**
 * 可用余额查询请求参数.<br>
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class UsableBalanceQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 客户名称
     */
    private String custName;
    
    /**
     * 客户等级
     */
    private String custGrade;
    
    /**
     * 分页信息
     */
    private PageInfo<UsableBalanceVo> pageInfo;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustGrade() {
        return custGrade;
    }

    public void setCustGrade(String custGrade) {
        this.custGrade = custGrade;
    }

    public PageInfo<UsableBalanceVo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<UsableBalanceVo> pageInfo) {
        this.pageInfo = pageInfo;
    }    
}
