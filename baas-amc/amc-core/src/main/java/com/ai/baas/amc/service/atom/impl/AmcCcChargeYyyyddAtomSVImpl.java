package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcCcChargeYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcCcChargeYyyyddCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcCcChargeYyyyddCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcCcChargeYyyyddAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class AmcCcChargeYyyyddAtomSVImpl implements IAmcCcChargeYyyyddAtomSV {

	private static Logger LOG = LoggerFactory.getLogger(AmcCcChargeYyyyddAtomSVImpl.class);
	
	@Override
	public int addAmcCcCharge(AmcCcChargeYyyydd record) throws SystemException {
		return MapperFactory.getAmcCcChargeYyyyddMapper().insert(record);
	}

	@Override
	public int updateAmcCcCharge(AmcCcChargeYyyydd record) throws SystemException {
		AmcCcChargeYyyyddCriteria example = new AmcCcChargeYyyyddCriteria();
		example.setBillMonth(record.getBillMonth());
		Criteria criteria = example.createCriteria();
		criteria.andCcChargeSeqEqualTo(record.getCcChargeSeq());
		return MapperFactory.getAmcCcChargeYyyyddMapper().updateByExample(record, example);
	}

	@Override
	public List<AmcCcChargeYyyydd> queryCcChargeByCcidAndSubjectId(String tenantId,
			String costCenterId, Long subjectId, String billMonth) throws SystemException {
		AmcCcChargeYyyyddCriteria sql = new AmcCcChargeYyyyddCriteria();
		sql.setBillMonth(billMonth);
		Criteria criteria = sql.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andCostCenterIdEqualTo(costCenterId);
		criteria.andSubjectIdEqualTo(subjectId);
		return MapperFactory.getAmcCcChargeYyyyddMapper().selectByExample(sql);
	}

}
