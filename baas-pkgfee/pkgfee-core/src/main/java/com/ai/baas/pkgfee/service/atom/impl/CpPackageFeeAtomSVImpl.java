package com.ai.baas.pkgfee.service.atom.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFee;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFeeCriteria;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFeeCriteria.Criteria;
import com.ai.baas.pkgfee.dao.mapper.factory.MapperFactory;
import com.ai.baas.pkgfee.service.atom.interfaces.ICpPackageFeeAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class CpPackageFeeAtomSVImpl implements ICpPackageFeeAtomSV {

	@Override
	public int addCpPackageFee(CpPackageFee record) throws SystemException {
		return MapperFactory.getCpPackageFeeMapper().insert(record);
	}

	@Override
	public CpPackageFee queryByPriceCode(String tenantId, String priceCode)
			throws SystemException {
		CpPackageFeeCriteria example = new CpPackageFeeCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andPriceCodeEqualTo(priceCode);
		criteria.andActiveTimeLessThanOrEqualTo(new Timestamp(System.currentTimeMillis()))   //产品的有效性检查
        .andInactiveTimeGreaterThanOrEqualTo(new Timestamp(System.currentTimeMillis()));    //产品的有效性检查
		List<CpPackageFee> packageFees = MapperFactory.getCpPackageFeeMapper().selectByExampleWithBLOBs(example);
		return packageFees.size() > 0 ? packageFees.get(0) : null;
	}

	@Override
	public int updatePackageFee(CpPackageFee record) throws SystemException {
//		CpPackageFeeCriteria example = new CpPackageFeeCriteria();
//		Criteria criteria = example.createCriteria();
//		criteria.andPackageIdEqualTo(record.getPackageId());
		return MapperFactory.getCpPackageFeeMapper().updateByPrimaryKeySelective(record);
	}

}
