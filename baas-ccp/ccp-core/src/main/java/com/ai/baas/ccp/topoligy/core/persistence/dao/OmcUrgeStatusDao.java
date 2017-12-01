package com.ai.baas.ccp.topoligy.core.persistence.dao;

import java.sql.Connection;
import java.util.Map;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatus;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;


public interface OmcUrgeStatusDao {
	  int insert(Connection connection, OmcUrgeStatus record) throws OmcException;
	  OmcUrgeStatus selectByparam(Connection connection, Map<String, String> param) throws OmcException;
	  int update(Connection connection, OmcUrgeStatus record) throws OmcException;
}
