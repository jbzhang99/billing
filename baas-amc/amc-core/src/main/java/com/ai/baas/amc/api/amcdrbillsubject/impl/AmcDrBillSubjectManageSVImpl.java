package com.ai.baas.amc.api.amcdrbillsubject.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.amcdrbillsubject.interfaces.IAmcDrBillSubjectManageSV;
import com.ai.baas.amc.api.amcdrbillsubject.param.AmcDrBillSubjectRelatedParamVO;
import com.ai.baas.amc.service.business.interfaces.IAmcDrBillSubjectBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.alibaba.dubbo.config.annotation.Service;
@Service
@Component
public class AmcDrBillSubjectManageSVImpl implements IAmcDrBillSubjectManageSV {

	@Autowired
	private IAmcDrBillSubjectBusiSV iAmcDrBillSubjectBusiSV;
	
	@Override
	public BaseResponse delAmcDrBillSubject(AmcDrBillSubjectRelatedParamVO vo) throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		
		try{
			this.iAmcDrBillSubjectBusiSV.delAmcDrBillSubject(vo);
		}catch(Exception e){
			throw new BusinessException("操作失败", e);
		}
		ResponseHeader responseHeader = new ResponseHeader(true,"0000","success");
		response.setResponseHeader(responseHeader);
		//
		return response;
	}

	@Override
	public BaseResponse addAmcDrBillSubject(AmcDrBillSubjectRelatedParamVO vo) throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		
		try{
			this.iAmcDrBillSubjectBusiSV.addAmcDrBillSubject(vo);
		}catch(Exception e){
			throw new BusinessException("操作失败", e);
		}
		ResponseHeader responseHeader = new ResponseHeader(true,"0000","success");
		response.setResponseHeader(responseHeader);
		//
		return response;
	}

}
