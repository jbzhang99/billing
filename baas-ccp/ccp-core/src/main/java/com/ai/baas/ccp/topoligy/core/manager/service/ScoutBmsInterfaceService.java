package com.ai.baas.ccp.topoligy.core.manager.service;

import java.sql.Connection;

import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterface;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;


public interface ScoutBmsInterfaceService {
	
	int addInterFace(Connection connection, OmcBmsInterface omcBmsInterface) throws OmcException;

}
