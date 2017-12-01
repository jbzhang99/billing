package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfo;

/**
 * 定价维度信息表原子服务
 *
 * Date: 2016年11月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public interface IPmDimensionInfoAtomSV {

	public int addDimension(PmDimensionInfo info);
	
	public List<PmDimensionInfo> getPmDimensionInfos(String tenantId,String mainProCode);
	
	public int delPmDimensionInfo(String tenantId,String mainProCode);
	public int updatePmDimensionInfoById(String tenantId,Long id,PmDimensionInfo record);
	
}
