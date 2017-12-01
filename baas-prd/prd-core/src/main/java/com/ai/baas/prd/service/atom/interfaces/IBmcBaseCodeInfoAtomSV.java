package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.api.product.params.mainProReq;
import com.ai.baas.prd.dao.mapper.bo.BmcBasedataCode;

public interface IBmcBaseCodeInfoAtomSV {

	List<BmcBasedataCode> getBasedata(mainProReq req);
	
	int getBaseDataCount(mainProReq req);
}
