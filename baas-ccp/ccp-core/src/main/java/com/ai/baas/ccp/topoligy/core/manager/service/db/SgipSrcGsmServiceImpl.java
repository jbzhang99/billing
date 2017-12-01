package com.ai.baas.ccp.topoligy.core.manager.service.db;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.SgipSrcGsmService;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcSmsInterfaceDao;

@Component
public class SgipSrcGsmServiceImpl implements SgipSrcGsmService {
    @Autowired
    private OmcSmsInterfaceDao omcSmsInterfaceDao;
	@Override
	public int insertMsg(Connection connection,OmcUrgeInterface smsInf) throws OmcException {
		return omcSmsInterfaceDao.insert(connection, smsInf);
	}
}
