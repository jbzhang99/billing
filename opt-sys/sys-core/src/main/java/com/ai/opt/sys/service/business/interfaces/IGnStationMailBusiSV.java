package com.ai.opt.sys.service.business.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sys.api.stationmail.param.StationMailVo;
import com.ai.opt.sys.api.stationmail.param.StationMails;
import com.ai.opt.sys.api.stationmail.param.StationMailsPageQueryVo;

public interface IGnStationMailBusiSV {
    PageInfo<StationMailVo> getStationMails(StationMailsPageQueryVo mailsPageQueryVo);

    void saveStationMails(StationMails stationMails) throws BusinessException;

    StationMailVo getStationMailById(Long mailId);
}
