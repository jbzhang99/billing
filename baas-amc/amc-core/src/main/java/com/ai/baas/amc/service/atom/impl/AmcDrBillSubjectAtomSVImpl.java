package com.ai.baas.amc.service.atom.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMap;
import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMapCriteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.dao.mapper.interfaces.AmcDrBillSubjectMapMapper;
import com.ai.baas.amc.service.atom.interfaces.IAmcDrBillSubjectAtomSV;
import com.ai.opt.sdk.util.CollectionUtil;
@Service
public class AmcDrBillSubjectAtomSVImpl implements IAmcDrBillSubjectAtomSV {
//	@Autowired
//	private AmcDrBillSubjectMapMapper iAmcDrBillSubjectMapMapper;

	@Override
	public List<AmcDrBillSubjectMap> queryAmcDrBillSubjectMap(AmcDrBillSubjectMap amcDrBillSubjectMap) {
		AmcDrBillSubjectMapCriteria example = new AmcDrBillSubjectMapCriteria();
		AmcDrBillSubjectMapCriteria.Criteria criteria = example.createCriteria();
		//
		criteria.andTenantIdEqualTo(amcDrBillSubjectMap.getTenantId());
		//
		criteria.andBillSubjectEqualTo(amcDrBillSubjectMap.getBillSubject());
		//
		List<AmcDrBillSubjectMap> amcDrBillSubjectMapList =  MapperFactory.getAmcDrBillSubjectMapMapper().selectByExample(example);
		//
		return amcDrBillSubjectMapList;
	}

	@Override
	public void delAmcDrBillSubject(AmcDrBillSubjectMap amcDrBillSubjectMap) {
		AmcDrBillSubjectMapCriteria example = new AmcDrBillSubjectMapCriteria();
		AmcDrBillSubjectMapCriteria.Criteria criteria = example.createCriteria();
		//
		criteria.andTenantIdEqualTo(amcDrBillSubjectMap.getTenantId());
		criteria.andDrSubjectEqualTo(amcDrBillSubjectMap.getDrSubject());
		criteria.andBillSubjectEqualTo(amcDrBillSubjectMap.getBillSubject());
		//
		MapperFactory.getAmcDrBillSubjectMapMapper().deleteByExample(example);
		
	}

	@Override
	public void addAmcDrBillSubject(AmcDrBillSubjectMap amcDrBillSubjectMap) {
		//
		MapperFactory.getAmcDrBillSubjectMapMapper().insert(amcDrBillSubjectMap);
	}
	
	
}
