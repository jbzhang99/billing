package com.ai.opt.sys.service.atom.interfaces;

import java.util.List;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.api.tenant.param.TenantInfoVo;
import com.ai.opt.sys.dao.mapper.bo.GnTenant;
import com.ai.opt.sys.dao.mapper.bo.GnTenantCriteria;

public interface ITenantAtomSV {
	
	int insert(GnTenant gnTenant) throws SystemException;
	
	GnTenant queryByTenantId(String tenantId) throws SystemException;
	
	List<GnTenant> queryTenantList(GnTenantCriteria criteria) throws SystemException;

	int queryTenantCount(GnTenantCriteria criteria) throws SystemException;
	
    int updateTenantById(GnTenant gnTenant) throws SystemException;
    
    List<GnTenant> queryTenantInfos(TenantInfoVo queryInfo) throws SystemException;
}
