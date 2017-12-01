package com.ai.baas.pkgfee.service.atom.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountInfo;
import com.ai.opt.base.exception.SystemException;

public interface IDiscountInfoAtomSV {

	public DstDiscountInfo queryByDiscountId(String discountId) throws SystemException;
	
}
