package com.ai.baas.cust.service.business.interfaces;

import java.util.List;

import com.ai.baas.cust.dao.mapper.bo.CpFullPresent;

public interface ICpFullPresentBusi {

	Integer addFullPresent(CpFullPresent present);
	CpFullPresent getFullPresent(String detailCode);
	//通过detailcode 获取满赠数据列表
	List<CpFullPresent> getFullPresents(String detailCode);
	CpFullPresent getFullPresent(Long presentId);
	Integer updateFullPresent(CpFullPresent present);
	Integer deleteFullPresent(String detailCode);
}
