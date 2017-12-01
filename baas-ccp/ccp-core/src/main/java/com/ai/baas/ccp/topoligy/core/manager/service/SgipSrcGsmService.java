package com.ai.baas.ccp.topoligy.core.manager.service;

import java.sql.Connection;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;


public interface SgipSrcGsmService {
	
	int insertMsg(Connection connection, OmcUrgeInterface smsInf) throws OmcException;
}
