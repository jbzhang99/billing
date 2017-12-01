package com.ai.baas.pkgfee.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountInfo;
import com.ai.baas.pkgfee.dao.mapper.factory.MapperFactory;
import com.ai.baas.pkgfee.service.atom.interfaces.IDiscountInfoAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class DiscountInfoAtomSVImpl implements IDiscountInfoAtomSV {

	@Override
	public DstDiscountInfo queryByDiscountId(String discountId)
			throws SystemException {
		return MapperFactory.getDiscountInfoMapper().selectByPrimaryKey(discountId);
	}

}
