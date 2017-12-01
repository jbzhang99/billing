package com.ai.baas.pkgfee.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.dao.mapper.bo.DstCouponInfo;
import com.ai.baas.pkgfee.dao.mapper.bo.DstCouponInfoCriteria;
import com.ai.baas.pkgfee.dao.mapper.bo.DstCouponInfoCriteria.Criteria;
import com.ai.baas.pkgfee.dao.mapper.factory.MapperFactory;
import com.ai.baas.pkgfee.service.atom.interfaces.IDstCouponInfoAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class DstCouponInfoAtomSVImpl implements IDstCouponInfoAtomSV {

	@Override
	public DstCouponInfo queryByCouponId(String couponId, String tenantId)
			throws SystemException {
		DstCouponInfoCriteria example = new DstCouponInfoCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andCouponIdEqualTo(couponId);
		List<DstCouponInfo> couponInfoList = MapperFactory.getDstCouponInfoMapper().selectByExample(example);
		return couponInfoList.size() > 0 ? couponInfoList.get(0) : null;
	}

	
}
