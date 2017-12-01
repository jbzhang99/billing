package com.ai.baas.pkgfee.service.atom.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.DstCouponInfo;
import com.ai.opt.base.exception.SystemException;

public interface IDstCouponInfoAtomSV {

	DstCouponInfo queryByCouponId(String couponId,String tenantId) throws SystemException;
	
}
