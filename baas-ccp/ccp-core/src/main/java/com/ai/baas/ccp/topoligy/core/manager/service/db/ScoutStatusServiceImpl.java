package com.ai.baas.ccp.topoligy.core.manager.service.db;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatus;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutStatusService;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcScoutStatusDao;
@Component
public class ScoutStatusServiceImpl implements ScoutStatusService {

    @Autowired
    private transient OmcScoutStatusDao omcScoutStatusDao;
    
	public int modifyScoutStatus(Connection connection, OmcScoutStatus omcScoutStatus) throws OmcException {
		int nInsertCnt = 0;
		int nRet = 1;
		int nUpdateCnt = omcScoutStatusDao.update(connection, omcScoutStatus);
		
		if (nUpdateCnt == 0){
			nInsertCnt = omcScoutStatusDao.insert(connection, omcScoutStatus);
		}
		
		if ((nUpdateCnt == 0)&&(nInsertCnt == 0)){
			throw new OmcException("UPDATE","omcScoutStatus更新失败【" + omcScoutStatus.toString() + "】");
		}
        return nRet;
	}

	@Override
	public OmcScoutStatus selectStatus(String tenantId,String businessCode, String subsId) throws OmcException {
		Connection connection = null;//JdbcProxy.getInstance().getConnection();
		Map<String, String> params = new HashMap<String, String>();
		params.put(OmcCalKey.OMC_TENANT_ID, tenantId);
		params.put(OmcCalKey.OMC_SUBS_ID, subsId);
		params.put(OmcCalKey.OMC_BUSINESS_CODE, businessCode);
		return (omcScoutStatusDao.selectByparam(connection, params));
	}
	
	
}
