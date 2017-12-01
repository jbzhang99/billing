package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfo;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfoCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IPmDimensionInfoAtomSV;
@Component
public class PmDimensionInfoAtomSVImpl implements IPmDimensionInfoAtomSV {


	@Override
	public int addDimension(PmDimensionInfo info) {
	
		return MapperFactory.getPmDimensionInfoMapper().insert(info);
	}

	@Override
	public List<PmDimensionInfo> getPmDimensionInfos(String tenantId, String mainProCode) {

		PmDimensionInfoCriteria criteria=new PmDimensionInfoCriteria();
		
		criteria.or().andTenantIdEqualTo(tenantId).andMainProductCodeEqualTo(mainProCode);
		
		return MapperFactory.getPmDimensionInfoMapper().selectByExample(criteria);
	}

	@Override
	public int delPmDimensionInfo(String tenantId, String mainProCode) {
		PmDimensionInfoCriteria criteria=new PmDimensionInfoCriteria();
		
		criteria.or().andTenantIdEqualTo(tenantId).andMainProductCodeEqualTo(mainProCode);
		
		return MapperFactory.getPmDimensionInfoMapper().deleteByExample(criteria);
	}

	@Override
	public int updatePmDimensionInfoById(String tenantId, Long id,PmDimensionInfo record) {
       PmDimensionInfoCriteria criteria=new PmDimensionInfoCriteria();
		
		criteria.or().andTenantIdEqualTo(tenantId).andIdEqualTo(id);
		return MapperFactory.getPmDimensionInfoMapper().updateByExampleSelective(record, criteria);
	}

}
