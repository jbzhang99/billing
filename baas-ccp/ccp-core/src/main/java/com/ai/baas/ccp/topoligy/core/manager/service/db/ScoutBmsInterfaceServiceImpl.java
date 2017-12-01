package com.ai.baas.ccp.topoligy.core.manager.service.db;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterface;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutBmsInterfaceService;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcBmsInterfaceDao;

/**
 * 
* @ClassName: ScoutBmsInterfaceServiceImpl 
* @Description: 写入停机接口表
* @author lvsj
* @date 2015年10月26日 下午12:27:11 
*
 */
@Component
public class ScoutBmsInterfaceServiceImpl implements ScoutBmsInterfaceService {
    
    @Autowired
    private OmcBmsInterfaceDao omcBmsInterfaceDao;
	@Override
	public int addInterFace(Connection connection,OmcBmsInterface omcBmsInterface) throws OmcException {
		return omcBmsInterfaceDao.insert(connection, omcBmsInterface);
	}
}
