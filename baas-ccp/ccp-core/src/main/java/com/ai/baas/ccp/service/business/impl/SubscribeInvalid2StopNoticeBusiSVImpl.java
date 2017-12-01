package com.ai.baas.ccp.service.business.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.ccp.api.subscribeinvalid2stopnotice.params.SubscribeInvalid2StopNoticeRequest;
import com.ai.baas.ccp.dao.mapper.bo.StopNoticeLog;
import com.ai.baas.ccp.dao.mapper.bo.StopNoticeLogCriteria;
import com.ai.baas.ccp.dao.mapper.factory.MapperFactory;
import com.ai.baas.ccp.service.business.interfaces.ISubscribeInvalid2StopNoticeBusiSV;
import com.ai.baas.ccp.util.BusinessUtil;
import com.ai.baas.ccp.util.CcpSeqUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

@Component
@Transactional
public class SubscribeInvalid2StopNoticeBusiSVImpl implements ISubscribeInvalid2StopNoticeBusiSV {

    @Override
    public void stopNotice(SubscribeInvalid2StopNoticeRequest request) {
        // check request
        this.checkRequest(request);
        // check if it was noticed
        if (this.checkIsDone(request)) {
            return;
        }
        // write notice log
        this.createStopNoticeLog(request);
        // create notice record
        
        // send notice record
        
    }

    private void createStopNoticeLog(SubscribeInvalid2StopNoticeRequest request) {
        StopNoticeLog stopNoticeLog = new StopNoticeLog();
        stopNoticeLog.setActiveTime(request.getActiveTime());
        stopNoticeLog.setCreateTime(DateUtil.getSysDate());
        stopNoticeLog.setExctId(request.getExctId());
        stopNoticeLog.setInactiveTime(request.getInactiveTime());
        stopNoticeLog.setLogId(CcpSeqUtil.createStopNoticeLogId());
        stopNoticeLog.setProductId(request.getProductId());
        stopNoticeLog.setSubsId(request.getSubsId());
        stopNoticeLog.setSubsProductId(request.getSubsProductId());
        stopNoticeLog.setTenantId(request.getTenantId());
        MapperFactory.getStopNoticeLogMapper().insert(stopNoticeLog);
    }

    private boolean checkIsDone(SubscribeInvalid2StopNoticeRequest request) {
        StopNoticeLogCriteria criteria = new StopNoticeLogCriteria();
        criteria.createCriteria().andSubsIdEqualTo(request.getSubsId())
                .andSubsProductIdEqualTo(request.getSubsProductId())
                .andProductIdEqualTo(request.getProductId())
                .andExctIdEqualTo(request.getExctId())
                .andActiveTimeEqualTo(request.getActiveTime())
                .andInactiveTimeEqualTo(request.getInactiveTime());
        List<StopNoticeLog> stopNoticeLogs = MapperFactory.getStopNoticeLogMapper().selectByExample(criteria);
        if (CollectionUtil.isEmpty(stopNoticeLogs)) {
            return false;
        }
        return true;
    }

    private void checkRequest(SubscribeInvalid2StopNoticeRequest request) {
        BusinessUtil.checkBaseInfo(request);
        if (StringUtil.isBlank(request.getExctId())) {
            throw new BusinessException("extId is null");
        }
        if (StringUtil.isBlank(request.getProductId())) {
            throw new BusinessException("productId is null");
        }
        if (StringUtil.isBlank(request.getSubsId())) {
            throw new BusinessException("subsId is null");
        }
        if (StringUtil.isBlank(request.getSubsProductId())) {
            throw new BusinessException("subsProductId is null");
        }
        if (null == (request.getActiveTime())) {
            throw new BusinessException("activeTime is null");
        }
        if (null == (request.getInactiveTime())) {
            throw new BusinessException("inactiveTime is null");
        }
    }

}
