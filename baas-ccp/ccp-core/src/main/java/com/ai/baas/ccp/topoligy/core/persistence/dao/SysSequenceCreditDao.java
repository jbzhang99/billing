package com.ai.baas.ccp.topoligy.core.persistence.dao;

import java.sql.Connection;

import com.ai.baas.ccp.dao.mapper.bo.SysSequenceCredit;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;


public interface SysSequenceCreditDao {
	SysSequenceCredit selectByKey(Connection connection, String key) throws OmcException;
	int update(Connection connection, SysSequenceCredit record) throws OmcException;
}
