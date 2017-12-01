package com.ai.baas.prd.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranchHis;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IPmDimensionBranchHisAtomSV;
@Component
public class PmDimensionBranchHisAtomImpl implements IPmDimensionBranchHisAtomSV {

	@Override
	public int addDimensionBranchHis(PmDimensionBranchHis his) {
		return MapperFactory.getPmDimensionBranchHisMapper().insert(his);
	}

}
