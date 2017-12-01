package com.ai.opt.sys.api.stationmail.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.sys.api.stationmail.interfaces.IGnStationMailQuerySV;

import javax.validation.constraints.NotNull;

public class StationMailsPageQueryVo extends BaseInfo{

    /**
     * 请求查询的页码
     */
    @NotNull(message="pageNo不能为空",groups = IGnStationMailQuerySV.GetStationMails.class)
    private Integer pageNo;

    /**
     * 每页显示条数
     */
    @NotNull(message="pageSize不能为空",groups = IGnStationMailQuerySV.GetStationMails.class)
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
