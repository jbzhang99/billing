package com.ai.baas.amc.service.business.interfaces;

import java.util.List;

import com.ai.baas.amc.api.amcdrbillsubject.param.AmcDrBillSubjectRelatedParamVO;

public interface IAmcDrBillSubjectBusiSV {
	public List<String> queryDrSubjectIdByTenantIdAndBillSubjectId(String tenantId,String billSubjectId);
	public void delAmcDrBillSubject(AmcDrBillSubjectRelatedParamVO vo);
	public void addAmcDrBillSubject(AmcDrBillSubjectRelatedParamVO vo);
	
}
