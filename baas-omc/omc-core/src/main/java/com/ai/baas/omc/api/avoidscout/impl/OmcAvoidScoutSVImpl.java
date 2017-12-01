package com.ai.baas.omc.api.avoidscout.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.omc.api.avoidscout.interfaces.IOmcAvoidScoutSV;
import com.ai.baas.omc.api.avoidscout.params.OmcAvoidScoutInfoVO;
import com.ai.baas.omc.service.business.interfaces.IOmcAvoidScoutBusi;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class OmcAvoidScoutSVImpl implements IOmcAvoidScoutSV {

//	private static final Logger log = LogManager
//			.getLogger(OmcAvoidScoutSVImpl.class);
	
	@Autowired
	private IOmcAvoidScoutBusi omcAvoidScoutBusi;

	@Override
	public BaseResponse addAvoidScout(OmcAvoidScoutInfoVO vo) throws BusinessException, SystemException {
		return omcAvoidScoutBusi.addAvoidScout(vo);
	}

	@Override
	public BaseResponse updateAvoidScout(OmcAvoidScoutInfoVO vo) throws BusinessException, SystemException {
		return omcAvoidScoutBusi.updAvoidScout(vo);
	}

	@Override
	public BaseResponse delAvoidScout(OmcAvoidScoutInfoVO vo) throws BusinessException, SystemException {
		return omcAvoidScoutBusi.delAvoidScout(vo);
	}

}
