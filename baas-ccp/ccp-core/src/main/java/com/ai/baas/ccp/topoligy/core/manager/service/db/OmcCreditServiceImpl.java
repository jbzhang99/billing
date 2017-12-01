package com.ai.baas.ccp.topoligy.core.manager.service.db;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.OmcCreditService;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcCreditDao;
import com.ai.baas.ccp.topoligy.core.pojo.OmcCredit;
import com.ai.baas.ccp.topoligy.core.util.db.JdbcProxy;

@Component
public class OmcCreditServiceImpl implements OmcCreditService {
	private static final JdbcProxy dbproxy = JdbcProxy.getInstance();

	@Autowired
	private transient OmcCreditDao omcCreditDao;
	
	/**
	 * 获取信用度
	 * @param tenantid
	 * @param ownetype
	 * @param ownerid
	 * @param resourcecode
	 * @return
     * @throws OmcException
     */
	@Override
	public List<OmcCredit> getAllCredit(String tenantid, String ownetype, String ownerid,String resourcecode) throws OmcException {
		return omcCreditDao.selectCredit(null,tenantid,ownetype,ownerid,resourcecode);
	}
}
