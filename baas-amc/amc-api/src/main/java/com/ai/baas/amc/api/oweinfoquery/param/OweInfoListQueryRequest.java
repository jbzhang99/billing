package com.ai.baas.amc.api.oweinfoquery.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

/**
 * 欠费列表查询请求
 *
 * Date: 2016年4月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class OweInfoListQueryRequest extends BaseInfo {

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
     * 欠费月份（起始），格式为YYYYMM
     */
    private String startDate;
    
    /**
     * 欠费月份（结束区间），格式为YYYYMM
     */
    private String endDate;
    
    /**
     * 最低欠费金额，单位为元
     */
    private Double bottomAmount; 
    
    /**
     * 最高欠费金额，单位为元
     */
    private Double topAmount;
    
    /**
     * 分页信息
     */
    private PageInfo<OweInfo> pageInfo;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getBottomAmount() {
        return bottomAmount;
    }

    public void setBottomAmount(Double bottomAmount) {
        this.bottomAmount = bottomAmount;
    }

    public Double getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(Double topAmount) {
        this.topAmount = topAmount;
    }

    public PageInfo<OweInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<OweInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }

}
