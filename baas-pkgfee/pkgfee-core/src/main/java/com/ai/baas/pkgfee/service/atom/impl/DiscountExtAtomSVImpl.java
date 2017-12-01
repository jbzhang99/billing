package com.ai.baas.pkgfee.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountExt;
import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountExtCriteria;
import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountExtCriteria.Criteria;
import com.ai.baas.pkgfee.dao.mapper.factory.MapperFactory;
import com.ai.baas.pkgfee.service.atom.interfaces.IDiscountExtAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class DiscountExtAtomSVImpl implements IDiscountExtAtomSV {

	@Override
	public List<DstDiscountExt> queryByDiscountId(String discountId,String tenantId) throws SystemException {
		DstDiscountExtCriteria example = new DstDiscountExtCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andDiscountIdEqualTo(discountId);
		return MapperFactory.getDiscountExtMapper().selectByExample(example);
	}

}
