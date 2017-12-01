package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranch;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranchCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IPmDimensionBranchAtomSV;

@Component
public class PmDimensionBranchAtomSVImpl implements IPmDimensionBranchAtomSV {

	@Override
	public int addBranch(PmDimensionBranch branch) {

		return MapperFactory.getPmDimensionBranchMapper().insertSelective(branch);
	}

	@Override
	public int delDimensionBranch(String tenantId, String dimCode, String mainProCode) {
		PmDimensionBranchCriteria criteria = new PmDimensionBranchCriteria();

		criteria.or().andTenantIdEqualTo(tenantId).andMainProductCodeEqualTo(mainProCode)
				.andDimensionCodeEqualTo(dimCode);

		return MapperFactory.getPmDimensionBranchMapper().deleteByExample(criteria);
	}

	@Override
	public List<PmDimensionBranch> getDimensionBranch(String tenantId, String mainProCode, String DimCode) {
		PmDimensionBranchCriteria criteria = new PmDimensionBranchCriteria();

		criteria.createCriteria().andTenantIdEqualTo(tenantId).andMainProductCodeEqualTo(mainProCode)
				.andDimensionCodeEqualTo(DimCode);

		return MapperFactory.getPmDimensionBranchMapper().selectByExample(criteria);
	}

	@Override
	public int delDimensionBranch(String tenantId, String mainProCode) {
		PmDimensionBranchCriteria criteria = new PmDimensionBranchCriteria();

		criteria.or().andTenantIdEqualTo(tenantId).andMainProductCodeEqualTo(mainProCode);

		return MapperFactory.getPmDimensionBranchMapper().deleteByExample(criteria);
	}

	@Override
	public int updatePmDimensionBranch(String tenantId, Long id,PmDimensionBranch pdb) {
		PmDimensionBranchCriteria criteria = new PmDimensionBranchCriteria();

		criteria.or().andTenantIdEqualTo(tenantId).andIdEqualTo(id);
		
		return MapperFactory.getPmDimensionBranchMapper().updateByExampleSelective(pdb, criteria);
	}

	@Override
	public List<PmDimensionBranch> getDimensionBranch(String tenantId, String mainProCode) {
		PmDimensionBranchCriteria criteria = new PmDimensionBranchCriteria();

		criteria.createCriteria().andTenantIdEqualTo(tenantId).andMainProductCodeEqualTo(mainProCode);

		return MapperFactory.getPmDimensionBranchMapper().selectByExample(criteria);
	}

	@Override
	public int delDimensionBranchById(String tenantId, Long id) {
		PmDimensionBranchCriteria criteria = new PmDimensionBranchCriteria();

		criteria.or().andTenantIdEqualTo(tenantId).andIdEqualTo(id);

		return MapperFactory.getPmDimensionBranchMapper().deleteByExample(criteria);
	}

}
