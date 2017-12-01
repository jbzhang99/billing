package com.ai.baas.pkgfee.service.business.interfaces;

import com.ai.baas.pkgfee.api.feecal.params.ChangeConfigParam;
import com.ai.baas.pkgfee.api.feecal.params.FeeCalAddParam;

public interface IPackageFeeCalculateBusiSV {

	void addPackageFeeCal(FeeCalAddParam param);
	
	void changeConfigPkgFeeCal(ChangeConfigParam param);
	
}
