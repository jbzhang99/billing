package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMap;
import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMapCriteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcDrBillSubjectMapAtomSV;
import com.ai.paas.ipaas.util.StringUtil;

@Component
public class AmcDrBillSubjectMapAtomSVImpl implements IAmcDrBillSubjectMapAtomSV {

	@Override
	public AmcDrBillSubjectMap getAmcDrBillSubjectInfo(String tenantId,String drSubjectId) {
		
		AmcDrBillSubjectMapCriteria amcDrBillSubjectMapCriteria = new AmcDrBillSubjectMapCriteria();
		amcDrBillSubjectMapCriteria.setDistinct(true);
		AmcDrBillSubjectMapCriteria.Criteria criteria = amcDrBillSubjectMapCriteria.createCriteria();
		if(!StringUtil.isBlank(tenantId)){
			criteria.andTenantIdEqualTo(tenantId);
		}
		if(!StringUtil.isBlank(drSubjectId)){
			criteria.andDrSubjectEqualTo(drSubjectId);
		}
		
		return (AmcDrBillSubjectMap)MapperFactory.getAmcDrBillSubjectMapMapper().selectByExample(amcDrBillSubjectMapCriteria);
		
	}

	
}
