package com.ai.baas.ccp.topoligy.core.persistence.dao;

import java.sql.Connection;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutLog;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;


public interface OmcScoutLogDao {
	int insert(Connection connection, OmcScoutLog record) throws OmcException;
}
