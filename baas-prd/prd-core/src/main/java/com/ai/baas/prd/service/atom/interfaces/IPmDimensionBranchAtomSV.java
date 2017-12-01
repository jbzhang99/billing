package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranch;

/**
 * 维度分支信息表原子操作
 *
 * Date: 2016年11月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public interface IPmDimensionBranchAtomSV {

	public int addBranch(PmDimensionBranch branch);
	/**
	 * 删除指定租户，和主产品下的数据
	 */
	public int delDimensionBranch(String tenantId,String dimCode,String mainProCode);
	
	
	public int delDimensionBranch(String tenantId,String mainProCode);
	
	public List<PmDimensionBranch> getDimensionBranch(String tenantId,String mainProCode,String DimCode);
	public List<PmDimensionBranch> getDimensionBranch(String tenantId, String mainProCode);
	public int updatePmDimensionBranch(String tenantId, Long id,PmDimensionBranch pdb);
	public int delDimensionBranchById(String tenantId,Long id);
	}
