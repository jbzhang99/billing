package com.ai.baas.ccp.topoligy.core.persistence.dao.impl;

import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterface;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcBmsInterfaceMapper;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcBmsInterfaceDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * 停开机接口表
 */
@Component
public class OmcBmsInterfaceDaoImpl implements OmcBmsInterfaceDao {
	@SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OmcBmsInterfaceDaoImpl.class);

	@Autowired
	private OmcBmsInterfaceMapper bmsInterfaceMapper;
	
	@Override
	public int insert(Connection connection, OmcBmsInterface record) throws OmcException {
	    return bmsInterfaceMapper.insertSelective(record);
	}

}
