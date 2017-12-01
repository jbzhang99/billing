package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailYyyyddCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailYyyyddCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcCcDetailYyyyddAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class AmcCcDetailYyyyddAtomSVImpl implements IAmcCcDetailYyyyddAtomSV {

	@Override
	public int addAmcCcDetail(AmcCcDetailYyyydd record) throws SystemException {
		return MapperFactory.getAmcCcDetailYyyyddMapper().insert(record);
	}

	@Override
	public int updateAmcCcDetail(AmcCcDetailYyyydd record) throws SystemException {
		AmcCcDetailYyyyddCriteria example = new AmcCcDetailYyyyddCriteria();
		example.setBillMonth(record.getBillMonth());
		Criteria criteria = example.createCriteria();
		criteria.andCcDetailSeqEqualTo(record.getCcDetailSeq());
		return MapperFactory.getAmcCcDetailYyyyddMapper().updateByExample(record, example);
	}

	@Override
	public List<AmcCcDetailYyyydd> queryCcDetailByCostCenterId(String tenantId,
			String costCenterId, String apportionAcctId, String billMonth)
			throws SystemException {
		AmcCcDetailYyyyddCriteria sql = new AmcCcDetailYyyyddCriteria();
		sql.setBillMonth(billMonth);
		Criteria criteria = sql.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andCostCenterIdEqualTo(costCenterId);
		criteria.andApportionAcctIdEqualTo(apportionAcctId);
		return MapperFactory.getAmcCcDetailYyyyddMapper().selectByExample(sql);
	}

}
