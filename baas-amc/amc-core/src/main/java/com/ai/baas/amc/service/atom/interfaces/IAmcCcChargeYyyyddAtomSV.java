package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.amc.dao.mapper.bo.AmcCcChargeYyyydd;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单月表查询
 * Date: 2016年11月3日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author majun
 */
public interface IAmcCcChargeYyyyddAtomSV {

	public int addAmcCcCharge(AmcCcChargeYyyydd amcCcCharge) throws SystemException;
	
	public int updateAmcCcCharge(AmcCcChargeYyyydd amcCcCharge) throws SystemException;
	
	public List<AmcCcChargeYyyydd> queryCcChargeByCcidAndSubjectId(String tenantId,String costCenterId,Long subjectId,String billMonth) throws SystemException;
	
	
}
