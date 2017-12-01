package com.ai.baas.ccp.topoligy.core.persistence.dao;

import java.sql.Connection;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;


public interface OmcSmsInterfaceDao {
	int insert(Connection connection, OmcUrgeInterface record) throws OmcException;
}
