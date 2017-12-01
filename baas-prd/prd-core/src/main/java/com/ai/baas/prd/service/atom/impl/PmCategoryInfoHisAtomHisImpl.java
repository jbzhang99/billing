package com.ai.baas.prd.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfoHis;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IPmCategoryInfoHisAtomSV;
@Component
public class PmCategoryInfoHisAtomHisImpl implements IPmCategoryInfoHisAtomSV {

	@Override
	public int addPmCategoryInfoHis(PmCategoryInfoHis his) {
		return MapperFactory.getPmCategoryInfoHisMapper().insert(his);
	}

}
