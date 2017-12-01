package com.ai.baas.ccp.topoligy.core.manager.service;

import java.sql.Connection;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatus;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;

/**
 * 信控状态
 */
public interface ScoutStatusService {
	int modifyScoutStatus(Connection connection, OmcScoutStatus scoutStatus)  throws OmcException;
	OmcScoutStatus selectStatus(String tenantId, String businessCode, String subsId) throws OmcException;
}
