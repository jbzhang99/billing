package com.ai.baas.ccp.topoligy.core.manager.service;

import java.sql.Connection;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatus;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;


public interface UrgeStatusService {
	int modifyUrgeStatus(Connection connection, OmcUrgeStatus omcUrgeStatus)  throws OmcException;
	OmcUrgeStatus selectUrgeStatus(String tenantId, String businessCode, String urgeType, String ownerType, String ownerId)  throws OmcException;
}
