package com.ai.opt.sys.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.api.tenant.param.TenantInfoVo;
import com.ai.opt.sys.dao.mapper.bo.GnTenant;
import com.ai.opt.sys.service.atom.interfaces.ITenantAtomSV;
import com.ai.opt.sys.service.business.interfaces.ITenantBusiSV;

@Service
@Transactional
public class TenantBusiSVImpl implements ITenantBusiSV {

	@Autowired
	ITenantAtomSV itenantAtomSV;

	@Override
	public GnTenant queryByTenantId(String tenantId) throws SystemException {
		return itenantAtomSV.queryByTenantId(tenantId);
	}

	@Override
	public boolean insertTenantAndSyncAccount(GnTenant gnTenant) throws SystemException {
		int insertCount = itenantAtomSV.insert(gnTenant);
		if(insertCount>0){
		    return true;
		}else{
		    return false;
		}
		
	}

    @Override
    public int updateByTenantId(GnTenant gnTenant) throws SystemException {
        return itenantAtomSV.updateTenantById(gnTenant);
    }

	@Override
	public List<GnTenant> queryTenantInfos(TenantInfoVo queryInfo) throws SystemException {
		// TODO Auto-generated method stub
		return itenantAtomSV.queryTenantInfos(queryInfo);
	}

}
