package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMap;

public interface IAmcDrBillSubjectAtomSV {
	public List<AmcDrBillSubjectMap> queryAmcDrBillSubjectMap(AmcDrBillSubjectMap amcDrBillSubjectMap);
	public void delAmcDrBillSubject(AmcDrBillSubjectMap amcDrBillSubjectMap);
	public void addAmcDrBillSubject(AmcDrBillSubjectMap amcDrBillSubjectMap);
	
}
