package com.ai.opt.sys.api.stationmail.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.sys.api.stationmail.interfaces.IGnStationMailQuerySV;

import javax.validation.constraints.NotNull;

public class StationMailQueryRequest extends BaseInfo{

    /**
     * 邮件标识，必填
     */
    @NotNull(message = "mailId不能为空",groups = IGnStationMailQuerySV.QueryStationMailDetail.class)
    private Long mailId;

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }
}
