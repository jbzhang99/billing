package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.PmSpecType;

public interface IPmSpecTypeAtomSV {

	public List<PmSpecType> selectByCategoryId(String CategoryId, String tenantId);
}
