package com.ai.baas.ccp.topoligy.core.manager.service.db;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutLog;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutLogService;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcScoutLogDao;
import com.ai.baas.ccp.topoligy.core.pojo.ScoutLog;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;
@Component
public class ScoutLogServiceImpl implements ScoutLogService {
    @Autowired
    private OmcScoutLogDao omcScoutLogDao;
	@Override
	public int insertScoutLog(Connection connection,ScoutLog scoutLog) throws OmcException {
		OmcScoutLog omcScoutlog = new OmcScoutLog();
		omcScoutlog.setLogid(scoutLog.getLogid());
		omcScoutlog.setBalanceinfo(scoutLog.getRealTimeBalance().toString());
		omcScoutlog.setBusinessCode(scoutLog.getOwner().getBusinesscode());
		omcScoutlog.setInsettime(DateUtils.currTimeStamp());
		omcScoutlog.setOwnerId(scoutLog.getOwner().getOwerid());
		omcScoutlog.setOwnertype(scoutLog.getOwner().getOwertype());
		omcScoutlog.setSystemId("system_id");
		omcScoutlog.setTenantId(scoutLog.getOwner().getTenantid());
		omcScoutlog.setScoutRule(scoutLog.getSectionRules().toString());
		omcScoutlog.setScoutType(scoutLog.getSourceType());
		omcScoutlog.setSourcetype(scoutLog.getSourceType());
		omcScoutlog.setStatus(scoutLog.getScostatus());
		
		return omcScoutLogDao.insert(connection, omcScoutlog);
	}
}
