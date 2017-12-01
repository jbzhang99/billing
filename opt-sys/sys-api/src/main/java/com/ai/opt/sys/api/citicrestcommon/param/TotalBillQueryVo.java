package com.ai.opt.sys.api.citicrestcommon.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 总账查询参数
 * Date: 2016年7月11日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public class TotalBillQueryVo extends BaseInfo{
    
    private static final long serialVersionUID = 8683735317621843633L;

    /**
     * 开始日期，目前只支撑month
     */
    private String startDate;
    /**
     * 结束日期，目前只支撑month
     */
    private String endDate;
    
    /**
     * 查询粒度，目前只支撑month
     */
    private String grainSize;

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

    public String getGrainSize() {
        return grainSize;
    }

    public void setGrainSize(String grainSize) {
        this.grainSize = grainSize;
    }
    
}
