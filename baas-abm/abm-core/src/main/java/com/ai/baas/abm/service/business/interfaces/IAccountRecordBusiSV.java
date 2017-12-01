package com.ai.baas.abm.service.business.interfaces;

import com.ai.baas.abm.api.account.params.AmcResBookVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;

public interface IAccountRecordBusiSV {

	BaseResponse saveAccountRecord(AmcResBookVo bookVo) throws BusinessException;
	
	BaseResponse clearExpireAccountRecord() throws BusinessException;
}
