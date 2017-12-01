package com.ai.opt.sys.service.atom.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.api.tenant.param.TenantInfoVo;
import com.ai.opt.sys.dao.mapper.bo.GnTenant;
import com.ai.opt.sys.dao.mapper.bo.GnTenantCriteria;
import com.ai.opt.sys.dao.mapper.factory.MapperFactory;
import com.ai.opt.sys.dao.mapper.interfaces.GnTenantMapper;
import com.ai.opt.sys.service.atom.interfaces.ITenantAtomSV;

@Component
public class TenantAtomSVImpl implements ITenantAtomSV {

	@Override
	public int insert(GnTenant gnTenant) throws SystemException {
		GnTenantMapper tenantMapper = MapperFactory.getGnTenantMapper();
		Timestamp createTime = gnTenant.getCreateTime();
		if(createTime == null){
			gnTenant.setCreateTime(DateUtil.getSysDate());
		}
		return tenantMapper.insertSelective(gnTenant);
	}

	@Override
	public GnTenant queryByTenantId(String tenantId) throws SystemException {
		GnTenantMapper tenantMapper = MapperFactory.getGnTenantMapper();
		return tenantMapper.selectByPrimaryKey(tenantId);
	}

	@Override
	public List<GnTenant> queryTenantList(GnTenantCriteria criteria) throws SystemException {
		// GnTenantCriteria sql = new GnTenantCriteria();
		// GnTenantCriteria.Criteria criteria = sql.createCriteria();
		// if (!StringUtil.isBlank(gnTenant.getTenantId())) {
		// criteria.andTenantIdEqualTo(gnTenant.getTenantId());
		// }
		// if (!StringUtil.isBlank(gnTenant.getTenantName())) {
		// criteria.andTenantNameLike("%"+gnTenant.getTenantName()+"%");
		// }
		// if (!StringUtil.isBlank(gnTenant.getState())) {
		// criteria.andStateEqualTo(gnTenant.getState());
		// }
		// sql.setLimitStart(start);
		// sql.setLimitEnd(end);
        GnTenantMapper tenantMapper = MapperFactory.getGnTenantMapper();
        return tenantMapper.selectByExample(criteria);
	}

	@Override
	public int updateTenantById(GnTenant gnTenant) throws SystemException {
		GnTenantMapper tenantMapper = MapperFactory.getGnTenantMapper();
		Timestamp updateTime = gnTenant.getUpdateTime();
		if(updateTime == null){
			gnTenant.setUpdateTime(DateUtil.getSysDate());
		}
        return tenantMapper.updateByPrimaryKeySelective(gnTenant);
	}

	@Override
	public int queryTenantCount(GnTenantCriteria criteria) throws SystemException {
		GnTenantMapper tenantMapper = MapperFactory.getGnTenantMapper();
		// GnTenantCriteria example = new GnTenantCriteria();
		// GnTenantCriteria.Criteria criteria = example.createCriteria();
		// if (!StringUtil.isBlank(gnTenant.getTenantId())) {
		// criteria.andTenantIdEqualTo(gnTenant.getTenantId());
		// }
		// if (!StringUtil.isBlank(gnTenant.getTenantName())) {
		// criteria.andTenantNameLike("%"+gnTenant.getTenantName()+"%");
		// }
		// if (!StringUtil.isBlank(gnTenant.getState())) {
		// criteria.andStateEqualTo(gnTenant.getState());
		// }
		return tenantMapper.countByExample(criteria);
	}

	@Override
	public List<GnTenant> queryTenantInfos(TenantInfoVo queryInfo) throws SystemException {
		// TODO Auto-generated method stub
		GnTenantMapper tenantMapper = MapperFactory.getGnTenantMapper();
		GnTenantCriteria example = new GnTenantCriteria();
		GnTenantCriteria.Criteria criteria = example.createCriteria();
		if (!StringUtil.isBlank(queryInfo.getTenantId())) {
			criteria.andTenantIdLike("%"+queryInfo.getTenantId()+"%");
		}
		if (!StringUtil.isBlank(queryInfo.getTenantName())) {
			criteria.andTenantNameLike("%"+queryInfo.getTenantName()+"%");
		}
		if (!StringUtil.isBlank(queryInfo.getState())) {
			criteria.andStateEqualTo(queryInfo.getState());
		}
		example.setOrderByClause("state desc");
		return tenantMapper.selectByExample(example);
	}

}
