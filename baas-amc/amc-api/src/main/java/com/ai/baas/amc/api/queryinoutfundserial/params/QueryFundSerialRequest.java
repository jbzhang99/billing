package com.ai.baas.amc.api.queryinoutfundserial.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

/**
 * 收支明细查询入参 Date: 2016年7月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangjl9
 */
public class QueryFundSerialRequest extends BaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 外部客户id（中信租户id）
     */
    private String extCustId;

    /**
     * 账户ID
     */
    private List<String> acctIds;

    /**
     * 收费记录类型 （操作类型，不传为查询全部）
     */
    private String optType;

    /**
     * 开始查询时间
     */
    private String beginTime;

    /**
     * 结束查询时间
     */
    private String endTime;

    /**
     * 分页时必填
     */
    PageInfo<FundSerialInfo> pageInfo;

    public String getExtCustId() {
        return extCustId;
    }

    public void setExtCustId(String extCustId) {
        this.extCustId = extCustId;
    }

    public List<String> getAcctIds() {
        return acctIds;
    }

    public void setAcctIds(List<String> acctIds) {
        this.acctIds = acctIds;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public PageInfo<FundSerialInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<FundSerialInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
