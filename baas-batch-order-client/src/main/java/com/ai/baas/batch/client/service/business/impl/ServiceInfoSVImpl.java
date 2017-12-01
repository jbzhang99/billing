package com.ai.baas.batch.client.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCode;
import com.ai.baas.batch.client.service.atom.interfaces.IPmBaseDataCodeAtom;
import com.ai.baas.batch.client.service.business.interfaces.IServiceInfoSV;
import com.ai.baas.batch.client.util.LoggerUtil;
import com.ai.opt.base.exception.BusinessException;

@Component("serviceInfo")
public class ServiceInfoSVImpl implements IServiceInfoSV{
	
	@Autowired
	@Qualifier("batchBaseCode")
	private IPmBaseDataCodeAtom pmBase;
	
	@Override
	public PmBasedataCode getBasedataCode(String tenantId, String serviceId) {
		// TODO Auto-generated method stub
		List<PmBasedataCode> pmBasedataCodes = pmBase.queryPmBaseData(tenantId, serviceId);
		if(pmBasedataCodes.size()!=1){
			System.out.println("serviceId match failed："+serviceId);
			LoggerUtil.log.error("serviceId match failed:"+serviceId);
			throw new BusinessException("serviceId match failed:"+serviceId);
		}
		return pmBasedataCodes.get(0); 
	}

}
