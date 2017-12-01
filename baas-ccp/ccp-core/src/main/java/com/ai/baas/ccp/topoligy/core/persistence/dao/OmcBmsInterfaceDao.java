package com.ai.baas.ccp.topoligy.core.persistence.dao;

import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterface;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;

import java.sql.Connection;


public interface OmcBmsInterfaceDao {
	int insert(Connection connection, OmcBmsInterface record) throws OmcException;
}
