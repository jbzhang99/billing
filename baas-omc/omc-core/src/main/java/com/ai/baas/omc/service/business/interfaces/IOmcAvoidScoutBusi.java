package com.ai.baas.omc.service.business.interfaces;

import com.ai.baas.omc.api.avoidscout.params.OmcAvoidScoutInfoVO;
import com.ai.opt.base.vo.BaseResponse;

public interface IOmcAvoidScoutBusi {

	public BaseResponse addAvoidScout(OmcAvoidScoutInfoVO vo);
	
	public BaseResponse updAvoidScout(OmcAvoidScoutInfoVO vo);
	
	public BaseResponse delAvoidScout(OmcAvoidScoutInfoVO vo);
}
