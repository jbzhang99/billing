package com.ai.baas.pkgfee.service.atom.interfaces;

import java.util.List;

import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountExt;
import com.ai.opt.base.exception.SystemException;

public interface IDiscountExtAtomSV {

	List<DstDiscountExt> queryByDiscountId(String discountId,String tenantId) throws SystemException;
	
}
