package com.ai.baas.amc.api.amcdrbillsubject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.amcdrbillsubject.interfaces.IAmcDrBillSubjectQuerySV;
import com.ai.baas.amc.api.amcdrbillsubject.param.DrSubjectResponse;
import com.ai.baas.amc.api.amcdrbillsubject.param.QueryDrSubjectParamVO;
import com.ai.baas.amc.service.business.interfaces.IAmcDrBillSubjectBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.alibaba.dubbo.config.annotation.Service;
@Service(validation = "true")
@Component
public class AmcDrBillSubjectQuerySVImpl implements IAmcDrBillSubjectQuerySV {
	@Autowired
	private IAmcDrBillSubjectBusiSV iAmcDrBillSubjectBusiSV;
	@Override
	public List<DrSubjectResponse> queryDrSubjectIdList(QueryDrSubjectParamVO queryDrSubjectParamVo)
			throws BusinessException, SystemException {
		String tenantId = queryDrSubjectParamVo.getTenantId();
		String billSubjectId = queryDrSubjectParamVo.getBillSubjectId();
		//
		List<String> drSubjectIdList = this.iAmcDrBillSubjectBusiSV.queryDrSubjectIdByTenantIdAndBillSubjectId(tenantId, billSubjectId);
		
		//
		List<DrSubjectResponse> drSubjectResponseList = new ArrayList<DrSubjectResponse>();
		DrSubjectResponse drSubjectResponse = null;
		for(String drSubjectId : drSubjectIdList){
			drSubjectResponse = new DrSubjectResponse();
			drSubjectResponse.setDrSubjectId(drSubjectId);
			//
			drSubjectResponseList.add(drSubjectResponse);
		}
		//
		return drSubjectResponseList;
	}

}
