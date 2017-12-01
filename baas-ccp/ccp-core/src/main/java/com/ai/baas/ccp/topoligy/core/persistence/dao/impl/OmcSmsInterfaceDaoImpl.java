package com.ai.baas.ccp.topoligy.core.persistence.dao.impl;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcUrgeInterfaceMapper;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcSmsInterfaceDao;

/**
 * 信控短信接口
 */
@Component
public class OmcSmsInterfaceDaoImpl implements OmcSmsInterfaceDao {

    @Autowired
    private OmcUrgeInterfaceMapper omcUrgeInterfaceMapper;
	@Override
	public int insert(Connection connection, OmcUrgeInterface record) throws OmcException {
	    return omcUrgeInterfaceMapper.insertSelective(record);
	}
}
