package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.CpPricemakingFactor;
import com.ai.opt.base.exception.SystemException;

public interface ICpPricemakingFactorAtomSV {

	public int addCpPricemakingFactor(CpPricemakingFactor record) throws SystemException;
	
	public List<CpPricemakingFactor> queryPricemakingFactorByPriceProductId(String priceProductId)throws SystemException;
	
	public int delCpPricemakingFactorByProductId(String tenantId,String priceProductId)throws SystemException;
	
}
