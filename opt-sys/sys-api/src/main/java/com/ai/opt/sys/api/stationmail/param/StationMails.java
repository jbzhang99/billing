package com.ai.opt.sys.api.stationmail.param;

import com.ai.opt.base.vo.BaseInfo;

import java.util.List;

public class StationMails extends BaseInfo{
    /**
     * 要发送站内信列表
     */
    private List<StationMailVo> mailVoList;

    public List<StationMailVo> getMailVoList() {
        return mailVoList;
    }

    public void setMailVoList(List<StationMailVo> mailVoList) {
        this.mailVoList = mailVoList;
    }
}
