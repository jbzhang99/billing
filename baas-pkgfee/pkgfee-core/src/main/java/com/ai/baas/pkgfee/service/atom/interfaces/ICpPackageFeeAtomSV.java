package com.ai.baas.pkgfee.service.atom.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFee;
import com.ai.opt.base.exception.SystemException;

public interface ICpPackageFeeAtomSV {

	public int addCpPackageFee(CpPackageFee record) throws SystemException;
	
	public CpPackageFee queryByPriceCode(String tenantId,String priceCode) throws SystemException;
	
	public int updatePackageFee(CpPackageFee record) throws SystemException;
	
}
