package com.ai.opt.sys.api.stationmail.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sys.api.stationmail.interfaces.IGnStationMailQuerySV;
import com.ai.opt.sys.api.stationmail.param.StationMailQueryRequest;
import com.ai.opt.sys.api.stationmail.param.StationMailVo;
import com.ai.opt.sys.api.stationmail.param.StationMailsPageQueryVo;
import com.ai.opt.sys.service.business.interfaces.IGnStationMailBusiSV;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service(validation = "true")
@Component
public class GnStationMailQuerySVImpl implements IGnStationMailQuerySV{

    @Autowired
    private IGnStationMailBusiSV gnStationMailBusiSV;

    @Override
    public PageInfo<StationMailVo> getStationMails(StationMailsPageQueryVo mailsPageQueryVo) throws BusinessException, SystemException {
        return gnStationMailBusiSV.getStationMails(mailsPageQueryVo);
    }

    @Override
    public StationMailVo queryStationMailDetail(StationMailQueryRequest mailQueryRequest) throws BusinessException, SystemException {
        return gnStationMailBusiSV.getStationMailById(mailQueryRequest.getMailId());
    }
}
