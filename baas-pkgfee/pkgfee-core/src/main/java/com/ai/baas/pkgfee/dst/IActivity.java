package com.ai.baas.pkgfee.dst;

import java.util.List;

import com.ai.baas.pkgfee.api.feecal.params.FeeCalAddParam;
import com.ai.baas.pkgfee.service.entity.PkgFeeRecord;

public interface IActivity {
	
	void setFeeCalParamData(FeeCalAddParam feeCalAddParam);

	/**
	 * 计算优惠活动金额
	 */
	List<PkgFeeRecord> calculateAmount();
	
}
