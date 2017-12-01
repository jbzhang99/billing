package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMap;

public interface IAmcDrBillSubjectMapBusiSV {
	public AmcDrBillSubjectMap getAmcDrBillSubjectInfo(String tenantId,String drSubjectId );
}
