package com.ai.baas.ccp.topoligy.core.persistence.dao.impl;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutLog;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcScoutLogMapper;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcScoutLogDao;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;

/**
 * 信控日志操作
 */
@Component
public class OmcScoutLogDaoImpl implements OmcScoutLogDao {

    @Autowired
    private OmcScoutLogMapper omcScoutLogMapper;
    
	@Override
	public int insert(Connection connection, OmcScoutLog record) throws OmcException {
	    String currmonth = DateUtils.getCurrMonth();
	    record.setYyyymm(currmonth);
	    return omcScoutLogMapper.insertSelective(record);
	}
}
