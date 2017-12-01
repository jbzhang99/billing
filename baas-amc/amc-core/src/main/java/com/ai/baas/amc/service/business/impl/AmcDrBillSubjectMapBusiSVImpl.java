package com.ai.baas.amc.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMap;
import com.ai.baas.amc.service.atom.interfaces.IAmcDrBillSubjectMapAtomSV;
import com.ai.baas.amc.service.business.interfaces.IAmcDrBillSubjectMapBusiSV;

@Service
@Transactional
public class AmcDrBillSubjectMapBusiSVImpl implements IAmcDrBillSubjectMapBusiSV {

	@Autowired
	IAmcDrBillSubjectMapAtomSV amcDrBillSubjectMapAtomSV;

	@Override
	public AmcDrBillSubjectMap getAmcDrBillSubjectInfo(String tenantId,String drSubjectId) {
		
		return this.amcDrBillSubjectMapAtomSV.getAmcDrBillSubjectInfo(tenantId, drSubjectId);
	}

}
