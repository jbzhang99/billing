package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMap;
import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMapCriteria;

public interface IAmcDrBillSubjectMapAtomSV {

	public AmcDrBillSubjectMap getAmcDrBillSubjectInfo(String tenantId,String drSubjectId);
	
}
