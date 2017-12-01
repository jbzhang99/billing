package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailYyyydd;
import com.ai.opt.base.exception.SystemException;

public interface IAmcCcDetailYyyyddAtomSV {

	public int addAmcCcDetail(AmcCcDetailYyyydd amcCcDetail) throws SystemException;
	
	public int updateAmcCcDetail(AmcCcDetailYyyydd amcCcDetail) throws SystemException;
	
	public List<AmcCcDetailYyyydd> queryCcDetailByCostCenterId(String tenantId,String costCenterId,String apportionAcctId,String billMonth) throws SystemException;
	
}
