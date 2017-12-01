package com.ai.baas.prd.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfoHis;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IPmDimensionInfoHisAtomSV;
@Component
public class PmDimensionInfoHisAtomImpl implements IPmDimensionInfoHisAtomSV {

	@Override
	public int addDimensionInfoHis(PmDimensionInfoHis his) {
		return MapperFactory.getPmDimensionInfoHisMapper().insert(his);
	}

}
