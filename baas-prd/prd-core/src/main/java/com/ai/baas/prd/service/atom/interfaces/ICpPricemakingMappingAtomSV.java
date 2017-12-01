package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.CpPricemakingMapping;
import com.ai.opt.base.exception.SystemException;

public interface ICpPricemakingMappingAtomSV {

	List<CpPricemakingMapping> queryAllPricemakingMappingRule()throws SystemException;
	
}
